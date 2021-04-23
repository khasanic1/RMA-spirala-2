package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.korisnik
import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Predmet

class KorisnikRepository {
    companion object{
        var korisnik : Korisnik = korisnik()
        var odabraniPredmet=0
        var odabranaGrupa=0
        var porukaPredmet=""
        var porukaGrupa=""
    }
}