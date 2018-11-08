package demo.ticker.model.repo

import com.google.gson.Gson
import demo.ticker.model.data.TickerData
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest

@RunWith(JUnit4::class)
class RepositoryImplTest : KoinTest {

    val MOCK_DATA = "{\"trading_pair_id\":\"BCH-ETH\",\"timestamp\":1541682180000,\"24h_high\":\"3\",\"24h_low\":\"2.7389564\",\"24h_open\":\"3\",\"24h_volume\":\"0.24896695\",\"last_trade_price\":\"2.7389564\",\"highest_bid\":\"2.741\",\"lowest_ask\":\"2.9969998\"}"

    val mModule = module {
        single { RepositoryImpl() as Repository }
    }

    val mRepository: Repository by inject()

    @Before
    fun setUp() {
        StandAloneContext.startKoin(listOf(mModule))
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun Get_Price_Wave_In_Percentage() {
        // last_trade_price: 2.7389564
        // open_24h: 3
        // Result is 0.912985466666667
        val data = Gson().fromJson(MOCK_DATA, TickerData::class.java)
        System.out.println("last_trade_price: ${data.last_trade_price}")
        System.out.println("open_24h: ${data.open_24h}")
        val result = RepositoryImpl.getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        System.out.println("Result result $result")
    }
}
