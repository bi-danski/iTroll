package org.me2you.itroll.ui.view.rootview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.me2you.itroll.root.mock.MockRootData
import org.me2you.itroll.root.state.CastDeviceUi
import org.me2you.itroll.root.utils.toIcon
import org.me2you.itroll.ui.theme.iTrollTheme

@Composable
fun ConnectChip(device: CastDeviceUi, selected: Boolean = false, onConnect: () -> Unit) {
    Surface(
        onClick = onConnect,
        shape = RoundedCornerShape(20.dp),
        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = if (selected) 1.5.dp else 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        ),
        shadowElevation = if (selected) 4.dp else 0.dp,
        tonalElevation = if (selected) 2.dp else 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Icon(
                imageVector = device.kind.toIcon(),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = device.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (selected)  MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
fun PreviewConnectChip(){
    iTrollTheme {
        ConnectChip(MockRootData.rootUiState.recentDevices.first(), onConnect = {})
    }
}