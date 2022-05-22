package com.example.newnoteapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import com.example.newnoteapp.R
import com.example.newnoteapp.adapter.NotesAdapter
import com.example.newnoteapp.datastore.UserManager
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    lateinit var notesAdapter : NotesAdapter
    lateinit var userManager: UserManager
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager.userNAME.asLiveData().observe(this){
            tv_welcomeuser.text = "Welcome, $it!"
        }

    }
}