package endava.astrolab.app.data.repository

import endava.astrolab.app.data.database.CompletedLessonDAO
import endava.astrolab.app.data.database.DbCompletedLesson
import endava.astrolab.app.data.database.LessonDAO
import endava.astrolab.app.model.Lesson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

class FakeLessonRepositoryImpl(
    private val lessonDao: LessonDAO,
    private val completedLessonDAO: CompletedLessonDAO,
    private val bgDispatcher: CoroutineDispatcher
) : LessonRepository {

    private val lessons: Flow<List<Lesson>> = lessonDao.lessons().flatMapLatest { dbLessonList ->
        completedLessonDAO.completed_lessons()
            .map { dbCompletedLessonList ->
                dbLessonList.map { dbLesson ->
                    Lesson(
                        id = dbLesson.id, title = dbLesson.title, content = dbLesson.content,
                        isCompleted = dbCompletedLessonList.any { it.id == dbLesson.id }
                    )
                }
            }
    }.flowOn(bgDispatcher)

    private val completedLessons = completedLessonDAO.completed_lessons().map {
        it.map { dbCompletedLesson ->
            Lesson(
                id = dbCompletedLesson.id,
                title = "",
                content = "",
                isCompleted = true,
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )
    override fun lessons(): Flow<List<Lesson>> = lessons
    override fun completedLessons(): Flow<List<Lesson>> = completedLessons

    override suspend fun lesson(lessonId: Int) = lessons.map { it ->
        it.first { lesson -> lessonId == lesson.id }
    }.first()

    override suspend fun addLessonToCompleted(lessonId: Int) {
        completedLessonDAO.insertCompletedLesson(DbCompletedLesson(lessonId))
    }

    override suspend fun removeLessonFromCompleted(lessonId: Int) {
        completedLessonDAO.deleteCompletedLesson(lessonId)
    }

    override suspend fun toggleCompleted(lessonId: Int) {
        val completedLessons = completedLessons.first()
        if (completedLessons.none { it.id == lessonId })
            addLessonToCompleted(lessonId)
        else
            removeLessonFromCompleted(lessonId)
    }
}
