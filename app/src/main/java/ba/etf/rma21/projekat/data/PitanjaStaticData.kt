package ba.etf.rma21.projekat.data

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Pitanje

fun pitanja(): List<Pitanje>{
        return listOf(

                Pitanje("Champagne", "What is it called when a bottle of champagne is open with a sword?", listOf("Sabering", "Carpenting", "Skydiving"), 0),
                Pitanje("Pillow", "Is pillow fighting a recognized sport in Canada ", listOf("Yes", "No", "About to be"), 0),
                Pitanje("President", "What was president Harry S Truman's middle name?", listOf("S", "F", "H"), 0) ,
                Pitanje("Singer", "Who composed the music for Sonic the Hedgehog 3?", listOf("Bakir Izetbegovic", "Alija", "Michael Jackson"), 2),
                Pitanje("Planet", "Whats the name of the seventh planet from the sun?", listOf("Mercury", "Uranus", "Neptune"), 1),
                Pitanje("Goat", "What is a baby goat called?", listOf("A kid", "An elephant", "Little fur ball"), 0),
                Pitanje("Eurovision", "Who won the Eurovision Song Contest 2005", listOf("Australia", "Bosnia and Herzegovina", "Ukraine"), 2),
                Pitanje("Marathon", "What is the criteria for an ultra marathon?", listOf("Any distance more than a marathon", "100km", "150m"), 0),
                Pitanje("River", "What is the world's longest river?", listOf("Neretva", "Amazon", "Nile"), 2),
                Pitanje("China", "Who was the first Western explorer to reach China?", listOf("Lil Wayne", "Marco Polo", "Kanye"), 1)

        )
}
