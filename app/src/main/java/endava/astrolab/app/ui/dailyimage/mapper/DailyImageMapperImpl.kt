package endava.astrolab.app.ui.dailyimage.mapper

import endava.astrolab.app.model.DailyImage
import endava.astrolab.app.ui.dailyimage.DailyImageViewState
import endava.astrolab.app.ui.dailyimage.ImageViewState

class DailyImageMapperImpl : DailyImageMapper {
    override fun toDailyImageViewState(dailyImage: DailyImage): DailyImageViewState {
        val imageViewState = ImageViewState(
            dailyImage.title.orEmpty(),
            dailyImage.url.orEmpty(),
            dailyImage.explanation.orEmpty(),
            dailyImage.copyright,
            dailyImage.date
        )
        return DailyImageViewState(imageViewState)
    }
}
