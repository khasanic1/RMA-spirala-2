package ba.etf.rma21.projekat.data.viewmodel

import ba.etf.rma21.projekat.data.repositories.KorisnikRepository

class KorisnikViewModel {
    fun dodajUpisanPredmet(godina: String,predmet: String, grupa: String){
        KorisnikRepository.korisnik.trenutnaGodina = godina.toInt()
        KorisnikRepository.korisnik.predmeti.add(predmet)
        KorisnikRepository.korisnik.grupe.add(grupa)
    }

}