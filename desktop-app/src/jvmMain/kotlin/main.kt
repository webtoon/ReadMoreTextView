import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.webtoonscorp.android.readmore.sample.R
import com.webtoonscorp.android.readmore.sample.compose.foundation.BasicReadMoreTextDemo

fun main() = application {
    var isOpen by remember { mutableStateOf(false) }
    var isOpen2 by remember { mutableStateOf(false) }
    var isOpen3 by remember { mutableStateOf(false) }
    Window(
        onCloseRequest = ::exitApplication,
        title = R.string.app_name,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = R.string.app_name)
                })
            }
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .clickable { isOpen = true }
                        .fillMaxWidth()
                        .padding(start = 18.dp, top = 16.dp, end = 18.dp, bottom = 18.dp)
                ) {
                    Text(
                        text = R.string.compose_foundation_title,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = R.string.compose_foundation_description,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 15.sp,
                    )
                }
                Divider()
                Column(
                    modifier = Modifier
                        .clickable { isOpen2 = true }
                        .fillMaxWidth()
                        .padding(start = 18.dp, top = 16.dp, end = 18.dp, bottom = 18.dp)
                ) {
                    Text(
                        text = R.string.compose_material_title,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = R.string.compose_material_description,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 15.sp,
                    )
                }
                Divider()
                Column(
                    modifier = Modifier
                        .clickable { isOpen3 = true }
                        .fillMaxWidth()
                        .padding(start = 18.dp, top = 16.dp, end = 18.dp, bottom = 18.dp)
                ) {
                    Text(
                        text = R.string.compose_material3_title,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = R.string.compose_material3_description,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 15.sp,
                    )
                }
                Divider()
            }
        }
    }
    if (isOpen) {
        Window(
            onCloseRequest = { isOpen = false },
            title = R.string.compose_foundation_title,
        ) {
            BasicReadMoreTextDemo()
        }
    }
    if (isOpen2) {
        Window(
            onCloseRequest = { isOpen2 = false },
            title = R.string.compose_material_title,
        ) {
            com.webtoonscorp.android.readmore.sample.compose.material.ReadMoreTextDemo()
        }
    }
    if (isOpen3) {
        Window(
            onCloseRequest = { isOpen3 = false },
            title = R.string.compose_material3_title,
        ) {
            com.webtoonscorp.android.readmore.sample.compose.material3.ReadMoreTextDemo()
        }
    }
}
