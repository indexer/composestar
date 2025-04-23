package com.indexer.composestart.presenation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indexer.composestart.data.FeatureToggleRepository
import com.indexer.composestart.domain.model.FormSection
import com.indexer.composestart.domain.usecase.ValidateFormUseCase
import com.indexer.composestart.presenation.intent.FormIntent
import com.indexer.composestart.presenation.state.FormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val toggleRepo: FeatureToggleRepository,
    private val validator: ValidateFormUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FormState())
    val state: StateFlow<FormState> = _state.asStateFlow()

    fun onIntent(intent: FormIntent) {
        when (intent) {
            is FormIntent.LoadForm -> loadForm()
            is FormIntent.FieldChanged -> updateField(intent.key, intent.value)
            is FormIntent.SubmitForm -> submitForm()
        }
    }

    private fun loadForm() = viewModelScope.launch {
        val json = loadJsonFromAssets() // Externalize for testability
        val form = Json.decodeFromString<FormSection>(json)
        val toggles = toggleRepo.fetchToggles()
        _state.value = FormState(fields = form.section, featureToggles = toggles)
    }

    private fun updateField(key: String, value: String) {
        _state.update {
            it.copy(values = it.values + (key to value), errors = it.errors - key)
        }
    }

    private fun submitForm() {
        val current = _state.value
        val errors = validator.invoke(current.fields, current.values, current.featureToggles)
        _state.update { it.copy(errors = errors) }
    }

    private fun loadJsonFromAssets(): String {
        // You can mock this during testing
        return """{
          "section": [
            {"key": "userName", "type": "editText", "hint": "User Name", "rules": {"required": true}},
            {"key": "userPassword", "type": "password", "hint": "Password", "type": "password", "rules": {"required": true}},
            {"key": "confirmPassword", "type": "password", "hint": "Confirm Password",  "type": "password","rules": {"match": "userPassword"},
            "errorText": "error_password_required"}
          ]
        }"""
    }
}
