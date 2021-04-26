package ba.etf.rma21.projekat.data.fragmenti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.MainActivity.Companion.nazivOtvorenogKviza
import ba.etf.rma21.projekat.MainActivity.Companion.poruka
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.crveniUpisan
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajKvizSaNazivom
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.getAll
import ba.etf.rma21.projekat.data.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.data.viewmodel.PitanjeViewModel
import com.example.cinaeste.view.KvizAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class FragmentKvizovi : Fragment(), KvizAdapter.OnItemClickListener {
    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }

    private lateinit var kvizovi: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var kvizoviAdapter: KvizAdapter
    private var kvizViewModel = KvizViewModel()
    private var pitanjeViewModel = PitanjeViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.kvizovi_fragment, container, false)
        kvizovi = view.findViewById(R.id.listaKvizova)
        kvizovi.layoutManager = GridLayoutManager(activity, 2)
        kvizoviAdapter = KvizAdapter(arrayListOf(), this)
        kvizovi.adapter = kvizoviAdapter
        kvizoviAdapter.updateKvizovi(kvizViewModel.getAll())

        spinner = view.findViewById(R.id.filterKvizova)
        val opcije = listOf(
            "Svi moji kvizovi",
            "Svi kvizovi",
            "Urađeni kvizovi",
            "Budući kvizovi",
            "Prošli kvizovi"
        )
        spinner.adapter = ArrayAdapter(kvizovi.context, android.R.layout.simple_list_item_1, opcije)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getMyKvizes())
                } else if (position == 1) {
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getAll())
                } else if (position == 2) {
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getDone())
                } else if (position == 3) {
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getFuture())
                } else if (position == 4) {
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getNotTaken())
                }
            }
        }

        return view
    }

    override fun onItemClick(position: Int) {
        var selekcijaSpinnera: Int = spinner.selectedItemPosition
        var listaKvizova = listOf<Kviz>()
        if (selekcijaSpinnera == 0) {
            listaKvizova = kvizViewModel.getMyKvizes()
        } else if (selekcijaSpinnera == 1) {
            listaKvizova = kvizViewModel.getAll()
        } else if (selekcijaSpinnera == 2) {
            listaKvizova = kvizViewModel.getDone()
        } else if (selekcijaSpinnera == 3) {
            listaKvizova = kvizViewModel.getFuture()
        } else if (selekcijaSpinnera == 4) {
            listaKvizova = kvizViewModel.getNotTaken()
        }


        MainActivity.nazivOtvorenogKviza = listaKvizova[position].naziv
        var smijeSeOtvoriti = false
        crveniUpisan = false
        for (k in kvizViewModel.getAll()) {
            if (k.naziv == MainActivity.nazivOtvorenogKviza) {
                if (k.datumRada != null || (k.datumRada == null && k.datumPocetka.before(Calendar.getInstance().time) && k.datumKraj.after(
                        Calendar.getInstance().time
                    ))
                ) {
                    //zeleni i plavi
                    for (uk in KorisnikRepository.korisnik.upisaniKvizovi) {
                        if (uk.naziv == nazivOtvorenogKviza) {
                            //zeleni i plavi upisan
                            smijeSeOtvoriti = true
                        }
                    }
                } else {
                    if (k.datumRada == null && k.datumPocetka.after(Calendar.getInstance().time)) {
                        //zuti
                        for (uk in KorisnikRepository.korisnik.upisaniKvizovi) {
                            if (uk.naziv == nazivOtvorenogKviza) {
                                smijeSeOtvoriti = false
                            }
                        }
                    } else if (k.datumRada == null && k.datumPocetka.before(Calendar.getInstance().time) && k.datumKraj.before(
                            Calendar.getInstance().time
                        )
                    ) {
                        //crveni
                        for (uk in KorisnikRepository.korisnik.upisaniKvizovi) {
                            if (uk.naziv == nazivOtvorenogKviza) {
                                //crveni upisan
                                smijeSeOtvoriti = true
                                crveniUpisan = true
                            }
                        }

                    }
                }
            }
        }
        if (smijeSeOtvoriti) {
            if (crveniUpisan) {
                val transaction = activity!!.supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    FragmentPokusaj.newInstance(
                        pitanjeViewModel.getPitanja(
                            "ProsoKvizPitanja",
                            listaKvizova[position].nazivPredmeta
                        )
                    )
                )
                transaction.addToBackStack(null)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.commit()
            } else {

                val transaction = activity!!.supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    FragmentPokusaj.newInstance(
                        pitanjeViewModel.getPitanja(
                            listaKvizova[position].naziv,
                            listaKvizova[position].nazivPredmeta
                        )
                    )
                )
                transaction.addToBackStack(null)
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.commit()
            }

            MainActivity.trenutniKvizInfo.naziv = listaKvizova[position].naziv
            MainActivity.trenutniKvizInfo.nazivPredmeta = listaKvizova[position].nazivPredmeta

            var kviz = dajKvizSaNazivom(MainActivity.trenutniKvizInfo.naziv)
            val navBar: BottomNavigationView = activity!!.findViewById(R.id.bottomNav)
            if (kviz.predan || crveniUpisan == true) {

                poruka = MainActivity.trenutniKvizInfo.naziv
                MainActivity.trenutniKvizInfo.predan = true
                MainActivity.trenutniKvizInfo.zaustavljen = false
                navBar.getMenu().findItem(R.id.kvizovi).setVisible(true)
                navBar.getMenu().findItem(R.id.predmeti).setVisible(true)
                navBar.getMenu().findItem(R.id.predajKviz).setVisible(false)
                navBar.getMenu().findItem(R.id.zaustaviKviz).setVisible(false)
            } else if (kviz.zaustavljen) {
                poruka = MainActivity.trenutniKvizInfo.naziv
                MainActivity.trenutniKvizInfo.predan = false
                MainActivity.trenutniKvizInfo.zaustavljen = true
                navBar.getMenu().findItem(R.id.kvizovi).setVisible(false)
                navBar.getMenu().findItem(R.id.predmeti).setVisible(false)
                navBar.getMenu().findItem(R.id.predajKviz).setVisible(true)
                navBar.getMenu().findItem(R.id.zaustaviKviz).setVisible(true)
            } else {
                MainActivity.trenutniKvizInfo.predan = false
                MainActivity.trenutniKvizInfo.zaustavljen = false
                navBar.getMenu().findItem(R.id.kvizovi).setVisible(false)
                navBar.getMenu().findItem(R.id.predmeti).setVisible(false)
                navBar.getMenu().findItem(R.id.predajKviz).setVisible(true)
                navBar.getMenu().findItem(R.id.zaustaviKviz).setVisible(true)
            }
        } else {

        }

    }


}