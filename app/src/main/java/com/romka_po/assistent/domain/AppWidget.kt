package com.romka_po.assistent.domain

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.romka_po.assistent.R

class AppWidget :GlanceAppWidget(){
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            // create your AppWidget here
            MyContent()
        }
    }

    @Composable
    private fun MyContent() {
        Row(GlanceModifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = GlanceModifier.padding(horizontal = 16.dp)) {
                Text(text = "Запись", style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    color = ColorProvider(Color(0xCCECECEC)),

                    textAlign = TextAlign.Center,
                )
                )
                Text(text = "Пройдено 7.4 км", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = ColorProvider(Color(0xCCECECEC)),

                    textAlign = TextAlign.Center,
                ))
            }
//            Box(GlanceModifier.fillMaxSize()) {
//                Image(provider = ImageProvider(R.drawable.baseline_cancel_24), contentDescription = null)
//            }
            Spacer(GlanceModifier.defaultWeight())
            Box(modifier = GlanceModifier.height(50.dp).width(50.dp).background(ColorProvider(Color(
                0xE6FFFFFF
            )
            )).cornerRadius(12.dp)) {
                Image(provider = ImageProvider(R.drawable.baseline_close_24), contentScale = ContentScale.FillBounds, colorFilter = ColorFilter.tint(
                    ColorProvider(Color(0xFF000000))
                ), contentDescription = null)
            }
        }
    }
}