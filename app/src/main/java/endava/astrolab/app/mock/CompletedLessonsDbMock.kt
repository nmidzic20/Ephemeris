package endava.astrolab.app.mock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

object CompletedLessonsDbMock {
    private val _completedIds = MutableStateFlow(
        LessonsMock.getLessonsList()
            .filter { lesson -> lesson.isCompleted }
            .map { lesson -> lesson.id }
            .toSet()
    )

    val completedIds: StateFlow<Set<Int>> = _completedIds

    fun insert(lessonId: Int) {
        _completedIds.update { it + lessonId }
    }

    fun delete(lessonId: Int) {
        _completedIds.update { it - lessonId }
    }
}
