# Button과 TextInputLayout

### Button (이미지 버튼도 이와 동일)

- **일반버튼**

  ```xml
  <Button
         android:id="@+id/button"
         android:layout_width="100dp"
         android:layout_height="100dp"
         android:layout_marginTop="25dp"
         android:text="Button"
         app:layout_constraintEnd_toStartOf="@+id/guideline2"
         app:layout_constraintStart_toStartOf="parent"
         app:layout_constraintTop_toBottomOf="@+id/textView" />
  
  ```

  - 글씨 대문자

  - 버튼 배경 기본

  - 클릭 효과 보임

    

- **투명버튼( background = "#00ff0000" )**

  ```xml
  <Button
         android:id="@+id/button2"
         android:layout_width="100dp"
         android:layout_height="100dp"
         android:background="#00ff0000"
         android:clickable="true"
         android:text="Button"
         android:textAllCaps="false"
         android:textColor="@color/black"
         app:layout_constraintEnd_toStartOf="@+id/guideline2"
         app:layout_constraintStart_toStartOf="parent"
         app:layout_constraintTop_toBottomOf="@+id/button" />
  ```

  - textAllCaps="false" 글씨 대문자, 소문자 입력하는데로 나옴

  - 버튼 배경 투명

  - 클릭효과 보이지 않음

    

- **색이 들어간 버튼( 빨간색 )**

  ``` xml
  <Button
         android:id="@+id/button4"
         android:layout_width="100dp"
         android:layout_height="100dp"
         android:background="#FF0000"
         android:text="Button"
         android:textAllCaps="false"
         app:layout_constraintEnd_toStartOf="@+id/guideline2"
         app:layout_constraintStart_toStartOf="parent"
         app:layout_constraintTop_toBottomOf="@+id/button3" />
  ```

  - Themes의 MaterialComponents를 AppCompat로 변경해야 버튼 배경색이 변함

  - 버튼 배경 빨강

  - 클릭효과 거의 보이지 않음

    

- **색이 들어간 버튼2 ( 빨간색 )**

  ``` xml
   <Button
         android:id="@+id/button5"
         android:layout_width="100dp"
         android:layout_height="100dp"
         android:layout_marginTop="5dp"
         android:background="@drawable/btn"
         android:text="Button"
         android:textAllCaps="false"
         app:layout_constraintEnd_toStartOf="@+id/guideline2"
         app:layout_constraintStart_toStartOf="parent"
         app:layout_constraintTop_toBottomOf="@+id/button4" />
  ```

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <selector xmlns:android="http://schemas.android.com/apk/res/android">
      <item android:state_pressed="true">
          <shape>
              <solid android:color="#FF5E5E"/>
          </shape>
      </item>
  
      <item android:state_pressed="false">
          <shape>
              <solid android:color="#FF0000"/>
          </shape>
      </item>
  </selector>
  ```

  - 버튼 배경 빨강

  - 클릭효과 보임

    ​	

### TextInputLayout

- 글을 작성할때도 edittext 힌트를 볼수 있다

  ```xml
  <com.google.android.material.textfield.TextInputLayout
      android:id="@+id/textInputLayout3"
      android:layout_width="300dp"
      android:layout_height="wrap_content"
      android:layout_marginTop="40dp"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toTopOf="parent">
  
      <com.google.android.material.textfield.TextInputEditText
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:background="#00ff0000"
          android:hint="text" />
  </com.google.android.material.textfield.TextInputLayout>
  ```

- PasswordToggle 추가( 글씨 암호화 기능 )

  ```xml
  <com.google.android.material.textfield.TextInputLayout
          android:id="@+id/textInputLayout4"
          android:layout_width="300dp"
          android:layout_height="wrap_content"
          android:layout_marginTop="30dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/textInputLayout3"
          app:passwordToggleEnabled="true">
  
          <com.google.android.material.textfield.TextInputEditText
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:background="#00ff0000"
              android:hint="text" />
      </com.google.android.material.textfield.TextInputLayout>
  ```

- 테두리 박스 씌우기

  ```xml
  <com.google.android.material.textfield.TextInputLayout
          android:id="@+id/textInputLayout5"
          style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
          android:layout_width="300dp"
          android:layout_height="wrap_content"
          android:layout_marginTop="30dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/textInputLayout4">
  
          <com.google.android.material.textfield.TextInputEditText
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:hint="text" />
      </com.google.android.material.textfield.TextInputLayout>
  ```

- 길이 제한

  ```xml
   <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/textInputLayout6"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        app:counterMaxLength="10"
        app:counterEnabled="true"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textInputLayout5">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="text"
            android:inputType="text"
            android:maxLength="10"/>
    </com.google.android.material.textfield.TextInputLayout>
  ```

- 형식 확인하기( 이메일 )

  ```xml
   <com.google.android.material.textfield.TextInputLayout
          android:id="@+id/tilEmail"
          android:layout_width="300dp"
          android:layout_height="wrap_content"
          android:layout_marginTop="30dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/textInputLayout7">
  
          <com.google.android.material.textfield.TextInputEditText
              android:id="@+id/textInputEdit"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:hint="email"
              android:background="#00ff0000" />
      </com.google.android.material.textfield.TextInputLayout>
  ```

  ```kotlin
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
  ```

  
