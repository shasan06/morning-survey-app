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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.Models.Login
import com.example.archelocapp_1.R
import com.example.archelocapp_1.databinding.ActivityMainBinding
import com.example.archelocapp_1.utils.AuthListener
import com.example.archelocapp_1.viewmodels.LoginViewModel

class LoginFragment  : Fragment(){

    private val TAG = "LoginFragment"
    private lateinit var binding:  ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        init()

    }

    private fun init() {
        binding.arrowBtn.setOnClickListener{
            
            with(loginViewModel){
               var userName = binding.UsernameBtn.text.toString()
                var password = binding.PasswordBtn.text.toString()
                if (userName.isEmpty()) userName = "shasan06"
                if (password.isEmpty()) password = "^dusk|FULL|HIGH^"
                login(userName, password , object : AuthListener<Boolean> {
                    override fun onSuccess(v: Boolean) {
                        if (v) {
                            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentPopIncludingNewSurveyFragment())
                        }
                    }
                })



//                loginViewModel.addNest("BLABLA")



            }
        }





    }
}

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