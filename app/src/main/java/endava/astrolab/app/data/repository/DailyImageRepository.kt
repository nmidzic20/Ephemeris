package endava.astrolab.app.data.repository

import endava.astrolab.app.model.DailyImage
import kotlinx.coroutines.flow.Flow

interface DailyImageRepository {
    fun dailyImage(): Flow<DailyImage>
}
