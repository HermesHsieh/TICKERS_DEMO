package demo.ticker.extension

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import demo.ticker.MyApplication
import java.io.Serializable
import kotlin.properties.ReadWriteProperty

object DelegatesExt {
    fun <T> getPreference(
            key: String,
            default: T,
            context: Context = MyApplication.instance,
            name: String = "default"
    ): ReadWriteProperty<Any?, T> = Preferences(key, default, context, name)
}

object Pref {
    object Key {
        const val DEMO_KEY = "DEMO_KEY"
    }
}

/**
 * Start an Activity for given class T and allow to work on intent with "run" lambda function
 */
inline fun <T : Fragment> T.withValues(vararg values: Pair<String, Serializable>): T = this.apply {
    val bundle = Bundle()
    values.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> FragmentActivity.getValue(key: String, default: T) = lazy {
    return@lazy if (intent == null || intent.extras == null) {
        default
    } else {
        intent.extras[key] as? T ?: default
    }
}

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> Fragment.getValue(key: String, default: T) = lazy {
    arguments?.get(key)  as? T ?: default
}
