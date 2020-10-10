package com.techdoctorbd.networkcallwithretrofit.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techdoctorbd.networkcallwithretrofit.R
import com.techdoctorbd.networkcallwithretrofit.interfaces.ApiInterface
import com.techdoctorbd.networkcallwithretrofit.models.ServerResponse
import com.techdoctorbd.networkcallwithretrofit.models.User
import com.techdoctorbd.networkcallwithretrofit.retrofit.RetrofitApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiInterface = RetrofitApiClient.getClient()!!.create(ApiInterface::class.java)

        btn_login.setOnClickListener {
            val user = User(ed_user_id.text.toString(), ed_password.text.toString())
            checkUserValidity(user)
        }

        btn_get_jokes.setOnClickListener {
            getJokeFromServer(user_id_jokes.text.toString())
        }

    }

    private fun checkUserValidity(userCredential: User) {
        val call: Call<ServerResponse?>? = apiInterface.getUserValidity(userCredential)

        call!!.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                val validity = response.body()
                Toast.makeText(this@MainActivity, validity!!.messageString, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getJokeFromServer(userId: String) {
        val call = apiInterface.getJoke(userId)
        call!!.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                val validity = response.body()
                tv_jokes.text = validity!!.messageString
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}