package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.grupe
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.predmeti

class PredmetRepository {
    companion object {
        fun getUpisani(): List<Predmet> {
            var korisnik_predmeti : List<String> = KorisnikRepository.korisnik.predmeti
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