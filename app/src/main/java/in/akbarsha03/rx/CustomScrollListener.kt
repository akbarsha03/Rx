package `in`.akbarsha03.rx

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.reactivex.subjects.PublishSubject

class CustomItemDecoration : RecyclerView.ItemDecoration() {

    val positionObservable: PublishSubject<Int> = PublishSubject.create()

    override fun getItemOffsets(outRect: Rect?, view: View, parent: RecyclerView?, state: RecyclerView.State?) {
        positionObservable.onNext(view.findViewById<TextView>(R.id.item).text.toString().toInt())
        super.getItemOffsets(outRect, view, parent, state)
    }
}