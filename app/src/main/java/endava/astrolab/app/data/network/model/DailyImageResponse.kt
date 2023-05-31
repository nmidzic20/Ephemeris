package endava.astrolab.app.data.network.model

import endava.astrolab.app.model.DailyImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyImageResponse(
    @SerialName("copyright")
    var copyright: String?,
    @SerialName("date")
    var date: String?,
    @SerialName("explanation")
    var explanation: String?,
    @SerialName("hdurl")
    var hdurl: String?,
    @SerialName("media_type")
    var mediaType: String?,
    @SerialName("service_version")
    var serviceVersion: String?,
    @SerialName("title")
    var title: String?,
    @SerialName("url")
    var url: String?
) {
    fun toDailyImage() = DailyImage(
        title = title,
        copyright = copyright,
        date = date,
        url = url,
        hdurl = hdurl,
        explanation = explanation
    )
}
