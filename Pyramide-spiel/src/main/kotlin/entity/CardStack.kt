package entity

import java.lang.IllegalStateException
import kotlin.random.Random
/**
 * Die [CardStack]-Klasse verwaltet 52 [Card]-Objekte, die in einem Kartenspiel verwendet werden.
 * Sie ist eine Datenstruktur zur Speicherung von [Card]-Objekten.
 * @param random Das zufällige Verhalten für das Mischen von Karten wird bereitgestellt.
 * Standardmäßig wird [Random] verwendet.
 * [cards] enthält eine MutableList aller Karten im Kartendeck.
 */
class CardStack(private val random: Random = Random) {
    /**
     * Eine Liste, die alle Karten in einem Kartendeck speichert.
     */
    var cards: MutableList<Card> = mutableListOf()

    init {
        // Das Kartendeck wird initialisiert, alle 52 Karten werden erzeugt und gemischt.
        for (suit in CardSuit.values()) {
            for (value in CardValue.deck()) {
                cards.add(Card(suit, value))
            }
        }

        cards.shuffle()
    }

    /**
     * Mischen der Karten im Stapel.
     */
    private fun shuffle() {
        cards.shuffle(random)
    }

    /**
     * Zieht die oberste Karte vom Stapel.
     * @return Die gezogene Karte, sofern genügend Karten vorhanden sind,
     * andernfalls wird eine [IllegalStateException] ausgelöst.
     */
    fun drawCard() =
        if (cards.size > 0)
            cards.removeAt(0)
        else
            throw IllegalStateException("Keine Karten mehr im Stapel.")

    /**
     * Überprüft, ob genügend Karten im Stapel vorhanden sind.
     * @return `true`, wenn mindestens 1 Karten vorhanden sind, andernfalls `false`.
     */
    fun cardimStack(): Boolean {
        return cards.size > 0
    }


}
