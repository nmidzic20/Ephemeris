package endava.astrolab.app.ui.lesson

data class LessonContentViewState(
    val content: String
)

data class LessonViewState(val lessonContentViewState: LessonContentViewState) {
    companion object {
        val EMPTY = LessonViewState(
            LessonContentViewState(content = "")
        )
    }
}
