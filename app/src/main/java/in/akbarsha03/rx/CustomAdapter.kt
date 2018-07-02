package `in`.akbarsha03.rx

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.list_item.view.*

class CustomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var list: ArrayList<String>

    init {
        Flowable.range(0, 50)
                .doOnSubscribe { list = ArrayList() }
                .doOnNext { list.add("$it") }
                .subscribe({}, { Log.e("TAG", it.message, it) },
                        { Log.d("TAG", "Generated ${list.size} items") })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CustomViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false))

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.findViewById<TextView>(R.id.list_item)
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.item.text = list[position]
    }
}