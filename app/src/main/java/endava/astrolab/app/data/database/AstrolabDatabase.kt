package endava.astrolab.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbCompletedLesson::class, DbLesson::class], version = 1)
abstract class AstrolabDatabase : RoomDatabase() {
    abstract fun completedLessonDao(): CompletedLessonDAO
    abstract fun lessonDao(): LessonDAO
}
