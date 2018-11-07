package demo.ticker.model.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

open class BaseEntity(
        private val type: Int = 1,
        val spanSize: Int = 1
) : MultiItemEntity {
    override fun getItemType(): Int {
        return type
    }
}