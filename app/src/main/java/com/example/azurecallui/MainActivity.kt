package com.example.azurecallui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.azure.android.communication.common.CommunicationTokenCredential
import com.azure.android.communication.common.CommunicationTokenRefreshOptions
import com.azure.android.communication.ui.CallComposite
import com.azure.android.communication.ui.CallCompositeBuilder
import com.azure.android.communication.ui.GroupCallOptions
import com.example.azurecallui.ui.theme.AzureCallUITheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            AzureCallUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Button(onClick = {
                        startCallComposite(context)
                    }) {
                        Text(text = "Call")
                    }
                }
            }
        }
    }
}

private fun startCallComposite(context: Context) {
    val communicationTokenRefreshOptions = CommunicationTokenRefreshOptions({ fetchToken() }, true)
    val communicationTokenCredential = CommunicationTokenCredential(communicationTokenRefreshOptions)

    val options = GroupCallOptions(
        context,
        communicationTokenCredential,
        UUID.fromString("5cb04a0a-c28a-416e-b002-08d9e99754fb"),
        "DISPLAY_NAME",
    )

    val callComposite: CallComposite = CallCompositeBuilder().build()
    callComposite.launch(options)
}

private fun fetchToken(): String? {
    return "eyJhbGciOiJSUzI1NiIsImtpZCI6IjEwNCIsIng1dCI6IlJDM0NPdTV6UENIWlVKaVBlclM0SUl4Szh3ZyIsInR5cCI6IkpXVCJ9.eyJza3lwZWlkIjoiYWNzOjkxODU5N2JlLTNmMjktNDY4MC1hOTFhLWI0ZjVlNjc1OGExN18wMDAwMDAwZi0yZmM0LTdkMGItZWM4ZC0wODQ4MjIwMDIwNzgiLCJzY3AiOjE3OTIsImNzaSI6IjE2NDQyMTM2NzMiLCJleHAiOjE2NDQzMDAwNzMsImFjc1Njb3BlIjoidm9pcCxjaGF0IiwicmVzb3VyY2VJZCI6IjkxODU5N2JlLTNmMjktNDY4MC1hOTFhLWI0ZjVlNjc1OGExNyIsImlhdCI6MTY0NDIxMzY3M30.b3NdenV_Ml1CZuH0UTiMGyjUakZBusPiXmFrIjtIBCAxkv3uDGW1Al5bv4QlMvnYHsBW7MhGx7aEKgBcIq0OH6QN51TdDTy47Et2DwVxisSPq4kJja652Y5xPycE34Vlm7ocrr785LYwYiJWIuiEgDXvGwUS_HoMTpeebCzZd2HYf8BvNkRJMWOzpJNviseYB0OuJP9U_WnUR6zgWae8v1Kot3MuXUqmD2dhwURwa3eY4mL4WWoZ1HcoiKNEAC8HWJAwy1PBY7csGKMQirtXlO1GjvcxOWGe9uHc3cr1K96Mg3tJ01pEEh07Mc7-Xsd3AzV8SNMO22SAj6zYpUEbmQ"
}
