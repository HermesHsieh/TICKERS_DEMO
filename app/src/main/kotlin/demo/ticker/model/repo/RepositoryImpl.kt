package demo.ticker.model.repo

import android.graphics.Color
import demo.ticker.extension.isZero
import demo.ticker.model.bean.SearchBean
import demo.ticker.model.data.TickerData
import demo.ticker.model.entity.SearchEntity
import java.math.BigDecimal

class RepositoryImpl : Repository {

    fun createSearchBean(data: TickerData): SearchBean {
        val bean = SearchBean()
        bean.pairName = data.trading_pair_id
        bean.lastTradePrice = data.last_trade_price
        bean.percentText = getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        bean.percentBgColorRes = getPercentageBgColor(bean.percentText)
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
            if (latestBigDecimal.isZero() || beforeBigDecimal.isZero()) {
                return "-"
            } else {
                return latestBigDecimal.divide(beforeBigDecimal, 2, BigDecimal.ROUND_DOWN).subtract(BigDecimal.ONE).toPlainString() + "%"
            }
        }

        fun getPercentageBgColor(percentage: String): Int {
            return when {
                percentage == "-" -> Color.GRAY
                percentage.startsWith("-") && percentage.length > 1 -> Color.GREEN
                percentage == "0.00%" -> Color.LTGRAY
                else -> Color.RED
            }
        }
    }
}