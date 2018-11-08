package demo.ticker.model.repo

import android.graphics.Color
import com.google.gson.Gson
import demo.ticker.model.data.TickerData
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryImplTest {

    val MOCK_DATA_LESS = "{\"trading_pair_id\":\"BCH-ETH\",\"timestamp\":1541682180000,\"24h_high\":\"3\",\"24h_low\":\"2.7389564\",\"24h_open\":\"3\",\"24h_volume\":\"0.24896695\",\"last_trade_price\":\"2.7389564\",\"highest_bid\":\"2.741\",\"lowest_ask\":\"2.9969998\"}"
    val MOCK_DATA_EQUAL = "{\"trading_pair_id\":\"DXT-USDT\",\"timestamp\":1541682180000,\"24h_high\":\"0.0076\",\"24h_low\":\"0.0076\",\"24h_open\":\"0.0076\",\"24h_volume\":\"0\",\"last_trade_price\":\"0.0076\",\"highest_bid\":\"0.004538\",\"lowest_ask\":\"0.139095\"}"
    val MOCK_DATA_ABOVE = "{\"trading_pair_id\":\"BCH-ETH\",\"timestamp\":1541682180000,\"24h_high\":\"3\",\"24h_low\":\"2.7389564\",\"24h_open\":\"2.553113\",\"24h_volume\":\"0.24896695\",\"last_trade_price\":\"3.2389564\",\"highest_bid\":\"2.741\",\"lowest_ask\":\"2.9969998\"}"

    val MOCK_DATA_OPEN_ZERO = "{\"trading_pair_id\":\"BCH-ETH\",\"timestamp\":1541682180000,\"24h_high\":\"3\",\"24h_low\":\"2.7389564\",\"24h_open\":\"0\",\"24h_volume\":\"0.24896695\",\"last_trade_price\":\"2.7389564\",\"highest_bid\":\"2.741\",\"lowest_ask\":\"2.9969998\"}"
    val MOCK_DATA_LATEST_ZERO = "{\"trading_pair_id\":\"BCH-ETH\",\"timestamp\":1541682180000,\"24h_high\":\"3\",\"24h_low\":\"2.7389564\",\"24h_open\":\"2.5432\",\"24h_volume\":\"0.24896695\",\"last_trade_price\":\"0\",\"highest_bid\":\"2.741\",\"lowest_ask\":\"2.9969998\"}"

    @Test
    fun Get_Percentage_Bg_Color_In_Invalid() {
        val test = "-"
        val result = RepositoryImpl.getPercentageBgColor(test)
        System.out.println("Result result $result")
        Assert.assertEquals(Color.GRAY, result)
    }

    @Test
    fun Get_Percentage_Bg_Color_In_Minus() {
        val test = "-43.1%"
        val result = RepositoryImpl.getPercentageBgColor(test)
        System.out.println("Result result $result")
        Assert.assertEquals(Color.GREEN, result)
    }

    @Test
    fun Get_Percentage_Bg_Color_In_Plus() {
        val test = "0.41%"
        val result = RepositoryImpl.getPercentageBgColor(test)
        System.out.println("Result result $result")
        Assert.assertEquals(Color.RED, result)
    }

    @Test
    fun Get_Percentage_Bg_Color_In_Equals() {
        val test = "0.00%"
        val result = RepositoryImpl.getPercentageBgColor(test)
        System.out.println("Result result $result")
        Assert.assertEquals(Color.LTGRAY, result)
    }

    @Test
    fun Get_Price_Wave_In_Percentage_Latest_Zero() {
        // last_trade_price: 0
        // open_24h: 2.5432
        // Result is invalid
        // Show  -
        val data = Gson().fromJson(MOCK_DATA_LATEST_ZERO, TickerData::class.java)
        System.out.println("last_trade_price: ${data.last_trade_price}")
        System.out.println("open_24h: ${data.open_24h}")
        val result = RepositoryImpl.getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        System.out.println("Result result $result")
        Assert.assertEquals("-", result)
    }

    @Test
    fun Get_Price_Wave_In_Percentage_Open_Zero() {
        // last_trade_price: 2.7389564
        // open_24h: 0
        // Result is invalid
        // Show  -
        val data = Gson().fromJson(MOCK_DATA_OPEN_ZERO, TickerData::class.java)
        System.out.println("last_trade_price: ${data.last_trade_price}")
        System.out.println("open_24h: ${data.open_24h}")
        val result = RepositoryImpl.getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        System.out.println("Result result $result")
        Assert.assertEquals("-", result)
    }

    @Test
    fun Get_Price_Wave_In_Percentage_Above() {
        // last_trade_price: 3.2389564
        // open_24h: 2.553113
        // Result is 1.268630256475134
        // Show  1.268630256475134-1 -> 0.268630256475134
        val data = Gson().fromJson(MOCK_DATA_ABOVE, TickerData::class.java)
        System.out.println("last_trade_price: ${data.last_trade_price}")
        System.out.println("open_24h: ${data.open_24h}")
        val result = RepositoryImpl.getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        System.out.println("Result result $result")
        Assert.assertEquals("0.26%", result)
    }

    @Test
    fun Get_Price_Wave_In_Percentage_Equals() {
        // last_trade_price: 0.0076
        // open_24h: 0.0076
        // Result is 1
        // Show  1-1 -> 0.00
        val data = Gson().fromJson(MOCK_DATA_EQUAL, TickerData::class.java)
        System.out.println("last_trade_price: ${data.last_trade_price}")
        System.out.println("open_24h: ${data.open_24h}")
        val result = RepositoryImpl.getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        System.out.println("Result result $result")
        Assert.assertEquals("0.00%", result)
    }

    @Test
    fun Get_Price_Wave_In_Percentage_Less() {
        // last_trade_price: 2.7389564
        // open_24h: 3
        // Result is 0.912985466666667
        // Show  0.91-1 -> -0.09
        val data = Gson().fromJson(MOCK_DATA_LESS, TickerData::class.java)
        System.out.println("last_trade_price: ${data.last_trade_price}")
        System.out.println("open_24h: ${data.open_24h}")
        val result = RepositoryImpl.getPriceWaveInPercentage(data.last_trade_price, data.open_24h)
        System.out.println("Result result $result")
        Assert.assertEquals("-0.09%", result)
    }
}
