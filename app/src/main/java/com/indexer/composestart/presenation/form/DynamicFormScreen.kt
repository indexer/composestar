package com.indexer.composestart.presenation.form

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.indexer.composestart.presenation.intent.FormIntent
import com.indexer.composestart.presenation.viewmodel.FormViewModel

@Composable
fun DynamicFormScreen(viewModel: FormViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.onIntent(FormIntent.LoadForm)
    }

    Column(Modifier.padding(16.dp)) {
        state.fields.forEach { field ->
            val value = state.values[field.key].orEmpty()
            val errorKey = state.errors[field.key]
            val errorMessage = errorKey?.let { key ->
                val resId = context.resources.getIdentifier(key, "string", context.packageName)
                if (resId != 0) context.getString(resId) else key
            }

            // Your input field here
            OutlinedTextField(
                value = value,
                onValueChange = { viewModel.onIntent(FormIntent.FieldChanged(field.key, it)) },
                label = { Text(field.hint) },
                isError = errorMessage != null,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            if (errorMessage != null) {
                Text(text = errorMessage, color = Color.Red)
            }
        }

        Button(onClick = { viewModel.onIntent(FormIntent.SubmitForm) }) {
            Text("Submit")
        }
    }
}

