package ba.etf.rma21.projekat.data.fragmenti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.MainActivity.Companion.trenutniKvizInfo
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajProcenat
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.porukaGrupa
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.porukaPredmet
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.procenat
import java.lang.String.format

class FragmentPoruka : Fragment() {
    companion object {
        fun newInstance(): FragmentPoruka = FragmentPoruka()
    }
    private lateinit var poruka : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.poruka_fragment, container, false)
        poruka = view.findViewById(R.id.tvPoruka)
        var prikaz : String
        if(MainActivity.daLiJePredan == true){
            prikaz = "Završili ste kviz "+ MainActivity.poruka+" sa tačnosti "+ procenat
            MainActivity.daLiJePredan = false
        }else{
            prikaz = "Uspješno ste upisani u grupu " + porukaGrupa + " predmeta " + porukaPredmet+"!"
        }
        poruka = view.findViewById(R.id.tvPoruka)
        poruka.setText(prikaz)
        return view
    }
}