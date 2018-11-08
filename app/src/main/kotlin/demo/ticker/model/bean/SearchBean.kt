package demo.ticker.model.bean

import java.io.Serializable

data class SearchBean(var _id: Int = -1) : Serializable {
    var pairName = ""
    var lastTradePrice = ""
    var percentText = ""
    var percentBgColorRes = -1
}