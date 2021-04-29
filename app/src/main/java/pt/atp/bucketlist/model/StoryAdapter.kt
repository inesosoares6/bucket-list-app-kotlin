package pt.atp.bucketlist.model

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_story.view.*
import pt.atp.bucketlist.R


class StoryAdapter internal constructor(
    private val places: List<Country>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<StoryAdapter.MainViewHolder?>() {

    interface OnItemClickListener {
        fun onItemClick(story: Country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater.inflate(R.layout.item_story, parent, false))
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val feed = places[position]
        holder.userName.text = feed.country.split(" ")[0]

        holder.userImage.setImageResource(feed.picture)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.userImage.clipToOutline = true
        }

        holder.bindView(places[position],listener)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.iv_user_image!!
        val userName = itemView.tv_user_name!!

        fun bindView(story: Country, listener: OnItemClickListener){
            itemView.setOnClickListener{listener.onItemClick(story)}
        }
    }
}
