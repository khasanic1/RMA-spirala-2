package ba.etf.rma21.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajKvizSaNazivom
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf


@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {
    @get:Rule
    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun mojTest1() {

    }

    fun mojTest2() {
        val kvizovi = KvizRepository.getAll()
        onView(withId(R.id.filterKvizova)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
            CoreMatchers.`is`("Svi kvizovi"))).perform(click())
        onView(withId(R.id.filterKvizova)).check(matches(withSpinnerText(containsString("Svi kvizovi"))))


        val pitanja = PitanjeKvizRepository.getPitanja(kvizovi[1].naziv, kvizovi[1].nazivPredmeta)
        var brojac=0
        while(brojac<5){
            onView(withId(R.id.listaKvizova)).perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[1].naziv)),
                    hasDescendant(withText(kvizovi[1].nazivPredmeta))), click()))
            onView(withId(R.id.navigacijaPitanja)).perform(NavigationViewActions.navigateTo(brojac))
            Espresso.onData(anything()).inAdapterView(withId(R.id.odgovoriLista)).atPosition(1).perform(click())
            onView(withId(R.id.tekstPitanja)).check(matches(withText(pitanja[brojac].tekst)))
            onView(withId(R.id.zaustaviKviz)).perform(click())
            brojac++
        }
        onView(withId(R.id.listaKvizova)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(CoreMatchers.allOf(hasDescendant(withText(kvizovi[1].naziv)),
                hasDescendant(withText(kvizovi[1].nazivPredmeta))), click()))
        onView(withId(R.id.predajKviz)).perform(click())
    }
}