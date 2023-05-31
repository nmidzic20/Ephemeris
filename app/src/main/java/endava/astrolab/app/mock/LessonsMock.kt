package endava.astrolab.app.mock

import endava.astrolab.app.model.Lesson

object LessonsMock {

    fun getLessonsList(): List<Lesson> = listOf(
        Lesson(0, "Lesson 1", "", false),
        Lesson(1, "Lesson 2", "", true),
        Lesson(2, "Lesson 3", "", true),
        Lesson(3, "Lesson 4", "", false),
        Lesson(4, "Lesson 5", "", false),
        Lesson(5, "Lesson 6", "", false),
        Lesson(6, "Lesson 7", "", false),
        )
}
