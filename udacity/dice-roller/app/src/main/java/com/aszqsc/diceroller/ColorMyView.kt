package com.aszqsc.diceroller

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_color_my_view.*

class ColorMyView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_my_view)
        setListeners()
    }

    private fun setListeners() {
        val clickableViews: List<View> =
            listOf(
                box_one,
                box_two,
                box_three,
                box_four,
                box_five,
                parent_layout,
                red_button,
                yellow_button,
                green_button
            )
        for (view in clickableViews) {
            view.setOnClickListener { setcolor(it) }
        }

    }

    private fun setcolor(it: View?) {
        when (it?.id) {
//        using color class
            R.id.box_one -> it.setBackgroundColor(Color.DKGRAY)
            R.id.box_two -> it.setBackgroundColor(Color.GRAY)

//        using Android color resources for backgroud
            R.id.box_three -> it.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four -> it.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five -> it.setBackgroundResource(android.R.color.holo_green_light)

            R.id.red_button -> box_three.setBackgroundResource(R.color.my_red)
            R.id.yellow_button -> box_four.setBackgroundResource(R.color.my_yellow)
            R.id.green_button -> box_five.setBackgroundResource(R.color.my_green)
            else -> it?.setBackgroundColor(Color.LTGRAY)
        }
    }
}