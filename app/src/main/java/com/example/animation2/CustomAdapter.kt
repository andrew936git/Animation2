package com.example.animation2

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class CustomAdapter(private val list: ArrayList<Product>):
    RecyclerView.Adapter<CustomAdapter.ProductViewHolder>(), Serializable {

    private var onProductClickListener: OnProductClickListener? = null

    interface OnProductClickListener{
        fun onProductClick(product: Product, position: Int)
    }

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val priceTV: TextView = itemView.findViewById(R.id.priceTV)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        holder.nameTV.text = product.name
        holder.priceTV.text = product.price.toString()
        holder.imageView.setImageResource(product.image)
        holder.itemView.setOnClickListener{
            if (onProductClickListener != null){
                onProductClickListener!!.onProductClick(product, position)
            }
        }
    }

    fun setOnProductClickListener(onProductClickListener: OnProductClickListener){
        this.onProductClickListener = onProductClickListener
    }
}