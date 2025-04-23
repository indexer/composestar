package com.indexer.composestart.domain.usecase

import com.indexer.composestart.domain.model.FormField

class ValidateFormUseCase {
    fun invoke(
        fields: List<FormField>,
        values: Map<String, String>,
        featureToggles: Map<String, Boolean>
    ): Map<String, String?> {
        return fields.associate { field ->
            val value = values[field.key].orEmpty()
            val rules = field.rules
            val errorId = field.errorText

            val error = when {
                rules?.required == true && value.isBlank() -> errorId
                rules?.match != null && featureToggles["confirmPasswordEnabled"] == true -> {
                    val matchValue = values[rules.match] ?: ""
                    if (value != matchValue) errorId else null
                }
                else -> null
            }

            field.key to error
        }.filterValues { it != null }
    }
}




