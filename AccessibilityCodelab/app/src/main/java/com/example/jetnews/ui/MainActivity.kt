package com.example.reply.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reply.data.LocalEmailsDataProvider
import com.example.reply.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            AppTheme {
                Surface {
                    ReplyAppContent(
                        uiState = uiState,
                        closeDetailScreen = {
                            viewModel.closeDetailScreen()
                        },
                        navigateToDetail = { emailId ->
                            viewModel.setSelectedEmail(emailId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ReplyAppContent(
    uiState: ReplyHomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {
    Column {
        ReplyApp(
            replyHomeUIState = uiState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail
        )
        ExampleText()
    }
}

@Composable
fun ExampleText() {
    Column {
        Text(
            text = "Hello M3 theming",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "You are learning typography",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ReplyAppPreview() {
    AppTheme {
        Surface {
            ReplyAppContent(
                uiState = ReplyHomeUIState(
                    emails = LocalEmailsDataProvider.allEmails
                ),
                closeDetailScreen = {},
                navigateToDetail = {}
            )
        }
    }
}