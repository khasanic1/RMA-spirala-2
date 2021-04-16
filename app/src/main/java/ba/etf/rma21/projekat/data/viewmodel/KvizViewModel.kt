package ba.etf.rma21.projekat.data.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import java.util.*

class KvizViewModel {

    fun getMyKvizes(): List<Kviz> {
        return sortiraj(KvizRepository.getMyKvizes())
    }

    fun getAll(): List<Kviz> {
        return sortiraj(KvizRepository.getAll())
    }

    fun getDone(): List<Kviz> {
        return sortiraj(KvizRepository.getDone())
    }

    fun getFuture(): List<Kviz> {
        return sortiraj(KvizRepository.getFuture())
    }

    fun getNotTaken(): List<Kviz> {
        return sortiraj(KvizRepository.getNotTaken())
    }

    fun sortiraj(lista : List<Kviz>) : List<Kviz>{
        return lista.sortedBy { it.datumPocetka }
    }
}