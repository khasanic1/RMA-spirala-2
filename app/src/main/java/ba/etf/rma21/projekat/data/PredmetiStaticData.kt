package ba.etf.rma21.projekat.data

import ba.etf.rma21.projekat.data.models.Predmet
fun predmeti(): List<Predmet>{
        return listOf(
                Predmet("Inženjerska matematika 1", 1),
                Predmet("Inženjerska fizika 1", 1),
                Predmet("Osnove elektrotehnike", 1),
                Predmet("Uvod u programiranje", 1),

                Predmet("Logički dizajn", 2),
                Predmet("Sistemsko programiranje", 2),
                Predmet("Razvoj mobilnih aplikacija", 2),

                Predmet("Web tehnologije", 3),
                Predmet("Računarska grafika", 3),
                Predmet("Softverski inženjering", 3),

                Predmet("Računarska vizija", 4),
                Predmet("Mašinsko učenje", 4),
                Predmet("Baze podataka", 4),

                Predmet("Razvoj igara", 5),
                Predmet("Tehnologije sigurnosti", 5),
                Predmet("Data mining", 5)
        )
}
