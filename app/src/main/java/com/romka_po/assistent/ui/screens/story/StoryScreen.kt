@file:OptIn(ExperimentalFoundationApi::class)

package com.romka_po.assistent.ui.screens.story

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.romka_po.assistent.R
import com.romka_po.assistent.model.nav.Screens
import kotlinx.coroutines.launch

@Composable
fun StoryScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        Column(verticalArrangement = Arrangement.Bottom) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(red = 27, green = 2, blue = 32))
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Explore your route",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "Let`s start here!",
                    style = MaterialTheme.typography.titleLarge
                )
                if (pagerState.pageCount - page - 1 != 0) {
                    FilledIconButton(modifier = Modifier
                        .padding(top = 50.dp)
                        .size(72.dp), onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowForward,
                            contentDescription = null
                        )
                    }
                } else {
                    Button(modifier = Modifier
                        .padding(top = 50.dp)
                        .height(72.dp), onClick = {
                        navController.navigate(Screens.Auth.route)
                    }) {
                        Text(
                            text = "Start",
                            letterSpacing = TextUnit(1f, TextUnitType.Sp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

            }


            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.backready),
                    contentDescription = null,
                )
                Row(
                    Modifier
                        .padding(top = 10.dp)
                        .height(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(10.dp)
                        )
                    }
                }
            }


        }
    }
}