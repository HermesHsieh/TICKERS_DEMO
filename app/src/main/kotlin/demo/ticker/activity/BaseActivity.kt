package demo.ticker.activity

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import okhttp3.*
import java.io.IOException

fun AppCompatActivity.displayHomeButton(display: Boolean) {
    supportActionBar?.setDisplayHomeAsUpEnabled(display)
    supportActionBar?.setHomeButtonEnabled(display)
}

abstract class BaseActivity : AppCompatActivity() {

    fun requestGetApi(url: String, callback: ResponseResult) {
        val request = Request.Builder().url(url).build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                if (result != null) {
                    callback.onSuccess(result)
                } else {
                    callback.onFailure("Error! Can not get response body content")
                }
            }
        })
    }

    interface ResponseResult {
        fun onSuccess(result: String)

        fun onFailure(message: String?)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return super.onOptionsItemSelected(item)
        }
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }
}