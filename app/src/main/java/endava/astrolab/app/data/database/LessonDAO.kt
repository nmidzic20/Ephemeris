package endava.astrolab.app.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDAO {
    @Query("SELECT * FROM lesson")
    fun lessons(): Flow<List<DbLesson>>
}
