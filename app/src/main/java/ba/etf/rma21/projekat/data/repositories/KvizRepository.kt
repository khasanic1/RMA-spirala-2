package ba.etf.rma21.projekat.data.repositories

import android.util.Log
import ba.etf.rma21.projekat.data.datum
import ba.etf.rma21.projekat.data.kvizovi
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.informacije
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.korisnik
import java.util.*

class KvizRepository {

    companion object {
        init {
        }
        fun getMyKvizes(): List<Kviz> {
            var lista = mutableListOf<Kviz>()
            var brojac = 0
            var vecRadjen=false

            var predan=false
            var zaustavljen=false
            for(kviz in korisnik.upisaniKvizovi){
                vecRadjen=false
                predan=false
                zaustavljen=false
                for(kvizInfo in informacije){
                    if(kviz.naziv==kvizInfo.naziv){
                        vecRadjen=true
                        if(kvizInfo.predan==true){
                            predan=true
                        }else{
                            zaustavljen=true
                        }
                        break
                    }
                }
                if(vecRadjen==true){
                    if(predan){
                        var novi = kviz

                        novi.datumRada= datum(13,4,2021)
                        novi.osvojeniBodovi=42069f
                        lista.add(novi)
                    }else{

                        lista.add(kviz)
                    }
                }else{
                    lista.add(kviz)
                }
            }
            return lista
        }

        fun getAll(): List<Kviz> {
            var lista = mutableListOf<Kviz>()
            var imaUmojim = false
            for(k in kvizovi()){
                var imaUmojim = false
                var temp = Kviz("","", datum(1,1,1), datum(1,1,1), datum(1,1,1),1,"",1f)
                for(moji in getMyKvizes()){
                    if(moji.naziv==k.naziv){
                        imaUmojim=true
                        temp = moji
                        break
                    }
                }
                if(imaUmojim){
                    lista.add(temp)
                }else{
                    lista.add(k)
                }
            }
            return lista
        }

        fun getDone(): List<Kviz> {
            var lista = mutableListOf<Kviz>()
            for(K in getMyKvizes()){
                if(K.datumRada!=null){
                    lista.add(K)
                }
            }
            return lista
        }

        fun getFuture(): List<Kviz> {
            var lista = mutableListOf<Kviz>()
            for(K in getMyKvizes()){
                if(K.datumRada==null && K.datumPocetka.after(Calendar.getInstance().time)){
                    lista.add(K)
                }
            }
            return lista
        }

        fun getNotTaken(): List<Kviz> {
            var lista = mutableListOf<Kviz>()
            for(K in getMyKvizes()){
                if(K.datumRada==null && K.datumPocetka.before(Calendar.getInstance().time) && K.datumKraj.before(Calendar.getInstance().time)){
                            lista.add(K)
                }
            }
            return lista
        }
    }
}