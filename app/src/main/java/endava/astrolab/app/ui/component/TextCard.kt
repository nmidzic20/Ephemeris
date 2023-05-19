package endava.astrolab.app.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import endava.astrolab.app.ui.theme.spacing

@Composable
fun TextCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        elevation = MaterialTheme.spacing.small,
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
            )
            Text(
                text = content,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
            )
        }
    }
}

@Preview
@Composable
fun TextCardPreview() {
    val content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    TextCard("Title", content)
}
