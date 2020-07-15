package com.aszqsc.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var info:Info
    init {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inf=DaggerIMagicBox.create()
        text_view.setText(inf.getInfo().text)
    }

}
class Info constructor(){
    val text=   "Hello Dagger2"
}

class Custom(){
    val a="ALO"
}

