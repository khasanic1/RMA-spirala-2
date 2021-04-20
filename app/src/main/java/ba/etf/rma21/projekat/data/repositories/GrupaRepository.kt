package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.grupe
import ba.etf.rma21.projekat.data.models.Grupa

class GrupaRepository {
    companion object {
        init {
        }
        fun getGroupsByPredmet(nazivPredmeta: String): List<Grupa> {
            var lista = mutableListOf<Grupa>()
            for(G in grupe()){
                if(G.nazivPredmeta==nazivPredmeta){
                    lista.add(G)
                }
            }
            return lista
        }
    }
}