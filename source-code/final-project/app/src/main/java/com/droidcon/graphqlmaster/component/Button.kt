package com.droidcon.graphqlmaster.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.graphqlmaster.ui.theme.Purple40
import com.droidcon.graphqlmaster.ui.theme.PurpleGrey40

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    ctaText: String,
    onClick: () -> Unit
) {
    Column(
        modifier
            .height(44.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(24.dp))
            .background(Purple40)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = ctaText,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier
            .height(56.dp)
            .width(56.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .background(Purple40)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.Add, "menu", tint = Color.White)
    }
}

@Composable
fun EditIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier
            .height(56.dp)
            .width(56.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .background(PurpleGrey40)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.Edit, "menu", tint = Color.White)
    }
}

@Composable
fun DeleteIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier
            .height(56.dp)
            .width(56.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .background(PurpleGrey40)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.Delete, "menu", tint = Color.White)
    }
}