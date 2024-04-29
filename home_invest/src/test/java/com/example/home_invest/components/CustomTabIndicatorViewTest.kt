import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.home_invest.ui.components.CustomTabIndicatorView
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class CustomTabIndicatorViewTest {

    @Test
    fun constructor_initializesPaintObject() {
        val view = CustomTabIndicatorView(context = ApplicationProvider.getApplicationContext())
        assertNotNull(view.paint)
    }

    @Test
    fun constructor_setsPaintColorToBlack() {
        val view = CustomTabIndicatorView(context = ApplicationProvider.getApplicationContext())
        assertEquals(Color.BLACK, view.paint.color)
    }

    @Test
    fun constructor_setsPaintStrokeWidthTo2f() {
        val view = CustomTabIndicatorView(context = ApplicationProvider.getApplicationContext())
        assertEquals(2f, view.paint.strokeWidth)
    }
}
