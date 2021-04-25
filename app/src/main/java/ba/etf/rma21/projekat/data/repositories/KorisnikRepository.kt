package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.data.korisnik
import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.KvizInfo
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.models.Predmet

class KorisnikRepository {
    companion object{
        var korisnik : Korisnik = korisnik()
        var informacije : MutableList<KvizInfo> = mutableListOf()
        var odabraniPredmet=0
        var odabranaGrupa=0
        var porukaPredmet=""
        var porukaGrupa=""
        var procenat = 0f

        fun dajProcenat(naziv : String, pitanja : List<Pitanje>){
            var kviz = dajKvizSaNazivom(naziv)
            var brojTacnih=0
            var brojac=0
            while(brojac<pitanja.size){
                if(kviz.listaOdgovora[brojac].second-1==pitanja[brojac].tacan){
                    brojTacnih++
                }
                brojac++
            }
            procenat= (brojTacnih.toFloat()/pitanja.size)*100
        }

        fun dajKvizSaNazivom(naziv:String) : KvizInfo{
            for(Kviz in informacije){
                if(Kviz.naziv== naziv){
                    return Kviz
                }
            }
            return KvizInfo("","",false, false,mutableListOf())
        }
    }


}