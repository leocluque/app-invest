import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.isA
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.RuntimeEnvironment
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.lang.reflect.Field

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Config.OLDEST_SDK])
class ShapeUtilsTest {

    private val mockContext = mock(Context::class.java)

    @Test
    fun `test setShapeColor`() {
        // Mock View e outros parâmetros necessários
        val view = mock(View::class.java)
        val hexColor = "#FF0000"
        val borderWidth = 2
        val borderColor = Color.BLACK

        // Chame o método setShapeColor
        ShapeUtils.setShapeColor(view, hexColor, borderWidth, borderColor)

        // Verifique se o background da view foi definido como um GradientDrawable
        verify(view).background = isA(GradientDrawable::class.java)

        // Verifique se o GradientDrawable tem as configurações corretas
        val drawable = view.background as GradientDrawable
        assertNotNull(drawable.toString(), "O GradientDrawable deve ser configurado")

        // Verifique a cor (garante que drawable não é null antes de acessar color)
        assertEquals(Color.parseColor(hexColor), drawable.color!!.defaultColor)

        // Use reflexão para acessar strokeWidth (não recomendado)
        val field = GradientDrawable::class.java.getDeclaredField("strokeWidth")
        field.isAccessible = true // Make the field accessible
        val strokeWidthValue = field.getInt(drawable) // Get the value

        // Assert the border width
        assertEquals(borderWidth, strokeWidthValue)

        // ... (rest of your assertions)
    }
}
