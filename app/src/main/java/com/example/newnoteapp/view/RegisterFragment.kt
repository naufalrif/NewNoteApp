package com.example.newnoteapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.newnoteapp.R
import com.example.newnoteapp.user.User
import com.example.newnoteapp.user.UserDB
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class RegisterFragment : Fragment() {
    private var dbUser: UserDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_reg.setOnClickListener {
            if (et_reg_username.text.isNotEmpty() &&
                et_reg_email.text.isNotEmpty() &&
                et_reg_pass.text.isNotEmpty() &&
                et_reg_pass_repeat.text.isNotEmpty()
            ) {

                if (et_reg_pass.text.toString() != et_reg_pass_repeat.text.toString()) {
                    Toast.makeText(requireContext(), "Password isn't right.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    inputUserData()
                    Navigation.findNavController(view)
                        .navigate(R.id.action_registerFragment_to_loginFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Semua data belum terisi", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun inputUserData() {
        dbUser = UserDB.getInstance(requireContext())
        GlobalScope.async {
            val usernameDataReg = et_reg_username.text.toString()
            val emailDataReg = et_reg_email.text.toString()
            val passDataReg = et_reg_pass.text.toString()
            val passConfirm = et_reg_pass_repeat.text.toString()

            val command = dbUser?.userDaoInterface()
                ?.insertUser(User(null, usernameDataReg, emailDataReg, passDataReg, passConfirm))
            activity?.runOnUiThread {
                if (command != 0.toLong()) {
                    Toast.makeText(requireContext(), "Registration success!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}