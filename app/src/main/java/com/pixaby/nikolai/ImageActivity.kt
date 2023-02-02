package com.pixaby.nikolai

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pixaby.nikolai.utils.WallpaperHelper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*
import java.lang.Exception

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val url=intent.getStringExtra("image")
        cs_succ_img.visibility= View.GONE
        cs_error_img.visibility= View.GONE
        cs_load_img.visibility= View.VISIBLE
        Picasso.get()
            .load(url)
            .into(img_set, object : Callback {
                override fun onSuccess() {
                    cs_succ_img.visibility= View.VISIBLE
                    cs_error_img.visibility= View.GONE
                    cs_load_img.visibility= View.GONE
                }

                override fun onError(e: Exception?) {
                    cs_succ_img.visibility= View.GONE
                    cs_error_img.visibility= View.VISIBLE
                    cs_load_img.visibility= View.GONE
                }

            })
        txt_bad.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        txt_home.setOnClickListener {

            WallpaperHelper.setHomeWallpaper(this,url!!)
        }

        txt_lock_home.setOnClickListener {
            WallpaperHelper.setHomeLockWallpaper(this,url!!)
        }
    }
}