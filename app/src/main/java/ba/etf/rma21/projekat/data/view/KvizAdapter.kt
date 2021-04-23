package com.example.cinaeste.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class KvizAdapter(
    private var kvizovi: List<Kviz>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<KvizAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_kviz, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = kvizovi.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val formatter = SimpleDateFormat("dd.MM.yyyy")
        if (kvizovi[position].datumRada != null) {
            holder.oznaka.setImageResource(R.drawable.plava)
            holder.datum.text = formatter.format(kvizovi[position].datumRada);
        } else if (kvizovi[position].datumPocetka.before(Calendar.getInstance().time) && kvizovi[position].datumKraj.after(
                Calendar.getInstance().time
            ) && kvizovi[position].datumRada == null
        ) {
            holder.oznaka.setImageResource(R.drawable.zelena)
            holder.datum.text = formatter.format(kvizovi[position].datumKraj);
        } else if (kvizovi[position].datumPocetka.after(Calendar.getInstance().time) && kvizovi[position].datumKraj.after(
                Calendar.getInstance().time
            ) && kvizovi[position].datumRada == null
        ) {
            holder.oznaka.setImageResource(R.drawable.zuta)
            holder.datum.text = formatter.format(kvizovi[position].datumPocetka);
        } else if (kvizovi[position].datumKraj.before(Calendar.getInstance().time) && kvizovi[position].datumRada == null) {
            holder.oznaka.setImageResource(R.drawable.crvena)
            holder.datum.text = formatter.format(kvizovi[position].datumKraj);
        }
        holder.predmet.text = kvizovi[position].nazivPredmeta;
        holder.ime_kviza.text = kvizovi[position].naziv;
        holder.vrijeme.text = kvizovi[position].trajanje.toString() + " min";
        if (kvizovi[position].osvojeniBodovi == null) {
            holder.bodovi.text = "";
        } else {
            holder.bodovi.text = kvizovi[position].osvojeniBodovi.toString();
        }


    }

    fun updateKvizovi(kvizovi: List<Kviz>) {
        this.kvizovi = kvizovi
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val oznaka: ImageView = itemView.findViewById(R.id.oznaka)
        val predmet: TextView = itemView.findViewById(R.id.predmet)
        val ime_kviza: TextView = itemView.findViewById(R.id.ime_kviza)
        val datum: TextView = itemView.findViewById(R.id.datum)
        val vrijeme: TextView = itemView.findViewById(R.id.vrijeme)
        val bodovi: TextView = itemView.findViewById(R.id.bodovi)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
