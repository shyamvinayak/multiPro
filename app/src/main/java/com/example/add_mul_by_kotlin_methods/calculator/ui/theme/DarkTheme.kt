import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define dark theme colors
private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF121212),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    onSecondary = Color(0xFF000000),
    background = Color(0xFF121212),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF121212),
    onSurface = Color(0xFFFFFFFF)
)

@Composable
fun DarkTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography(),
        content = content
    )
}
