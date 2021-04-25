package ba.etf.rma21.projekat.data.viewmodel

import android.util.Log
import ba.etf.rma21.projekat.data.korisnik
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.KvizRepository.Companion.getAll
import ba.etf.rma21.projekat.data.repositories.PredmetRepository.Companion.getUpisani

class KorisnikViewModel {
    fun dodajUpisanPredmet(godina: String,predmet: String, grupa: String){
        KorisnikRepository.korisnik.trenutnaGodina = godina.toInt()
        for(kviz in getAll()){
            if(kviz.nazivPredmeta == predmet && kviz.nazivGrupe == grupa){
                KorisnikRepository.korisnik.upisaniKvizovi.add(kviz)
            }
        }

    }
}