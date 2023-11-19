import entity.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import service.GameService
import service.RootService
import org.junit.jupiter.api.BeforeEach
import service.*
/**
* Die Tests überprüfen, ob der [GameService] korrekt funktioniert, insbesondere beim Starten
* eines neuen Spiels mit verschiedenen Parametern.
 * */
class GameServiceTest {
    private lateinit var gameService: GameService
    private lateinit var rootService: RootService

    @BeforeEach
    fun setup() {
        rootService = RootService()
        gameService = rootService.gameService
    }
    /**
     * Testet die [startNewGame]-Methode des [GameService]-Objekts.
     * Überprüft, ob ein neues Spiel korrekt erstellt wird und ob Spieler, Kartenpyramide
     * und andere Spielparameter wie erwartet initialisiert werden.
     */
    @Test
    fun testStartNewGame() {
        val playerNames = listOf("Player1", "Player2")


        // Rufen Sie die startNewGame-Methode auf, um ein neues Spiel zu starten.
        gameService.startNewGame(playerNames)

        // Überprüfen Sie, ob das aktuelle Spielobjekt in RootService nicht null ist.
        val currentGame = rootService.currentGame
        assertNotNull(currentGame)

        // Überprüfen Sie, ob die Anzahl der erstellten Spieler korrekt ist.
        val players = currentGame!!.players
        assertEquals(2, players.size)

        // Überprüfen Sie, ob die Pyramide wie erwartet erstellt wurde.
        checkNotNull(currentGame)
        val pyramid = currentGame.pyramid
        assertEquals(7, pyramid.size) // Überprüfe die Anzahl der Reihen
        for (row in pyramid) {
            assertEquals(pyramid.indexOf(row) + 1, row.size) // Überprüfe die Anzahl der Karten in jeder Reihe
        }

        // Überprüfen, ob die Spieler richtig hinzugefügt wurden.
        for (i in playerNames.indices) {
            assertEquals(playerNames[i], players[i].playerName)
        }


        // Überprüfen, ob die Spieler richtig hinzugefügt wurden.
        for (i in playerNames.indices) {
            assertEquals(playerNames[i], players[i].playerName)
        }
    }
/**
 * Testet die [startNewGame]-Methode des [GameService]-Objekts mit ungültigen Spieleranzahlen.
 * Überprüft, ob die Methode eine [IllegalArgumentException] auslöst, wenn die Anzahl der Spieler
 * nicht zwischen 1 und 2 liegt.

 */

    @Test
    fun testStartNewGameWithInvalidPlayerNames() {
        // Teste, ob die Methode eine Ausnahme auslöst, wenn die Anzahl der Spieler nicht zwischen 1 und 2 liegt.
        val playerNames = listOf("Player1", "Player2", "Player3")
        assertThrows(IllegalArgumentException::class.java) {
            gameService.startNewGame(playerNames)
        }
    }

}
