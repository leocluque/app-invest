import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View

object ShapeUtils {

    fun setShapeColor(view: View, hexColor: String?, borderWidth: Int = 0, borderColor: Int = Color.TRANSPARENT) {
        val color = Color.parseColor(hexColor)

        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setColor(color)
        drawable.setStroke(borderWidth, borderColor)
        drawable.cornerRadius = 12.dpToPx(view.context).toFloat()

        view.background = drawable
    }

    private fun Int.dpToPx(context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}
