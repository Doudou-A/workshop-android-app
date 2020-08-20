package com.example.workshop_barbershop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    //var activity: EditText? = null
    //var day: Spinner? = null
    val addUrl : String = "http://172.20.10.3:8888/AddUser.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //finding views
        // activity = findViewById(R.id.activity)
        // day = findViewById(R.id.day)
        btn_insert.setOnClickListener {
            //we send user data to database
            addActivity()
        }

    }

    private fun addActivity() {

        val getEmail = etvEmail.text.toString()
        val getPassword= etvPassword.text.toString()
        val getName = etvName.text.toString()
        val getFirstName = etvFirstName.text.toString()
        val getNumber = etvNumber.text.toString()
        //val getDay = day?.selectedItem.toString()
        val stringRequest = object : StringRequest(Request.Method.POST, addUrl,
            Response.Listener<String> { response ->
            try {
                val obj = JSONObject(response)
                Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }, object : Response.ErrorListener{
            override fun onErrorResponse(volleyError: VolleyError) {
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            }
        }){
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params.put("email", getEmail)
                params.put("password", getPassword)
                params.put("name", getName)
                params.put("firstName", getFirstName)
                params.put("number", getNumber)
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }
}