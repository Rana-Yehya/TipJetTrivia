package com.example.tipjettrivia.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(modifier: Modifier = Modifier,
                imageVector: ImageVector,
                onClick: () ->Unit,
                tint: Color = Color.Black.copy(alpha = 0.5f),
                backgroundColor: Color = MaterialTheme.colors.background,
                elevation: Dp = 5.dp){

    Card(modifier = Modifier
        .padding(all = 4.dp)
        .clickable { onClick.invoke() }
        /*.then(IconbuttonSizeModifier)*/,
        shape = CircleShape,
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Icon(imageVector = imageVector, contentDescription = "Add or Remove" , tint = tint)
    }

}