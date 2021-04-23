package ba.etf.rma21.projekat.data.fragmenti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje

class FragmentPitanje(pitanje:Pitanje): Fragment() {

    companion object{
        fun newInstance(pitanje:Pitanje) : FragmentPitanje = FragmentPitanje(pitanje)
    }
    private var pitanje=pitanje
    private lateinit var tekst : TextView
    private lateinit var listaOdgovora : ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.pitanje_fragment, container, false)
        tekst = view.findViewById(R.id.tekstPitanja)
        tekst.text = pitanje.naziv
        listaOdgovora = view.findViewById(R.id.odgovoriLista)
        listaOdgovora.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, pitanje.opcije)
        return view;
    }

}