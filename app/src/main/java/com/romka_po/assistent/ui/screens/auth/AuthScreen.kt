@file:OptIn(ExperimentalFoundationApi::class)

package com.romka_po.assistent.ui.screens.auth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.romka_po.assistent.R
import com.romka_po.assistent.model.nav.Screens
import com.romka_po.assistent.ui.components.auth.AuthField
import com.romka_po.assistent.ui.components.auth.CardRadioButton
import com.romka_po.assistent.ui.components.auth.InputType
import com.romka_po.assistent.ui.components.auth.PositionInColumn
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.yandex.authsdk.YandexAuthException
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthSdk
import com.yandex.authsdk.YandexAuthToken
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    val currentAuthSDK = remember { mutableIntStateOf(-1) }
    val sdk = YandexAuthSdk.create(YandexAuthOptions(LocalContext.current))
    val launcher =
        rememberLauncherForActivityResult(sdk.contract) { result -> handleResult(result) }
    val loginOptions = YandexAuthLoginOptions()
    val authLauncher = rememberLauncherForActivityResult(
        contract = VK.getVKAuthActivityResultContract()
    ) { result ->
        handleVKResult(result)
    }
    SideEffect {
//        launcher.launch(loginOptions)
//        authLauncher.launch(arrayListOf(VKScope.WALL, VKScope.PHOTOS))

    }




    HorizontalPager(state = pagerState) { page ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 48.dp, horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello!",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(0.7f)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Sign in",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.7f)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Please fill your information",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.7f)
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterVertically
                ),
            ) {
                if (page == 0) {
                    currentAuthSDK.intValue = -1
                    AuthField(position = PositionInColumn.TOP, InputType.EMAIL)
                    AuthField(position = PositionInColumn.BOTTOM, InputType.PASSWORD)
                } else {
                    CardRadioButton(
                        ImageVector.vectorResource(id = R.drawable.icons8_google),
                        "Google",
                        0,
                        currentAuthSDK,
                        PositionInColumn.TOP
                    )
                    CardRadioButton(
                        ImageVector.vectorResource(id = R.drawable.icons8_vkontakte),
                        "VK",
                        1,
                        currentAuthSDK,
                        PositionInColumn.MIDDLE

                    )
                    CardRadioButton(
                        ImageVector.vectorResource(id = R.drawable.icons8_yandex),
                        "Yandex",
                        2,
                        currentAuthSDK,
                        PositionInColumn.BOTTOM

                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.navigate(Screens.DashBoard.route) },
                    contentPadding = PaddingValues(horizontal = 56.dp, vertical = 16.dp)
                ) {
                    Text(text = "Sign in now", style = MaterialTheme.typography.titleMedium)
                }

                FilledIconButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.Login, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign up")
            }
        }
    }
}

//private fun handleResult(result: Result<YandexAuthToken?>) {
//    result.fold(
//        onSuccess = { token ->
//            if (token != null) {
//                // Success auth
//            }
//        },
//        onFailure = { exception ->
//            if (exception is YandexAuthException) {
//                // Process error
//            }
//        },
//    )
//}
//private fun handleVKResult(result: VKAuthenticationResult) {
//    result.fo
//}
private fun handleResult(result: Any?) {
    when (result) {
        is Result<*> -> {
            result.fold(
                onSuccess = { token ->
                    if (token != null) {
                        Log.i("LOGI", "handleResult: $token")
                    }
                },
                onFailure = { exception ->
                    if (exception is YandexAuthException) {
                        // Process error
                    }
                },
            )
        }
        is VKAuthenticationResult -> {

        }
    }
}