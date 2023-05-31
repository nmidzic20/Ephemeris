package endava.astrolab.app.ui.dailyimage.mapper

import endava.astrolab.app.model.DailyImage
import endava.astrolab.app.ui.dailyimage.DailyImageViewState
import endava.astrolab.app.ui.dailyimage.ImageViewState
import java.util.regex.Matcher
import java.util.regex.Pattern

class DailyImageMapperImpl : DailyImageMapper {
    override fun toDailyImageViewState(dailyImage: DailyImage): DailyImageViewState {

        val videoId = extractVideoId(dailyImage.url!!)

        val imageViewState = ImageViewState(
            dailyImage.title.orEmpty(),
            dailyImage.url.orEmpty(),
            dailyImage.explanation.orEmpty(),
            dailyImage.copyright,
            dailyImage.date,
            dailyImage.mediaType,
            videoId
        )
        return DailyImageViewState(imageViewState)
    }

    private fun extractVideoId(url : String) : String
    {
        val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(url)
        val videoId = if (matcher.find()) {
            matcher.group()
        } else {
            ""
        }
        return videoId
    }
}
