package ba.etf.rma21.projekat.data.models

data class Korisnik (
    var trenutnaGodina : Int,
    var upisaniKvizovi : MutableList<Kviz>
)