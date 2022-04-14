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
            name.setText("")
            number.setText("")
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