package ba.etf.rma21.projekat

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.EditText
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import com.example.cinaeste.view.KvizListAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizoviAdapter: KvizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kvizovi = findViewById(R.id.listaKvizova)
        kvizovi.layoutManager = GridLayoutManager(this, 2)
        kvizoviAdapter = KvizListAdapter(arrayListOf())
        kvizovi.adapter = kvizoviAdapter
        kvizoviAdapter.updateKvizovi(KvizRepository.getAll())
    }
}

