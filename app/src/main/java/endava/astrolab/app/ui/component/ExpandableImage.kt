package endava.astrolab.app.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import endava.astrolab.app.ui.theme.spacing
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun ExpandableImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val defaultHeight = 300.dp

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val expandedHeight = screenHeight // 600.dp

    var height by remember {
        mutableStateOf(defaultHeight)
    }

    val largePadding = MaterialTheme.spacing.large
    val noPadding = 0.dp

    var padding by remember {
        mutableStateOf(largePadding)
    }

    val transitionDurationMilis = 1000

    val animateHeight by animateDpAsState(
        targetValue = height,
        animationSpec = tween(durationMillis = transitionDurationMilis)
    )

    val animatePadding by animateDpAsState(
        targetValue = padding,
        animationSpec = tween(durationMillis = transitionDurationMilis)
    )

    val zoomState = rememberZoomState()

    Card(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        elevation = MaterialTheme.spacing.small,
        modifier = modifier
            .padding(animatePadding)
            .height(animateHeight)
            .clickable {
                height = if (height == defaultHeight) expandedHeight else defaultHeight
                padding = if (padding == largePadding) noPadding else largePadding
            }

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = imageUrl,
                onSuccess = { state ->
                    zoomState.setContentSize(state.painter.intrinsicSize)
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .zoomable(zoomState),

            )
        }
    }
}

@Preview
@Composable
private fun ExpandableImagePreview() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ExpandableImage(
            "https://upload.wikimedia.org/wikipedia/commons/e/e7/Everest_North_Face_toward_Base_Camp_Tibet_Luca_Galuzzi_2006.jpg",
            modifier = Modifier
        )
        TextCard(title = "evrg", content = "wrggrg")
    }
}
