package ba.etf.rma21.projekat.data

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Pitanje

fun pitanja(): List<Pitanje>{
        return listOf(

                Pitanje("Gravitacija", "Šta je gravitacija?", listOf("Sila uzajamnog privlačenja između masa", "Auto", "Jelo"), 1),

                Pitanje("Newton", "Ko je Isaac Newton?", listOf("Fudbaler", "Fizičar i matematičar", "Gitarista"), 2)
        )
}
