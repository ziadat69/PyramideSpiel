package view

import tools.aqua.bgw.core.BoardGameApplication
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color
import javax.swing.text.BoxView
import javax.swing.*


class PLayerSelectScene : BoardGameScene(1920,1080),Refreshable{



    private val headLineLabel = Label(
        width = 550,
        height = 50,
        posX = 500  ,
        posY = 30 ,
        text = "Start New Game",
        font = Font(size = 60, fontStyle = Font.FontStyle.ITALIC),
    )
    private val subLineLabel = Label(
        width = 250,
        height = 35,
        posX = 500,
        posY = 100,
        text = "Welcome to Game",
        font = Font(size = 25, color = Color.DARK_GRAY)
    )
    private val p1Label = Label(
        width = 300, height = 35,
        posX = 175, posY = 250,
        text = "Player 1" ,
        font = Font(size = 40)
    )
    // type inference fails here, so explicit  ": TextField" is required
    // see https://discuss.kotlinlang.org/t/unexpected-type-checking-recursive-problem/6203/14
    val p1Input: TextField = TextField(
        width = 300, height = 35,
        posX = 175, posY = 300,
        font = Font(size = 30)
    ).apply {
        onKeyTyped = {
            startButton.isDisabled = this.text.isBlank() || p2Input.text.isBlank()
        }
    }
    private val p2Label = Label(
        width = 300, height = 35,
        posX = 900, posY = 250,
        text = "Player 2",
        font = Font(size = 40)

    )

    // type inference fails here, so explicit  ": TextField" is required
    // see https://discuss.kotlinlang.org/t/unexpected-type-checking-recursive-problem/6203/14
    val p2Input: TextField = TextField(
        width = 300, height = 35,
        posX = 900, posY = 300,
        font = Font(size = 30)

    ).apply {
        onKeyTyped = {
            startButton.isDisabled = p1Input.text.isBlank() || this.text.isBlank()
        }
    }

    val quitButton = Button(
        width = 300, height = 70,
        posX = 350, posY = 750,
        text = "Quit",
        font = Font(size = 40)
    ).apply {
        visual = ColorVisual(221, 136, 136)

    }

    val startButton = Button(
        width = 300, height = 70,
        posX = 750, posY = 750,
        text = "Play",
        font = Font(size = 40)

    ).apply {
        this.isDisabled = p1Input.text.isEmpty() || p2Input.text.isEmpty()
        visual = ColorVisual(Color(136, 221, 136))
    }


    val gameInfoLabel = Label(
        width = 600,
        height = 1000,  // Erhöhen Sie die Höhe je nach Bedarf
        posX = 1250,
        posY = 80,
        isWrapText = true,
        visual =  ColorVisual(Color.darkGray),
        text = """
        Pyramide - Das Kartenspiel

        Willkommen zu Pyramide, dem aufregenden 2-Spieler-Kartenspiel.

        **Material und Aufbau**

       - Spielkarten: Ein Standard-Blatt von 52 Karten.
       - Spielaufbau: 28 Karten in einer Pyramidenform. 
       - Äußere Karten sind offen, innere Karten verdeckt. 
       - Nachziehstapel: Übrige Karten.
       - Reservestapel: Wichtige Rolle im Spiel.

        **Punktewertung**

        Ziel: Sammle Kartenpaare mit Gesamtwertung 15.
        Kartenwerte:
        - König = 13
        - Dame = 12
        - Bube = 11
        - Karten 2-10 haben aufgedruckten Zahlenwert.
        - Ass als Joker für beliebige Zahlenwerte, 
          (zwei Asse dürfen nicht entfernt werden)

        Punkte:
        - Paare mit Gesamtwertung 15: 2 Punkte.
        - Paare mit Ass: 1 Punkt.

        Spieler mit meisten Punkten gewinnt 🏆🥇

        **Spielablauf**

        Aktionen:
        - Paar entfernen: 2 Karten mit Gesamtwertung 15 aus Pyramide/Reservestapel entfernen.
        - Karte aufdecken: Oberste Karte vom Nachziehstapel auf Reservestapel legen.
        - Passen: Bei keiner der obigen Aktionen.

        **Spielende**

        - Spielende: Pyramide entfernt oder beide Spieler passen
         oder Nachziehstapel ist leer.

        Tauche ein in dieses spannende Kartenduell und genieße Pyramide!
    """.trimIndent(),
        font = Font(18, Color.white, fontWeight = Font.FontWeight.BOLD,fontStyle = Font.FontStyle.ITALIC)
    )



    init {
        opacity = .5
        addComponents(
            headLineLabel,subLineLabel, p1Label, p1Input , p2Label
            , p2Input,startButton,gameInfoLabel,
            quitButton)
        background = ColorVisual(108, 168, 59)

    }


}