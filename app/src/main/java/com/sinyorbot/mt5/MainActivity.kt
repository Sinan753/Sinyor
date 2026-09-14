package com.sinyorbot.mt5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SinyorBotDashboard()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinyorBotDashboard() {
    var isBotRunning by remember { mutableStateOf(false) }
    var accountStatus by remember { mutableStateOf("Bağlantı Bekleniyor...") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SinyorBot MT5 Kontrol Paneli") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Durum: $accountStatus",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (isBotRunning) "Bot Aktif: Sinyaller taranıyor..." else "Bot Durduruldu.",
                        color = if (isBotRunning) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                }
            }

            Button(
                onClick = {
                    val nextState = !isBotRunning
                    isBotRunning = nextState
                    accountStatus = if (nextState) "MT5 Sunucusuna Bağlı" else "Bağlantı Kesildi"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (isBotRunning) "Botu Durdur" else "Botu Başlat")
            }
        }
    }
}
