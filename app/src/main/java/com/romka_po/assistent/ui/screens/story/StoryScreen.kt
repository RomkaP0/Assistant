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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
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
        Box(modifier = Modifier) {
            Column(verticalArrangement = Arrangement.Bottom) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
//                            if (!isSystemInDarkTheme()) {
//                                Brush.horizontalGradient(
//                                    0.7f to Color(red = 205, green = 107, blue = 106),
//                                    0.85f to Color(red = 201, green = 102, blue = 97),
//                                    1f to Color(red = 194, green = 79, blue = 86),
//                                )
//                            } else {
////                                Brush.horizontalGradient(
////                                    0.7f to Color(red = 205, green = 107, blue = 106),
////                                    0.85f to Color(red = 201, green = 102, blue = 97),
////                                    1f to Color(red = 194, green = 79, blue = 86),
////                                )
//                                Brush.horizontalGradient(
//                                    0f to Color(17, 17, 17),
//                                    0.85f to Color(17, 17, 17),
//                                    1f to Color(10, 10, 10)
//
//                                )
//                            }
                            MaterialTheme.colorScheme.surface
                        )
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = when (page) {
                            0 -> "Explore your routes"
                            1 -> "Manage car`s resources"
                            else -> "Prevent repairing"
                        },
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
                                text = "Start exploration",
                                letterSpacing = TextUnit(1f, TextUnitType.Sp),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                }


                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.city_day),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary, )

                )

            }
            Row(
                Modifier
                    .padding(top = 10.dp)
                    .height(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val alpha =
                        if (page >= iteration) 0.2f else 0.6f
                    val color = MaterialTheme.colorScheme.onBackground
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        thickness = 4.dp,
                        color = color.copy(alpha = alpha)
                    )
                }
            }
        }
    }
}
