package ba.etf.rma21.projekat.data.viewmodel

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.pitanja
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository

class PitanjeViewModel {
    fun getPitanja(nazivKviza:String,nazivPredmeta:String):List<Pitanje>{
        return PitanjeKvizRepository.getPitanja(nazivKviza,nazivPredmeta)
    }
}