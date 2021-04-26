package ba.etf.rma21.projekat

import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ba.etf.rma21.projekat.data.fragmenti.*
import ba.etf.rma21.projekat.data.models.KvizInfo
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajKvizSaNazivom
import ba.etf.rma21.projekat.data.viewmodel.PitanjeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){
    companion object{
        var daLiOtvaraProcenat=false
        var trenutniKvizInfo : KvizInfo = KvizInfo("","",false,false, mutableListOf())
        var nazivOtvorenogKviza : String = ""
        var poruka : String = ""
    }


    private lateinit var bottomNavigation: BottomNavigationView
    private var pitanjeViewModel = PitanjeViewModel()

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
                daLiOtvaraProcenat=true
                trenutniKvizInfo.predan=true
                trenutniKvizInfo.zaustavljen=false
                poruka = trenutniKvizInfo.naziv
                KorisnikRepository.informacije.add(trenutniKvizInfo)

                if(dajKvizSaNazivom(trenutniKvizInfo.naziv).naziv!=""){
                    if(dajKvizSaNazivom(trenutniKvizInfo.naziv).zaustavljen==true) {
                        var kviz = dajKvizSaNazivom(trenutniKvizInfo.naziv)
                        KorisnikRepository.informacije.remove(kviz)
                        Log.d("keno", "izbacen ovaj: " + kviz.naziv)
                    }
                }

                for(Kviz in KorisnikRepository.informacije){
                    Log.d("keno", "Naziv: " + Kviz.naziv + "\nPredmet: "+Kviz.nazivPredmeta + "\nPredan: "+ Kviz.predan+"\nZaustavljen: "+Kviz.zaustavljen )
                    Log.d("keno", Kviz.listaOdgovora.size.toString())
                }
                KorisnikRepository.dajProcenat(nazivOtvorenogKviza, pitanjeViewModel.getPitanja(trenutniKvizInfo.naziv,trenutniKvizInfo.nazivPredmeta))
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container, FragmentPokusaj.newInstance(
                    pitanjeViewModel.getPitanja(trenutniKvizInfo.naziv,trenutniKvizInfo.nazivPredmeta)))
                transaction.addToBackStack(null)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.commit()

                if(trenutniKvizInfo.predan==true){
                    bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(false)
                    bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(false)

                    bottomNavigation.getMenu().findItem(R.id.kvizovi).setVisible(true)
                    bottomNavigation.getMenu().findItem(R.id.predmeti).setVisible(true)
                }else{

                    bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(true)
                    bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(true)

                    bottomNavigation.getMenu().findItem(R.id.kvizovi).setVisible(false)
                    bottomNavigation.getMenu().findItem(R.id.predmeti).setVisible(false)
                }

                trenutniKvizInfo = KvizInfo("","",false,false, mutableListOf())

                return@OnNavigationItemSelectedListener true
            }
            R.id.zaustaviKviz -> {


                trenutniKvizInfo.predan=false
                trenutniKvizInfo.zaustavljen=true
                if(dajKvizSaNazivom(trenutniKvizInfo.naziv).naziv!=""){
                    if(dajKvizSaNazivom(trenutniKvizInfo.naziv).zaustavljen==true) {
                        var kviz = dajKvizSaNazivom(trenutniKvizInfo.naziv)
                        KorisnikRepository.informacije.remove(kviz)
                        Log.d("keno", "izbacen ovaj: " + kviz.naziv)
                    }
                }
                KorisnikRepository.informacije.add(trenutniKvizInfo)


                bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(false)
                bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(false)

                bottomNavigation.getMenu().findItem(R.id.kvizovi).setVisible(true)
                bottomNavigation.getMenu().findItem(R.id.predmeti).setVisible(true)


                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                bottomNavigation.setSelectedItemId(R.id.kvizovi)

                trenutniKvizInfo = KvizInfo("","",false,false, mutableListOf())
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
        bottomNavigation.getMenu().findItem(R.id.predajKviz).setVisible(false)
        bottomNavigation.getMenu().findItem(R.id.zaustaviKviz).setVisible(false)
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

