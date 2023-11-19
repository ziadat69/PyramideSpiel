package entity

import java.util.*


/**
 * Ein Enum zur Unterscheidung der 13 möglichen Werte in einem französischen Kartenspiel:
 * 2-10, Bube, Dame, König und Ass.
 *
 * Die Werte sind nach ihrer häufigsten Reihenfolge geordnet:
 * 2 < 3 < ... < 10 < Bube < Dame < König < Ass
 */
enum class CardValue {
    TWO,    // 2
    THREE,  // 3
    FOUR,   // 4
    FIVE,   // 5
    SIX,    // 6
    SEVEN,  // 7
    EIGHT,  // 8
    NINE,   // 9
    TEN,    // 10
    JACK,   // Bube (J)
    QUEEN,  // Dame (Q)
    KING,   // König (K)
    ACE,    // Ass (A)
    ;

    /**
     * Liefert ein einzelnes Zeichen zur Darstellung dieses Werts.
     * Gibt eine der Zeichenfolgen zurück: 2/3/4/5/6/7/8/9/10/J/Q/K/A
     */
    override fun toString() =
        when(this) {
            TWO -> "2"
            THREE -> "3"
            FOUR -> "4"
            FIVE -> "5"
            SIX -> "6"
            SEVEN -> "7"
            EIGHT -> "8"
            NINE -> "9"
            TEN -> "10"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
            ACE -> "A"
        }


    companion object {

        /**
         * Eine Menge von Werten für ein reduziertes Set von 4x13=52 Karten (beginnend mit der 2).
         */
        fun deck(): Set<CardValue> {
            return EnumSet.of(TWO, THREE, FOUR, ACE, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING,FIVE,SIX)
        }


    }
}
