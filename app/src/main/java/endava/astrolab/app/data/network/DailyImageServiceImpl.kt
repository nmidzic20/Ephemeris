package endava.astrolab.app.data.network

import endava.astrolab.app.data.network.model.DailyImageResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val BASE_URL = "https://api.nasa.gov/planetary"
private const val API_KEY = "yAzp7ZztVhXKiRqBTPok6gV1fIfpbHu3hBFamjgD"

class DailyImageServiceImpl(private val client: HttpClient) : DailyImageService {
    override suspend fun fetchDailyImage(): DailyImageResponse = client.get("$BASE_URL/apod?api_key=$API_KEY").body()
}
