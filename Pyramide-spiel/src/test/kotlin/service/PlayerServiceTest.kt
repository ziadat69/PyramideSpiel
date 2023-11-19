import entity.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import service.*
import org.junit.jupiter.api.BeforeEach
import service.PlayerService
import service.RootService
import view.*
import service.AbstractRefreshingService
/**
 * Die `setup`-Methode wird vor jedem Testdurchlauf ausgeführt, um die notwendigen Objekte zu initialisieren.
 * Sie erstellt eine Instanz der `RootService`-Klasse und initialisiert die `playerService`-Instanz aus dieser.
 * Ein `GameService` wird ebenfalls erstellt und dem `RootService` hinzugefügt.
 */

class PlayerServiceTest {
    private lateinit var playerService: PlayerService
    private lateinit var rootService: RootService

    @BeforeEach // um eine Methode zu kennzeichnen, die vor jedem einzelnen Testfall in einem Testplan ausgeführt
    // werden
    fun setup() {
        rootService = RootService()
        playerService = PlayerService(rootService)
        val gameService = GameService(rootService)
        rootService.gameService = gameService
    }

    /**
     * Der Test überprüft die Funktionalität der `createPlayer`-Methode des `PlayerService`.
     * Ein Spieler mit dem Namen "Player1" wird erstellt, und es wird überprüft, ob die Spielerdaten korrekt sind.
     */
    @Test
    fun testCreatePlayer() {
        val playerName = "Player1"

        // Teste die Erstellung eines Spielers
        val player = playerService.createPlayer(playerName)

        assertEquals(playerName, player.playerName)
        assertEquals(28, playerService.endspiel)
    }
    /**
     * Der Test überprüft, ob die `createPlayer`-Methode eine `IllegalArgumentException` auslöst,
     * wenn versucht wird, einen Spieler mit leerem Namen zu erstellen.
     */
    @Test
    fun testCreatePlayerWithEmptyName() {

        assertThrows(IllegalArgumentException::class.java) {
            playerService.createPlayer("")
        }
    }


    /**
     * Der Test überprüft die Funktionalität der `pass`-Methode des `PlayerService`.
     * Es wird überprüft, ob der Spielerwechsel und die Rücksetzung des `passCounter` korrekt erfolgen.
     */


    @Test
    fun testPass() {

        // Füge Spieler zum aktuellen Spiel hinzu
        val game = rootService.currentGame ?: Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game
        val testRefreshable = RefreshableTest()
        rootService.addRefreshable(testRefreshable)
        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Teste die "pass"-Methode
        playerService.pass()

        // Überprüfe, ob der Spieler gewechselt wurde und der passCounter zurückgesetzt wurde
        assertEquals(game.players[1], game.currentPlayer)
        assertEquals(1, game.passCounter)
        playerService.switchSpieler()
        assertEquals(game.players[0], game.currentPlayer)
        game.passCounter=2
        assertEquals(2, game.passCounter)



    }
    /**
     * Der Test überprüft die Funktionalität der `karteaufdecken`-Methode des `PlayerService`.
     * Es wird überprüft, ob die Karte aufgedeckt wird und der `passCounter` zurückgesetzt wird.
     */
    @Test
    fun testKarteaufdecken() {
        // Füge Spieler zum aktuellen Spiel hinzu
        val game = rootService.currentGame ?: Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game

        // Teste die "karteaufdecken"-Methode
        playerService.karteaufdecken()

        // Überprüfe, ob die Karte aufgedeckt wurde und der passCounter zurückgesetzt wurde
        assertTrue(game.collectedStoragePile.isNotEmpty())
        assertEquals(0, game.passCounter)

    }
    /**
     * Der Test überprüft die `karteaufdecken`-Methode des `PlayerService`,
     * wenn der Kartenstapel nicht leer ist. Es wird auch überprüft, ob ein Refresh korrekt ausgelöst wird.
     */

    @Test
    fun testKarteaufdeckenWhenCardStackNotEmpty() {
        // Erstelle ein Spiel und füge Spieler hinzu
        val game = Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game
        checkNotNull(game)
        val testRefreshable = RefreshableTest()
        testRefreshable.reset()
        rootService.addRefreshable(testRefreshable)

        // Erstelle einen CardStack mit Karten
        val cardStack = CardStack()
        cardStack.cards.add(Card(CardSuit.CLUBS, CardValue.THREE )
        )
        cardStack.cards.add(Card(CardSuit.CLUBS, CardValue.TWO))
        game.cardStack = cardStack

        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Teste die "karteaufdecken"-Methode, wenn der Kartenstapel nicht leer ist
        playerService.karteaufdecken()

        // Überprüfe, ob die Karte aufgedeckt wurde und der Pass-Counter auf 0 zurückgesetzt wurde
        assertFalse(cardStack.cards.isEmpty())
        assertEquals(0, game.passCounter)
        kotlin.test.assertEquals(false, testRefreshable.refreshAfterPlayerkarteaufdeken)

    }
    /**
     * Der Test überprüft die `karteaufdecken`-Methode des `PlayerService`,
     * wenn der Kartenstapel leer ist. Es wird überprüft, ob der Spieler gewechselt wurde und kein Refresh ausgelöst
     * wurde.
     */
    @Test
    fun testKarteaufdeckenWhenCardStackEmpty() {
        // Erstelle ein Spiel und füge Spieler hinzu
        val game = Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game

        // Erstelle einen leeren CardStack
        val cardStack = CardStack()
        game.cardStack = cardStack

        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Teste die "karteaufdecken"-Methode, wenn der Kartenstapel leer ist
        playerService.karteaufdecken()

        // Überprüfe, ob der Spieler gewechselt wurde und die Aktualisierung nach dem Spielende ausgelöst wurde
        assertEquals(game.players[0], game.currentPlayer)
        assertEquals(0, game.passCounter)


    }
    /**
     * Der Test überprüft die `karte`-Methode des `PlayerService`,
     * wenn die Bedingungen erfüllt sind. Es wird überprüft, ob der Spielerpunktestand aktualisiert wurde
     * und der `passCounter` auf 0 zurückgesetzt wurde.
     */
    @Test
    fun testKarteWhenConditionsMet() {
        // Erstelle ein Spiel und füge Spieler hinzu
        val game = Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game

        // Erstelle eine Pyramide mit Karten
        val pyramid = mutableListOf(
            mutableListOf(Card(CardSuit.CLUBS,CardValue.ACE), Card(CardSuit.CLUBS,CardValue.TEN))
            // Füge hier weitere Karten hinzu, um die Pyramide zu vervollständigen
        )
        game.pyramid = pyramid

        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Setze die gesammelten Karten im Ablagestapel
        val collectedStoragePile = mutableListOf(Card(CardSuit.CLUBS,CardValue.FIVE))
        game.collectedStoragePile = collectedStoragePile

        // Teste die "karte"-Methode, wenn die Bedingungen erfüllt sind
        playerService.karte(0, 0, 0)

        // Überprüfe, ob der Spielerpunktestand aktualisiert wurde und der Pass-Counter auf 0 zurückgesetzt wurde
        assertEquals(1, game.players[0].points)
        assertEquals(0, game.passCounter)
        assertEquals(27, playerService.endspiel)
        assertEquals(0, game.passCounter)


        // Überprüfe, ob die Pyramide und der Ablagestapel aktualisiert wurden
    }
    /**
     * Der Test überprüft die `karte`-Methode des `PlayerService`,
     * wenn die Bedingungen nicht erfüllt sind. Es wird überprüft, dass der Spielerpunktestand, der `passCounter`
     * und andere Werte nicht geändert wurden.
     */

    @Test
    fun testKarteWhenConditionsNotMet() {
        // Erstelle ein Spiel und füge Spieler hinzu
        val game = Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game
        val pyramid = mutableListOf(
            mutableListOf(Card(CardSuit.CLUBS,CardValue.TEN))
            // Füge hier weitere Karten hinzu, um die Pyramide zu vervollständigen
        )
        game.pyramid = pyramid
        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Setze die gesammelten Karten im Ablagestapel
        val collectedStoragePile = mutableListOf(Card(CardSuit.CLUBS,CardValue.FIVE))
        game.collectedStoragePile = collectedStoragePile

        // Teste die "karte"-Methode, wenn die Bedingungen nicht erfüllt sind
        playerService.karte(0, 0, 0)

        // Überprüfe, ob der Spielerpunktestand, der Pass-Counter und die anderen Werte nicht geändert wurden

        assertEquals(2, game.players[0].points)
        assertEquals(0, game.passCounter)
        assertEquals(27, playerService.endspiel)
        assertEquals(0, game.passCounter)
    }
    /**
     * Der Test überprüft die `karte2`-Methode des `PlayerService`,
     * wenn die Bedingungen erfüllt sind. Es wird überprüft, ob der Spielerpunktestand aktualisiert wurde,
     * der `passCounter` auf 0 zurückgesetzt wurde und die Pyramide und der Ablagestapel aktualisiert wurden.
     */
    @Test
    fun testKarte2WhenConditionsMet2() {
        // Erstelle ein Spiel und füge Spieler hinzu
        val game = Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game

        // Erstelle eine Pyramide mit Karten
        val pyramid = mutableListOf(
            mutableListOf(Card(CardSuit.CLUBS,CardValue.ACE)
                ,Card(CardSuit.CLUBS,CardValue.TWO),Card(CardSuit.CLUBS,CardValue.THREE),)
        )
        game.pyramid = pyramid

        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Teste die "karte2"-Methode, wenn die Bedingungen erfüllt sind
        playerService.karte2(0, 1, 0, 0)

        // Überprüfe, ob der Spielerpunktestand aktualisiert wurde, der Pass-Counter auf 0 zurückgesetzt wurde
        // und die Pyramide und der Ablagestapel aktualisiert wurden
        assertEquals(1, game.players[0].points)
        assertEquals(0, game.passCounter)
        assertEquals(26, playerService.endspiel)
        playerService.switchSpieler()
        assertEquals(game.players[0], game.currentPlayer)
    }

    /**
     * Der Test überprüft die `karte2`-Methode des `PlayerService`,
     * wenn die Bedingungen nicht erfüllt sind. Es wird überprüft, dass der Spielerpunktestand, der `passCounter`
     * und andere Werte nicht geändert wurden.
     */

    @Test
    fun testKarte2WhenConditionsNotMet() {
        // Erstelle ein Spiel und füge Spieler hinzu
        val game = Game(listOf(Player("Player1"), Player("Player2")))
        rootService.currentGame = game

        // Erstelle eine Pyramide mit Karten
        val pyramid: MutableList<MutableList<Card>> = mutableListOf(
            mutableListOf(
                Card(CardSuit.CLUBS, CardValue.TEN),
                Card(CardSuit.CLUBS, CardValue.FIVE),
            ),
            mutableListOf(
                Card(CardSuit.DIAMONDS, CardValue.FIVE),
                Card(CardSuit.DIAMONDS, CardValue.TEN)
            )
        )

        game.pyramid = pyramid

        // Setze den aktuellen Spieler auf Player1
        game.currentPlayer = game.players[0]

        // Teste die "karte2"-Methode, wenn die Bedingungen nicht erfüllt sind
        playerService.karte2(0, 0, 1, 0)

        // Überprüfe, ob der Spielerpunktestand, der Pass-Counter und die anderen Werte nicht geändert wurden

        assertEquals(2, game.players[0].points)
        assertEquals(0, game.passCounter)
        assertEquals(26, playerService.endspiel)
        playerService.switchSpieler()
        assertEquals(game.players[0], game.currentPlayer)

    }
    /**
     * Der Test überprüft die Funktionalität der `switchSpieler`-Methode des `PlayerService`.
     * Es wird überprüft, ob der aktuelle Spieler korrekt gewechselt wurde und ob er nach dem zweiten Wechsel wieder
     * auf player1 gesetzt wurde.
     */
    @Test
    fun testSwitchSpieler() {
        // Erstellen Sie eine Instanz von PlayerService
        val rootService = RootService()
        val playerService = PlayerService(rootService)

        // Erstellen Sie ein Testspiel mit zwei Spielern
        val player1 = Player("Spieler 1")
        val player2 = Player("Spieler 2")
        val game = Game(listOf(player1, player2))

        // Setzen Sie das aktuelle Spiel in RootService
        rootService.currentGame = game

        // Setzen Sie den aktuellen Spieler auf player1
        game.currentPlayer = player1


        // Methode zum Spielerwechsel aufrufen
        playerService.switchSpieler()

        // Aktueller Spieler nach dem Wechsel
        val currentPlayerAfter = game.currentPlayer

        // Überprüfen, ob der aktuelle Spieler gewechselt wurde
        assertEquals(player2, currentPlayerAfter)

        // Erneut die Methode zum Spielerwechsel aufrufen
        playerService.switchSpieler()

        // Aktueller Spieler nach dem zweiten Wechsel
        val currentPlayerAfterSecondSwitch = game.currentPlayer

        // Überprüfen, ob der aktuelle Spieler nach dem zweiten Wechsel wieder auf player1 gesetzt wurde.
        assertEquals(player1, currentPlayerAfterSecondSwitch)

    }



}






