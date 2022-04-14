### ArrayList 저장하기

- 코틀린 사용
- Sharedpreferences 사용
- json 사용
- ArrayList -> json 형태로 변경-> json 데이터 string형태로 전환하여 Sharedpreferences를 이용하여 저장 -> 저장된 데이터에서 데이터를 추출하여 ArrayList 안에 담기

**MainActivity**

```kotlin
package com.example.arraylistsharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBtn : Button = findViewById(R.id.add_btn)
        val saveBtn : Button = findViewById(R.id.save_btn)
        val textView : TextView = findViewById(R.id.textView)
        val name : EditText = findViewById(R.id.name)
        val number : EditText = findViewById(R.id.number)

        addBtn.setOnClickListener {
            arrayList.add(MainData(name.text.toString(),number.text.toString()))
            textView.text = arrayList.toString()
        }
        saveBtn.setOnClickListener {
            saveData()
        }


        loadData()
        textView.text = arrayList.toString()

    }

    private fun saveData(){
        val obj = JSONObject()
        val jArray = JSONArray()

        for(i in 0 until arrayList.size){
            val sObject = JSONObject()
            sObject.put("name", arrayList[i].name)
            sObject.put("number", arrayList[i].number)
            jArray.put(sObject)
        }
        obj.put("posts",jArray)

        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("name",obj.toString())
        edit.apply()

        Log.d(TAG, "saveData: $obj")
    }

    private fun loadData(){
        val pref = getSharedPreferences("pref",0)
        val str = pref.getString("name","0")
        if(str != "0"){
            val jsonObject = JSONObject(str.toString())
            val posts = jsonObject.getString("posts")
            val jsonArray = JSONArray(posts)
            if(jsonArray.length()==0){
                return
            }
            for(i in 0 until jsonArray.length()){
                val jsonObject1 = jsonArray.getJSONObject(i)
                val name = jsonObject1.getString("name")
                val number = jsonObject1.getString("number")

                val mainData = MainData(name,number)
                arrayList.add(mainData)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }
}
```

**Data**

```kotlin
package com.example.arraylistsharedpreferences

val arrayList : ArrayList<MainData> = ArrayList()
```

**MainData**

```kotlin
package com.example.arraylistsharedpreferences

data class MainData(val number : String, val name : String)
```

**activity_main**

```kotlin
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <EditText
        android:id="@+id/name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="136dp"
        android:hint="name"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/number"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="36dp"
        android:hint="number"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/name" />

    <Button
        android:id="@+id/add_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="추가"
        app:layout_constraintBottom_toTopOf="@+id/textView"
        app:layout_constraintEnd_toStartOf="@+id/number"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/number" />

    <Button
        android:id="@+id/save_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="저장"
        app:layout_constraintBottom_toTopOf="@+id/textView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/number"
        app:layout_constraintTop_toBottomOf="@+id/number" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text=""
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.602" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**버튼클릭시 arraylist에 데이터추가**

```kotlin
addBtn.setOnClickListener {
            arrayList.add(MainData(name.text.toString(),number.text.toString()))
            textView.text = arrayList.toString()
        }
```

**데이터 저장하기**

```kotlin
private fun saveData(){
        val obj = JSONObject()
        val jArray = JSONArray()

        for(i in 0 until arrayList.size){
            val sObject = JSONObject()
            sObject.put("name", arrayList[i].name)
            sObject.put("number", arrayList[i].number)
            jArray.put(sObject)
        }
        obj.put("posts",jArray) //ArrayList를 json형태로 변경

        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()
        edit.putString("name",obj.toString())
        edit.apply() //json형태의 데이터를 문자열로 변경한 데이터를 SharedPreferences로 저장

        Log.d(TAG, "saveData: $obj")
    
```

**저장된 데이터 불러오기**

```kotlin
private fun loadData(){
        val pref = getSharedPreferences("pref",0) //저장된 데이터 가져오기
        val str = pref.getString("name","0")
        if(str != "0"){
            val jsonObject = JSONObject(str.toString())
            val posts = jsonObject.getString("posts")
            val jsonArray = JSONArray(posts)
            if(jsonArray.length()==0){
                return
            }
            for(i in 0 until jsonArray.length()){
                val jsonObject1 = jsonArray.getJSONObject(i)
                val name = jsonObject1.getString("name") //json 추출
                val number = jsonObject1.getString("number")

                val mainData = MainData(name,number) //추출된 데이터를 mainData에 저장
                arrayList.add(mainData) //mainData값을 arrayList에 추가
            }
        }
    }
```
