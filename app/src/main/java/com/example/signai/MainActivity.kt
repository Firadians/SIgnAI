package com.example.signai

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import java.sql.Types.NULL
import javax.annotation.Nullable


class MainActivity : AppCompatActivity() {

    private lateinit var nohpInput : EditText
    private lateinit var textView2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view bindings
        nohpInput = findViewById(R.id.nohpInput)
        textView2 = findViewById(R.id.textView2)
        usernameLengthChecker()
    }

    private fun usernameLengthChecker(){
        nohpInput.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()){
                if (text!!.length > 16) {
                    nohpInput.setError("To Long!")
                }
            }
            else if (text.isEmpty()) {
                nohpInput.setError("please enter")
            }
        }
    }
}