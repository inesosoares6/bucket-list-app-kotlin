package pt.atp.bucketlist.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_feed.view.*
import kotlinx.android.synthetic.main.layout_center_profile.view.*


class FavoritesAdapter internal constructor(private val places: List<Country>) : RecyclerView.Adapter<FavoritesAdapter.MainViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater.inflate(pt.atp.bucketlist.R.layout.item_fav, parent, false))
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val feed = places[position]
        holder.userName.text = feed.country
        holder.userImage.setImageResource(feed.picture)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.iv_image!!
        val userName = itemView.tv_description!!
    }
}
