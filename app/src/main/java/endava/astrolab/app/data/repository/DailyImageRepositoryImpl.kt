package endava.astrolab.app.data.repository

import endava.astrolab.app.data.network.DailyImageService
import endava.astrolab.app.model.DailyImage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.shareIn

class DailyImageRepositoryImpl(
    private val dailyImageService: DailyImageService,
    private val bgDispatcher: CoroutineDispatcher
) : DailyImageRepository {
    private val dailyImage: Flow<DailyImage> = flow {

        emit(dailyImageService.fetchDailyImage())
    }.mapLatest { dailyImageResponse ->
        dailyImageResponse.toDailyImage()
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1,
    )

    override fun dailyImage(): Flow<DailyImage> = dailyImage
}
