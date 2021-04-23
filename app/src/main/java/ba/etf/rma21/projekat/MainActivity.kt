package ba.etf.rma21.projekat

import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ba.etf.rma21.projekat.data.fragmenti.FragmentKvizovi
import ba.etf.rma21.projekat.data.fragmenti.FragmentPokusaj
import ba.etf.rma21.projekat.data.fragmenti.FragmentPredmeti
import com.example.cinaeste.view.KvizAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){



    private lateinit var bottomNavigation: BottomNavigationView

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.kvizovi -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predmeti -> {
                val upisFragment = FragmentPredmeti.newInstance()
                openFragment(upisFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predajKviz -> {
                bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(false);
                bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(false);

                bottomNavigation.getMenu().findItem(R.id.kvizovi).setVisible(true);
                bottomNavigation.getMenu().findItem(R.id.predmeti).setVisible(true);
                return@OnNavigationItemSelectedListener true
            }
            R.id.zaustaviKviz -> {
                bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(false);
                bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(false);

                bottomNavigation.getMenu().findItem(R.id.kvizovi).setVisible(true);
                bottomNavigation.getMenu().findItem(R.id.predmeti).setVisible(true);
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

            // set an exit transition
            sharedElementExitTransition = Fade()
            exitTransition = Fade()
        }
        setContentView(R.layout.activity_main)

        bottomNavigation= findViewById(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigation.selectedItemId= R.id.kvizovi
        bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(false);
        bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(false);
        openFragment(FragmentKvizovi.newInstance())


    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onBackPressed() {
        if(bottomNavigation.selectedItemId != R.id.kvizovi){
            val kvizoviFragment = FragmentKvizovi.newInstance()
            openFragment(kvizoviFragment)
            bottomNavigation.setSelectedItemId(R.id.kvizovi)
        }
    }




}

