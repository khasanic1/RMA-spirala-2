package ba.etf.rma21.projekat.data

import android.content.res.Resources
import ba.etf.rma21.projekat.data.models.Kviz
import java.util.*

fun datum(dan : Int, mjesec : Int, godina : Int) : Date{
    return Calendar.getInstance().run{
        set(godina, mjesec-1, dan)
        time
    }
}

fun kvizovi(): List<Kviz> {
    return listOf(
            Kviz(
                    "Kviz 1",
                    "RMA",
                    datum(10,4,2021),
                    datum(20,4,2021),
                    datum(13,4,2021),
                    2,"RMA1-2", 1.5f
                    ),
            Kviz(
                    "Kviz 2",
                    "ORM",
                    datum(10,4,2021),
                    datum(20,4,2021),
                    null,
                    5,"ORM7", null
            ),
            Kviz(
                    "Kviz 3",
                    "RA",
                    datum(20,4,2021),
                    datum(25,4,2021),
                    null,
                    30,"RA4", null
            ),
            Kviz(
                    "Kviz 4",
                    "OOAD",
                    datum(10,4,2021),
                    datum(14,4,2021),
                    null,
                    15,"OOAD1", null
            )

    )
}
