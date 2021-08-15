package com.example.archelocapp_1.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.Models.Login

import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityMainBinding
import com.example.archelocapp_1.utils.AuthListener
import com.example.archelocapp_1.viewmodels.LoginViewModel
import java.util.EnumSet.of
import java.util.List.of
import java.util.Map.of
import java.util.Optional.of
import java.util.OptionalDouble.of
import java.util.OptionalInt.of
import java.util.Set.of


// Fragment for the screen 1 that is the login in screen

class LoginFragment  : Fragment() {

    private val TAG = "LoginFragment"

    //private lateinit var binding:  ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ActivityMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)

        val navigationControl = findNavController()
        Log.i("LoginFragment", "Calling ViewModelProviders")
        loginViewModel = ViewModelProvider.of
        (this).get(LoginViewModel::class.java)
        binding.arrowBtn.setOnClickListener {

            val username = binding.UsernameBtn.text;
            val password = binding.PasswordBtn.text;


            //User Authentication based on entering the login credentials (loginCredentials is a function in LoginviewModel)
            loginViewModel.loginCredentials(username.toString(), password.toString())
            while (loginViewModel.authChecking == "not_complete" || loginViewModel.authChecking == "in_progress") {
            }

            if (loginViewModel.authChecking == "complete") {

                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                //findNavController().navigate(LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment())


                // Pass recieved  authenticated token to next fragment
                val action = LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment()//Login_FragmentDirections.surveyOptions()
                action.token = loginViewModel.token
                navigationControl.navigate(action)
                loginViewModel.authChecking = "not_complete"

            } else {
                binding.UsernameBtn.setTextColor(Color.rgb(255, 0, 0))
                binding.PasswordBtn.setTextColor(Color.rgb(255, 0, 0))
                loginViewModel.authChecking = "not_complete"

            }

        }
        return binding.root
    }


}




//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
//        init()
//
//    }
//
//    private fun init() {
//        binding.arrowBtn.setOnClickListener{
//
//            with(loginViewModel){
//               var userName = binding.UsernameBtn.text.toString()
//                var password = binding.PasswordBtn.text.toString()
//                if (userName.isEmpty()) userName = "shasan06"
//                if (password.isEmpty()) password = "^dusk|FULL|HIGH^"
//                login(userName, password , object : AuthListener<Boolean> {
//                    override fun onSuccess(v: Boolean) {
//                        if (v) {
//                            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
//                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment())
//                        }
//                    }
//                })
//
//
//
////                loginViewModel.addNest("BLABLA")
//
//
//
//            }
//        }
//
//
//
//
//
//    }
//}

//to authenticate
// Authenticate User based on credentials entered
//viewModel.checkUserCredentials(username_login.toString(),password_login.toString())
//while( viewModel.authCheck == "not_complete" || viewModel.authCheck == "in_progress") { }
//
//if (viewModel.authCheck == "complete") {
//
//    // Pass recieved  authenticated token to next fragment
//    val action = Login_FragmentDirections.surveyOptions()
//    action.token = viewModel.token
//    navController.navigate(action)
//    viewModel.authCheck = "not_complete"
//
//} else {
//    binding.usernameText.setTextColor(Color.rgb(255, 0, 0))
//    binding.passwordText.setTextColor(Color.rgb(255, 0, 0))
//    viewModel.authCheck = "not_complete"
//
//}
//
//}
//return binding.root
//}