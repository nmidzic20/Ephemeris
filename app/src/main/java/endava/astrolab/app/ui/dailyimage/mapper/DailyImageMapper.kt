package endava.astrolab.app.ui.dailyimage.mapper

import endava.astrolab.app.model.DailyImage
import endava.astrolab.app.ui.dailyimage.DailyImageViewState

interface DailyImageMapper {
    fun toDailyImageViewState(dailyImage: DailyImage): DailyImageViewState
}
