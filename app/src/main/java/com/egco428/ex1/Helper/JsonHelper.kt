package com.egco428.ex1.Helper

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class JsonHelper {

    internal var stream:String? = null

    fun getHTTPData(urlString: String): String {
        try {
            val url = URL(urlString)
            val urlConnection = url.openConnection() as HttpURLConnection
            if (urlConnection.getResponseCode() === 200) {
                val r = BufferedReader(InputStreamReader(urlConnection.getInputStream()))
                val sb = StringBuilder()
                var line: String
                line = r.readLine()

                while (line != null) {
                    sb.append(line)
                    if(r.readLine()==null) break
                    else line = r!!.readLine()
                }
                stream = sb.toString()
                urlConnection.disconnect()
            }

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stream!!
    }

    /*companion object {
        internal var stream: String? = null
    }*/
}