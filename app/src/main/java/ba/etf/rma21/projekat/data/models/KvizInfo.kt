package ba.etf.rma21.projekat.data.models

class KvizInfo(var naziv :String,
               var nazivPredmeta: String,
               var predan : Boolean,
               var zaustavljen : Boolean,
               var listaOdgovora : MutableList<Pair<String, Int>>
)