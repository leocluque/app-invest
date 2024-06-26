import android.content.Context
import android.util.AttributeSet
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.home_invest.ui.components.CircleProgressBar
import com.example.home_invest.ui.components.ProgressItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CircleProgressBarTest {

    private lateinit var circleProgressBar: CircleProgressBar

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        circleProgressBar = CircleProgressBar(context)
    }

    @Test
    fun testConfigureComponent() {
        val title = "Title"
        val subtitle = "Subtitle"
        val value = "Value"
        val items = listOf(
            ProgressItem("Item 1", 25f, "#FF0000"),
            ProgressItem("Item 2", 50f, "#00FF00"),
            ProgressItem("Item 3", 25f, "#0000FF")
        )

        circleProgressBar.configureComponent(title, subtitle, value, items)

        assertEquals(title, circleProgressBar.getTitle())
        assertEquals(subtitle, circleProgressBar.getSubtitle())
        assertEquals(value, circleProgressBar.getBalance())
        assertEquals(items, circleProgressBar.getItems())
    }

    @Test
    fun testGetTitle() {
        val title = "Title"
        circleProgressBar.configureComponent(title = title)
        assertEquals(title, circleProgressBar.getTitle())
    }

    @Test
    fun testGetSubtitle() {
        val subtitle = "Subtitle"
        circleProgressBar.configureComponent(subtitle = subtitle)
        assertEquals(subtitle, circleProgressBar.getSubtitle())
    }

    @Test
    fun testGetBalance() {
        val value = "Value"
        circleProgressBar.configureComponent(value = value)
        assertEquals(value, circleProgressBar.getBalance())
    }

    @Test
    fun testGetItems() {
        val items = listOf(
            ProgressItem("Item 1", 25f, "#FF0000"),
            ProgressItem("Item 2", 50f, "#00FF00"),
            ProgressItem("Item 3", 25f, "#0000FF")
        )
        circleProgressBar.configureComponent(list = items)
        assertEquals(items, circleProgressBar.getItems())
    }
}
