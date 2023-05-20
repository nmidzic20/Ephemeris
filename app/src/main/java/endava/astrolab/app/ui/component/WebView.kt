package endava.astrolab.app.ui.component

import android.util.Base64
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import java.nio.charset.Charset

class MyWebViewClient : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        view?.loadUrl(
            "javascript:document.body.style.margin='8%'; " +
                "document.getElementsByTagName('h1')[0].style.padding='0% 0% 8% 5%'; " +
                "document.getElementsByTagName('h1')[0].style.color='red' ; void 0"
        )
    }
}

@Composable
fun WebView(filePath: String, modifier: Modifier = Modifier) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = MyWebViewClient()
            loadData(Base64.encodeToString(filePath.toByteArray(), Base64.DEFAULT), "text/html", "base64")
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
        }
    }, update = {
        it.loadData(Base64.encodeToString(filePath.toByteArray(), Base64.DEFAULT), "text/html", "base64")
    }, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun WebViewPreview() {
    val sampleHtmlString = "<!DOCTYPE html><html><body><h1>My First Heading</h1><p>My first paragraph.</p></body></html>"
    var astronomyBookDataHtmlString: String = ""

    val input_stream = LocalContext.current.assets.open("lesson_1_1.html")
    astronomyBookDataHtmlString = input_stream.readBytes().toString(Charset.defaultCharset())

    WebView(astronomyBookDataHtmlString)
}
