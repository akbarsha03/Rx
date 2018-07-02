package `in`.akbarsha03.rx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customLayoutManager = CustomLayoutManager(this)
        recyclerView.layoutManager = customLayoutManager

        val customAdapter = CustomAdapter()
        recyclerView.adapter = customAdapter

        val customItemDecoration = CustomItemDecoration()
        recyclerView.addItemDecoration(customItemDecoration)

        customItemDecoration.positionObservable
                .delay(300, TimeUnit.MILLISECONDS)
                /*
                 * A state subject has been used to listen to know the scroll direction
                 * Which will be passed along with the current stream
                 */
                .withLatestFrom(customLayoutManager.directionObservable.switchIfEmpty { dummy ->
                    dummy.onNext(SCROLLING_UP)
                },
                        /*
                         * Basically both streams are merged here
                         */
                        BiFunction<Int, Int, String>
                        { item, scrollDirection ->
                            if (scrollDirection == SCROLLING_UP
                                    && customLayoutManager.findFirstVisibleItemPosition() <= item) {
                                "view at $item is visible for more than 300ms"
                            } else if (scrollDirection == SCROLLING_DOWN
                                    && customLayoutManager.findLastVisibleItemPosition() >= item) {
                                "view at $item is visible for more than 300ms"
                            } else ""
                        })
                .skipWhile { it.isEmpty() }
                /*
                 * I'm not switching any threads here. As we don't use any heavy operations
                 * even the subscribeOn operator below is of no use to operate on background
                 * thread
                 */
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("TAG", it)
                }, { Log.e("TAG", it.message, it) })
    }
}