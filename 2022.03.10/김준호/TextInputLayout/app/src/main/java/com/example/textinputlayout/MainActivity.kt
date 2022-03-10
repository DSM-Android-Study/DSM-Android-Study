package com.example.textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText : TextInputEditText = findViewById(R.id.textInputEdit)

        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                text()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
    private fun text() : Boolean{
        val tilEmail : TextInputLayout = findViewById(R.id.tilEmail)
        val value = tilEmail.editText?.text.toString()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        return if (value.isEmpty()) {
            tilEmail.error = "이메일을 입력해주세요."
            false
        } else if (!value.matches(emailPattern.toRegex())) {
            tilEmail.error = "이메일 형식이 잘 못 되었습니다."
            false
        } else {
            tilEmail.error = null
            tilEmail.isErrorEnabled = false
            true
        }
    }
}