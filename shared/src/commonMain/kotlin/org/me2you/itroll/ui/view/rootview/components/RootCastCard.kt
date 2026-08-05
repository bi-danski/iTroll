package org.me2you.itroll.ui.view.rootview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.ui.theme.Blue600
import org.me2you.itroll.ui.theme.Green600
import org.me2you.itroll.ui.theme.iTrollTheme

@Composable
fun RootCastCard(
    availableDeviceCount: Int,
    recentDevices: List<CastDeviceUi>,
    onCardClick: () -> Unit,
    onQuickConnectClick: (CastDeviceUi) -> Unit,
) {
    var selectedDevice by remember(recentDevices) { mutableStateOf<CastDeviceUi?>(null) }
    val displayedDevice = selectedDevice ?: recentDevices.firstOrNull()

    Card(
        onClick = onCardClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(0.15f)
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                RootIconBadge(
                    icon = Icons.Filled.Cast,
                    background = MaterialTheme.colorScheme.primaryContainer,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Icon(
                            modifier = Modifier
                                .clickable(onClick = {})
                                .padding(4.dp),
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh Devices",
                            tint = MaterialTheme.colorScheme.tertiary.copy(0.5f)
                        )
                    }
                    if (availableDeviceCount > 0) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Blue600.copy(0.05f),
                        ) {
                            Text(
                                text = "$availableDeviceCount Available",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Green600,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = "Discover",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Find and connect to devices",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            if (recentDevices.isNotEmpty()) {
                Spacer(Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    recentDevices.forEach { device ->
                        ConnectChip(
                            device = device,
                            selected = device == displayedDevice,
                            onConnect = { selectedDevice = device },
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))
                displayedDevice?.let { device ->
                    RootCastDetail(
                        device = device,
                        onConnectClick = { onQuickConnectClick(device) },
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewRootCastCard(){
    iTrollTheme {
        RootCastCard(
            MockRootData.rootUiState.availableDeviceCount,
            MockRootData.rootUiState.recentDevices,
            {},
            {}
        )
    }
}