package com.splitthebill

import com.splitthebill.data.repositories.AuthRepository
import com.splitthebill.di.ViewModelModule
import com.splitthebill.ui.viewmodels.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.mockito.Mockito
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ViewModelModule::class]
)
object TestModule {

    @Provides
    @Singleton
    fun provideTesstAuthViewModel(): AuthViewModel {
        val authViewModel: AuthViewModel = Mockito.mock(AuthViewModel::class.java, Mockito.RETURNS_DEEP_STUBS)
        return authViewModel
    }

}