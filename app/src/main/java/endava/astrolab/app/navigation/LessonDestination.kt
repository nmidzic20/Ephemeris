package endava.astrolab.app.navigation

const val LESSON_ROUTE = "Lesson"
const val LESSON_ID_KEY = "lessonId"
const val LESSON_ROUTE_WITH_PARAMS = "$LESSON_ROUTE/{$LESSON_ID_KEY}"

object LessonDestination : AstrolabDestination(LESSON_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(lessonId: Int): String = "$LESSON_ROUTE/$lessonId"
}