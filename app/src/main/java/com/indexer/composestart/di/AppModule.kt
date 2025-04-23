package com.indexer.composestart.di

import com.indexer.composestart.data.FeatureToggleRepository
import com.indexer.composestart.data.FeatureToggleRepositoryImpl
import com.indexer.composestart.domain.usecase.ValidateFormUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideValidateFormUseCase(): ValidateFormUseCase = ValidateFormUseCase()

    @Provides
    fun provideToggleRepo(): FeatureToggleRepository = FeatureToggleRepositoryImpl()
}
