package demo.ticker.model.data

import com.google.gson.annotations.SerializedName

/**
 * "ticker": {
"trading_pair_id": "ETH-BTC",
"timestamp": 1526442660000,
"24h_high": "0.08519",
"24h_low": "0.0825143",
"24h_open": "0.083655",
"24h_volume": "296.60529380000025",
"last_trade_price": "0.0839425",
"highest_bid": "0.0836897",
"lowest_ask": "0.0839091"
}
 */
data class TickerData(
        @SerializedName("trading_pair_id")
        val trading_pair_id: String?,
        @SerializedName("timestamp")
        val timestamp: Long?,
        @SerializedName("24h_high")
        val high_24h: String?,
        @SerializedName("24h_low")
        val low_24h: String?,
        @SerializedName("24h_open")
        val open_24h: String?,
        @SerializedName("24h_volume")
        val volume_24h: String?,
        @SerializedName("last_trade_price")
        val last_trade_price: String?,
        @SerializedName("highest_bid")
        val highest_bid: String?,
        @SerializedName("lowest_ask")
        val lowest_ask: String?
)