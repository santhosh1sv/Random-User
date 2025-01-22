import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun DrawArc(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .width(240.dp)
            .height(120.dp).clipToBounds()
    ) {
        val width = size.width
        val height = size.height
        val paint = Paint()
        paint.color = Color.Red
        paint.style = PaintingStyle.Fill

        drawArc(
            color = Color.Red,
            topLeft = Offset(width / 2.5f, height / 2),
            startAngle = 60f,
            sweepAngle = 180f,
            useCenter = true,
            style = Stroke(width = 10f, cap = StrokeCap.Round),
        )
    }
}