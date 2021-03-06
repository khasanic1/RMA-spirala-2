package ba.etf.rma21.projekat.data.fragmenti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.korisnik
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.odabranaGrupa
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.odabraniPredmet
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.porukaGrupa
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.porukaPredmet
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.getUpisani
import ba.etf.rma21.projekat.data.viewmodel.GrupaViewModel
import ba.etf.rma21.projekat.data.viewmodel.KorisnikViewModel
import ba.etf.rma21.projekat.data.viewmodel.PredmetViewModel

class FragmentPredmeti : Fragment() {
    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private var predmetViewModel: PredmetViewModel = PredmetViewModel()
    private var groupViewModel: GrupaViewModel = GrupaViewModel()
    private var korisnikViewModel: KorisnikViewModel = KorisnikViewModel()
    private lateinit var dugme: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.upis_predmeta_fragment, container, false)

        dugme = view.findViewById(ba.etf.rma21.projekat.R.id.dodajPredmetDugme)
        dugme.isEnabled = false

        val listaGodina = listOf<String>("Odabir Godine", "1", "2", "3", "4", "5")

        spinner1 = view.findViewById(ba.etf.rma21.projekat.R.id.odabirGodina)
        spinner2 = view.findViewById(ba.etf.rma21.projekat.R.id.odabirPredmet)
        spinner3 = view.findViewById(ba.etf.rma21.projekat.R.id.odabirGrupa)

        spinner1.adapter =
            ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaGodina)

        var odabirGodinePozicija: Int
        var odabirPredmetaPozicija: Int
        var odabirGrupePozicija: Int

        if (korisnik.trenutnaGodina != 0) {
            spinner1.setSelection(korisnik.trenutnaGodina)
        }
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                if (listaGodina[position] == "Odabir Godine") {
                    korisnik.trenutnaGodina = 0
                    odabirGodinePozicija = 0
                } else {
                    korisnik.trenutnaGodina = listaGodina[position].toInt()
                    odabirGodinePozicija = position
                }
                dugme.isEnabled = false
                val listaPredmeta = mutableListOf<String>()
                listaPredmeta.add("Odabir Predmeta")
                spinner2.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaPredmeta)




                if (korisnik.trenutnaGodina != 0) {
                    spinner2.isEnabled = true
                    var predmetiSaGodine: MutableList<Predmet> = mutableListOf<Predmet>()
                    for (P in predmetViewModel.getPredmetsByGodina(listaGodina[position].toInt())) {
                        predmetiSaGodine.add(P)
                    }
                    for (upisan in getUpisani()) {
                        for (P in predmetViewModel.getPredmetsByGodina(listaGodina[position].toInt())) {
                            if (upisan.naziv == P.naziv) {
                                predmetiSaGodine.remove(upisan)
                            }
                        }
                    }
                    for (P in predmetiSaGodine) {
                        listaPredmeta.add(P.naziv)
                    }
                    spinner2.adapter = ArrayAdapter(
                        inflater.context,
                        android.R.layout.simple_list_item_1,
                        listaPredmeta
                    )
                    spinner2.setSelection(odabraniPredmet)
                } else {
                    spinner2.isEnabled = false
                }



                spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        dugme.isEnabled = false
                        if (listaPredmeta[position] == "Odabir Predmeta") {
                            odabraniPredmet = 0
                            odabirPredmetaPozicija = 0
                        } else {
                            odabraniPredmet = position;
                            odabirPredmetaPozicija = position
                        }


                        val listaGrupa = mutableListOf<String>()
                        listaGrupa.add("Odabir Grupe")
                        spinner3.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaGrupa)


                        if (odabraniPredmet != 0) {
                            spinner3.isEnabled = true
                            for (P in groupViewModel.getGroupsByPredmet(listaPredmeta[position])) {
                                listaGrupa.add(P.naziv)
                            }
                            spinner3.adapter = ArrayAdapter(
                                inflater.context,
                                android.R.layout.simple_list_item_1,
                                listaGrupa
                            )
                            spinner3.setSelection(odabranaGrupa)
                        } else {
                            spinner3.isEnabled = false
                        }


                        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    if (parent!!.getItemAtPosition(position).equals("Odabir Grupe")) {
                                        dugme.isEnabled = false
                                        odabranaGrupa = 0
                                    } else {
                                        odabirGrupePozicija = position
                                        odabranaGrupa = position
                                        dugme.isEnabled = true
                                        dugme.setOnClickListener() {
                                            porukaPredmet = listaPredmeta[odabirPredmetaPozicija]
                                            porukaGrupa = listaGrupa[odabirGrupePozicija]
                                            korisnikViewModel.dodajUpisanPredmet(
                                                listaGodina[odabirGodinePozicija],
                                                listaPredmeta[odabirPredmetaPozicija],
                                                listaGrupa[odabirGrupePozicija]
                                            )
                                            odabraniPredmet = 0
                                            odabranaGrupa = 0
                                            val transaction =
                                                activity!!.supportFragmentManager.beginTransaction()
                                            transaction.replace(
                                                R.id.container,
                                                FragmentPoruka.newInstance()
                                            )
                                            transaction.addToBackStack(null)
                                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                            transaction.commit()
                                        }
                                    }
                                }
                            }

                    }
                }


            }
        }
        return view;
    }


}