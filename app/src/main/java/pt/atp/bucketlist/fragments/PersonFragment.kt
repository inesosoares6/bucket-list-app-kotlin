package pt.atp.bucketlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pt.atp.bucketlist.R

class PersonFragment: Fragment(R.layout.fragment_account)  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_account,container,false)
        return rootView
    }

}