package endava.astrolab.app.ui.dailyimage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import endava.astrolab.app.mock.DailyImageMock
import endava.astrolab.app.ui.component.ExpandableImage
import endava.astrolab.app.ui.component.TextCard
import endava.astrolab.app.ui.dailyimage.mapper.DailyImageMapper
import endava.astrolab.app.ui.dailyimage.mapper.DailyImageMapperImpl

private val dailyImageMapper: DailyImageMapper = DailyImageMapperImpl()
val dailyImageViewState = dailyImageMapper.toDailyImageViewState(DailyImageMock.getDailyImage())

@Composable
fun DailyImageRoute(
    viewModel: DailyImageViewModel
) {
    // val dailyImageViewState: DailyImageViewState by viewModel.dailyImageViewState.collectAsState()

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
        ExpandableImage(
            dailyImage.imageUrl,
            modifier = Modifier
        )
        TextCard(title = dailyImage.title, content = dailyImage.explanation)
    }
}

@Preview
@Composable
fun DailyImageScreenPreview() {
    DailyImageScreen(dailyImageViewState)
}
