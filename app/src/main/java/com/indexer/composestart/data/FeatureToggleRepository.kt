package com.indexer.composestart.data

import kotlinx.coroutines.delay

interface FeatureToggleRepository {
    suspend fun fetchToggles(): Map<String, Boolean>
}

class FeatureToggleRepositoryImpl : FeatureToggleRepository {
    override suspend fun fetchToggles(): Map<String, Boolean> {
        // Simulate backend call
        delay(500)
        return mapOf("confirmPasswordEnabled" to true)
    }
}
