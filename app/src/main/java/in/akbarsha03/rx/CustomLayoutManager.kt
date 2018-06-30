package `in`.akbarsha03.rx

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log

class CustomLayoutManager : LinearLayoutManager {

    private var context: Context? = null

    constructor(context: Context) : super(context) {
        this.context = context
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        this.context = context
    }

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) :
            super(context, orientation, reverseLayout) {
        this.context = context
    }

    override fun computeVerticalScrollOffset(state: RecyclerView.State?): Int {
        Log.d("TAG", "Item count ${state?.itemCount}")
        return super.computeVerticalScrollOffset(state)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

        when (state ?: 0) {
            RecyclerView.SCROLL_STATE_IDLE -> {

            }
            RecyclerView.SCROLL_STATE_DRAGGING -> {
            }
            RecyclerView.SCROLL_STATE_SETTLING -> {
            }
        }

        return super.scrollVerticallyBy(dy, recycler, state)
    }
}