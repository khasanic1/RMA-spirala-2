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
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository.Companion.dajKvizSaNazivom
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers
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

        onView(withId(R.id.filterKvizova)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Svi moji kvizovi"))).perform(click())

        onView(withId(R.id.predmeti)).perform(click())
        onView(withId(R.id.odabirGodina)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("2"))).perform(click())
        onView(withId(R.id.odabirPredmet)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Razvoj mobilnih aplikacija"))).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("RI2-1"))).perform(click())
        onView(withId(R.id.dodajPredmetDugme)).perform(click())
        onView(withSubstring("Uspješno ste upisani u grupu"))
        onView(withId(R.id.kvizovi)).perform(click())
        onView(withId(R.id.filterKvizova)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Prošli kvizovi"))).perform(click())
        onView(withId(R.id.listaKvizova)).check(UtilTestClass.hasItemCount(KvizRepository.getNotTaken().size))
        assertThat(1, `is`(Matchers.`is`(KvizRepository.getNotTaken().size)))

        onView(withId(R.id.predmeti)).perform(click())
        onView(withId(R.id.odabirGodina)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("3"))).perform(click())
        onView(withId(R.id.odabirPredmet)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Web tehnologije"))).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("RI3-2"))).perform(click())
        onView(withId(R.id.dodajPredmetDugme)).perform(click())
        onView(withSubstring("Uspješno ste upisani u grupu"))
        onView(withId(R.id.kvizovi)).perform(click())
        onView(withId(R.id.filterKvizova)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Prošli kvizovi"))).perform(click())
        onView(withId(R.id.listaKvizova)).check(UtilTestClass.hasItemCount(KvizRepository.getNotTaken().size))
        assertThat(2, `is`(Matchers.`is`(KvizRepository.getNotTaken().size)))

        onView(withId(R.id.predmeti)).perform(click())
        onView(withId(R.id.odabirGodina)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("3"))).perform(click())
        onView(withId(R.id.odabirPredmet)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Računarska grafika"))).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("RI3-2"))).perform(click())
        onView(withId(R.id.dodajPredmetDugme)).perform(click())
        onView(withSubstring("Uspješno ste upisani u grupu"))
        onView(withId(R.id.kvizovi)).perform(click())
        onView(withId(R.id.filterKvizova)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Prošli kvizovi"))).perform(click())
        onView(withId(R.id.listaKvizova)).check(UtilTestClass.hasItemCount(KvizRepository.getNotTaken().size))
        assertThat(3, `is`(Matchers.`is`(KvizRepository.getNotTaken().size)))

        onView(withId(R.id.kvizovi)).perform(click())
        onView(withId(R.id.filterKvizova)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Svi moji kvizovi"))).perform(click())
        onView(withId(R.id.listaKvizova)).check(UtilTestClass.hasItemCount(KvizRepository.getMyKvizes().size))
        assertThat(4, `is`(Matchers.`is`(KvizRepository.getMyKvizes().size)))



    }

    @Test
    fun mojTest2() {
        val kvizovi = KvizRepository.getAll()
        onView(withId(R.id.filterKvizova)).perform(click())
        Espresso.onData(allOf(`is`(instanceOf(String::class.java)), `is`("Svi kvizovi"))).perform(click())
        onView(withId(R.id.filterKvizova)).check(matches(withSpinnerText(containsString("Svi kvizovi"))))
        val pitanja = PitanjeKvizRepository.getPitanja(kvizovi[1].naziv, kvizovi[1].nazivPredmeta)
        var brojac=0
        while(brojac<5){
            onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(allOf(hasDescendant(withText(kvizovi[1].naziv)), hasDescendant(withText(kvizovi[1].nazivPredmeta))), click()))
            onView(withId(R.id.navigacijaPitanja)).perform(NavigationViewActions.navigateTo(brojac))
            Espresso.onData(anything()).inAdapterView(withId(R.id.odgovoriLista)).atPosition(1).perform(click())
            onView(withId(R.id.tekstPitanja)).check(matches(withText(pitanja[brojac].tekst)))
            onView(withId(R.id.zaustaviKviz)).perform(click())
            brojac++
        }
        onView(withId(R.id.listaKvizova)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(allOf(hasDescendant(withText(kvizovi[1].naziv)), hasDescendant(withText(kvizovi[1].nazivPredmeta))), click()))
        onView(withId(R.id.predajKviz)).perform(click())
    }
}