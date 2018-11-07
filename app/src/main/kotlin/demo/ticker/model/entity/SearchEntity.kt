package demo.ticker.model.entity

import demo.ticker.model.bean.SearchBean
import demo.ticker.model.type.ViewItemType

class SearchEntity(
        type: ViewItemType = ViewItemType.SEARCH,
        val data: SearchBean = SearchBean(),
        spanSize: Int = 1
) : BaseEntity(type.value, spanSize)