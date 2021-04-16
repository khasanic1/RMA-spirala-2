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


class UpisPredmet : AppCompatActivity() {

    private lateinit var kvizovi: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var kvizoviAdapter: KvizAdapter
    private var kvizViewModel = KvizViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upis_predmeta)
    }
}

