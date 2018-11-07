package demo.ticker.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preferences<T>(
        val key: String,
        val defaultValue: T,
        val context: Context,
        val name: String
) : ReadWriteProperty<Any?, T> {

    val prefs: SharedPreferences by lazy { context.getSharedPreferences(name, Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getPreference(key, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getPreference(key: String, defaultValue: T): T {
        with(prefs)
        {
            val result: Any = when (defaultValue) {
                is Boolean -> getBoolean(key, defaultValue)
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                is String -> getString(key, defaultValue)
                else -> throw IllegalArgumentException("This type cannot be found in Preferences")
            }
            return result as T
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun <T> putPreference(key: String, value: T) {
        with(prefs.edit())
        {
            when (value) {
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("This type cannot be saved into Preferences")
            }.apply()
        }
    }
}