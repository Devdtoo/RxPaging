package com.adildevarif.rxpaging.repo

import android.app.Application
import androidx.paging.rxjava2.RxPagingSource
import com.htf.tasmemcom.repository.remote.NetworkService
import com.htf.tasmemcom.repository.tempModel.ApiResponse
import com.htf.tasmemcom.repository.tempModel.Data
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostDataSource(var networkService: NetworkService, val application: Application) : RxPagingSource<Int,Data>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Data>> {
        var currentPageKey = params.key ?: 1
        //var prevPageKey = if (currentPageKey == 1) null else currentPageKey - 1
        //var nextPageKey = currentPageKey.plus(1)


       return networkService.getListData(currentPageKey)
           .subscribeOn(Schedulers.io())
           .observeOn(Schedulers.io())
           .map { toLoadResult(it,currentPageKey) }
    }

    private fun toLoadResult(response: ApiResponse, position: Int): LoadResult<Int, Data> {
        return LoadResult.Page(
            data = response.data,
            prevKey = if (position == 1) null else position - 1,
            nextKey =  if(position == response.total_pages) null else position+1
        )
    }


}