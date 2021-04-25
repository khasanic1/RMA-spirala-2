package ba.etf.rma21.projekat.data.fragmenti

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.MainActivity.Companion.trenutniKvizInfo
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.KvizInfo
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeRepository.Companion.listaOdgovorenihPitanja
import ba.etf.rma21.projekat.data.viewmodel.SharedViewModel


class FragmentPitanje(pitanje: Pitanje) : Fragment() {

    companion object {
        fun newInstance(pitanje: Pitanje): FragmentPitanje = FragmentPitanje(pitanje)
    }

    private var pitanje = pitanje
    private lateinit var tekst: TextView
    private lateinit var listaOdgovora: ListView
    private lateinit var viewModel : SharedViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.pitanje_fragment, container, false)

        viewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        tekst = view.findViewById(R.id.tekstPitanja)
        tekst.text = pitanje.tekst
        listaOdgovora = view.findViewById(R.id.odgovoriLista)
        listaOdgovora.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, pitanje.opcije)

        var kvizPoredjenja : KvizInfo = KvizInfo("","",false, false,mutableListOf())
        for(Kviz in KorisnikRepository.informacije){
            if(Kviz.naziv==MainActivity.nazivOtvorenogKviza){
                kvizPoredjenja=Kviz
            }
        }

        var pozicijaOdgovora = 0
        while(pozicijaOdgovora < kvizPoredjenja.listaOdgovora.size){
            if(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].first==pitanje.naziv){
                break
            }
            pozicijaOdgovora++
        }
        if(kvizPoredjenja.naziv!="" && kvizPoredjenja.predan==true){



            Log.d("keno","uslo kad je predan pitanje " + pozicijaOdgovora.toString())
            if(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second != 0){

                Log.d("keno","uslo kad je predan pitanje odgovoren")
                if((kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second)-1 == pitanje.tacan){
                    listaOdgovora.post{ (listaOdgovora.get(pitanje.tacan) as TextView).setTextColor(Color.parseColor("#3DDC84")) }
                }else{
                    //Log.d("keno","tacan je: "+listaOdgovora.get(pitanje.tacan) + " a stavljen je: "
                           // +(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second-1).toString())
                    listaOdgovora.post{(listaOdgovora.get(pitanje.tacan)  as TextView).setTextColor(Color.parseColor("#3DDC84"))}
                    listaOdgovora.post{(listaOdgovora.get(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second-1) as TextView)
                        .setTextColor(Color.parseColor("#DB4F3D"))}
                }
                listaOdgovora.setEnabled(false);
            }else{

                Log.d("keno","uslo kad je predan pitanje nije odgovoren")
                listaOdgovora.setEnabled(false);
            }

        }else if(kvizPoredjenja.naziv!="" && kvizPoredjenja.zaustavljen==true){


            Log.d("keno","uslo kad je zaustavljen pitanje")

            if(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second != 0){
                if((kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second)-1 == pitanje.tacan){
                    listaOdgovora.post{ (listaOdgovora.get(pitanje.tacan) as TextView).setTextColor(Color.parseColor("#3DDC84")) }
                }else{
                    //Log.d("keno","tacan je: "+listaOdgovora.get(pitanje.tacan) + " a stavljen je: "
                    // +(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second-1).toString())
                    listaOdgovora.post{(listaOdgovora.get(pitanje.tacan)  as TextView).setTextColor(Color.parseColor("#3DDC84"))}
                    listaOdgovora.post{(listaOdgovora.get(kvizPoredjenja.listaOdgovora[pozicijaOdgovora].second-1) as TextView)
                        .setTextColor(Color.parseColor("#DB4F3D"))}
                }
                listaOdgovora.setEnabled(false);
            }else{

            }

        }else{
            pozicijaOdgovora=0
            while(pozicijaOdgovora < trenutniKvizInfo.listaOdgovora.size){
                if(trenutniKvizInfo.listaOdgovora[pozicijaOdgovora].first==pitanje.naziv){
                    break
                }
                pozicijaOdgovora++
            }
            Log.d("keno", "pozicija: " +pozicijaOdgovora.toString())
            Log.d("keno","uslo kad je prvi put otvoren pitanje")
            if(trenutniKvizInfo.listaOdgovora[pozicijaOdgovora].second != 0){
                if((trenutniKvizInfo.listaOdgovora[pozicijaOdgovora].second)-1 == pitanje.tacan){
                    listaOdgovora.post{ (listaOdgovora.get(pitanje.tacan) as TextView).setTextColor(Color.parseColor("#3DDC84")) }
                }else{
                    listaOdgovora.post{(listaOdgovora.get(pitanje.tacan)  as TextView).setTextColor(Color.parseColor("#3DDC84"))}
                    listaOdgovora.post{(listaOdgovora.get(trenutniKvizInfo.listaOdgovora[pozicijaOdgovora].second-1) as TextView)
                        .setTextColor(Color.parseColor("#DB4F3D"))}
                }
                listaOdgovora.setEnabled(false);
            }else{

            }
        }
        listaOdgovora.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id ->
            Log.d("keno1", (position+1).toString())
            MainActivity.trenutniKvizInfo.listaOdgovora[pozicijaOdgovora] = Pair(pitanje.naziv, position+1)
            if(position==pitanje.tacan){
                (view as TextView).setTextColor(Color.parseColor("#3DDC84"))
                viewModel.setInt(1)
                viewModel.getInt()
            }else{
                (view as TextView).setTextColor(Color.parseColor("#DB4F3D"))
                (listaOdgovora.get(pitanje.tacan) as TextView).setTextColor(Color.parseColor("#3DDC84"))
                viewModel.setInt(0)
                viewModel.getInt()
            }
            listaOdgovora.setEnabled(false);
        }
        return view;
    }

}