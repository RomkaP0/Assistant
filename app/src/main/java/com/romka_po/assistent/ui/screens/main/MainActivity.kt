package com.romka_po.assistent.ui.screens.main

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.romka_po.assistent.ui.components.main.AppNavHost
import com.romka_po.assistent.ui.components.map.rememberMapViewWithLifecycle
import com.romka_po.assistent.ui.theme.AssistentTheme
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()

        MapKitFactory.initialize(this)

        setContent {
            val navController = rememberNavController()
            val mapState = rememberMapViewWithLifecycle()

            AssistentTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        NavigationBar() {

                        }
                    }
                ) { padding ->
                    AppNavHost(modifier = Modifier.padding(padding), navController, mapState)
                }
            }

        }
    }
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.ACCESS_FINE_LOCATION"
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                1
            )
        }
    }
}

