package com.himanshu.newspoint.feature.auth.ui.dashboard

import com.vinners.newspoint.data.sessionManagement.UserSessionManager
import com.vinners.newspoint.feature.auth.ui.AuthViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager
) : AuthViewModel(userSessionManager){
}