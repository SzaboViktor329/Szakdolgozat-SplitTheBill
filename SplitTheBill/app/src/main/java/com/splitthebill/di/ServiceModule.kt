package com.splitthebill.di

import com.splitthebill.services.CashFlowOptimizationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//This module is responsible to handle the dependency injections for the services defined in the services folder.
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideCashFlowOptimizationService(): CashFlowOptimizationService {
        return CashFlowOptimizationService()
    }
}