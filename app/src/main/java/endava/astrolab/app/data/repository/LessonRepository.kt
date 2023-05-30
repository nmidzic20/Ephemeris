package endava.astrolab.app.data.repository

import endava.astrolab.app.model.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    fun lessons(): Flow<List<Lesson>>
    suspend fun lesson(lessonId: Int): Lesson
    fun completedLessons(): Flow<List<Lesson>>
    suspend fun addLessonToCompleted(lessonId: Int)
    suspend fun removeLessonFromCompleted(lessonId: Int)
    suspend fun toggleCompleted(lessonId: Int)
}