package com.example.newnoteapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.newnoteapp.R
import com.example.newnoteapp.datastore.UserManager
import com.example.newnoteapp.user.UserDB
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private var dbUser: UserDB? = null
    lateinit var userManager : UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserManager(requireContext())
        dbUser = UserDB.getInstance(requireContext())

        btn_login.setOnClickListener {
            if (loginAuth()){
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        tv_register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginAuth() : Boolean {
        if (et_username.text.isNotEmpty() && et_pass.text.isNotEmpty()) {
            dbUser = UserDB.getInstance(requireContext())
            val inUserName = et_username.text.toString()
            val inPassword = et_pass.text.toString()

            val user = dbUser?.userDaoInterface()?.loginCheck(inUserName, inPassword)
            return if (user.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Wrong email or password!", Toast.LENGTH_SHORT)
                    .show()
                false
            } else {
                loginDataStore(inUserName,inPassword)
                Toast.makeText(requireContext(), "Login sukses!", Toast.LENGTH_LONG).show()

                true

            }
        }else{
            Toast.makeText(requireContext(), "Email and password section must be filled!", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    fun loginDataStore(username : String, password : String){
        GlobalScope.launch {
            userManager.login(username, password)
            userManager.setStatus("yes")
        }
    }
}