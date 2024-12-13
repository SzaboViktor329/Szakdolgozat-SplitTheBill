package com.splitthebill.di

import com.splitthebill.data.repositories.AuthRepository
import com.splitthebill.data.repositories.BillRepository
import com.splitthebill.data.repositories.EventRepository
import com.splitthebill.data.repositories.FriendRepository
import com.splitthebill.data.repositories.GroupRepository
import com.splitthebill.data.repositories.RegistrationRepository
import com.splitthebill.data.repositories.UserRepository
import com.splitthebill.services.CashFlowOptimizationService
import com.splitthebill.ui.viewmodels.AddBillViewModel
import com.splitthebill.ui.viewmodels.AuthViewModel
import com.splitthebill.ui.viewmodels.BillListViewModel
import com.splitthebill.ui.viewmodels.BillViewModel
import com.splitthebill.ui.viewmodels.CreateGroupViewModel
import com.splitthebill.ui.viewmodels.EventDetailViewModel
import com.splitthebill.ui.viewmodels.EventsViewModel
import com.splitthebill.ui.viewmodels.FriendRequestViewModel
import com.splitthebill.ui.viewmodels.FriendViewModel
import com.splitthebill.ui.viewmodels.GroupDetailsViewModel
import com.splitthebill.ui.viewmodels.GroupViewModel
import com.splitthebill.ui.viewmodels.RegistrationViewModel
import com.splitthebill.ui.viewmodels.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//This module is responsible to handle the dependency injections for HiltViewModels.
@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideAddBillViewModel(billRepository: BillRepository): AddBillViewModel {
        return AddBillViewModel(billRepository)
    }

    @Provides
    @Singleton
    fun provideAuthViewModel(authRepository: AuthRepository): AuthViewModel {
        return AuthViewModel(authRepository)
    }

    @Provides
    @Singleton
    fun provideBillListViewModel(billRepository: BillRepository): BillListViewModel {
        return BillListViewModel(billRepository)
    }

    @Provides
    @Singleton
    fun provideBillViewModel(userRepository: UserRepository): BillViewModel {
        return BillViewModel(userRepository)
    }

    @Provides
    @Singleton
    fun provideCreateGroupViewModel(groupRepository: GroupRepository): CreateGroupViewModel {
        return CreateGroupViewModel(groupRepository)
    }

    @Provides
    @Singleton
    fun provideEventDetailViewModel(
        eventRepository: EventRepository,
        groupRepository: GroupRepository,
        cashFlowOptimizationService: CashFlowOptimizationService
    ): EventDetailViewModel {
        return EventDetailViewModel(eventRepository, groupRepository, cashFlowOptimizationService)
    }

    @Provides
    @Singleton
    fun provideEventsViewModel(eventRepository: EventRepository): EventsViewModel {
        return EventsViewModel(eventRepository)
    }

    @Provides
    @Singleton
    fun provideFriendRequestViewModel(friendRepository: FriendRepository): FriendRequestViewModel{
        return FriendRequestViewModel(friendRepository)
    }

    @Provides
    @Singleton
    fun provideFriendViewModel(friendRepository: FriendRepository): FriendViewModel {
        return FriendViewModel(friendRepository)
    }

    @Provides
    @Singleton
    fun provideGroupDetailsViewModel(groupRepository: GroupRepository): GroupDetailsViewModel {
        return GroupDetailsViewModel(groupRepository)
    }

    @Provides
    @Singleton
    fun provideGroupViewModel(groupRepository: GroupRepository): GroupViewModel {
        return GroupViewModel(groupRepository)
    }

    @Provides
    @Singleton
    fun provideRegistrationViewModel(registrationRepository: RegistrationRepository): RegistrationViewModel {
        return RegistrationViewModel(registrationRepository)
    }

    @Provides
    @Singleton
    fun provideUserViewModel(userRepository: UserRepository, authRepository: AuthRepository): UserViewModel {
        return UserViewModel(userRepository, authRepository)
    }
}