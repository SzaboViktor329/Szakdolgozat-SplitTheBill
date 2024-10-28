package com.splitthebill.ui.utils

fun createMonogram(input: String): String {
    if (input == "") return ""

    val words = input.split(" ").filter { it.isNotEmpty() }

    return when {
        words.size == 1 -> words[0].take(2).uppercase()
        words.size >= 2 -> "${words[0].first().uppercase()}${words[1].first().uppercase()}"
        else -> ""
    }
}