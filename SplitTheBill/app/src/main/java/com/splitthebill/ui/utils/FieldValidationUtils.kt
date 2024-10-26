package com.splitthebill.ui.utils

import android.text.TextUtils
import android.util.Patterns

fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    val minLength = 6
    return password.length >= minLength
}