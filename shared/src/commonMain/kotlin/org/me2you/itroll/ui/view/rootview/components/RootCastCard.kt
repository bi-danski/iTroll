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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import itroll.shared.generated.resources.Res
import itroll.shared.generated.resources.ic_device_scan
import itroll.shared.generated.resources.ic_refresh
import org.jetbrains.compose.resources.painterResource
import org.me2you.itroll.cast.mock.MockRootData
import org.me2you.itroll.cast.state.CastDeviceUi
import org.me2you.itroll.ui.theme.Blue600
import org.me2you.itroll.ui.theme.Green600
import org.me2you.itroll.ui.theme.iTrollTheme

@Composable
fun RootCastCard(
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
            containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(0.35f)
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
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (recentDevices.isNotEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .clickable(onClick = {})
                                    .size(28.dp)
                                    .padding(4.dp),
                                painter = painterResource(Res.drawable.ic_refresh),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        } else {
                            Icon(
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(4.dp),
                                painter = painterResource(Res.drawable.ic_refresh),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .clickable(onClick = {})
                                .size(30.dp)
                                .padding(4.dp),
                            painter = painterResource(Res.drawable.ic_device_scan),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }

                    if (recentDevices.isNotEmpty()) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Blue600.copy(0.09f),
                        ) {
                            Text(
                                text = "${recentDevices.size} Found",
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
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text="Press the Scan to discover devices")
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
//            emptyList<CastDeviceUi>(),
            MockRootData.castUiState.recentDevices,
            {},
            {}
        )
    }
}