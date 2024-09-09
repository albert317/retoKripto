package pe.com.albert.features.app_rating.presentation.list_application

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.com.albert.features.app_rating.domain.model.Application
import pe.com.albert.features.app_rating.domain.model.exampleData
import pe.com.albert.features.app_rating.presentation.R
import pe.com.albert.features.app_rating.presentation.util.MyTopAppBar

@Composable
fun ApplicationListScreen(goToForm: (String,String) -> Unit = { _,_ -> Unit }) {
    val viewModel: ApplicationListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.setIntent(ApplicationListUiIntent.LoadData)
    }

    ListScreenContent(applications = state.applications, onClick = {
        viewModel.setIntent(ApplicationListUiIntent.GoToForm(it.id,it.name))
    })

    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect {
            when (it) {
                is ApplicationListNavigation.GoToForm -> goToForm(it.id, it.name)
            }
        }
    }
}

@Composable
fun ListScreenContent(
    applications: List<Application> = emptyList(), onClick: (Application) -> Unit
) {
    val act = LocalContext.current as Activity

    Scaffold(
        topBar = {
            MyTopAppBar("Applicaciones"){
                act.finish()
            }
        },
        content = {innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                items(applications) { application ->
                    itemApplication(application, onClick)
                }
            }
        }
    )
}

@Composable
fun itemApplication(application: Application, onClick: (Application) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(application) }
        .padding(4.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_hexagon_24),
                contentDescription = "App Icon"
            )
            Text(text = application.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreenContent(applications = listOf(exampleData, exampleData, exampleData, exampleData)) {}
}