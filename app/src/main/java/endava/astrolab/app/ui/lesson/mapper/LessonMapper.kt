package endava.astrolab.app.ui.lesson.mapper

import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.lesson.LessonViewState

interface LessonMapper {
    fun toLessonViewState(lesson: Lesson): LessonViewState
}
