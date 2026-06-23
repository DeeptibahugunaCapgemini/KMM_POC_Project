package com.mcdonalds.kmmagentcore.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcdonalds.kmmagentcore.data.dto.*
import com.mcdonalds.kmmagentcore.data.dto.ContentSection
import com.mcdonalds.kmmagentcore.data.dto.Header
import com.mcdonalds.kmmagentcore.data.dto.Section
import com.mcdonalds.kmmagentcore.data.dto.TextComponent
import com.mcdonalds.kmmagentcore.data.dto.UIComponent
import com.mcdonalds.kmmagentcore.presentation.AgentOrchestrator
import com.mcdonalds.kmmagentcore.presentation.ScreenState
@Composable
    fun OrderInputBar(componentPlaceholder: String, componentActions: List<ButtonAction>) {

        var text by remember { mutableStateOf("") }
        var isFocused by remember { mutableStateOf(false) }

        val focusColor = if (isFocused) Color.LightGray else Color.Unspecified


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) ,// outer background if needed
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .border(
                        width = 2.dp,
                        color = focusColor,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(56.dp)
                        .padding(2.dp)
                        .background(Color(0xFFF3F1F1))
                        .clip(RoundedCornerShape(30.dp))
                        .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(30.dp)
                    ),

                    contentAlignment = Alignment.CenterStart
                ) {

                    BasicTextField(
                        value = text,
                        onValueChange = { text = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            ,
                        decorationBox = { innerTextField ->
                            if (text.isEmpty()) {
                                Text(
                                    componentPlaceholder,
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))


            // Send Button
            componentActions.forEach {
                Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .clickable { /* send action */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color.White
                )
            }
        }}
    }