package com.egco428.ex1

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.egco428.ex1.Helper.JsonHelper
import com.egco428.ex1.Models.Quote
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quoteBtn.setOnClickListener(){
            var asyncTask = object :AsyncTask<String,String,String>(){
                override fun onPreExecute() {
                    super.onPreExecute()
                    Toast.makeText(this@MainActivity,"Please Wait...",Toast.LENGTH_SHORT).show()
                }

                override fun doInBackground(vararg params: String?): String {
                    val jsonHelper = JsonHelper()
                    return jsonHelper.getHTTPData("https://talaikis.com/api/quotes/random/")
                }

                override fun onPostExecute(result: String?) {
                    super.onPostExecute(result)
                    val quoteText = Gson().fromJson(result,Quote::class.java)
                    textQuote.text = quoteText.quote
                    textAuthor.text = quoteText.author
                    textCat.text = quoteText.cat
                }
            }
            asyncTask.execute()
        }
    }
}
