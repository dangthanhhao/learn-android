package com.aszqsc.diceroller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.aszqsc.diceroller.databinding.ActivityAboutMeBinding


class AboutMe : AppCompatActivity() {
    private  lateinit var binding: ActivityAboutMeBinding
    private  val myName:SampleDataClass= SampleDataClass("Dang thanh Hao")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_about_me)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_about_me)
        binding.btnDone.setOnClickListener {
            addNickName(it)
        }

        binding.customData=myName
//        val btnDone=findViewById<Button>(R.id.btnDone)
//
//        btnDone.setOnClickListener {
//            val txtName=findViewById<EditText>(R.id.edtTxt)
//            Toast.makeText(this,"Hello ${txtName.text}",Toast.LENGTH_SHORT).show()
//        }
    }

    private fun addNickName(it: View?) {
        Toast.makeText(this,"Hello ${binding.edtTxt.text}",Toast.LENGTH_SHORT).show()
        binding.apply {
//            txtName.text=binding.edtTxt.text
            customData?.name=edtTxt.text.toString()
            invalidateAll()

        }
//        hide keyboard
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it?.windowToken,0)
    }
}