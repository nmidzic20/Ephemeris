package endava.astrolab.app.ui.home.mapper

import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.component.LessonItemViewState
import endava.astrolab.app.ui.home.HomeLessonViewState
import endava.astrolab.app.ui.home.HomeViewState

class HomeMapperImpl : HomeMapper {
    override fun toHomeViewState(lessons: List<Lesson>): HomeViewState {

        val homeLessonViewState: List<HomeLessonViewState> = lessons.map { lesson ->
            HomeLessonViewState(
                lesson.id,
                LessonItemViewState(
                    lesson.title,
                    lesson.isCompleted
                )
            )
        }
        return HomeViewState(homeLessonViewState)
    }
}
