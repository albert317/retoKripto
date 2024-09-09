package pe.com.albert.features.app_rating.presentation.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.presentation.R
import pe.com.albert.features.app_rating.presentation.util.MyTopAppBar
import pe.com.albert.features.app_rating.presentation.util.optionsCpuResources
import pe.com.albert.features.app_rating.presentation.util.optionsFrequencyUse
import pe.com.albert.features.app_rating.presentation.util.optionsLastUpdate
import pe.com.albert.features.app_rating.presentation.util.optionsMemoryResources

@Composable
fun FormScreen(
    idApplication: String,
    nameApplication: String,
    goToRating: (idApplication: String, nameApplication: String) -> Unit,
    backPressed: () -> Unit
) {
    val viewModel: FormViewMode = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect {
            when (it) {
                is FormNavigation.GoToApplicationList -> {
                    goToRating(idApplication, nameApplication)
                }
            }
        }
    }
    FormScreenContent(nameApplication = nameApplication, saveRating = {
        viewModel.setIntent(
            FormUiIntent.SaveProperty(
                it.copy(
                    idApplication = idApplication
                )
            )
        )
    }, backPressed = backPressed, goToRating = {
        goToRating(idApplication, nameApplication)
    }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreenContent(
    nameApplication: String = "",
    saveRating: (Property) -> Unit = {},
    backPressed: () -> Unit = {},
    goToRating: () -> Unit = {}
) {
    val id = remember { mutableStateOf("") }
    val idApplication = remember { mutableStateOf("") }
    val cpuResources = remember { mutableStateOf("") }
    val isRedundant = remember { mutableStateOf(false) }
    val isObsolete = remember { mutableStateOf(false) }

    var expandedCpuResources by remember { mutableStateOf(false) }
    var selectedOptionCpuResources by remember { mutableStateOf("") }

    var memoryExpanded by remember { mutableStateOf(false) }
    var memorySelectedOption by remember { mutableStateOf("") }

    var frequencyExpanded by remember { mutableStateOf(false) }
    var frequencySelectedOption by remember { mutableStateOf("") }

    var lastUpdateExpanded by remember { mutableStateOf(false) }
    var lastUpdateSelectedOption by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            MyTopAppBar(nameApplication) {
                backPressed()
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { goToRating() }) {
                Icon(Icons.Filled.Star, contentDescription = "Estadisticas")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .selectableGroup()
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.help_text),
                    style = MaterialTheme.typography.bodyMedium,
                )
                ExposedDropdownMenuBox(
                    expanded = expandedCpuResources,
                    modifier = Modifier.fillMaxWidth(),
                    onExpandedChange = { expandedCpuResources = !expandedCpuResources },
                ) {
                    OutlinedTextField(
                        value = selectedOptionCpuResources + if (selectedOptionCpuResources.isNotEmpty()) " %" else "",
                        onValueChange = { cpuResources.value = it },
                        label = { Text("Porcentaje de Uso de CPU") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCpuResources) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        readOnly = true,
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = expandedCpuResources,
                        onDismissRequest = { expandedCpuResources = false },
                    ) {
                        optionsCpuResources.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption + if (selectionOption.isNotEmpty()) " %" else "") },
                                onClick = {
                                    selectedOptionCpuResources = selectionOption
                                    expandedCpuResources = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                ExposedDropdownMenuBox(
                    expanded = memoryExpanded,
                    modifier = Modifier.fillMaxWidth(),
                    onExpandedChange = { memoryExpanded = !memoryExpanded },
                ) {
                    OutlinedTextField(
                        value = memorySelectedOption + if (memorySelectedOption.isNotEmpty()) " MB" else "",
                        onValueChange = { cpuResources.value = it },
                        label = { Text("Memoria Usa") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = memoryExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        readOnly = true,
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = memoryExpanded,
                        onDismissRequest = { memoryExpanded = false },
                    ) {
                        optionsMemoryResources.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption + if (selectionOption.isNotEmpty()) " MB" else "") },
                                onClick = {
                                    memorySelectedOption = selectionOption
                                    memoryExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                ExposedDropdownMenuBox(
                    expanded = frequencyExpanded,
                    modifier = Modifier.fillMaxWidth(),
                    onExpandedChange = { frequencyExpanded = !frequencyExpanded },
                ) {
                    OutlinedTextField(
                        value = frequencySelectedOption + if (frequencySelectedOption.isNotEmpty()) " días" else "",
                        onValueChange = { cpuResources.value = it },
                        label = { Text("Frecuencia de Uso") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = frequencyExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        readOnly = true,
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = frequencyExpanded,
                        onDismissRequest = { frequencyExpanded = false },
                    ) {
                        optionsFrequencyUse.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption + if (selectionOption.isNotEmpty()) " días" else "") },
                                onClick = {
                                    frequencySelectedOption = selectionOption
                                    frequencyExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Es Redundante",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    )
                    Switch(
                        checked = isRedundant.value,
                        onCheckedChange = { isRedundant.value = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Es Obsoleta",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    )
                    Switch(
                        checked = isObsolete.value,
                        onCheckedChange = { isObsolete.value = it })
                }

                ExposedDropdownMenuBox(
                    expanded = lastUpdateExpanded,
                    modifier = Modifier.fillMaxWidth(),
                    onExpandedChange = { lastUpdateExpanded = !lastUpdateExpanded },
                ) {
                    OutlinedTextField(
                        value = lastUpdateSelectedOption + if (lastUpdateSelectedOption.isNotEmpty()) " meses" else "",
                        onValueChange = { cpuResources.value = it },
                        label = { Text("Ultima Actualización") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = lastUpdateExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        readOnly = true,
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = lastUpdateExpanded,
                        onDismissRequest = { lastUpdateExpanded = false },
                    ) {
                        optionsLastUpdate.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption + if (selectionOption.isNotEmpty()) " meses" else "") },
                                onClick = {
                                    lastUpdateSelectedOption = selectionOption
                                    lastUpdateExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        saveRating(
                            Property(
                                idApplication = idApplication.value,
                                cpuResources = selectedOptionCpuResources,
                                isRedundant = isRedundant.value,
                                isObsolete = isObsolete.value,
                                memoryResources = memorySelectedOption,
                                frequencyUse = frequencySelectedOption,
                                lastUpdate = lastUpdateSelectedOption,
                                id = ""
                            )
                        )

                    },
                    enabled = selectedOptionCpuResources.isNotEmpty() &&
                            memorySelectedOption.isNotEmpty() &&
                            frequencySelectedOption.isNotEmpty() &&
                            lastUpdateSelectedOption.isNotEmpty()
                ) {
                    Text(text = "Guardar")
                }
            }
        }
    )
}

@Composable
@Preview
fun FormScreenPreview() {
    FormScreenContent()
}