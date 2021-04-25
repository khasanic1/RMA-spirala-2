package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.korisnik
import ba.etf.rma21.projekat.data.models.Korisnik

class PitanjeRepository {
    companion object{
        var listaOdgovorenihPitanja : MutableList<Int> = mutableListOf()
    }
}