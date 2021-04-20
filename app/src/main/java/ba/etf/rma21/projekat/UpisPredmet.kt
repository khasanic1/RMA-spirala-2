package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.text.Selection.setSelection
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.korisnik
import ba.etf.rma21.projekat.data.viewmodel.GrupaViewModel
import ba.etf.rma21.projekat.data.viewmodel.KorisnikViewModel
import ba.etf.rma21.projekat.data.viewmodel.PredmetViewModel
import com.example.cinaeste.view.KvizAdapter


class UpisPredmet : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var kvizoviAdapter: KvizAdapter
    private var predmetViewModel : PredmetViewModel = PredmetViewModel()
    private var groupViewModel : GrupaViewModel = GrupaViewModel()
    private var korisnikViewModel : KorisnikViewModel = KorisnikViewModel()
    private lateinit var dugme : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        /*val binding = UpisPredmetaBinding.inflate(layoutInflater)
        setContentView(binding.root)*/
        super.onCreate(savedInstanceState)
        setContentView(ba.etf.rma21.projekat.R.layout.upis_predmeta)
        dugme = findViewById(ba.etf.rma21.projekat.R.id.dodajPredmetDugme)
        dugme.isEnabled=false
        val listaGodina = listOf<String>("Odabir Godine","1","2","3","4","5")
        spinner1 = findViewById(ba.etf.rma21.projekat.R.id.odabirGodina)
        spinner2 = findViewById(ba.etf.rma21.projekat.R.id.odabirPredmet)
        spinner3 = findViewById(ba.etf.rma21.projekat.R.id.odabirGrupa)
        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaGodina)
        var odabirGodinePozicija = 0
        var odabirPredmetaPozicija = 0
        var odabirGrupePozicija = 0
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent!!.getItemAtPosition(position).equals("Odabir Godine")){
                    dugme.isEnabled=false
                    korisnik.trenutnaGodina=0
                }else{
                    korisnik.trenutnaGodina=listaGodina[position].toInt()
                    dugme.isEnabled=false
                    odabirGodinePozicija=position
                    val listaPredmeta = mutableListOf<String>()
                    listaPredmeta.add("Odabir Predmeta")
                    for(P in predmetViewModel.getPredmetsByGodina(listaGodina[position].toInt())){
                        listaPredmeta.add(P.naziv)
                    }
                    spinner2.adapter = ArrayAdapter(this@UpisPredmet,android.R.layout.simple_list_item_1, listaPredmeta)
                    spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if(parent!!.getItemAtPosition(position).equals("Odabir Predmeta")){
                                dugme.isEnabled=false
                            }else{
                                dugme.isEnabled=false
                                odabirPredmetaPozicija=position
                                val listaGrupa = mutableListOf<String>()
                                listaGrupa.add("Odabir Grupe")
                                for(P in groupViewModel.getGroupsByPredmet(listaPredmeta[position])){
                                    listaGrupa.add(P.naziv)
                                }
                                spinner3.adapter = ArrayAdapter(this@UpisPredmet,android.R.layout.simple_list_item_1, listaGrupa)
                                spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                        if(parent!!.getItemAtPosition(position).equals("Odabir Grupe")){
                                            dugme.isEnabled=false
                                        }else{
                                            odabirGrupePozicija=position
                                            dugme.isEnabled=true
                                            dugme.setOnClickListener(){
                                                korisnikViewModel.dodajUpisanPredmet(listaGodina[odabirGodinePozicija],listaPredmeta[odabirPredmetaPozicija],listaGrupa[odabirGrupePozicija])
                                                val intent = Intent(this@UpisPredmet, MainActivity::class.java)
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

    }
    override fun onResume() {
        super.onResume()
        if(korisnik.trenutnaGodina!=0){

            spinner1.setSelection(korisnik.trenutnaGodina)
        }
        //spinner1.setSelection(arrayAdapter.getPosition("Category 2"));
    }


}

