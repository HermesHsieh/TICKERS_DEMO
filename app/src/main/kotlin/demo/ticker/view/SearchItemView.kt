package demo.ticker.view

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintSet.PARENT_ID
import android.widget.FrameLayout
import demo.ticker.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class SearchItemView : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            verticalPadding = dip(5)
            horizontalPadding = dip(10)
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            constraintLayout {
                verticalPadding = dip(2)
                rightPadding = dip(2)
                leftPadding = dip(8)
                textView {
                    id = R.id.name_view
                    textSize = 18f
                    textColor = Color.BLACK
                    verticalPadding = dip(20)
                    horizontalPadding = dip(10)
                }.lparams(width = 0, height = wrapContent) {
                    leftToLeft = PARENT_ID
                    topToTop = PARENT_ID
                    bottomToBottom = PARENT_ID
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}
