package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.grupe
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.predmeti
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.korisnik

class PredmetRepository {
    companion object {
        fun getUpisani(): List<Predmet> {
            var korisnik_predmeti : MutableList<String> = mutableListOf()
            // TODO: 4/25/2021 mozda imaju 2 kviza sa istim predmetom razlicitom grupom
            for(kviz in korisnik.upisaniKvizovi){
                korisnik_predmeti.add(kviz.nazivPredmeta)
            }
            var listaPredmeta = mutableListOf<Predmet>()
            for(P in predmeti()){
                for(KP in korisnik_predmeti){
                    if(KP==P.naziv){
                        listaPredmeta.add(P)
                    }
                }
            }
            return listaPredmeta
        }

        fun getAll(): List<Predmet> {
            return predmeti()
        }

        fun getPredmetsByGodina(godina:Int) : List<Predmet>{
            var lista = mutableListOf<Predmet>()
            for(P in predmeti()){
                if(P.godina==godina){
                    lista.add(P)
                }
            }
            return lista
        }

    }

}