package demo.ticker.model.data

import com.google.gson.annotations.SerializedName

/**
 * "error": {
"error_code": "error_code_string"
}
 */
class ErrorData(
        @SerializedName("error_code")
        val error_code: String?
)