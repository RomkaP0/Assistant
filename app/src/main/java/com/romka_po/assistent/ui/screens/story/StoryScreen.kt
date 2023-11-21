@file:OptIn(ExperimentalFoundationApi::class)

package com.romka_po.assistent.ui.screens.story

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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

    HorizontalPager(modifier = Modifier.fillMaxSize(), state = pagerState) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(top = 14.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier
                    .padding(vertical = 16.dp, horizontal = 30.dp)
                    .height(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val alpha =
                        if (page >= iteration) 0.6f else 0.2f
                    val color = MaterialTheme.colorScheme.primary
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
            Column(modifier = Modifier.weight(1f).padding(top = 14.dp, bottom = 80.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
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
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.city_day),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)

            )


        }
    }
}
