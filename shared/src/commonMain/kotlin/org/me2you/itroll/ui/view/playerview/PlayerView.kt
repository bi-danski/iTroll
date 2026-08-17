package org.me2you.itroll.ui.view.playerview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.me2you.itroll.ui.navigation.RootNavigator
import org.me2you.itroll.vm.RootViewModel

@Composable
fun PlayerView(rootNavigator: RootNavigator, rootViewModel: RootViewModel){
    val rootUiState by rootViewModel.castUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Player") },
                navigationIcon = {
                    IconButton(onClick = { rootNavigator.popBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent) //MaterialTheme.colorScheme.surface),
            )
        },
//        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        innerPadding ->
    }
}