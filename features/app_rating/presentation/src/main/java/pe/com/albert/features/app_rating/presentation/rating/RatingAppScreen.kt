package pe.com.albert.features.app_rating.presentation.rating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.presentation.util.MyTopAppBar

@Composable
fun RatingAppScreen(idApplication: String, nameApplication: String, backPressed: () -> Unit = {}) {
    val viewModel: RatingViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setIntent(RatingUiIntent.LoadData(idApplication))
    }

    RatingAppScreenContent(
        nameApplication = nameApplication,
        backPressed = { backPressed },
        property = state.value.property,
        message = "Rating de la app"
    )
}

@Composable
fun RatingAppScreenContent(
    nameApplication: String,
    backPressed: () -> Unit,
    property: Property?,
    message: String
) {

    Scaffold(
        topBar = {
            MyTopAppBar(nameApplication) {
                backPressed()
            }
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Datos promedio en base a las respuestas. (${property?.cantidadEncuentados} respuestas)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Row(Modifier.padding(8.dp)) {
                    Text(
                        "Procentaje de CPU",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = property?.cpuResources.toString() + " %",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row(Modifier.padding(8.dp)) {
                    Text(
                        "Memoria Usada",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = property?.memoryResources.toString() + " MB",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(Modifier.padding(8.dp)) {
                    Text(
                        "Frecuencia de uso",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = property?.frequencyUse.toString() + " días",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(Modifier.padding(8.dp)) {
                    Text(
                        "Es Redundante",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = if (property?.isRedundant == true) "Si" else "No",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(Modifier.padding(8.dp)) {
                    Text(
                        "Es Obsoleta",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = if (property?.isObsolete == true) "Si" else "No",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(Modifier.padding(8.dp)) {
                    Text(
                        "Ultima actualización",
                        modifier = Modifier.weight(2f),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = property?.lastUpdate.toString() + " meses",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    )
}