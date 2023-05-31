package endava.astrolab.app.ui.dailyimage

data class ImageViewState(
    val title: String,
    val imageUrl: String,
    val explanation: String,
    val copyright: String?,
    val date: String?,
    val mediaType: String?,
    val videoId: String?
) {
    companion object {
        val EMPTY = ImageViewState(title = "", imageUrl = "", explanation = "", copyright = "", date = "", mediaType = "", videoId = "")
    }
}

data class DailyImageViewState(val imageViewState: ImageViewState)
