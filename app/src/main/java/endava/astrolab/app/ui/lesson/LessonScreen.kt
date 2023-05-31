package endava.astrolab.app.ui.lesson

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import endava.astrolab.app.ui.component.WebView
import java.nio.charset.Charset

@Composable
fun LessonRoute(
    viewModel: LessonViewModel
) {
    // val lessonViewState: LessonViewState by viewModel.lessonViewState.collectAsState()

    LessonScreen(
        // lessonViewState
    )
}

@Composable
fun LessonScreen(
    // lessonViewState: LessonViewState,
    /*onLessonClick: (Int) -> Unit,
    * onLessonLongClick: (Int) -> Unit,*/
    modifier: Modifier = Modifier
) {
    // val lessons = lessonViewState.content

    val sampleHtmlString = "<!DOCTYPE html><html><body><h1>My First Heading</h1><p>My first paragraph.</p></body></html>"
    var astronomyBookDataHtmlString: String = ""

    val input_stream = LocalContext.current.assets.open("lesson_1_1.html")
    astronomyBookDataHtmlString = input_stream.readBytes().toString(Charset.defaultCharset())

    WebView(astronomyBookDataHtmlString)
}
