package com.mcdonalds.kmmagentcore.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mcdonalds.kmmagentcore.data.dto.ChipProps

@Composable
fun ChipItemComponent(
    isSelected: Boolean = false,
    chipProps: ChipProps,
    modifier: Modifier= Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Gray else Color.LightGray,
                shape = RoundedCornerShape(50)
            )
            .background(  Color.White
                // if (isSelected) Color.White else Color(0xFFF5F5F5)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Image(
            painter = rememberAsyncImagePainter(model = chipProps.icon),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = chipProps.label, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black
        )
    }
}