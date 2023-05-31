package endava.astrolab.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_lesson")
data class DbCompletedLesson(
    @PrimaryKey val id: Int,
)
