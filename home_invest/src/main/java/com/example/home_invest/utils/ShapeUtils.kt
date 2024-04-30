import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View

object ShapeUtils {

    fun setShapeColor(view: View?, hexColor: String?, borderWidth: Int = 0, borderColor: Int = Color.TRANSPARENT) {
        val color = Color.parseColor(hexColor)

        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(color)
            setStroke(borderWidth, borderColor)
            cornerRadius = view?.context?.let { 12.dpToPx(it) } ?: 12f
        }

        view?.background = drawable
    }

    internal fun Int.dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        )
    }
}

