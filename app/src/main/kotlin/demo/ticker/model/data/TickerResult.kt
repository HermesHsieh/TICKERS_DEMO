package demo.ticker.model.data

import com.google.gson.annotations.SerializedName

data class TickerResult(
        @SerializedName("ticker")
        val ticker: TickerData?
)