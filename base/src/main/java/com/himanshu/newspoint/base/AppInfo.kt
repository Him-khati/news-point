package com.himanshu.newspoint.base

data class AppInfo(
    val debugBuild: Boolean,
    val appVersion: String,
    val lastCommit: String,
    val packageName: String,
    val baseApiUrl: String
)