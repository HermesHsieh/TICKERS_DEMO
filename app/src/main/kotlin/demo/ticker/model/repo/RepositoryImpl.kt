package demo.ticker.model.repo

import android.graphics.Color
import demo.ticker.extension.isZero
import demo.ticker.model.bean.SearchBean
import demo.ticker.model.data.TickerData
import demo.ticker.model.entity.SearchEntity
import java.math.BigDecimal
import java.text.DecimalFormat

class RepositoryImpl : Repository {

    fun createSearchBean(data: TickerData): SearchBean {
        val bean = SearchBean()
        bean.pairName = data.trading_pair_id
        bean.lastTradePrice = data.last_trade_price
        bean.percentText = getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        bean.percentBgColorRes = getPercentageBgColor(data.last_trade_price, data.open_24h)
        return bean
    }

    fun createSearchEntity(bean: SearchBean): SearchEntity {
        return SearchEntity(data = bean)
    }

    override fun getSearchEntity(list: List<TickerData>): List<SearchEntity> {
        val resultList = ArrayList<SearchEntity>()
        val sortList = list.sortedBy { it.trading_pair_id }
        for (data in sortList) {
            resultList.add(createSearchEntity(createSearchBean(data)))
        }
        return resultList
    }

    companion object {
        fun getPriceWaveInPercentage(latestPrice: String, beforePrice: String): String {
            var latestBigDecimal = BigDecimal(latestPrice)
            var beforeBigDecimal = BigDecimal(beforePrice)
            if (latestBigDecimal.isZero() || beforeBigDecimal.isZero()) {
                return "-"
            } else {
                val value = latestBigDecimal.divide(beforeBigDecimal, 4, BigDecimal.ROUND_DOWN).subtract(BigDecimal.ONE).multiply(BigDecimal("100"))
                return DecimalFormat("0.00").format(value) + "%"
            }
        }

        fun getPercentageBgColor(latestPrice: String, beforePrice: String): Int {
            var latestBigDecimal = BigDecimal(latestPrice)
            var beforeBigDecimal = BigDecimal(beforePrice)
            if (latestBigDecimal.isZero() || beforeBigDecimal.isZero()) {
                return Color.GRAY
            } else if (latestBigDecimal.subtract(beforeBigDecimal).isZero()) {
                return Color.LTGRAY
            } else if (latestBigDecimal.divide(beforeBigDecimal, 4, BigDecimal.ROUND_DOWN).subtract(BigDecimal.ONE) > BigDecimal.ZERO) {
                return Color.RED
            } else {
                return Color.GREEN
            }
        }
    }
}