package endava.astrolab.app.data.repository

import endava.astrolab.app.mock.CompletedLessonsDbMock
import endava.astrolab.app.mock.LessonsMock
import endava.astrolab.app.model.Lesson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

class FakeLessonRepositoryImpl(
    // private val lessonDao: CompletedLessonDAO,
    private val bgDispatcher: CoroutineDispatcher
) : LessonRepository {

    private val fakeLessons = LessonsMock.getLessonsList().toMutableList()

    private val lessons: Flow<List<Lesson>> = CompletedLessonsDbMock.completedIds
        .mapLatest { completedIds ->
            fakeLessons.map {
                val isCompleted = it.id in completedIds
                Lesson(it.id, it.title, it.content, isCompleted)
            }
        }
        .flowOn(bgDispatcher)

    override suspend fun lesson(lessonId: Int) = lessons.map { it ->
        it.first { lesson -> lessonId == lesson.id }
    }.first()

    override fun lessons(): Flow<List<Lesson>> = lessons
    override fun completedLessons(): Flow<List<Lesson>> = lessons.map {
        it.filter { fakeLesson: Lesson -> fakeLesson.isCompleted }
    }

    override suspend fun addLessonToCompleted(lessonId: Int) {
        CompletedLessonsDbMock.insert(lessonId)
    }

    override suspend fun removeLessonFromCompleted(lessonId: Int) {
        CompletedLessonsDbMock.delete(lessonId)
    }

    override suspend fun toggleCompleted(lessonId: Int) {
        withContext(bgDispatcher) {
            if (CompletedLessonsDbMock.completedIds.value.contains(lessonId)) {
                removeLessonFromCompleted(lessonId)
            } else {
                addLessonToCompleted(lessonId)
            }
        }
    }
}
