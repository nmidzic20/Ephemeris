package endava.astrolab.app.model

data class Lesson(
    val id: Int,
    val title: String,
    val content: String,
    val isCompleted: Boolean,
) {
    companion object {
        val EMPTY = Lesson(id = 0, title = "", content = "", isCompleted = false)
    }
}
