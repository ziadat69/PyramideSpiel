package entity


/**
 * Eine Datenklasse, die einen Spieler im Spiel repräsentiert.
 * @param playerName Der Name des Spielers.
 */
data class Player(var playerName: String) {
    var game: Game? = null  // Das Spiel, dem der Spieler zugeordnet ist (kann null sein).
    var points: Int = 0  // Die Punktzahl des Spielers.

    init {
        // Überprüft, ob der Spielername nicht leer ist. Andernfalls wird eine Ausnahme ausgelöst.
        if (playerName.isEmpty()) throw IllegalArgumentException("Bitte geben Sie einen Namen ein!")
    }
}

