package com.example.receiver.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.receiver.adapters.UsersAdapter
import com.example.receiver.databinding.ActivityMainBinding
import com.example.receiver.models.UsersPojo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var dataName: String
    private lateinit var dataId: String
    private lateinit var dataEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentFilter = IntentFilter("android.example.Receiver.myBroadcast")
        registerReceiver(receiver, intentFilter)

        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        binding.getData.setOnClickListener {

            viewModel.getAllData.observe(this, Observer {
                usersAdapter = UsersAdapter(it)
                binding.recyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    adapter = usersAdapter
                }
                usersAdapter.notifyDataSetChanged()
            })
        }

    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if ("android.example.Receiver.myBroadcast" == intent!!.action) {
                dataName = intent.getStringExtra("android.example.name")!!
                dataId = intent.getStringExtra("android.example.id")!!
                dataEmail = intent.getStringExtra("android.example.email")!!

                viewModel.insertData(UsersPojo(dataName, dataEmail, dataId))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}