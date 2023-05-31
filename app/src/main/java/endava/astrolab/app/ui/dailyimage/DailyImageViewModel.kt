package endava.astrolab.app.ui.dailyimage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.astrolab.app.data.repository.DailyImageRepository
import endava.astrolab.app.ui.dailyimage.mapper.DailyImageMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DailyImageViewModel(
    private val dailyImageRepository: DailyImageRepository,
    dailyImageMapper: DailyImageMapper
) : ViewModel() {

    val dailyImageViewState: StateFlow<DailyImageViewState> = dailyImageRepository
        .dailyImage()
        .map { dailyImage ->
            dailyImageMapper.toDailyImageViewState(dailyImage)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = DailyImageViewState(ImageViewState.EMPTY),
        )
}
