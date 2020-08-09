package com.himanshu.newspoint.data.models

import android.net.Uri

data class LoggedInUser(
    val sessionToken: String,
    val displayName: String?,
    val mobileNumber: String,
    val email: String? = null,
    val profilePictureUri: Uri? = null
)