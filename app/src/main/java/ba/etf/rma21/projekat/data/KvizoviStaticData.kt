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
            ),
            Kviz(
                    "Kviz 5",
                    "RMA",
                    datum(10,4,2021),
                    datum(13,4,2021),
                    null,
                    15,"RMA2-2", null
            ),
            Kviz(
                    "Kviz 6",
                    "ORM",
                    datum(18,5,2021),
                    datum(20,5,2021),
                    null,
                    45,"ORM4", null
            ),
            Kviz(
                    "Kviz 7",
                    "RA",
                    datum(20,3,2021),
                    datum(25,5,2021),
                    datum(15,4,2021),
                    30,"RA1", 10f
            ),
            Kviz(
                    "Kviz 8",
                    "OOAD",
                    datum(10,4,2021),
                    datum(14,5,2021),
                    null,
                    15,"OOAD5", null
            ),
            Kviz(
                    "Kviz 9",
                    "IM1",
                    datum(28,5,2021),
                    datum(29,5,2021),
                    null,
                    25,"IM1-1", null
            ),
            Kviz(
                    "Kviz 10",
                    "IM2",
                    datum(16,4,2021),
                    datum(16,5,2021),
                    datum(16,5,2021),
                    15,"IM2-3", 7.6f
            ),
            Kviz(
                    "Kviz 11",
                    "IM2",
                    datum(11,4,2021),
                    datum(11,4,2021),
                    null,
                    15,"IM2-4", null
            )


    )
}
