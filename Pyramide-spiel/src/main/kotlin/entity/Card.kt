package entity

/**
 * Eine Datenklasse, die Informationen über eine Spielkarte enthält.
 * @param suit Die Farbe der Karte (z.B., ♣/♠/♥/♦).
 * @param value Der Wert der Karte.
 */
data class Card(val suit: CardSuit, val value: CardValue) {

    /**
     * Gibt eine Zeichenfolge zurück, die aus der Kartenfarbe (CardSuit) und dem Kartenwert
     (CardValue) besteht.
     */
    override fun toString() = "$suit$value"

    /**
     * Der Punktwert der Karte, der auf ihrem Wert basiert.
     * @return Die Punkte, die dieser Karte zugeordnet sind.
     * @throws IllegalArgumentException, wenn der Kartenwert ungültig ist.
     */
    private val points = when (value.toString()) {
        "2" -> 2
        "3" -> 3
        "4" -> 4
        "5" -> 5
        "6" -> 6
        "7" -> 7
        "8" -> 8
        "9" -> 9
        "10"-> 10
        "Q"-> 12
        "K"->13
        "J" -> 11
        "A" -> 0
        else -> throw IllegalArgumentException("Ungültiger Kartenwert")
    }

    /**
     * Ruft den Punktewert der Karte ab.
     * @return Die Punkte, die dieser Karte zugeordnet sind.
     */
    fun getPoints() = points
}