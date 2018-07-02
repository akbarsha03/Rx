package `in`.akbarsha03.rx

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import io.reactivex.subjects.PublishSubject

const val SCROLLING_UP = 1
const val SCROLLING_DOWN = -1

class CustomLayoutManager : LinearLayoutManager {

    private var context: Context? = null
    val directionObservable: PublishSubject<Int> = PublishSubject.create()

    constructor(context: Context) : super(context) {
        this.context = context
        directionObservable.onNext(SCROLLING_UP)
    }

    @Suppress("unused")
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        this.context = context
    }

    @Suppress("unused")
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) :
            super(context, orientation, reverseLayout) {
        this.context = context
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        if (dy > 0)
            directionObservable.onNext(SCROLLING_UP)
        else directionObservable.onNext(SCROLLING_DOWN)
        return super.scrollVerticallyBy(dy, recycler, state)
    }
}