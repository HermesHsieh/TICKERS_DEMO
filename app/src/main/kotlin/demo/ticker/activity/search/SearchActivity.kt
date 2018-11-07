package demo.ticker.activity.search

import android.os.Bundle
import android.util.Log
import android.view.Menu
import demo.ticker.R
import demo.ticker.activity.BaseActivity
import demo.ticker.activity.displayHomeButton
import demo.ticker.getTickersApiUrl
import demo.ticker.model.entity.SearchEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.setContentView

class SearchActivity : BaseActivity() {

    val TAG = this.javaClass.simpleName

    var searchView: android.support.v7.widget.SearchView? = null

    val searchEvent = SearchEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = SearchView()
        view.setContentView(this)
        displayHomeButton(false)

        searchEvent.searchEntityList.observe(this, android.arch.lifecycle.Observer { list ->
            list?.let {
                view.searchAdapter.list = list as ArrayList<SearchEntity>
                view.searchAdapter.notifyDataSetChanged()
            }
        })

        view.searchAdapter.setListener { position, entity ->
            //            val centerId = entity.data.centerId
//            if (!dbRepository.isAddedFavoriteCenter(centerId)) {
//                val result = dbRepository.addFavoriteCenter(centerId)
//                if (result > 0) {
//                    val decorView = window.peekDecorView()
//                    if (decorView != null) {
//                        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                        inputMethodManager.hideSoftInputFromWindow(decorView.windowToken, 0)
//                    }
//                    setResult(Activity.RESULT_OK)
//                    finish()
//                } else {
//                    toast("Added fail")
//                }
//            }
        }

        performGetTickers()
    }

    fun queryCenter(queryWord: String) {
        doAsync {
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.menu_search_view)
//        searchItem?.expandActionView()
//        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
//            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
//                return false
//            }
//
//            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
//                searchView?.clearFocus()
//                finish()
//                return false
//            }
//        })

        searchView = searchItem?.actionView as android.support.v7.widget.SearchView
//        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                queryCenter(query)
                searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                queryCenter(newText)
                return false
            }
        })
        return true
    }

    fun performGetTickers() {
        requestGetApi(getTickersApiUrl(), object : ResponseApiString {
            override fun onSuccess(result: String?) {
                Log.d(TAG, "onSuccess: $result")
            }

            override fun onFailure(message: String?) {
                Log.d(TAG, "onFailure: $message")
            }
        })
    }
}