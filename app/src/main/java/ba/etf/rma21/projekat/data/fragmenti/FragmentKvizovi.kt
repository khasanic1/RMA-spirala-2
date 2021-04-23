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
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.viewmodel.KvizViewModel
import ba.etf.rma21.projekat.data.viewmodel.PitanjeViewModel
import com.example.cinaeste.view.KvizAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentKvizovi : Fragment(), KvizAdapter.OnItemClickListener {
    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }

    private var godina : Int? = null
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
        var view =  inflater.inflate(R.layout.kvizovi_fragment, container, false)
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
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==0){
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getMyKvizes())
                }else if(position==1){
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getAll())
                }else if(position==2){
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getDone())
                }else if(position==3){
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getFuture())
                }else if(position==4){
                    kvizoviAdapter.updateKvizovi(kvizViewModel.getNotTaken())
                }
            }
        }

        return view;
    }

    override fun onItemClick(position: Int) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentPokusaj.newInstance(pitanjeViewModel.getPitanja("asd","asd")))
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
        val navBar: BottomNavigationView = activity!!.findViewById(R.id.bottomNav)
        navBar.getMenu().findItem(R.id.kvizovi).setVisible(false);
        navBar.getMenu().findItem(R.id.predmeti).setVisible(false);
        navBar.getMenu().findItem(R.id.predajKviz).setVisible(true);
        navBar.getMenu().findItem(R.id.zaustaviKviz).setVisible(true);
    }


}