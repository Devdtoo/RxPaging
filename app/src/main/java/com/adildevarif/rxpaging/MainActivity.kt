package com.adildevarif.rxpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.flowable
import androidx.recyclerview.widget.LinearLayoutManager
import com.adildevarif.rxpaging.repo.MainListAdapter
import com.adildevarif.rxpaging.repo.PostDataSource
import com.htf.tasmemcom.repository.remote.Networking
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()

    }

    private fun setupAdapter() {

        var mainListAdapter = MainListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mainListAdapter
        }

        var data = Pager(PagingConfig(pageSize = 6)) {
            PostDataSource(Networking.create(),application)
        }.flow

        lifecycleScope.launch {
            data.collect {
                mainListAdapter.submitData(it)
            }
        }

    }


}