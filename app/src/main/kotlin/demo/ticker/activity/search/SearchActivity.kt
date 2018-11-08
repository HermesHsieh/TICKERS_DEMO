package demo.ticker.activity.search

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import demo.ticker.R
import demo.ticker.activity.BaseActivity
import demo.ticker.activity.displayHomeButton
import demo.ticker.getTickersApiUrl
import demo.ticker.isJsonObject
import demo.ticker.model.data.ResponseTickersResult
import demo.ticker.model.entity.SearchEntity
import demo.ticker.model.repo.Repository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity() {

    val TAG = this.javaClass.simpleName

    var searchView: android.support.v7.widget.SearchView? = null

    val repository: Repository by inject()

    var searchSourceList = ArrayList<SearchEntity>()

    val searchEntityList = MutableLiveData<List<SearchEntity>>()

    var searchItem: MenuItem? = null

    val searchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = SearchView()
        view.setContentView(this)
        displayHomeButton(false)

        searchEntityList.observe(this, android.arch.lifecycle.Observer { list ->
            find<SwipeRefreshLayout>(R.id.swipe_refresh_view).isRefreshing = false
            list?.let {
                searchAdapter.list = list as ArrayList<SearchEntity>
                searchAdapter.notifyDataSetChanged()
            }
        })

        searchAdapter.setListener { position, entity ->
        }
    }

    override fun onResume() {
        super.onResume()
        performGetTickers()
    }

    fun queryTickers(queryWord: String) {
        doAsync {
            if (!queryWord.isEmpty()) {
                val filterList = searchSourceList.filter { it.data.pairName.toLowerCase().contains(queryWord.toLowerCase()) }
                if (filterList != null) {
                    searchEntityList.postValue(filterList)
                }
            }
        }
    }

    fun performGetTickers() {
        doAsync {
            requestGetApi(getTickersApiUrl(), object : ResponseResult {
                override fun onSuccess(result: String) {
                    Log.d(TAG, "onSuccess: $result")
                    if (isJsonObject(result)) {
                        val resultData = Gson().fromJson(result, ResponseTickersResult::class.java)
                        Log.d(TAG, "resultData: $resultData")
                        if (resultData.success == null) {
                            toast("Response content error!")
                            return
                        }
                        if (resultData.success) {
                            resultData?.result?.tickers?.let { list ->
                                searchSourceList = repository.getSearchEntity(list) as ArrayList<SearchEntity>
                                searchEntityList.postValue(searchSourceList)
                                return
                            }
                        } else {
                            resultData.error?.error_code?.let { errorMsg ->
                                toast(errorMsg)
                                return
                            }
                            toast("Response content error!")
                        }
                    } else {
                        toast("Response content is not a json format")
                    }
                }

                override fun onFailure(message: String?) {
                    Log.d(TAG, "onFailure: $message")
                    if (message != null) {
                        toast(message)
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchItem = menu?.findItem(R.id.menu_search_view)
        searchView = searchItem?.actionView as android.support.v7.widget.SearchView
        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                queryTickers(query)
                searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                queryTickers(newText)
                return false
            }
        })
        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                searchView?.clearFocus()
                performGetTickers()
                return true
            }
        })
        return true
    }
}