package com.pixaby.nikolai

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pixaby.nikolai.retrofit.PixabyInterface
import com.pixaby.nikolai.retrofit.model.ImageModel
import com.pixaby.nikolai.retrofit.model.RequestModel
import kotlinx.android.synthetic.main.activity_category.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        cs_succ.visibility=View.GONE
        cs_error.visibility=View.GONE
        cs_load.visibility=View.VISIBLE
        val imageList = ArrayList<ImageModel>()
        val recyclerView = findViewById<View>(R.id.rec_image) as RecyclerView
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.setLayoutManager(staggeredGridLayoutManager)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PixabyInterface::class.java)
        val call = service.getQuotes(intent.getStringExtra("category")!!)
        call.enqueue(object : Callback<RequestModel> {
            override fun onResponse(call: Call<RequestModel>, response: Response<RequestModel>) {
                if (response.code() == 200) {
                    cs_succ.visibility=View.VISIBLE
                    cs_error.visibility=View.GONE
                    cs_load.visibility=View.GONE
                    for (i in response.body()!!.hits){
                        imageList.add(i)
                    }
                    val adapter = ImageAdapter(this@CategoryActivity, imageList,intent.getStringExtra("ru_name")!!)
                    recyclerView.setAdapter(adapter)
                }
            }
            override fun onFailure(call: Call<RequestModel>, t: Throwable) {
                cs_succ.visibility=View.GONE
                cs_error.visibility=View.VISIBLE
                cs_load.visibility=View.GONE
                Log.e("t",t.toString())
            }
        })
    }
}