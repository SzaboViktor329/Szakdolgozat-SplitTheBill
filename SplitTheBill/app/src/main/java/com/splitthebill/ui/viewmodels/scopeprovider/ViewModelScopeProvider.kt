package com.splitthebill.ui.viewmodels.scopeprovider

import androidx.lifecycle.ViewModelStoreOwner

/*
    Used to hold the ViewModelStoreOwner, which is initially being set in the corresponding navGraph.
    The goal of its use is to when a viewModel is injected to a given screen, it will no longer recreate a whole new viewModel,
    instead it will use the same viewModel in the whole navGraph scope.
 */
class ViewModelScopeProvider {
    companion object {
        var mainNavStoreOwner : ViewModelStoreOwner? = null
    }
}