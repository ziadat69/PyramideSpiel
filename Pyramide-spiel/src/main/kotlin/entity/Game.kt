package entity

/**
 * Hauptklasse zur Speicherung von Spielinformationen.
 * Die [Game]-Klasse repräsentiert den aktuellen Stand des Spiels.
 * @param players Ein Array zur Speicherung aller Spieler des aktuellen Spiels.
 */
data class Game(val players: List<Player>) {
    var pyramid: MutableList<MutableList<Card>> = mutableListOf()  // Eine Liste zur Speicherung der Pyramide im Spiel.
    var cardStack = CardStack()  // Ein Kartendeck für das Spiel.
    var currentPlayer: Player = players[0]  // Der aktuelle Spieler, der am Zug ist.
    var passCounter: Int = 0  // Ein Zähler für Pässe im Spiel.
    var collectedStoragePile: MutableList<Card> = mutableListOf()  // Eine Liste zur Speicherung von gesammelten Karten.

    init {
        // Überprüft, ob die Anzahl der Spieler  2 sind
        if (players.size <2 || players.size > 2)
            throw IllegalArgumentException("Anzahl der zugelassenen Spieler muss 2 und sein.")
    }
}
