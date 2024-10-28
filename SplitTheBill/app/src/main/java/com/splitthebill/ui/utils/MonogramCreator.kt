package com.splitthebill.ui.utils

fun createMonogram(input: String): String {
    if (input == "") return ""

    val words = input.split(" ").filter { it.isNotEmpty() }

    return when {
        words.size == 1 -> words[0].take(2).uppercase()
        else -> words.joinToString("") { it.first().uppercase() }
    }
}