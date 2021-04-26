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
                    "Inženjerska matematika 1",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    2,"RI1-1", 1.5f
                    ),
            Kviz(
                    "Kviz 2",
                    "Inženjerska matematika 1",
                    datum(10,4,2021),
                    datum(20,4,2022),null,
                    2,"RI1-2", null
            ),
            Kviz(
                    "Kviz 3",
                    "Inženjerska fizika 1",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    2,"RI1-1", null
            ),
            Kviz(
                    "Kviz 4",
                    "Inženjerska fizika 1",
                    datum(10,4,2021),
                    datum(20,4,2022),null,
                    2,"RI1-2", null
            ),
            Kviz(
                    "Kviz 5",
                    "Osnove elektrotehnike",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    2,"RI1-1", null
            ),
            Kviz(
                    "Kviz 6",
                    "Osnove elektrotehnike",
                    datum(10,4,2021),
                    datum(20,4,2022),null,
                    2,"RI1-2", null
            ),
            Kviz(
                    "Kviz 7",
                    "Uvod u programiranje",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    2,"RI1-1", null
            ),
            Kviz(
                    "Kviz 8",
                    "Uvod u programiranje",
                    datum(10,4,2021),
                    datum(20,4,2022),null,
                    2,"RI1-2", null
            ),
            Kviz(
                    "Kviz 9",
                    "Logički dizajn",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    2,"RI2-1", null
            ),
            Kviz(
                    "Kviz 10",
                    "Logički dizajn",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    2,"RI2-2", null
            ),
            Kviz(
                    "Kviz 11",
                    "Sistemsko programiranje",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    23,"RI2-1", null
            ),
            Kviz(
                    "Kviz 12",
                    "Sistemsko programiranje",
                    datum(10,4,2022),
                    datum(20,4,2022),
                    null,
                    22,"RI2-2", null
            ),
            Kviz(
                    "Kviz 13",
                    "Razvoj mobilnih aplikacija",
                    datum(10,4,2021),
                    datum(12,4,2021),
                    null,
                    23,"RI2-1", null
            ),
            Kviz(
                    "Kviz 14",
                    "Razvoj mobilnih aplikacija",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    22,"RI2-2", null
            ),
            Kviz(
                    "Kviz 15",
                    "Web tehnologije",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    32,"RI3-1", null
            ),
            Kviz(
                    "Kviz 16",
                    "Web tehnologije",
                    datum(10,4,2021),
                    datum(17,4,2021),
                    null,
                    42,"RI3-2", null
            ),
            Kviz(
                    "Kviz 17",
                    "Računarska grafika",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    32,"RI3-1", null
            ),
            Kviz(
                    "Kviz 18",
                    "Računarska grafika",
                    datum(10,4,2021),
                    datum(17,4,2021),
                    null,
                    42,"RI3-2", null
            ),
            Kviz(
                    "Kviz 19",
                    "Softverski inženjering",
                    datum(10,4,2021),
                    datum(20,4,2022),
                    null,
                    17,"RI3-1", null
            ),
            Kviz(
                    "Kviz 20",
                    "Softverski inženjering",
                    datum(10,4,2022),
                    datum(17,4,2022),
                    null,
                    11,"RI3-2", null
            ),
            Kviz(
                    "Kviz 21",
                    "Računarska vizija",
                    datum(10,4,2021),
                    datum(17,4,2022),
                    null,
                    14,"RI4-1", null
            ),
            Kviz(
                    "Kviz 22",
                    "Računarska vizija",
                    datum(10,4,2022),
                    datum(17,4,2022),
                    null,
                    3,"RI4-2", null
            ),
            Kviz(
                    "Kviz 23",
                    "Mašinsko učenje",
                    datum(10,4,2021),
                    datum(17,4,2022),
                    null,
                    7,"RI4-1", null
            ),
            Kviz(
                    "Kviz 24",
                    "Mašinsko učenje",
                    datum(10,4,2021),
                    datum(19,4,2021),
                    null,
                    60,"RI4-2", null
            ),
            Kviz(
                    "Kviz 25",
                    "Baze podataka",
                    datum(10,4,2021),
                    datum(17,4,2022),
                    null,
                    58,"RI4-1", null
            ),
            Kviz(
                    "Kviz 26",
                    "Baze podataka",
                    datum(10,4,2021),
                    datum(19,4,2021),
                    null,
                    43,"RI4-2", null
            ),
            Kviz(
                    "Kviz 27",
                    "Razvoj igara",
                    datum(10,4,2021),
                    datum(17,4,2022),
                    null,
                    14,"RI5-1", null
            ),
            Kviz(
                    "Kviz 28",
                    "Razvoj igara",
                    datum(10,4,2022),
                    datum(17,4,2022),
                    null,
                    3,"RI5-2", null
            ),
            Kviz(
                    "Kviz 29",
                    "Tehnologije sigurnosti",
                    datum(10,4,2021),
                    datum(17,4,2022),
                    null,
                    7,"RI5-1", null
            ),
            Kviz(
                    "Kviz 30",
                    "Tehnologije sigurnosti",
                    datum(10,4,2021),
                    datum(19,4,2021),
                    null,
                    60,"RI5-2", null
            ),
            Kviz(
                    "Kviz 31",
                    "Data mining",
                    datum(10,4,2021),
                    datum(17,4,2022),
                    null,
                    58,"RI5-1", null
            ),
            Kviz(
                    "Kviz 32",
                    "Data mining",
                    datum(10,4,2021),
                    datum(19,4,2021),
                    null,
                    43,"RI5-2", null
            )



    )
}
