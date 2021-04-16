package ba.etf.rma21.projekat.data.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository

class KvizViewModel {
    fun getMyKvizes(): List<Kviz> {
        return KvizRepository.getMyKvizes()
    }

    fun getAll(): List<Kviz> {
        return KvizRepository.getAll()
    }

    fun getDone(): List<Kviz> {
        return KvizRepository.getDone()
    }

    fun getFuture(): List<Kviz> {
        return KvizRepository.getFuture()
    }

    fun getNotTaken(): List<Kviz> {
        return KvizRepository.getNotTaken()
    }
}