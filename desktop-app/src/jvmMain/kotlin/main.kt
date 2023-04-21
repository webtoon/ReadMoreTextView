import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.webtoonscorp.android.readmore.shared.StringResources
import com.webtoonscorp.android.readmore.shared.desktop.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = StringResources.app_name,
    ) {
        App()
    }
}
