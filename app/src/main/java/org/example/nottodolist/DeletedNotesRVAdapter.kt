package org.example.nottodolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeletedNotesRVAdapter(private val context : Context, private val listener : IDeletedNotesRVAdapter) : RecyclerView.Adapter<DeletedNotesRVAdapter.DeletedNotesViewHolder>()  {
    private val allDeletedNote = ArrayList<DeletedNote>()

    inner class DeletedNotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text1)
        val deleteButton: ImageView = itemView.findViewById(R.id.reButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeletedNotesRVAdapter.DeletedNotesViewHolder {
        val viewHolder = DeletedNotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_deleted_note, parent, false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allDeletedNote[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(
        holder: DeletedNotesRVAdapter.DeletedNotesViewHolder,
        position: Int
    ) {
        val currentNote = allDeletedNote[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
       return allDeletedNote.size
    }

    fun onUpdate(newList : List<DeletedNote>) {
        allDeletedNote.clear()
        allDeletedNote.addAll(newList)

        notifyDataSetChanged()
    }
}

interface IDeletedNotesRVAdapter {
    fun onItemClicked(note : DeletedNote)
}