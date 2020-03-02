package com.example.onlinebooksapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlinx.android.synthetic.main.activity_homepage.uname as uname1
import kotlinx.android.synthetic.main.activity_signup.upwd as upwd1

class signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btnsignup.setOnClickListener {
//            var intent=Intent(this,adminDashboard::class.java)
//            startActivity(intent)
            if(uid.text.toString().equals("")||uname.text.toString().equals("")||upwd.text.toString().equals("")||umobile.text.toString().equals(""))
            {
                Toast.makeText(this,"Required Fields Missing!!",Toast.LENGTH_LONG).show()

            }
            else
            {

                    var uid=uid.text.toString()
                    var uname=uname.text.toString()
                    var upwd=upwd.text.toString()
                    var umobile=umobile.text.toString()
                    callService(this,uid,uname,upwd,umobile)
            }
        }
        }
    fun callService(c: Context, uid: String, uname:String, upwd:String, umobile:String) {
        try {
            val client = OkHttpClient()

            val formBody = FormBody.Builder()
                .add("uid", uid)
                .add("uname", uname)
                .add("upwd", upwd)
                .add("umobile", umobile)
                .build()
            val request = Request.Builder()
                .url("http://192.168.43.134:80/books/insertuser.php")
                .post(formBody)
                .build()
            client.newCall(request).enqueue(object : Callback {


                override fun onFailure(call: Call, e: IOException) {

                    Log.d("Exception", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        var str = response.body!!.string()
                        Log.d("Message", str)
                        var json = JSONObject(str)
                        var id = json.getInt("success")
                        var message = json.getString("message")
                        Log.e("id", id.toString())
                        Log.v("test", message.toString())
                        if(response.isSuccessful && id != 0){
                            Log.d("RESULT", "SUCCESS")
                            var intent = Intent(c,homepage::class.java)
                            startActivity(intent)
                        }else{
                            Log.d("RESULT", "FAIL")
                            runOnUiThread {
                                Toast.makeText(c, message.toString(), Toast.LENGTH_LONG).show()
                            }
                        }



                        /* if (!response.isSuccessful) throw IOException("Unexpected code $response")
                       var str=response.body!!.string()
                       Log.e("test",str)

                       val jsonObj = JSONObject(str)
                       var flag=jsonObj.getInt("success")
                       var message=jsonObj.getString("message")

                       if(flag==1)
                       {
                           Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                           finish();

                       }
                       else
                       {
                           Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()

                       }

                   */
                    }
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    }

