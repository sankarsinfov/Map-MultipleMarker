package com.example.mapapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ListAdaptor(var context: Context, var arrayList: ArrayList<Result>):RecyclerView.Adapter<ListAdaptor.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
      val itemHolder=LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem,parent,false)
        return ItemHolder(
            itemHolder
        )
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var gridListDetails: Result =arrayList.get(position)

        val imageUrl = gridListDetails.icon
        Picasso.get().load(imageUrl).into(holder.icon)
        holder.title.setText(gridListDetails.name)
        holder.vcity.setText(gridListDetails.vicinity)
        holder.rating.setText(""+gridListDetails.rating)

    }

    class ItemHolder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        var icon=itemview.findViewById<ImageView>(R.id.img_shop_image)
        var title=itemview.findViewById<TextView>(R.id.txt_Name)
        var vcity=itemview.findViewById<TextView>(R.id.txt_vcity)
        var rating=itemview.findViewById<TextView>(R.id.txt_rating)
    }
}




