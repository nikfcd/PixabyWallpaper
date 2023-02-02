package com.pixaby.nikolai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixaby.nikolai.retrofit.model.ImageModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import java.lang.Exception

class ImageAdapter(var context: Context, var images: ArrayList<ImageModel>, val ru_name:String) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val res = images[position].webformatURL
        Picasso.get()
            .load(res)
            .into(holder.images, object : Callback {
                override fun onSuccess() {

                    holder.itemView.cs_succ_item.visibility= View.VISIBLE
                    holder.itemView.cs_error_item.visibility= View.GONE
                    holder.itemView.cs_load_item.visibility= View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.itemView.cs_succ_item.visibility= View.GONE
                    holder.itemView.cs_error_item.visibility= View.VISIBLE
                    holder.itemView.cs_load_item.visibility= View.GONE
                }

            })
        holder.name.text = images[position].user

        holder.itemView.setOnClickListener {
            val intetn= Intent(context,ImageActivity::class.java)
            intetn.putExtra("image",images[position].largeImageURL)
            context.startActivity(intetn)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var images: ImageView

        var name:TextView
        init {
            images = view.findViewById<View>(R.id.img_category) as ImageView
            name=view.findViewById<View>(R.id.txt_category_name) as TextView
        }
    }
}
