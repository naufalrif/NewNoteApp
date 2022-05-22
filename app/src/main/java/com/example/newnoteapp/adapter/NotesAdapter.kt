package com.example.newnoteapp.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newnoteapp.MainActivity
import com.example.newnoteapp.R
import com.example.newnoteapp.notes.Notes
import com.example.newnoteapp.notes.NotesDB
import kotlinx.android.synthetic.main.custom_layout_delete.view.*
import kotlinx.android.synthetic.main.custom_layout_edit.view.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.notes_adapter.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.time.LocalDateTime

@DelicateCoroutinesApi
class NotesAdapter(private val listnotes : List<Notes>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private var dbNotes: NotesDB? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = LocalDateTime.now()
        holder.itemView.tv_judulfilm.text = listnotes[position].title
        holder.itemView.tv_penulis.text = current.toString()



    }
    override fun getItemCount(): Int {
        return listnotes.size
    }
}