package endava.astrolab.app.ui.dailyimage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import endava.astrolab.app.ui.component.ExpandableImage
import endava.astrolab.app.ui.component.TextCard
import endava.astrolab.app.ui.component.YoutubeScreen

@Composable
fun DailyImageRoute(
    viewModel: DailyImageViewModel
) {
    val dailyImageViewState: DailyImageViewState by viewModel.dailyImageViewState.collectAsState()

    DailyImageScreen(
        dailyImageViewState
    )
}

@Composable
fun DailyImageScreen(
    dailyImageViewState: DailyImageViewState,
    modifier: Modifier = Modifier
) {
    val dailyImage = dailyImageViewState.imageViewState

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (dailyImageViewState.imageViewState.mediaType != "video")
            ExpandableImage(
                dailyImage.imageUrl,
                modifier = Modifier
            )
        else
            dailyImage.videoId?.let { YoutubeScreen(videoId = it, modifier = Modifier) }
        TextCard(title = dailyImage.title, content = dailyImage.explanation)
    }
}
