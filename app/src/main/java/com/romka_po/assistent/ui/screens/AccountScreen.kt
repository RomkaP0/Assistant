package com.romka_po.assistent.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DirectionsCarFilled
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.romka_po.assistent.R
import com.romka_po.assistent.ui.components.account.ColumnLine


@Composable
fun AccountScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .fillMaxSize()
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Profile", style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.8.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF212121),

                    )
            )
            FloatingActionButton(
                modifier = Modifier
                    .zIndex(10f)
//                    .padding(6.dp)
                    .size(40.dp),
                onClick = { navController.navigateUp() },
                containerColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null
                )
            }
        }

        Image(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 12.dp)
                .fillMaxWidth(0.4f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(100.dp))
                .background(Color.Blue),
            painter = painterResource(id = R.drawable.aaa),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Roman Polyanskiy",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF212121),
                )
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            text = "roman.kradyk@gmail.com",

            )
        HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))
        Column {
            ColumnLine(text = "Edit Profile", icon = Icons.Outlined.PersonOutline, false) {

            }
            ColumnLine(text = "VAZ Granta", icon = Icons.Outlined.DirectionsCarFilled, false) {

            }

            ColumnLine(text = "Notification", icon = Icons.Outlined.Notifications, false) {

            }
            ColumnLine(text = "Security", icon = Icons.Outlined.Shield, false) {

            }

            ColumnLine(text = "Logout", icon = Icons.AutoMirrored.Outlined.Logout, true) {

            }
        }
    }
}