package com.sample.mvvmrxsample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.mvvmrxsample.databinding.ActivityMainBinding
import com.sample.mvvmrxsample.utils.ViewModelFactory
import com.sample.mvvmrxsample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        fetchPhotos()
        setupObserver()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
        binding.recyclerView.apply {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager.orientation = RecyclerView.VERTICAL
            binding.recyclerView.layoutManager = layoutManager
            mAdapter = UsersAdapter()
            binding.recyclerView.adapter = mAdapter
        }
    }

    private fun fetchPhotos() {
        binding.loading.visibility = View.VISIBLE
        viewModel.fetchUsers(20, 0)
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, {
            binding.loading.visibility = View.GONE
            if (it.isNotEmpty()) {
                mAdapter.setUsers(it)
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}