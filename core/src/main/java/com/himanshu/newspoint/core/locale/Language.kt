package com.himanshu.newspoint.core.locale

/**
 * List Of Languages App Supports
 */
enum class Language constructor(
    private val language: String
) {
    ENGLISH("en"),
    HINDI("hi");

    val languageNameShort get() = language
}