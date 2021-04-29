package pt.atp.bucketlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.atp.bucketlist.fragments.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val favoritesFragment = FavoritesFragment()
        val personFragment = PersonFragment()
        val visitedFragment = VisitedFragment()

        setCurrentFragment(homeFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->setCurrentFragment(homeFragment)
                R.id.nav_more->setCurrentFragment(addFragment)
                R.id.nav_favorites->setCurrentFragment(favoritesFragment)
                R.id.nav_person->setCurrentFragment(personFragment)
                R.id.nav_search->setCurrentFragment(visitedFragment)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_photo -> {
                Toast.makeText(applicationContext, "click on photo", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_live ->{
                val intent = Intent(applicationContext, ActivityMaps::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_chat ->{
                Toast.makeText(applicationContext, "click on chat", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}