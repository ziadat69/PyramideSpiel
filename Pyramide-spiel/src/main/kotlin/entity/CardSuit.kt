package entity

/**
 * Ein Enum zur Unterscheidung der vier möglichen Farben in einem französischen Kartenspiel:
 * Kreuz, Pik, Herz oder Karo.
 */
enum class CardSuit {
    CLUBS,    // Kreuz ♣
    SPADES,   // Pik ♠
    HEARTS,   // Herz ♥
    DIAMONDS, // Karo ♦
    ;

    /**
     * Liefert ein einzelnes Zeichen zur Darstellung dieser Farbe.
     * Gibt eines der Symbole: ♣/♠/♥/♦ zurück.
     */
    override fun toString() = when (this) {
        CLUBS -> "♣"
        SPADES -> "♠"
        HEARTS -> "♥"
        DIAMONDS -> "♦"
    }
}
