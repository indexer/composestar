package com.indexer.composestart.presenation.state

import com.indexer.composestart.domain.model.FormField

data class FormState(
    val fields: List<FormField> = emptyList(),
    val values: Map<String, String> = emptyMap(),
    val errors: Map<String, String?> = emptyMap(),
    val featureToggles: Map<String, Boolean> = emptyMap(),
    val isLoading: Boolean = false
)
