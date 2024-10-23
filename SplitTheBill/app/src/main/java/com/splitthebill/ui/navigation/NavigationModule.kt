package com.splitthebill.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNavController

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainNavController

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {


    @Provides
    @AuthNavController
    fun provideAuthNavController(): String {
        return "initial"
    }


    /*
    @Provides
    @MainNavController
    fun provideMainNavController(): NavHostController {
        return null
    }

     */


}