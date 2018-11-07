package demo.ticker.model.data

import com.google.gson.annotations.SerializedName

/**
 * {
"success": true,
"result": {
"tickers": [
{
"trading_pair_id": "ETH-BTC",
"timestamp": 1526442600000,
"24h_high": "0.08519",
"24h_low": "0.0825143",
"24h_open": "0.0832193",
"24h_volume": "297.48782148000026",
"last_trade_price": "0.0839425",
"highest_bid": "0.083694",
"lowest_ask": "0.0839903"
}
]
}
}
======================
 * "error": {
"error_code": "error_code_string"
}
 */
data class ResponseTickersResult(
        @SerializedName("success")
        val success: Boolean?,
        @SerializedName("result")
        val result: TickersResult?,
        @SerializedName("error")
        val error: ErrorData?
)