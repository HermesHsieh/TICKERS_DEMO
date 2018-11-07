package demo.ticker.model.data

import com.google.gson.annotations.SerializedName

data class TickersResult(
        @SerializedName("tickers")
        val tickers: List<TickerData>?
)