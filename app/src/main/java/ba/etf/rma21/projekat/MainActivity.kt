package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.data.viewmodel.KvizViewModel
import com.example.cinaeste.view.KvizAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var kvizoviAdapter: KvizAdapter
    private lateinit var upisDugme: FloatingActionButton
    private var kvizViewModel = KvizViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kvizovi = findViewById(R.id.listaKvizova)
        kvizovi.layoutManager = GridLayoutManager(this, 2)
        kvizoviAdapter = KvizAdapter(arrayListOf())
        kvizovi.adapter = kvizoviAdapter
        kvizoviAdapter.updateKvizovi(kvizViewModel.getAll())

        upisDugme = findViewById(R.id.upisDugme)
        upisDugme.setOnClickListener{
            val intent = Intent(this, UpisPredmet::class.java)
            startActivity(intent)
        }
        spinner = findViewById(R.id.filterKvizova)
        val opcije = listOf("Svi moji kvizovi", "Svi kvizovi", "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcije)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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
    }

    override fun onResume() {
        super.onResume()
        kvizoviAdapter.updateKvizovi(kvizViewModel.getMyKvizes())
    }

}

