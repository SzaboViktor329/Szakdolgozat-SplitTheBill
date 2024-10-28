package com.splitthebill.ui.viewmodels.scopeprovider

import androidx.lifecycle.ViewModelStoreOwner

class ViewModelScopeProvider {
    companion object {
        var mainNavStoreOwner : ViewModelStoreOwner? = null
        var authNavStoreOwner : ViewModelStoreOwner? = null
    }
}