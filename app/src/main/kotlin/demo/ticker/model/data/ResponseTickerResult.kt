package demo.ticker.model.data

import com.google.gson.annotations.SerializedName

/**
 *
{
"success": true,
"result": {
"ticker": {
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
}
}
======================
 * "error": {
"error_code": "error_code_string"
}
 **/
data class ResponseTickerResult(
        @SerializedName("success")
        val success: Boolean?,
        @SerializedName("result")
        val result: TickerResult?,
        @SerializedName("error")
        val error: ErrorData?
)