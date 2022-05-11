package com.medxplain.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


abstract class PaginationScrollListener(private val layoutmanager:LinearLayoutManager
                                        ,private val view: FloatingActionButton?):RecyclerView.OnScrollListener(){




    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)



        val visibleItemCount = layoutmanager.childCount
        val totalItemCount = layoutmanager.itemCount
        val  firstVisibleItemPosition = layoutmanager.findFirstVisibleItemPosition()



        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >=
                totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }


    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        super.onScrollStateChanged(recyclerView, newState)


        if (view!=null) {
            if (RecyclerView.SCROLL_STATE_IDLE == newState)
                view.show()
            else view.hide()
        }

      //  view?.show()
    }



   // protected abstract fun isLastItemLoad()
     protected abstract fun loadMoreItems()
     // abstract fun getTotalPageCount():Int
     abstract fun isLastPage():Boolean
     abstract fun isLoading():Boolean



}