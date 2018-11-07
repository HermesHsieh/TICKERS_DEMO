package demo.ticker.activity.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import demo.ticker.R
import demo.ticker.model.entity.SearchEntity
import demo.ticker.model.type.ViewItemType
import demo.ticker.view.SearchItemView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var list: ArrayList<SearchEntity> = ArrayList()

    var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(position: Int, entity: SearchEntity)
    }

    fun setListener(listener: (pos: Int, data: SearchEntity) -> Unit) {
        itemClickListener = object : OnItemClickListener {
            override fun onClick(position: Int, entity: SearchEntity) {
                listener(position, entity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        when (viewType) {
            ViewItemType.SEARCH.value -> {
                return SearchViewHolder(SearchItemView().createView(AnkoContext.create(parent.context, parent.context)))
            }
        }
        return SearchViewHolder(SearchItemView().createView(AnkoContext.create(parent.context, parent.context)))
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return list[position].itemType
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemPosition = position
        holder.itemEntity = list[position]
        holder.bindItem(list[position])
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.find<TextView>(R.id.name_view)
        var itemPosition: Int = -1
        var itemEntity: SearchEntity = SearchEntity()

        init {
            this.itemView.setOnClickListener { itemClickListener?.onClick(itemPosition, itemEntity) }
        }

        fun bindItem(entity: SearchEntity) {
        }

    }
}