package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.pitanja
import ba.etf.rma21.projekat.data.pitanjaKviz

class PitanjeKvizRepository {
    companion object{
        fun getPitanja(nazivKviza:String,nazivPredmeta:String):List<Pitanje>{

            var pitanjaPoKvizu = mutableListOf<Pitanje>()
            for(pk in pitanjaKviz()){
                if(nazivKviza==pk.kviz){
                    for(p in pitanja()){
                        if(p.naziv==pk.naziv){
                            pitanjaPoKvizu.add(p)
                        }
                    }
                }
            }
            return pitanjaPoKvizu
        }
    }
}