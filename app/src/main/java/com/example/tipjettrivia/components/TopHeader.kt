import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TopHeader(TipsMoney: Double = 0.0){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(all = 20.dp)
        /*
                To work with specific corners:
                .clip(CircleShape.copy(all = CornerSize(12.dp)))
        */

        .clip(RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color.Magenta.copy(alpha = 0.1f)
    ) {
        Column(modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            val total = "%.2f".format(TipsMoney)
            Text(text = "Total tips:" , style = MaterialTheme.typography.h6)
            Text(text = "$${total}" , style = MaterialTheme.typography.h5, fontWeight = FontWeight.ExtraBold)
        }
    }
}
