package `in`.akbarsha03.rx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customLayoutManager = CustomLayoutManager(this)
        recyclerView.layoutManager = customLayoutManager

        val customAdapter = CustomAdapter()
        recyclerView.adapter = customAdapter
    }
}