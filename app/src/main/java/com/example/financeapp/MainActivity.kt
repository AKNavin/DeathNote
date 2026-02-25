package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.FinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppTheme {
                FinanceDashboard()
            }
        }
    }
}

data class Transaction(
    val title: String,
    val date: String,
    val amount: String,
    val positive: Boolean
)

@Composable
fun FinanceDashboard() {
    val transactions = listOf(
        Transaction("Salary", "2026-02-01", "+$4,500", true),
        Transaction("Groceries", "2026-02-22", "-$82", false),
        Transaction("Fuel", "2026-02-23", "-$45", false),
        Transaction("Freelance", "2026-02-24", "+$650", true)
    )

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFF7FAFC), Color(0xFFE6EEF8))
                    )
                )
                .padding(padding)
                .padding(16.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item {
                    Text("Finance Overview", style = MaterialTheme.typography.headlineMedium)
                    Text(
                        "Track spending and team-ready architecture",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF516173)
                    )
                }

                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Current Balance", color = Color(0xFF94A3B8))
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "$24,120.00",
                                color = Color.White,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        StatCard(
                            modifier = Modifier.weight(1f),
                            label = "Income",
                            value = "$5,150",
                            tone = Color(0xFF0F766E)
                        )
                        StatCard(
                            modifier = Modifier.weight(1f),
                            label = "Spend",
                            value = "$2,420",
                            tone = Color(0xFFB91C1C)
                        )
                    }
                }

                item {
                    Text("Recent Transactions", fontWeight = FontWeight.SemiBold)
                }

                items(transactions) { tx ->
                    TransactionRow(tx)
                }
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, label: String, value: String, tone: Color) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(label, color = tone, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(6.dp))
            Text(value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun TransactionRow(tx: Transaction) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(tx.title, fontWeight = FontWeight.Medium)
                Text(tx.date, color = Color(0xFF64748B), style = MaterialTheme.typography.bodySmall)
            }
            Text(
                tx.amount,
                color = if (tx.positive) Color(0xFF0F766E) else Color(0xFFB91C1C),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
