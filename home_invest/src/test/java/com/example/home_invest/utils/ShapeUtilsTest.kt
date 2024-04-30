import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Config.OLDEST_SDK])
class ShapeUtilsTest {

    @Test
    fun `test setShapeColor with null or empty hexColor`() {
        // Configuração
        val context: Context = RuntimeEnvironment.application.applicationContext
        val view = View(context)
        val hexColor = "#FF0000"
        val borderWidth = 2
        val borderColor = Color.BLACK

        // Chamada do método
        ShapeUtils.setShapeColor(view, hexColor, borderWidth, borderColor)

        // Verificações
        val drawable = view.background as? GradientDrawable
        assertEquals(
            Color.parseColor(hexColor),
            drawable?.color?.defaultColor
        ) // Verifica se a cor é transparente
        assertEquals(12f, drawable?.cornerRadius) // Verifica se o raio da borda é 12dp
    }

}
