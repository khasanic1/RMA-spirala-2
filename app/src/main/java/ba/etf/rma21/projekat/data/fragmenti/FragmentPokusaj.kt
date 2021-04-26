package ba.etf.rma21.projekat.data.fragmenti

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.MainActivity.Companion.nazivOtvorenogKviza
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.crveniUpisan
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajKvizSaNazivom
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajProcenat
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.informacije
import ba.etf.rma21.projekat.data.repositories.PitanjeRepository.Companion.listaOdgovorenihPitanja
import ba.etf.rma21.projekat.data.viewmodel.SharedViewModel
import com.google.android.material.navigation.NavigationView


@Suppress("DEPRECATION")
class FragmentPokusaj(pitanja: List<Pitanje>) : Fragment(){
    companion object{
        fun newInstance(pitanja: List<Pitanje>): FragmentPokusaj = FragmentPokusaj(pitanja)

    }
    private lateinit var lista : NavigationView
    private lateinit var pitanjeLayout : FrameLayout
    private var pitanja = pitanja
    private var itemId = 0
    private var order = 0
    private var brojPitanja = 1
    private var viewModel: SharedViewModel = SharedViewModel()
    private var trenutnoPitanje=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.pokusaj_fragment, container, false)
        lista = view.findViewById(R.id.navigacijaPitanja)
        pitanjeLayout = view.findViewById(R.id.framePitanje)


        var daLiJePredan : Boolean = predan()
        var daLiJeZaustavljen : Boolean = zaustavljen()


        if(daLiJePredan || crveniUpisan==true){
            for(P in pitanja){
                lista.menu.add(R.id.lista_nav, itemId++, order++, (brojPitanja++).toString())
            }
            MainActivity.daLiOtvaraProcenat =true
            lista.menu.add(R.id.lista_nav, itemId++, order++, "Rezultat")
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framePitanje, FragmentPoruka.newInstance())
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()

            var listaOdg : MutableList<Pair<String, Int>> = mutableListOf()
            var kviz= dajKvizSaNazivom(nazivOtvorenogKviza)
            listaOdg=kviz.listaOdgovora
            if(!crveniUpisan){
                var brojac=0
                while(brojac<pitanja.size){
                    if (listaOdg[brojac].second-1 == pitanja[brojac].tacan) {
                        val obojena = SpannableString(lista.menu.getItem(brojac).title)
                        obojena.setSpan(ForegroundColorSpan(Color.parseColor("#3DDC84")), 0, obojena.length, 0)
                        lista.menu.getItem(brojac).title = obojena
                    } else {
                        val obojena = SpannableString(lista.menu.getItem(brojac).title)
                        obojena.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")), 0, obojena.length, 0)
                        lista.menu.getItem(brojac).title = obojena
                    }
                    brojac++
                }
                dajProcenat(nazivOtvorenogKviza,pitanja)
            }else{

                val obojena = SpannableString(lista.menu.getItem(0).title)
                obojena.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")), 0, obojena.length, 0)
                lista.menu.getItem(0).title = obojena
            }


        }else if(daLiJeZaustavljen){
            var listaOdg : MutableList<Pair<String, Int>> = mutableListOf()
            var kviz= dajKvizSaNazivom(nazivOtvorenogKviza)
            if(kviz.naziv!=""){
                listaOdg=kviz.listaOdgovora
            }
            MainActivity.trenutniKvizInfo.listaOdgovora =kviz.listaOdgovora

            for(P in pitanja){
                lista.menu.add(R.id.lista_nav, itemId++, order++, (brojPitanja++).toString())
            }
            var brojac=0
            while(brojac<pitanja.size){
                if(listaOdg[brojac].second==0){

                }else{
                    if (listaOdg[brojac].second-1 == pitanja[brojac].tacan) {
                        val obojena = SpannableString(lista.menu.getItem(brojac).title)
                        obojena.setSpan(ForegroundColorSpan(Color.parseColor("#3DDC84")), 0, obojena.length, 0)
                        lista.menu.getItem(brojac).title = obojena
                    } else {
                        val obojena = SpannableString(lista.menu.getItem(brojac).title)
                        obojena.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")), 0, obojena.length, 0)
                        lista.menu.getItem(brojac).title = obojena
                    }
                }
                brojac++
            }
            trenutnoPitanje=0
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framePitanje, FragmentPitanje.newInstance(pitanja[0]))
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()

        }else{
            for(P in pitanja){
                lista.menu.add(R.id.lista_nav, itemId++, order++, (brojPitanja++).toString())
                listaOdgovorenihPitanja.add(0)
                MainActivity.trenutniKvizInfo.listaOdgovora.add(Pair(P.naziv,0))
            }
            trenutnoPitanje=0
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framePitanje, FragmentPitanje.newInstance(pitanja[0]))
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }

        lista.setNavigationItemSelectedListener{ menuItem ->
            if(menuItem.toString()=="Rezultat"){
                MainActivity.daLiOtvaraProcenat = true
                val transaction = activity!!.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framePitanje, FragmentPoruka.newInstance())
                transaction.addToBackStack(null)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.commit()
            }else{
                val id = menuItem.itemId
                trenutnoPitanje=menuItem.itemId
                val transaction = activity!!.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framePitanje, FragmentPitanje.newInstance(pitanja[id]))
                transaction.addToBackStack(null)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.commit()
            }

            true

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        viewModel.setInt(-1)
        viewModel.getInt().observe(viewLifecycleOwner,
            Observer<Int> { broj->

                var broj = viewModel.getInt().value
                if(broj==-1){

                }else if (broj == 1) {
                    val obojena = SpannableString(lista.menu.getItem(trenutnoPitanje).title)
                    obojena.setSpan(ForegroundColorSpan(Color.parseColor("#3DDC84")), 0, obojena.length, 0)
                    lista.menu.getItem(trenutnoPitanje).title = obojena
                } else {
                    val obojena = SpannableString(lista.menu.getItem(trenutnoPitanje).title)
                    obojena.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")), 0, obojena.length, 0)
                    lista.menu.getItem(trenutnoPitanje).title = obojena
                }
            }
        )
    }

    private fun predan() : Boolean{
        for(kvizInfo in informacije){
            if(kvizInfo.naziv == nazivOtvorenogKviza){
                if(kvizInfo.predan){
                    return true
                }
            }
        }
        return false
    }

    private fun zaustavljen() : Boolean{
        for(kvizInfo in informacije){
            if(kvizInfo.naziv == nazivOtvorenogKviza){
                if(kvizInfo.zaustavljen){
                    return true
                }
            }
        }
        return false
    }



}