package endava.astrolab.app.ui.lesson.mapper

import endava.astrolab.app.model.Lesson
import endava.astrolab.app.ui.lesson.LessonContentViewState
import endava.astrolab.app.ui.lesson.LessonViewState

class LessonMapperImpl : LessonMapper {
    override fun toLessonViewState(lesson: Lesson): LessonViewState {
        val lessonContentViewState = LessonContentViewState(lesson.content)
        return LessonViewState(lessonContentViewState)
    }
}
