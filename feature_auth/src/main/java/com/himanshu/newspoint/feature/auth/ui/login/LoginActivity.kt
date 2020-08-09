package com.himanshu.newspoint.feature.auth.ui.login

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.vinners.newspoint.core.base.BaseActivity
import com.vinners.newspoint.core.taskState.Lce
import com.vinners.newspoint.core.taskState.Lse
import com.vinners.newspoint.feature.auth.R
import com.vinners.newspoint.feature.auth.databinding.ActivityLoginBinding
import com.vinners.newspoint.feature.auth.di.AuthViewModelFactory
import com.himanshu.newspoint.feature.auth.di.DaggerAuthComponent
import com.vinners.newspoint.feature.auth.ui.otp.OtpActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory
    private var highlightedEditTextIndex = 0
    private var previousLength = 0
    private var mobileNo: String? = null
    private var isVerified: Boolean = false
    override val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onInitDependencyInjection() {
        _root_ide_package_.com.himanshu.newspoint.feature.auth.di.DaggerAuthComponent
            .builder()
            .coreComponent(getCoreComponent())
            .build()
            .inject(this@LoginActivity)
    }

    override fun onInitDataBinding() {


        viewBinding.userPasswordInputLayout.visibility = View.GONE
        viewBinding.et1.setOnClickListener {
            showSoftKeyboard(viewBinding.YoEt)
        }
        viewBinding.et2.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et3.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et4.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et5.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et6.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et7.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et8.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et9.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }
        viewBinding.et10.setOnClickListener { showSoftKeyboard(viewBinding.YoEt) }

        startListeningForOtpTypeEvents()
       /* viewBinding.loginBtn.setOnClickListener {
            if (viewBinding.userPasswordInputLayout.isVisible) {
                viewModel.login(
                    mobileNo,
                    viewBinding.passwordEt.text.toString()
                )
            } else {
                viewModel.isUserRegister(mobileNo)
            }
        }*/
        viewBinding.loginOtpBtn.setOnClickListener {
            viewModel.isUserRegister(mobileNo)
            // viewModel.loginWithOtp(mobileNo)
        }
        viewBinding.forgotPassword.setOnClickListener {
            viewModel.forgetPassword(mobileNo)
        }
    }

    override fun onInitViewModel() {
        viewModel.registerWithOtpState.observe(this@LoginActivity, Observer {
            /* when (it) {
                 Lce.Loading -> {
                 }
                 is Lce.Content -> {
                     when (it.content) {
                         is UserNameCheckStates.OtpNotVerified -> {
                             val otpToken =
                                 (it.content as UserNameCheckStates.OtpNotVerified).otpToken
                             openConfirmOtpScreen(otpToken)
                         }
                         UserNameCheckStates.RegistrationFormNotCompleted -> openRegistrationForm()
                        UserNameCheckStates.ShowPasswordField -> showPasswordView()
                     }
                 }
                 is Lce.Error -> showInformationDialog(it.error)
             }*/
            when (it) {
                Lce.Loading -> {

                }
                is Lce.Content -> {
                    isVerified = it.content.isOtpVerified
                    if (it.content.isOtpVerified)
                        viewModel.loginWithOtp(mobileNo)
                    else
                        openConfirmOtpScreen(it.content.otpToken)
                }
                is Lce.Error -> {
                    showInformationDialog(it.error)
                }
            }
        })

        viewModel.loginFormState.observe(
            this@LoginActivity,
            Observer {
                showInformationDialog(it)
            })

        viewModel.loginStateChange.observe(
            this@LoginActivity,
            Observer {
                val loginResult = it ?: return@Observer
                when (loginResult) {
                    Lce.Loading -> {

                    }
                    is Lce.Content -> {
                        if (loginResult.content.firstName == null) {
                            openRegistrationForm()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "login successfully",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            /*  val bundle =
                                  bundleOf(DashboardFragment.INTENT_USER_NAME to loginResult.content.firstName)
                              findNavController().navigateSafely(
                                  R.id.open_dashboard_from_login_action,
                                  args = bundle
                              )*/
                        }
                    }
                    is Lce.Error -> {
                        showInformationDialog(loginResult.error)
                    }
                }
            })
        viewModel.loginWithOtpState.observe(this@LoginActivity, Observer {
            when (it) {
                Lce.Loading -> {

                }
                is Lce.Content -> {
                    openConfirmOtpScreen(it.content.otpToken)
                }
                is Lce.Error -> {
                    showInformationDialog(it.error)
                }
            }
        })
        viewModel.forgetPasswordState.observe(this@LoginActivity, Observer {
            when (it) {
                Lse.Loading -> {

                }
                is Lse.Success -> {
                    Toast.makeText(this, "Password Sent", Toast.LENGTH_SHORT).show()
                }
                is Lse.Error -> {

                }
            }
        })
    }

    private fun startListeningForOtpTypeEvents() {
        viewBinding.YoEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val currentLength = s!!.length
                Log.d("TAG", s.toString())

                if (currentLength > previousLength) {
                    //User has Entered A digit
                    insertDigitInCurrentAndHighlightNextOne(s, currentLength)
                } else {
                    //User has deleted A digit
                    deleteHighlightedDigit()
                }

                previousLength = currentLength
            }

            private fun deleteHighlightedDigit() {
                if (highlightedEditTextIndex == 0) {
                    val editText = viewBinding.editTextHolder.getChildAt(0) as EditText
                    editText.text = null
                } else {

                    val editText =
                        viewBinding.editTextHolder.getChildAt(highlightedEditTextIndex) as EditText
                    val text = editText.text.toString()

                    if (highlightedEditTextIndex == 9 && text.isNotEmpty()) {
                        editText.text = null
                    } else {

                        editText.text = null
                        editText.background =
                            ContextCompat.getDrawable(
                                this@LoginActivity,
                                R.drawable.login_mobile_edit_text_background
                            )

                        val editTextNext =
                            viewBinding.editTextHolder.getChildAt(highlightedEditTextIndex - 1) as EditText
                        editTextNext.background =
                            ContextCompat.getDrawable(
                                this@LoginActivity,
                                R.drawable.login_mobile_edit_text_background
                            )

                        if (text.isEmpty())
                            editTextNext.text = null

                        highlightedEditTextIndex--
                    }
                }
            }

            private fun insertDigitInCurrentAndHighlightNextOne(
                s: CharSequence,
                currentLength: Int
            ) {
                val editText = viewBinding.editTextHolder.getChildAt(previousLength) as EditText
                editText.setText(s.subSequence(previousLength, currentLength).toString())

                if (currentLength < 10) {
                    editText.setBackgroundResource(R.color.white)
                    val editTextNext =
                        viewBinding.editTextHolder.getChildAt(currentLength) as EditText
                    editTextNext.background =
                        ContextCompat.getDrawable(
                            this@LoginActivity,
                            R.drawable.login_mobile_edit_text_background
                        )
                    highlightedEditTextIndex = currentLength
                }
                mobileNo = s.toString()
            }
        })
    }

    private fun openConfirmOtpScreen(otpToken: String?) {

        val intent = Intent(this@LoginActivity, OtpActivity::class.java)
        intent.putExtra(OtpActivity.INTENT_EXTRA_OTP_TOKEN, otpToken)
        intent.putExtra(OtpActivity.INTENT_EXTRA_MOBILE, mobileNo)
        intent.putExtra(OtpActivity.INTENT_EXTRA_IS_REGISTER, isVerified)
        startActivity(intent)
    }

    private fun openRegistrationForm() {
        // viewBinding.userPasswordInputLayout.setVisibilityGone()
        /*
        val bundle =
            bundleOf(RegisterFragment.INTENT_EXTRA_MOBILE to mobileNo)
        findNavController().navigateSafely(
            R.id.open_register_fragment_action,
            args = bundle
        )*/
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}