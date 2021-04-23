package ba.etf.rma21.projekat.data.fragmenti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import com.google.android.material.navigation.NavigationView


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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.pokusaj_fragment, container, false)
        lista = view.findViewById(R.id.navigacijaPitanja)
        pitanjeLayout = view.findViewById(R.id.framePitanje)

        for(P in pitanja){
            lista.menu.add(R.id.lista_nav, itemId++,order++,(brojPitanja++).toString())

        }

        lista.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framePitanje, FragmentPitanje.newInstance(pitanja[id]))
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
            true
        })

        return view;
    }
}