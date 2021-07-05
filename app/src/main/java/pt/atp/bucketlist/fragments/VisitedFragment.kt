package pt.atp.bucketlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.atp.bucketlist.R
import pt.atp.bucketlist.model.Country
import pt.atp.bucketlist.model.FeedAdapter
import pt.atp.bucketlist.model.Place
import pt.atp.bucketlist.model.StoryAdapter
import pt.atp.bucketlist.objects.Places

class VisitedFragment: Fragment(R.layout.fragment_visited)  {

    private var listener = object : StoryAdapter.OnItemClickListener{
        override fun onItemClick(story: Country) {
            Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_visited,container,false)

        val stories: RecyclerView = rootView.findViewById(R.id.rv_stories)
        val feed: RecyclerView = rootView.findViewById(R.id.rv_feed)

        stories.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = StoryAdapter(Places.countriesVisited, listener)
        }

        feed.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = FeedAdapter(Places.placesVisited)
        }

        return rootView
    }

}