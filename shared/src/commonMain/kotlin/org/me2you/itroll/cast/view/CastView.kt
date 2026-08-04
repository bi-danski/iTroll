package org.me2you.itroll.cast.view

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.me2you.itroll.cast.view.components.CastDeviceItem
import org.me2you.itroll.cast.view.components.NoCastDevice
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.ui.theme.iTrollTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastView(
    availableDeviceCount: Int,
    devices: List<CastDeviceUi>,
    onBackClick: () -> Unit,
    onConnectClick: (CastDeviceUi) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cast") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) { innerPadding ->
        if (devices.isEmpty()) NoCastDevice(modifier = Modifier.padding(innerPadding))
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
                            text = "$availableDeviceCount ${if (availableDeviceCount == 1) "device" else "devices"} available",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp),
                        )
                    }

                    items(devices, key = { it.id }) { device ->
                        CastDeviceItem(
                            device = device,
                            onConnectClick = { onConnectClick(device) },
                        )
                    }
                }
    }
}

@Preview
@Composable
fun PreviewCastScreen() {
    iTrollTheme {
        CastView(
            availableDeviceCount = MockRootData.rootUiState.availableDeviceCount,
            devices = MockRootData.rootUiState.recentDevices,
            onBackClick = {},
            onConnectClick = {},
        )
    }
}