package endava.astrolab.app.ui.lesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import endava.astrolab.app.mock.LessonsMock
import endava.astrolab.app.ui.component.WebView
import endava.astrolab.app.ui.lesson.mapper.LessonMapper
import endava.astrolab.app.ui.lesson.mapper.LessonMapperImpl
import java.nio.charset.Charset

val lessonMapper: LessonMapper = LessonMapperImpl()
val lessonViewState = lessonMapper.toLessonViewState(LessonsMock.getLessonsList()[0])
@Composable
fun LessonRoute(
    viewModel: LessonViewModel
) {
    //val lessonViewState: LessonViewState by viewModel.lessonViewState.collectAsState()

    LessonScreen(
        lessonViewState
    )
}

@Composable
fun LessonScreen(
    lessonViewState: LessonViewState,
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

@Preview
@Composable
fun LessonScreenPreview() {


    LessonScreen(lessonViewState = lessonViewState)
}
