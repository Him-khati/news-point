package com.himanshu.newspoint.feature.auth.ui.register.validations

import com.vinners.newspoint.core.ValidationException

class RegisterValidationException(
     registerValidationResult: RegisterValidationResult
): ValidationException(registerValidationResult.message) {
    val result = registerValidationResult
}