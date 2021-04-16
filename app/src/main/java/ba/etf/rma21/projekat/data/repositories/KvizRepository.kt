package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.kvizovi
import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*

class KvizRepository {

    companion object {
        init {
        }

        fun getMyKvizes(): List<Kviz> {
            return kvizovi();
        }

        fun getAll(): List<Kviz> {
            return kvizovi();
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