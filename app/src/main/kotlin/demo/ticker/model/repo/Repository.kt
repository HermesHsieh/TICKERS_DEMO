package demo.ticker.model.repo

import demo.ticker.model.data.TickerData
import demo.ticker.model.entity.SearchEntity

interface Repository {
    fun getSearchEntity(list: List<TickerData>): List<SearchEntity>
}