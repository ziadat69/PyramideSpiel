package view
import service.*
import service.RootService
import tools.aqua.bgw.core.BoardGameApplication
/**
 * Diese Klasse repräsentiert die Hauptanwendung für das SoPra-Spiel.
 * Die Anwendung verwendet die AquaBoardGame-Plattform und ermöglicht es Benutzern,
 * zwischen verschiedenen Spielszenen zu wechseln, wie der Spielauswahl, dem Spiel selbst
 * und der Spielend-Szene. Die Klasse implementiert auch das [Refreshable]-Interface,
 * um auf Ereignisse wie das Starten eines neuen Spiels oder das Beenden des Spiels zu reagieren.
 */


class SopraApplication : BoardGameApplication("SoPra Game"),Refreshable {

    private val rootService = RootService()
    private val gameScene = GameScene(rootService)

    private val pLayerSelectScene = PLayerSelectScene().apply {
        startButton.onMouseClicked = {
            rootService.currentGame = null
            if (p1Input.text != "") {
                if (p2Input.text != "") {
                    rootService.gameService.startNewGame(
                        listOf<String>(p1Input.text.trim(), p2Input.text.trim())
                    )
                }
            }
            this@SopraApplication.showGameScene(gameScene)
        }
        quitButton.onMouseClicked = {
            exit()
        }
    }

    private val gameFinishedScene = GameFinishedScene(rootService).apply {

        quit.onMouseClicked = {
            exit()
        }


    }
    init {
        rootService.addRefreshables(gameScene,pLayerSelectScene,gameFinishedScene,this)
        this.showGameScene(gameScene)
        this.showGameScene(pLayerSelectScene)

    }
    override fun refreshAfterStartNewGame() {
        this.showGameScene(pLayerSelectScene)

    }

    override fun refreshAfterGameEnd() {
        this.hideMenuScene()
        this.showMenuScene(gameFinishedScene, 500)
    }


}

