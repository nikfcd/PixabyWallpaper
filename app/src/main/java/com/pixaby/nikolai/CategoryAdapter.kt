package com.pixaby.nikolai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.item_category.view.*
import java.lang.Exception

class CategoryAdapter(private val mList: List<CategoryModel>, val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int,) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Picasso.get()
            .load(ItemsViewModel.img)
            .into(holder.imageView, object : Callback {
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
        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.ru_name

        holder.itemView.setOnClickListener {
            val intetn=Intent(context,CategoryActivity::class.java)
            intetn.putExtra("category",ItemsViewModel.category)
            intetn.putExtra("ru_name",ItemsViewModel.ru_name)
            context.startActivity(intetn)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_category)
        val textView: TextView = itemView.findViewById(R.id.txt_category_name)
    }
}