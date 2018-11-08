package demo.ticker.view

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintSet.PARENT_ID
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import demo.ticker.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class SearchItemView : AnkoComponent<Context> {

    val ITEM_VERTICAL_PADDING = 8
    val ITEM_HORIZONTAL_PADDING = 6

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            verticalPadding = dip(3)
            horizontalPadding = dip(5)
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            constraintLayout {
                textView {
                    id = R.id.name_view
                    text = "BTC-ETH"
                    textSize = 16f
                    textColor = Color.BLACK
                }.lparams(width = 0, height = wrapContent) {
                    leftToLeft = PARENT_ID
                    topToTop = PARENT_ID
                    bottomToBottom = PARENT_ID
                    rightToLeft = R.id.price_view
                    matchConstraintPercentWidth = 0.35f
                }
                textView {
                    id = R.id.price_view
                    text = "2.7389564"
                    textColor = Color.BLACK
                    gravity = Gravity.START
                }.lparams(width = 0, height = wrapContent) {
                    leftToRight = R.id.name_view
                    topToTop = PARENT_ID
                    rightToLeft = R.id.percentage_view
                    bottomToBottom = PARENT_ID
                    matchConstraintPercentWidth = 0.35f
                }
                textView {
                    id = R.id.percentage_view
                    text = "2.73%"
                    textColor = Color.WHITE
                    backgroundColor = Color.RED
                    gravity = Gravity.END
                }.lparams(width = 0, height = wrapContent) {
                    leftToRight = R.id.price_view
                    topToTop = PARENT_ID
                    bottomToBottom = PARENT_ID
                    rightToRight = PARENT_ID
                    matchConstraintPercentWidth = 0.3f
                }
            }.lparams(width = matchParent, height = matchParent).applyRecursively {
                when (it) {
                    is TextView -> {
                        it.textSize = 16f
                        it.verticalPadding = dip(ITEM_VERTICAL_PADDING)
                        it.horizontalPadding = dip(ITEM_HORIZONTAL_PADDING)
                    }
                }
            }
        }
    }
}
