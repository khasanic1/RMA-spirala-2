package ba.etf.rma21.projekat.data.fragmenti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.porukaGrupa
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.porukaPredmet

class FragmentPoruka : Fragment() {
    companion object {
        fun newInstance(): FragmentPoruka = FragmentPoruka()
    }
    private lateinit var poruka : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.poruka_fragment, container, false)
        poruka = view.findViewById(R.id.tvPoruka)
        var prikaz = "Uspješno ste upisani u grupu " + porukaGrupa + " predmeta " + porukaPredmet
        poruka.setText(prikaz)
        return view;
    }
}