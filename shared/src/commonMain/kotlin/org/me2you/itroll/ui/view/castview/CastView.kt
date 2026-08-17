package org.me2you.itroll.ui.view.castview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.me2you.itroll.ui.navigation.RootNavigator
import org.me2you.itroll.ui.view.castview.components.CastDeviceItem
import org.me2you.itroll.ui.view.castview.components.NoCastDevice
import org.me2you.itroll.vm.RootViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastView(rootNavigator: RootNavigator, rootViewModel: RootViewModel) {
    val rootUiState by rootViewModel.castUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cast") },
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
    ) { innerPadding ->
        if (rootUiState.recentDevices.isEmpty()) NoCastDevice(modifier = Modifier.padding(innerPadding))
            else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    item {
                        Text(
                            text = "${rootUiState.availableDeviceCount} " +
                                    "${ if (rootUiState.availableDeviceCount == 1) "device" else "devices" } available",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp),
                        )
                    }

                    items(rootUiState.recentDevices, key = { it.id }) { device ->
                        CastDeviceItem(
                            device = device,
                            onConnectClick = { rootViewModel.onConnectClick(device) },
                        )
                    }
                }
    }
}
