package endava.astrolab.app.ui.dailyimage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.astrolab.app.data.repository.DailyImageRepository
import endava.astrolab.app.data.repository.LessonRepository
import endava.astrolab.app.model.DailyImage
import endava.astrolab.app.ui.dailyimage.mapper.DailyImageMapper
import endava.astrolab.app.ui.home.HomeViewState
import endava.astrolab.app.ui.home.mapper.HomeMapper
import kotlinx.coroutines.flow.*

class DailyImageViewModel(
    private val dailyImageRepository: DailyImageRepository,
    dailyImageMapper: DailyImageMapper) : ViewModel() {

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
