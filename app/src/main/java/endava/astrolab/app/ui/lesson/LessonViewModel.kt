package endava.astrolab.app.ui.lesson

import androidx.lifecycle.ViewModel
import endava.astrolab.app.data.repository.LessonRepository
import endava.astrolab.app.ui.lesson.mapper.LessonMapper

class LessonViewModel(
    private val lessonId: Int,
    private val lessonRepository: LessonRepository,
    lessonMapper: LessonMapper,
) : ViewModel() {

    private val initialLessonViewState = LessonViewState.EMPTY

    /*val lessonViewState: StateFlow<LessonViewState> =
        lessonRepository
            .lesson(lessonId)
            .map { lesson ->
                lessonMapper.toLessonViewState(lesson)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = initialLessonViewState,
            )*/
}
