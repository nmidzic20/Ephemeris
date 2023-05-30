package endava.astrolab.app.data.network

import endava.astrolab.app.data.network.model.DailyImageResponse

interface DailyImageService {
    suspend fun fetchDailyImage(): DailyImageResponse
}