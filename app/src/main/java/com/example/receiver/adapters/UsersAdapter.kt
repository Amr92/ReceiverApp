package com.example.receiver.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.receiver.R
import com.example.receiver.models.UsersPojo

class UsersAdapter(
    private val receivedDataList: List<UsersPojo>
): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_data_item, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.name.text = receivedDataList[position].name
        holder.email.text = receivedDataList[position].email
        holder.userId.text = receivedDataList[position].userId
    }

    override fun getItemCount(): Int {
        return receivedDataList.size
    }

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.tv_name_2)
        val userId: TextView = itemView.findViewById(R.id.tv_id_2)
        val email: TextView = itemView.findViewById(R.id.tv_email_2)
    }
}