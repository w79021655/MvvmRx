package com.sample.mvvmrxsample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.mvvmrxsample.R
import com.sample.mvvmrxsample.data.UsersItem

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    private var mContext: Context? = null
    private var list = ArrayList<UsersItem>()

    fun setUsers(list: ArrayList<UsersItem>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        mContext = parent.context.applicationContext
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cell_user, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val usersItem = list[position]
        Glide.with(mContext!!).load(usersItem.avatarUrl).centerCrop().into(holder.avatar)
        holder.login.text = usersItem.login

        if (usersItem.siteAdmin) {
            holder.badge.visibility = View.VISIBLE
        } else {
            holder.badge.visibility = View.GONE
        }
    }

    inner class UsersViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var avatar: ImageView = itemView!!.findViewById(R.id.avatar)
        var login: TextView = itemView!!.findViewById(R.id.login)
        var badge: TextView = itemView!!.findViewById(R.id.badge)
    }
}