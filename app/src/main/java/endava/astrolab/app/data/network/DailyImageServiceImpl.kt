package endava.astrolab.app.data.network

import endava.astrolab.app.data.network.model.DailyImageResponse
import io.ktor.client.*

const val BASE_IMAGE_URL = ""
private const val BASE_URL = ""
private const val API_KEY = ""

class DailyImageServiceImpl(private val client: HttpClient) : DailyImageService {
    override suspend fun fetchDailyImage(): DailyImageResponse {
        TODO("Not yet implemented")
    }
}