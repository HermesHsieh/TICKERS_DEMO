package demo.ticker.activity.search

import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import demo.ticker.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.longToast
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchView : AnkoComponent<SearchActivity> {

    override fun createView(ui: AnkoContext<SearchActivity>) = with(ui) {
        constraintLayout {
            id = R.id.constraint_layout
            backgroundColor = ContextCompat.getColor(context, R.color.colorBackground)
            swipeRefreshLayout {
                id = R.id.swipe_refresh_view
                setOnRefreshListener {
                    if (ui.owner.searchItem != null && !ui.owner.searchItem!!.isActionViewExpanded) {
                        ui.owner.performGetTickers()
                    } else {
                        longToast("You can't refresh in search mode")
                        isRefreshing = false
                    }
                }
                recyclerView {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = ui.owner.searchAdapter
                }
            }.lparams(width = 0, height = 0) {
                leftToLeft = ConstraintSet.PARENT_ID
                rightToRight = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
                bottomToBottom = ConstraintSet.PARENT_ID
            }
        }
    }
}