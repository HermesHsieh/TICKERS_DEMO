package demo.ticker

import android.support.multidex.MultiDexApplication
import demo.ticker.model.repo.MockRepository
import demo.ticker.model.repo.Repository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class MyApplication : MultiDexApplication() {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    val mModule = module {
        single { this@MyApplication }

        single { MockRepository() as Repository }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(mModule))
    }
}