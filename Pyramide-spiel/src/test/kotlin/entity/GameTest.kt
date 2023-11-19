package entity

import entity.Game
import entity.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertFailsWith

/**
 * a class to test the Game class
 */
internal class GameTest {

    @Test
    fun testGameInitialization() {
        // Testet, ob der Konstruktor bei ungültiger Spieleranzahl eine IllegalArgumentException auslöst.

        assertFailsWith<IllegalArgumentException> {
            Game(listOf(Player("Player1"))) // Zu wenige Spieler sollten eine Ausnahme auslösen.
        }

        assertFailsWith<IllegalArgumentException> {
            Game(listOf(Player("Player1"), Player("Player2"), Player("Player3"),
                Player("Player4"), Player("Player5")))
        // Zu viele Spieler sollten eine Ausnahme auslösen.
        }

        // Gültige Anzahl von Spielern sollte keine Ausnahme auslösen.
        assertDoesNotThrow {
            Game(listOf(Player("Player1"), Player("Player2")))
        }
    }

    @Test
    fun testGameProperties() {
        // Überprüft, ob die Spieleigenschaften korrekt gesetzt und abgerufen werden.
        val player1 = Player("Ahmad")
        val player2 = Player("Ziadat")
        val game = Game(listOf(player1, player2))

        // Überprüft, ob die Spielerliste korrekt initialisiert wurde.
        assertEquals(2, game.players.size)
        assertEquals(player1, game.players[0])
        assertEquals(player2, game.players[1])

        // Überprüft, ob die Pyramidenliste initialisiert wurde.
        assertNotNull(game.pyramid)

        // Überprüft, ob das Kartendeck initialisiert wurde.
        assertNotNull(game.cardStack)

        // Überprüft, ob der aktuelle Spieler initialisiert wurde.
        assertEquals(player1, game.currentPlayer)

        // Überprüft, ob der Passzähler initialisiert wurde.
        assertEquals(0, game.passCounter)

        // Überprüft, ob die Liste der gesammelten Karten initialisiert wurde.
        assertNotNull(game.collectedStoragePile)
    }
}
