package demo.ticker

import demo.ticker.model.type.ApiActionType
import demo.ticker.model.type.ApiFeatureType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

const val CHBINHOOD_HOST = "https://api.cobinhood.com"
const val VERSION = "v1"

private fun getBaseApiUrl(): String {
    return "$CHBINHOOD_HOST/$VERSION/"
}

private fun getApiUrl(featureType: ApiFeatureType, actionType: ApiActionType): String {
    return getBaseApiUrl() + "${featureType.value}/${actionType.value}"
}

fun getTickersApiUrl(): String {
    return getApiUrl(ApiFeatureType.Market, ApiActionType.TICKERS)
}

fun isJsonObject(string: String?): Boolean {
    if (string == null) {
        return false
    }
    try {
        JSONObject(string)
    } catch (e: JSONException) {
        e.printStackTrace()
        try {
            JSONArray(string)
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }
    return true
}