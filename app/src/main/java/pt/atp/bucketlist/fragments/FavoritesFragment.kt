package pt.atp.bucketlist.fragments

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.atp.bucketlist.R
import pt.atp.bucketlist.model.FavoritesAdapter
import pt.atp.bucketlist.objects.Places


class FavoritesFragment: Fragment(R.layout.fragment_favorites)  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val rootView: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        val favorite: RecyclerView = rootView.findViewById(R.id.rv_fav)
        val site: TextView = rootView.findViewById(R.id.website)

        favorite.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,3)
            adapter = FavoritesAdapter(Places.favourites)
        }

        site.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, site.text)
            startActivity(intent)
        }

        return rootView
    }
}