package com.example.add_mul_by_kotlin_methods.RetrofitPro.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.add_mul_by_kotlin_methods.RetrofitPro.Repository
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val repository:Repository
) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? = state.anchorPosition
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        return  try{
            val response = repository.getPaginatedMovieList(page)
            println("Response:--${response}")
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1)
            )
        }catch (e:Exception){
            println("ErrorList:---$e")
            LoadResult.Error(
                e
            )
        }catch (e: HttpException) {
            println("HttpError:---$e")
            LoadResult.Error(
                e
            )
        }
    }


}