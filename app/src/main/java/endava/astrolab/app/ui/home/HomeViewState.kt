package endava.astrolab.app.ui.home

import endava.astrolab.app.ui.component.LessonItemViewState

data class HomeLessonViewState(
    val id: Int,
    val lessonItemViewState: LessonItemViewState
)

data class HomeViewState(val homeLessonViewStateList: List<HomeLessonViewState>)
