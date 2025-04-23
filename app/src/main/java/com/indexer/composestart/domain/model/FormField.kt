package com.indexer.composestart.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class FormField(
    val key: String,
    val type: String,
    val hint: String,
    val rules: ValidationRules? = null,
    val errorText: String? = null
)


@Serializable
data class FormSection(val section: List<FormField>)

