package demo.ticker.activity.search

import android.arch.lifecycle.MutableLiveData
import demo.ticker.model.entity.SearchEntity

class SearchEvent {
    val searchEntityList = MutableLiveData<List<SearchEntity>>()
}