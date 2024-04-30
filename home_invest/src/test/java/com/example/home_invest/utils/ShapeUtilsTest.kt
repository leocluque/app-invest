import ShapeUtils.dpToPx
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ShapeUtilsTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun `test setShapeColor`() {
        // Dado
        val view = View(context)
        val hexColor = "#FF5733"
        val borderWidth = 2
        val borderColor = Color.BLACK

        // Quando
        ShapeUtils.setShapeColor(view, hexColor, borderWidth, borderColor)

        // Então
        val background = view.background as GradientDrawable
        assertEquals(borderWidth, 2)
        assertEquals(borderColor, Color.BLACK)
    }

    @Test
    fun `test setShapeColor - default values`() {
        // Dado
        val view = View(context)
        val hexColor = "#FF5733"

        ShapeUtils.setShapeColor(view, hexColor)

        val background = view.background as GradientDrawable

        assertEquals(0, 0)
        assertEquals(Color.parseColor(hexColor), background.color?.defaultColor ?: 0)
    }

    @Test
    fun `test dpToPx`() {
        val dpValue = 12
        val expectedPxValue = 12f // 12dp convertidos para pixels

        val pxValue = dpValue.dpToPx(context)

        assertEquals(expectedPxValue.toInt(), pxValue.toInt())
    }
}

