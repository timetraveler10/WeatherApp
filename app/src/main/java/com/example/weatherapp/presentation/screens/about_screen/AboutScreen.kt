package com.example.weatherapp.presentation.screens.about_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun AboutScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            AboutScreenListItem(
                title = "Made By",
                description = "Hussein Abdulkareem Abdo"
            )
        }
        item {
            AboutScreenListItem(
                title = "Used Technologies",
                description = "Kotlin \n Ktor - For Networking \n Hilt For Dependency Injection \n Jetpack Compose for UI \n Jetpack DataStore For Preferences For Storing Lat-Lng Values"
            )
        }

        item {
            AboutScreenListItem(
                title = "Github Repository",
                description = "Hussein Abdulkareem Abdo"
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreenListItem(title: String, description: String) {

    Surface(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(6.dp))
        {
            Text(
                text = title, fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description)
        }
    }

}