package ba.etf.rma21.projekat.data.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.pitanja
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository

class PitanjeViewModel {
    fun getPitanja(nazivKviza:String,nazivPredmeta:String):List<Pitanje>{
        return PitanjeKvizRepository.getPitanja(nazivKviza,nazivPredmeta)
        //return listOf(Pitanje("p1","Tačan odgovor je b", listOf("a","b","c"),1), Pitanje("p2","Tačan odgovor je c", listOf("a","b","c"),2))
    }
}