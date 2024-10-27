package com.splitthebill.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.splitthebill.data.repositories.AuthRepository
import com.splitthebill.data.repositories.RegistrationRepository
import com.splitthebill.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Objects
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepository(firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository {
        return UserRepository(firestore)
    }

    @Singleton
    @Provides
    fun provideRegistrationRepository(firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore): RegistrationRepository {
        return  RegistrationRepository(firebaseAuth, firestore)
    }




}