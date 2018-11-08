package demo.ticker.model.repo

import demo.ticker.model.bean.SearchBean
import demo.ticker.model.data.TickerData
import demo.ticker.model.entity.SearchEntity
import java.math.BigDecimal

class RepositoryImpl : Repository {

    fun createSearchBean(data: TickerData): SearchBean {
        val bean = SearchBean()
        bean.pairName = data.trading_pair_id
        bean.lastTradePrice = data.last_trade_price
        return bean
    }

    fun createSearchEntity(bean: SearchBean): SearchEntity {
        return SearchEntity(data = bean)
    }

    override fun getSearchEntity(list: List<TickerData>): List<SearchEntity> {
        val resultList = ArrayList<SearchEntity>()
        for (data in list) {
            resultList.add(createSearchEntity(createSearchBean(data)))
        }
        return resultList
    }

    companion object {
        fun getPriceWaveInPercentage(latestPrice: String, beforePrice: String): String {
            var latestBigDecimal = BigDecimal(latestPrice)
            var beforeBigDecimal = BigDecimal(beforePrice)
            return latestBigDecimal.divide(beforeBigDecimal, 2, BigDecimal.ROUND_DOWN).toPlainString() + "%"
        }
    }
}