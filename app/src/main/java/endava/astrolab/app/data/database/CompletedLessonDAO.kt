package endava.astrolab.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedLessonDAO {
    @Query("SELECT * FROM completed_lesson")
    fun completed_lessons(): Flow<List<DbCompletedLesson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedLesson(lesson: DbCompletedLesson)

    @Query("DELETE FROM completed_lesson WHERE id = :lessonId")
    suspend fun deleteCompletedLesson(lessonId: Int)
}
