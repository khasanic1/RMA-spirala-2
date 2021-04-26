package ba.etf.rma21.projekat.data

import android.content.res.Resources
import ba.etf.rma21.projekat.data.models.Korisnik
import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*


fun korisnik(): Korisnik {
    return Korisnik(0,mutableListOf(Kviz(
        "Kviz 2",
        "Inženjerska matematika 1",
        datum(11,4,2021),
        datum(20,4,2022),null,
        2,"RI1-2", null
    )))

}

