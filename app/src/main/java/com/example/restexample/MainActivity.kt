package com.example.restexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var api: MyApi? = null
    lateinit var imgWeb: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeApi()
        callGetPosts()
        callSetPosts()

        imgWeb = findViewById(R.id.imgWeb)
        loadImage()
    }

    private fun initializeApi() {
        api = RetrofitClient.retrofit.create(MyApi::class.java)
    }

    private fun callGetPosts() {
        val callGetPosts = api?.getPosts()
        callGetPosts?.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body() //En este caso devuelve la lista de posts
            }
        })
    }

    private fun callSetPosts() {
        val callSetPost = api?.setPost(Post(1, null, "Titulo", "Cuerpo"))
        callSetPost?.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                response.body() //En este caso devuelve un objeto del tipo Post
            }
        })
    }

    private fun loadImage() {
        Picasso.get().isLoggingEnabled = true
        Picasso
            .get()
            .load("http://i.imgur.com/DvpvklR.png")
            .into(imgWeb)
    }

}
