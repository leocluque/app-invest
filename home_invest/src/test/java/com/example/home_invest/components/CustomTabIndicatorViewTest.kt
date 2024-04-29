import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.test.core.app.ApplicationProvider
import com.example.home_invest.ui.components.CustomTabIndicatorView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CustomTabIndicatorViewTest {

    private lateinit var context: Context
    private lateinit var view: CustomTabIndicatorView

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        view = CustomTabIndicatorView(context)
        // Define o tamanho da view manualmente
        view.measure(100, 100)
        view.layout(0, 0, 100, 100)
    }

    @Test
    fun testLineColor() {
        val expectedColor = Color.BLACK
        assertEquals(expectedColor, view.paint.color)
    }

    @Test
    fun testLineWidth() {
        val expectedWidth = 2f
        assertEquals(expectedWidth, view.paint.strokeWidth)
    }

    @Test
    fun testDrawLine() {
        val canvas = Canvas()
        val expectedRect = RectF(0f, 0f, 100f, 2f) // Linha horizontal de 2 pixels de altura
        view.onDraw(canvas)
        val drawnRect = RectF(0f, 0f, view.width.toFloat(), view.paint.strokeWidth)

        assertEquals(expectedRect, drawnRect)
    }
}
