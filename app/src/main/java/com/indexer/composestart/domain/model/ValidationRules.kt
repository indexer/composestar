package com.indexer.composestart.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ValidationRules(
    val required: Boolean = false,
    val match: String? = null
)