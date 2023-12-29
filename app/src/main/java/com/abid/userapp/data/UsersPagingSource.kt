package com.abid.userapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abid.userapp.data.response.DataItem
import com.abid.userapp.data.retrofit.ApiService

class UsersPagingSource(private val apiService: ApiService): PagingSource<Int, DataItem>() {

    private companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let{ anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX

            val responseData = apiService.getUsers(position, params.loadSize)

            LoadResult.Page(
                data = responseData.data,
                prevKey = if(position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if(responseData.data.isNullOrEmpty()) null else position + 1
            )
        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }
}