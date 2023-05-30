package endava.astrolab.app.ui.dailyimage

data class ImageViewState(
    val title: String,
    val imageUrl: String,
    val explanation: String,
    val copyright: String?,
    val date: String?
) {
    companion object {
        val EMPTY = ImageViewState(title = "", imageUrl = "", explanation = "", copyright = "", date = "")
    }
}

data class DailyImageViewState(val imageViewState: ImageViewState)
