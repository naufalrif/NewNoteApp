package com.example.newnoteapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newnoteapp.MainActivity
import com.example.newnoteapp.R
import com.example.newnoteapp.notes.Notes
import com.example.newnoteapp.notes.NotesDB
import kotlinx.android.synthetic.main.custom_layout_delete.view.*
import kotlinx.android.synthetic.main.custom_layout_edit.view.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class DetailFragment(private val listnotes : List<Notes>) : Fragment() {
    private var dbNotes: NotesDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?, position : Int) {
        super.onViewCreated(view, savedInstanceState)
        //delete
        btn_delete.setOnClickListener {
            dbNotes = NotesDB.getInstance(it.context)

            val customDeleteDialog = LayoutInflater.from(it.context)
                .inflate(R.layout.custom_layout_delete, null, false)
            val deleteDataDialog = AlertDialog.Builder(it.context)
                .setView(customDeleteDialog)
                .create()

            customDeleteDialog.btn_delete_confirm.setOnClickListener {
                GlobalScope.async {

                    val command = dbNotes?.notesDao()?.deleteNotesData(listnotes[position])

                    (customDeleteDialog.context as MainActivity).runOnUiThread {
                        if (command != 0) {
                            Toast.makeText(
                                customDeleteDialog.context,
                                "Note ${listnotes[position].title} successfully deleted.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            (customDeleteDialog.context as MainActivity).recreate()
                        } else {
                            Toast.makeText(
                                customDeleteDialog.context,
                                "Note ${listnotes[position].title} failed to delete.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
            deleteDataDialog.show()

            customDeleteDialog.btn_no_delete.setOnClickListener {
                deleteDataDialog.dismiss()
            }
        }

        //edit
        btn_edit.setOnClickListener {
            dbNotes = NotesDB.getInstance(it.context)

            val customEdit =
                LayoutInflater.from(it.context).inflate(R.layout.custom_layout_edit, null, false)
            val editDialog = AlertDialog.Builder(it.context)
                .setView(customEdit)
                .create()

            customEdit.et_note_title_edit.setText(listnotes[position].title)
            customEdit.et_note_edit.setText(listnotes[position].note)

            customEdit.btn_save_note.setOnClickListener {
                val newtitle = customEdit.et_note_title_edit.text.toString()
                val newnote = customEdit.et_note_edit.text.toString()
                listnotes[position].title = newtitle
                listnotes[position].note = newnote

                GlobalScope.async {
                    val command = dbNotes?.notesDao()?.editNotesData(listnotes[position])

                    (customEdit.context as MainActivity).runOnUiThread{
                        if(command != 0){
                            Toast.makeText(it.context, "Successfully updated.", Toast.LENGTH_SHORT).show()
                            (customEdit.context as MainActivity).recreate()
                        }else{
                            Toast.makeText(it.context, "Failed to update.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            editDialog.show()
        }
    }
}