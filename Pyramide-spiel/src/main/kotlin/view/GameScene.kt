package view
import entity.Card
import entity.Player
import service.CardImageLoader
import service.RootService
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color
import tools.aqua.bgw.animation.*
import tools.aqua.bgw.components.gamecomponentviews.TokenView
import tools.aqua.bgw.visual.CompoundVisual
import tools.aqua.bgw.visual.TextVisual

class GameScene(private val rootService: RootService) :BoardGameScene(1920,1080),Refreshable {

    private val cardImageLoader = CardImageLoader()
    val cardWidth = 130
    val cardHeight = 200
    val rowSpacing = 20 // Vertikaler Abstand zwischen den Reihen
    val colSpacing = 10

    private var change1Boolean = false
    private var change1Int = -1
    private var change3Int = -1
    private var change4Int = -1
    private var change5Int = -1
    private var change2Boolean = false
    private var change2Int = -1
    private var change3Boolean = false
    private var change6Int = -1
    private var change7Int = -1
    val kartenStatus: MutableList<Boolean> = MutableList(20) { false }


    //left card independent on placedCards
    private var kartenErstReihe1: CardView = CardView(
        height =  cardHeight*0.8, width = cardWidth*0.8,
        posX = 735 - cardWidth / 2, posY = 350 -15 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {

            resetScale(this, 0, 0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 0
            change7Int = 0
        }
    }

    //middle card independent on placedCards
    private val kartenZweiteReihe1: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth *0.8,
        posX = 735 - cardWidth - colSpacing / 2, posY = 350 - 8 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this,1,0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 1
            change7Int = 0
        }
    }

    //right card independent on placedCards
    private val kartenZweiteReihe2: CardView = CardView(
        height = cardHeight  *0.8, width  = cardWidth *0.8 ,
        posX = 735 + colSpacing / 2, posY = 350 -8 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {

            resetScale(this, 1, 1)
            change1Boolean = true
            change3Boolean = true
            change6Int = 1
            change7Int = 1

        }
    }
    private val kartenDritteReihe1: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 1.5 * cardWidth - 1.5 * colSpacing, posY = 350 - 1 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this,2,0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 2
            change7Int = 0

        }
    }

    // Dritte Reihe, mittlere Karte
    private val kartenDritteReihe2: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 0.5 * cardWidth - 0.5 * colSpacing, posY = 350 - 1 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[0]) {
                resetScale(this, 2, 1)
                change1Boolean = true
                change3Boolean = true
                change6Int = 2
                change7Int = 1
            }
        }
    }

    // Dritte Reihe, rechte Karte
    private val kartenDritteReihe3: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 0.5 * cardWidth + 0.5 * colSpacing, posY = 350 - 1 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this,2,2)
            change1Boolean = true
            change3Boolean = true
            change6Int = 2
            change7Int = 2

        }
    }
    private val kartenVierteReihe1: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 2 * cardWidth - 2 * colSpacing, posY = 350 +4 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 3, 0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 3
            change7Int = 0
        }
    }

    // Fourth row, second card
    private val kartenVierteReihe2: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 1 * cardWidth - 1 * colSpacing, posY = 350 +4 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[1]){
                resetScale(this,3,1)
                change1Boolean = true
                change3Boolean = true
                change6Int = 3
                change7Int = 1}
        }
    }

    // Fourth row, third card
    private val kartenVierteReihe3: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 0 * cardWidth - 0 * colSpacing, posY = 350 +4  * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[2]) {
                resetScale(this, 3, 2)
                change1Boolean = true
                change3Boolean = true
                change6Int = 3
                change7Int = 2
            }
        }
    }
    // Fourth row, fourth card
    private val kartenVierteReihe4: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 1 * cardWidth + 1 * colSpacing, posY = 350 +4 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 3, 3)
            change1Boolean = true
            change3Boolean = true
            change6Int = 3
            change7Int = 3
        }
    }
    private val kartenFunfteReihe1: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 2.3 * cardWidth - 2 * colSpacing, posY = 350 + 11 * rowSpacing,

        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 4, 0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 4
            change7Int = 0
        }
    }

    // Fünfte Reihe, zweite Karte
    private val kartenFunfteReihe2: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 1.4 * cardWidth - 1 * colSpacing, posY = 350 + 11 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[3]){
                resetScale(this, 4, 1)
                change1Boolean = true
                change3Boolean = true
                change6Int = 4
                change7Int = 1
            }
        }
    }

    // Fünfte Reihe, dritte Karte
    private val kartenFunfteReihe3: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 0.4 * cardWidth - 1 * colSpacing, posY = 350 + 11 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[4]) {
                resetScale(this, 4, 2)
                change1Boolean = true
                change3Boolean = true
                change6Int = 4
                change7Int = 2
            }
        }
    }

    // Fünfte Reihe, vierte Karte
    private val kartenFunfteReihe4: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 0.5 * cardWidth + 1 * colSpacing, posY = 350 + 11 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[5]) {
                resetScale(this, 4, 3)
                change1Boolean = true
                change3Boolean = true
                change6Int = 4
                change7Int = 3
            }
        }
    }

    // Fünfte Reihe, fünfte Karte
    private val kartenFunfteReihe5: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 1.5 * cardWidth + 2 * colSpacing, posY = 350 + 11 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 4, 4)
            change1Boolean = true
            change3Boolean = true
            change6Int = 4
            change7Int = 4
        }
    }
    // Sechste Reihe, erste Karte
    private val kartenSechsteReihe1: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 2.7 * cardWidth - 2.5 * colSpacing, posY = 350 + 17 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 5, 0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 5
            change7Int = 0
        }
    }

    // Sechste Reihe, zweite Karte
    private val kartenSechsteReihe2: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 1.7 * cardWidth - 1.5 * colSpacing, posY = 350 + 17 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[6]) {
                resetScale(this, 5, 1)
                change1Boolean = true
                change3Boolean = true
                change6Int = 5
                change7Int = 1}
        }
    }

    // Sechste Reihe, dritte Karte
    private val kartenSechsteReihe3: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 0.7 * cardWidth - 0.5 * colSpacing, posY = 350 + 17 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[7]) {
                resetScale(this, 5, 2)
                change1Boolean = true
                change3Boolean = true
                change6Int = 5
                change7Int = 2
            }
        }
    }

    // Sechste Reihe, vierte Karte
    private val kartenSechsteReihe4: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 0.2 * cardWidth + 0.5 * colSpacing, posY = 350 + 17 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[8]) {
                resetScale(this, 5, 3)
                change1Boolean = true
                change3Boolean = true
                change6Int = 5
                change7Int = 3}
        }
    }

    // Sechste Reihe, fünfte Karte
    private val kartenSechsteReihe5: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 1.2 * cardWidth + 1.5 * colSpacing, posY = 350 + 17 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[(9)]) {
                resetScale(this, 5, 4)
                change1Boolean = true
                change3Boolean = true
                change6Int = 5
                change7Int =4
            }
        }
    }

    // Sechste Reihe, sechste Karte
    private val kartenSechsteReihe6: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 2.2 * cardWidth + 2.5 * colSpacing, posY = 350 + 17 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 5, 5)
            change1Boolean = true
            change3Boolean = true
            change6Int = 5
            change7Int = 5
        }
    }
    // Siebte Reihe, erste Karte
    private val kartenSiebteReihe1: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 3 * cardWidth - 3 * colSpacing, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 6, 0)
            change1Boolean = true
            change3Boolean = true
            change6Int = 6
            change7Int = 0
        }
    }

    // Siebte Reihe, zweite Karte
    private val kartenSiebteReihe2: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 2 * cardWidth - 2 * colSpacing, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[(10)]) {
                resetScale(this, 6, 1)
                change1Boolean = true
                change3Boolean = true
                change6Int = 6
                change7Int = 1
            }
        }
    }

    // Siebte Reihe, dritte Karte
    private val kartenSiebteReihe3: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 - 1 * cardWidth - 1 * colSpacing, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[(11)]) {
                resetScale(this, 6, 2)
                change1Boolean = true
                change3Boolean = true
                change6Int = 6
                change7Int = 2}
        }
    }

    // Siebte Reihe, vierte Karte
    private val kartenSiebteReihe4: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[(12)]){
                resetScale(this, 6, 3)
                change1Boolean = true
                change3Boolean = true
                change6Int = 6
                change7Int = 3}
        }
    }

    // Siebte Reihe, fünfte Karte
    private val kartenSiebteReihe5: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 0.9 * cardWidth + 1 * colSpacing, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[(13)]){
                resetScale(this, 6, 4)
                change1Boolean = true
                change3Boolean = true
                change6Int = 6
                change7Int = 4}
        }
    }

    // Siebte Reihe, sechste Karte
    private val kartenSiebteReihe6: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 1.8 * cardWidth + 2 * colSpacing, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            if(kartenStatus[(14)]){
                resetScale(this, 6, 5)
                change1Boolean = true
                change3Boolean = true
                change6Int = 6
                change7Int = 5}
        }
    }

    // Siebte Reihe, siebte Karte
    private val kartenSiebteReihe7: CardView = CardView(
        height = cardHeight * 0.8, width = cardWidth * 0.8,
        posX = 735 + 2.8 * cardWidth + 3 * colSpacing, posY = 350 + 22 * rowSpacing,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 6, 6)
            change1Boolean = true
            change3Boolean = true
            change6Int = 6
            change7Int = 6
        }
    }




    private val pass = Button(
        width = 100, height = 100,
        posX = 120, posY = 500,
        text = "Passen" ,
        font = Font(size = 21, color = Color.white)
    ).apply {
        visual = ColorVisual(Color.RED)
        onMouseClicked = {
            rootService.currentGame?.let {
                rootService.playerService.pass()
            }
        }

    }

    private val karte = Button(
        width = 300, height = 70,
        posX = 150, posY = 300,
        text = " \uD83D\uDC49 Paar entfernen",
        font = Font(size = 30)
    ).apply {

        onMouseClicked = {
            rootService.currentGame?.let {
                if (change1Boolean &&   change2Boolean ) {

                    change1Boolean = false
                    change2Boolean = false
                    rootService.playerService.karte(change6Int,change7Int,change2Int)
                    change6Int =-1
                    change7Int= -1
                    change2Int=-1
                }
                if (change1Boolean &&   change3Boolean ) {
                    change1Boolean = false
                    change3Boolean = false
                    rootService.playerService.karte2(change1Int,change3Int,change4Int,change5Int)
                    change1Int =-1
                    change3Int= -1
                    change4Int =-1
                    change5Int= -1
                }
                change1Boolean = false
                change2Boolean = false
                change3Boolean = false
            }
        }
    }
    private val currentPlayer = Label(
        posX = 20,
        posY = 100,
        width = 300,
        height = 70,
        font = Font(45, fontStyle = Font.FontStyle.NORMAL)
    )

    private val actualPlayer = Label(
        posX = 170,
        posY = 150,
        width = 250,
        height = 50,
        font = Font(30, fontStyle = Font.FontStyle.NORMAL),
        text = "ist an der Reihe "
    )
    private val karteaufdecken = Button(
        width = 300, height = 70,
        posX = 1565, posY = 900,
        text = "karte aufdecken",
        font = Font(size = 30)
    ).apply {
        onMouseClicked = {
            rootService.currentGame?.let {
                rootService.playerService.karteaufdecken()
            }
        }
    }



    private val playerScore = Label(
        posX = 1500,
        posY = 100,
        width = 250,
        height = 50,
        font = Font(45, fontStyle = Font.FontStyle.NORMAL),
    )

    private val scoreLabel = Label(
        posX = 1410,
        posY = 100,
        width = 250,
        height = 50,
        font = Font(size = 30, fontStyle = Font.FontStyle.NORMAL),
        text = "Score : "
    )
    private val drawstack1: CardView = CardView(
        height = 200, width = 130,
        posX = 1722, posY = 650,
        front = ImageVisual(cardImageLoader.backImage)
    )
    private val drawstack3: CardView = CardView(
        height = 255, width = 155,
        posX = 1522, posY = 625,
        front = ImageVisual(cardImageLoader.blankImage)
    )
    private val cardinstack: TokenView =
        TokenView(
            posX = 1730,
            posY = 650,
            height = 60,
            width = 60,
            visual = CompoundVisual(ImageVisual(cardImageLoader.backImage), TextVisual(text = "24",
                Font(40, fontStyle = Font.FontStyle.NORMAL)),))

    private var selectedCardCount = 0

    private fun resetScale(selectedCardView: CardView, selectedCardIndex: Int,selectedCardIndex2: Int) {

        val cardViews = listOf(
            kartenErstReihe1,
            kartenZweiteReihe1,
            kartenZweiteReihe2,
            kartenDritteReihe1,
            kartenDritteReihe2,
            kartenDritteReihe3,
            kartenVierteReihe1,
            kartenVierteReihe2,
            kartenVierteReihe3,
            kartenVierteReihe4, kartenFunfteReihe1 ,kartenFunfteReihe2,kartenFunfteReihe3,kartenFunfteReihe4,
            kartenFunfteReihe5
            ,kartenSechsteReihe1,kartenSechsteReihe2,kartenSechsteReihe3,kartenSechsteReihe4,kartenSechsteReihe5,
            kartenSechsteReihe6
            ,kartenSiebteReihe1,kartenSiebteReihe2,kartenSiebteReihe3,kartenSiebteReihe4,kartenSiebteReihe5,
            kartenSiebteReihe6,kartenSiebteReihe7,
            drawstack2
        )

        if (selectedCardCount >= 2) {
            cardViews.forEach { it.scale(1.0) }
            selectedCardCount = 0
        }


        // Aktiviere die Skalierung für die ausgewählte Karte
        selectedCardView.scale(1.2)
        selectedCardCount++

        if(selectedCardIndex!=9){

            if (selectedCardCount == 1) {
                change1Int = selectedCardIndex
                change3Int = selectedCardIndex2// Für die erste ausgewählte Karte
            } else if (selectedCardCount == 2) {

                change4Int = selectedCardIndex
                change5Int = selectedCardIndex2 // Für die zweite ausgewählte Karte

            }}

    }
    val game = rootService.currentGame
    private val drawstack2: CardView = CardView(
        height = 200, width = 130,
        posX = 1535, posY = 650,
        front = ImageVisual(cardImageLoader.blankImage)
    ).apply {
        onMouseClicked = {
            resetScale(this, 9, 0)
            change2Boolean = true
            change2Int = 0

        }
    }

    override fun refreshAfterPlayerkarteaufdeken(player: Player) {

        val game = rootService.currentGame
        checkNotNull(game)
        val movementAnimation = MovementAnimation(
            componentView = drawstack1,
            byX = 0,
            byY = -50,
            duration = 100

        ).apply {  onFinished = {
            drawstack1.posY + 50
        } }

        for ((index, card) in game.collectedStoragePile.withIndex()) {
            // Hier wird iCard für jede gesammelte Karte aufgerufen.
            iCard(drawstack2, card)
        }

        if (game.collectedStoragePile.size != 0) {

            removeComponents(drawstack3)

        }
        playAnimation(movementAnimation)


        cardinstack.visual =  CompoundVisual(ImageVisual(cardImageLoader.backImage),
            TextVisual(text = game.cardStack.cards.size.toString(),Font(50, fontStyle = Font.FontStyle.NORMAL)))

        if(game.cardStack.cards.size==0){removeComponents(drawstack1)}
    }

    private fun iCard(cardView: CardView, card: Card) {
        cardView.frontVisual = ImageVisual(cardImageLoader.frontImageFor(card.suit, card.value))
        cardView.showFront()
    }

    private val gesammltekarte: TokenView =
        TokenView(
            posX = 1510,
            posY = 170,
            height = 200,
            width = 130,
            visual = CompoundVisual(ColorVisual.LIGHT_GRAY, TextVisual(text = "Gesammelter Kartenstapel",
                Font(18, fontStyle = Font.FontStyle.ITALIC))))


    override fun refreshAfterremove(player: Player, playerCard: Int, playerCard2: Int,tableCard: Int) {

        val game = rootService.currentGame
        checkNotNull(game)

        val cardToCardViewMap: Map<Card, CardView> = mapOf(
            // Hier können Sie die Zuordnung Ihrer Karten zu CardView-Objekten erstellen.
            // Zum Beispiel:
            game.pyramid[0][0] to  kartenErstReihe1,  // card1 ist eine Card, cardView1 ist das zugehörige CardView
            game.pyramid[1][0] to kartenZweiteReihe1, game.pyramid[1][1] to kartenZweiteReihe2,
            game.pyramid[2][0] to kartenDritteReihe1, game.pyramid[2][1] to kartenDritteReihe2,
            game.pyramid[2][2] to kartenDritteReihe3,
            game.pyramid[3][0] to kartenVierteReihe1, game.pyramid[3][1] to kartenVierteReihe2,
            game.pyramid[3][2] to kartenVierteReihe3, game.pyramid[3][3] to kartenVierteReihe4,
            game.pyramid[4][0] to kartenFunfteReihe1,
            game.pyramid[4][1] to kartenFunfteReihe2, game.pyramid[4][2] to kartenFunfteReihe3,
            game.pyramid[4][3] to kartenFunfteReihe4, game.pyramid[4][4] to kartenFunfteReihe5,
            game.pyramid[5][0] to kartenSechsteReihe1,
            game.pyramid[5][1] to kartenSechsteReihe2, game.pyramid[5][2] to kartenSechsteReihe3,
            game.pyramid[5][3] to kartenSechsteReihe4, game.pyramid[5][4] to kartenSechsteReihe5,
            game.pyramid[5][5] to kartenSechsteReihe6,
            game.pyramid[6][0] to kartenSiebteReihe1, game.pyramid[6][1] to kartenSiebteReihe2,
            game.pyramid[6][2] to kartenSiebteReihe3, game.pyramid[6][3] to kartenSiebteReihe4,
            game.pyramid[6][4] to kartenSiebteReihe5, game.pyramid[6][5] to kartenSiebteReihe6,
            game.pyramid[6][6] to kartenSiebteReihe7,

            game.collectedStoragePile[0] to drawstack2
        )
        val zuEntfernendeKarte = game.pyramid[playerCard][playerCard2]

        // Holen Sie die zugehörige CardView-Komponente zur Karte.
        val zuEntfernendeKarteView = cardToCardViewMap[zuEntfernendeKarte]

        if (zuEntfernendeKarteView != null) {
            val movementAnimation = MovementAnimation.toComponentView(
                componentView = zuEntfernendeKarteView,
                toComponentViewPosition = gesammltekarte,
                scene = this, // Assuming 'this' refers to the GameScene
                duration = 1000
            ).apply {
                onFinished = {
                    zuEntfernendeKarteView.removeFromParent()

                }
            }
            playAnimation(movementAnimation)
        }


        game.collectedStoragePile.removeLastOrNull()


        for ((index, card) in game.collectedStoragePile.withIndex()) {
            // Hier wird iCard für jede gesammelte Karte aufgerufen.
            iCard(drawstack2, card)
        }
        if (game.collectedStoragePile.size == 0) {

            // Hier wird iCard für jede gesammelte Karte aufgerufen.
            addComponents(drawstack3)
        }
        //iCard(this, cardList.last())
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[2][0] ||
            game.pyramid[playerCard][playerCard2] ==  game.pyramid[2][2]){
            kartenStatus[0] = true
            initializeCard(kartenDritteReihe2,game.pyramid[2][1] , true)

        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][0]){
            kartenStatus[1] = true
            initializeCard(kartenVierteReihe2,game.pyramid[3][1] , true)

        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][1]){
            kartenStatus[2] = true
            initializeCard(kartenVierteReihe3,game.pyramid[3][2] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][3]){
            kartenStatus[2] = true
            initializeCard(kartenVierteReihe3,game.pyramid[3][2] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][2]){
            kartenStatus[1] = true
            initializeCard(kartenVierteReihe2,game.pyramid[3][1] , true)
        }

        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][0]){
            kartenStatus[3] = true
            initializeCard(kartenFunfteReihe2,game.pyramid[4][1] , true)

        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][1]){
            kartenStatus[4] = true

            initializeCard(kartenFunfteReihe3,game.pyramid[4][2] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][2]){
            kartenStatus[4] = true
            initializeCard(kartenFunfteReihe4,game.pyramid[4][3] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][4]){
            kartenStatus[5] = true
            initializeCard(kartenFunfteReihe4,game.pyramid[4][3] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][3]){
            kartenStatus[4] = true
            initializeCard(kartenFunfteReihe3,game.pyramid[4][2] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][2]){
            kartenStatus[3] = true
            initializeCard(kartenFunfteReihe2,game.pyramid[4][1] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][0]){
            kartenStatus[3] = true
            initializeCard(kartenFunfteReihe2,game.pyramid[4][1] , true)

        }

        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][0]){
            initializeCard(kartenSechsteReihe2,game.pyramid[5][1] , true)
            kartenStatus[6] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][1]){
            initializeCard(kartenSechsteReihe3,game.pyramid[5][2] , true)
            kartenStatus[7] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][2]){
            initializeCard(kartenSechsteReihe4,game.pyramid[5][3] , true)
            kartenStatus[8] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][3]){
            initializeCard(kartenSechsteReihe5,game.pyramid[5][4] , true)
            kartenStatus[9] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][5]){
            initializeCard(kartenSechsteReihe5,game.pyramid[5][4] , true)
            kartenStatus[9] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][4]){
            initializeCard(kartenSechsteReihe4,game.pyramid[5][3] , true)
            kartenStatus[8] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][3]){
            initializeCard(kartenSechsteReihe3,game.pyramid[5][2] , true)
            kartenStatus[7] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][2]){
            initializeCard(kartenSechsteReihe2,game.pyramid[5][1] , true)
            kartenStatus[6] = true
        }




        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][0]){
            initializeCard(kartenSiebteReihe2,game.pyramid[6][1] , true)
            kartenStatus[10] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][1]){
            kartenStatus[11] = true
            initializeCard(kartenSiebteReihe3,game.pyramid[6][2] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][2]){
            kartenStatus[12] = true
            initializeCard(kartenSiebteReihe4,game.pyramid[6][3] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][3]){
            kartenStatus[13] = true
            initializeCard(kartenSiebteReihe5,game.pyramid[6][4] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][4]){
            initializeCard(kartenSiebteReihe6,game.pyramid[6][5] , true)
            kartenStatus[14] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][6]){
            initializeCard(kartenSiebteReihe6,game.pyramid[6][5] , true)
            kartenStatus[14] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][5]){
            initializeCard(kartenSiebteReihe5,game.pyramid[6][4] , true)
            kartenStatus[13] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][4]){
            kartenStatus[12] = true
            initializeCard(kartenSiebteReihe4,game.pyramid[6][3] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][3]){
            kartenStatus[11] = true
            initializeCard(kartenSiebteReihe3,game.pyramid[6][2] , true)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][2]){
            initializeCard(kartenSiebteReihe2,game.pyramid[6][1] , true)
            kartenStatus[10] = true
        }

    }
    var x= false

    override fun refreshAfterremove2
                (player: Player,playerCard: Int, playerCard2: Int, tableCard1: Int, tableCard2: Int){

        val game = rootService.currentGame
        checkNotNull(game)

        val cardToCardViewMap: Map<Card, CardView> = mapOf(

            game.pyramid[0][0] to  kartenErstReihe1,  // card1 ist eine Card, cardView1 ist das zugehörige CardView
            game.pyramid[1][0] to kartenZweiteReihe1,
            game.pyramid[1][1] to kartenZweiteReihe2,
            game.pyramid[2][0] to kartenDritteReihe1,
            game.pyramid[2][1] to kartenDritteReihe2,
            game.pyramid[2][2] to kartenDritteReihe3,
            game.pyramid[3][0] to kartenVierteReihe1,
            game.pyramid[3][1] to kartenVierteReihe2,
            game.pyramid[3][2] to kartenVierteReihe3,
            game.pyramid[3][3] to kartenVierteReihe4,
            game.pyramid[4][0] to kartenFunfteReihe1,
            game.pyramid[4][1] to kartenFunfteReihe2,
            game.pyramid[4][2] to kartenFunfteReihe3,
            game.pyramid[4][3] to kartenFunfteReihe4,
            game.pyramid[4][4] to kartenFunfteReihe5,
            game.pyramid[5][0] to kartenSechsteReihe1,
            game.pyramid[5][1] to kartenSechsteReihe2,
            game.pyramid[5][2] to kartenSechsteReihe3,
            game.pyramid[5][3] to kartenSechsteReihe4,
            game.pyramid[5][4] to kartenSechsteReihe5,
            game.pyramid[5][5] to kartenSechsteReihe6,
            game.pyramid[6][0] to kartenSiebteReihe1,
            game.pyramid[6][1] to kartenSiebteReihe2,
            game.pyramid[6][2] to kartenSiebteReihe3,
            game.pyramid[6][3] to kartenSiebteReihe4,
            game.pyramid[6][4] to kartenSiebteReihe5,
            game.pyramid[6][5] to kartenSiebteReihe6,
            game.pyramid[6][6] to kartenSiebteReihe7

        )

        val zuEntfernendeKarte = game.pyramid[playerCard][playerCard2]

        // Holen Sie die zugehörige CardView-Komponente zur Karte.
        val zuEntfernendeKarteView = cardToCardViewMap[zuEntfernendeKarte]
        if (zuEntfernendeKarteView != null) {
            val movementAnimation = MovementAnimation.toComponentView(
                componentView = zuEntfernendeKarteView,
                toComponentViewPosition = gesammltekarte,
                scene = this, // Assuming 'this' refers to the GameScene
                duration = 1000
            ).apply {
                onFinished = {
                    zuEntfernendeKarteView.removeFromParent()

                }
            }
            playAnimation(movementAnimation)
        }


        val zuEntfernendeKarte2 = game.pyramid[tableCard1][tableCard2]

        val zuEntfernendeKarteView2 = cardToCardViewMap[zuEntfernendeKarte2]

        if (zuEntfernendeKarteView2 != null) {
            val movementAnimation = MovementAnimation.toComponentView(
                componentView = zuEntfernendeKarteView2,
                toComponentViewPosition = gesammltekarte,
                scene = this, // Assuming 'this' refers to the GameScene
                duration = 1000
            ).apply {
                onFinished = {
                    zuEntfernendeKarteView2.removeFromParent()

                }
            }
            playAnimation(movementAnimation)
        }
         x = true

        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[2][0] ||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[2][0]) {

            initializeCard(kartenDritteReihe2,game.pyramid[2][1] , true)



            kartenStatus[0] = true
        }

        if (  game.pyramid[playerCard][playerCard2] ==  game.pyramid[2][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[2][2]){

            initializeCard(kartenDritteReihe2,game.pyramid[2][1] , true)



            kartenStatus[0] = true

        }



        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][0] ||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[3][0] ){

            initializeCard(kartenVierteReihe2,game.pyramid[3][1] , true)
            kartenStatus[1] = true

        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][1]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[3][1] ){

            initializeCard(kartenVierteReihe3,game.pyramid[3][2] , true)
            kartenStatus[2] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][3]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[3][3] ){

            initializeCard(kartenVierteReihe3,game.pyramid[3][2] , true)
            kartenStatus[2] = true
        }

        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[3][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[3][2] ){

            initializeCard(kartenVierteReihe2,game.pyramid[3][1] , true)
            kartenStatus[1] = true
        }

        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][0] ||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][0] )
        {     kartenStatus[3] = true

            initializeCard(kartenFunfteReihe2,game.pyramid[4][1] , true,)
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][1]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][1] ){
            initializeCard(kartenFunfteReihe3,game.pyramid[4][2] , true)
            kartenStatus[4] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][2] ){
            initializeCard(kartenFunfteReihe4,game.pyramid[4][3] , true)
            kartenStatus[5] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][4]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][4] ){
            initializeCard(kartenFunfteReihe4,game.pyramid[4][3] , true)
            kartenStatus[5] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][3]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][3] ){
            initializeCard(kartenFunfteReihe3,game.pyramid[4][2] , true)
            kartenStatus[4] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][2] ){
            initializeCard(kartenFunfteReihe2,game.pyramid[4][1] , true)
            kartenStatus[3] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[4][0]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[4][0] ){
            kartenStatus[3] = true
            initializeCard(kartenFunfteReihe2,game.pyramid[4][1] , true)

        }

        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][0]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][0] ){
            kartenStatus[6] = true
            initializeCard(kartenSechsteReihe2,game.pyramid[5][1] , true )
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][1]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][1] ){
            initializeCard(kartenSechsteReihe3,game.pyramid[5][2] , true )
            kartenStatus[7] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][2] ){
            initializeCard(kartenSechsteReihe4,game.pyramid[5][3] , true )
            kartenStatus[8] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][3]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][3] ){
            initializeCard(kartenSechsteReihe5,game.pyramid[5][4] , true )
            kartenStatus[9] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][5]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][5] ){
            initializeCard(kartenSechsteReihe5,game.pyramid[5][4] , true )
            kartenStatus[9] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][4]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][4] ){
            initializeCard(kartenSechsteReihe4,game.pyramid[5][3] , true)
            kartenStatus[8] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][3]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][3] ){
            initializeCard(kartenSechsteReihe3,game.pyramid[5][2] , true)
            kartenStatus[7] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[5][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[5][2] ){
            initializeCard(kartenSechsteReihe2,game.pyramid[5][1] , true )
            kartenStatus[6] = true
        }


        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][0]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][0]){
            initializeCard(kartenSiebteReihe2,game.pyramid[6][1] , true)
            kartenStatus[10] = true

        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][1]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][1]){
            initializeCard(kartenSiebteReihe3,game.pyramid[6][2] , true)
            kartenStatus[11] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][2]){
            initializeCard(kartenSiebteReihe4,game.pyramid[6][3] , true)
            kartenStatus[12] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][3]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][3]){
            initializeCard(kartenSiebteReihe5,game.pyramid[6][4] , true)
            kartenStatus[13] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][4]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][4]){
            initializeCard(kartenSiebteReihe6,game.pyramid[6][5] , true)
            kartenStatus[14] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][6]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][6]){
            initializeCard(kartenSiebteReihe6,game.pyramid[6][5] , true)
            kartenStatus[14] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][5]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][5]){
            initializeCard(kartenSiebteReihe5,game.pyramid[6][4] , true)
            kartenStatus[13] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][4]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][4]){
            initializeCard(kartenSiebteReihe4,game.pyramid[6][3] , true)
            kartenStatus[12] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][3]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][3]){
            initializeCard(kartenSiebteReihe3,game.pyramid[6][2] , true)
            kartenStatus[11] = true
        }
        if ( game.pyramid[playerCard][playerCard2] ==  game.pyramid[6][2]||
            game.pyramid[tableCard1][tableCard2] ==  game.pyramid[6][2]){
            initializeCard(kartenSiebteReihe2,game.pyramid[6][1] , true )
            kartenStatus[10] = true
        }
    }

    private fun initializeCard(cardView: CardView, card: Card,  isOuterCard: Boolean) {

        if (isOuterCard) {
            if (x==false) {
                cardView.frontVisual = ImageVisual(cardImageLoader.frontImageFor(card.suit, card.value))
            }

           else {

                if (cardView.visual == cardView.frontVisual) {
                    val flipAnimation = FlipAnimation(
                        gameComponentView = cardView,
                        fromVisual = cardView.backVisual,
                        toVisual = ImageVisual(
                            cardImageLoader.frontImageFor(
                                card.suit,
                                card.value
                            )
                        ),
                        duration = 1000
                    ).apply {
                        val frontVisual =
                            ImageVisual(
                                cardImageLoader.frontImageFor(
                                    card.suit,
                                    card.value
                                )
                            )

                        onFinished = {
                            cardView.frontVisual = frontVisual


                        }
                    }
                    playAnimation(flipAnimation)

                } }

            } else {
                cardView.frontVisual = ImageVisual(cardImageLoader.backImage)
            }
            cardView.showFront()

    }

    // Define a function to check if a card is open
    override fun refreshAfterStartNewGame() {
        val game = rootService.currentGame
        checkNotNull(game)
        currentPlayer.text = game.currentPlayer.playerName
        playerScore.text = game.currentPlayer.points.toString()
        val erste = game.pyramid[0][0]
        val zweite = game.pyramid[1][0]
        val dritte = game.pyramid[1][1]
        val virte = game.pyramid[2][0]
        val fuenfte = game.pyramid[2][1]
        val sechste = game.pyramid[2][2]
        val siebte = game.pyramid[3][0]
        val achte = game.pyramid[3][1]
        val neunte = game.pyramid[3][2]
        val zehn = game.pyramid[3][3]
        val elfte = game.pyramid[4][0]
        val zwoelfte = game.pyramid[4][1]
        val dreizehnte = game.pyramid[4][2]
        val vierzehnte = game.pyramid[4][3]
        val fuenfzehnte = game.pyramid[4][4]
        val sechzehnte = game.pyramid[5][0]
        val siebzehnte = game.pyramid[5][1]
        val achtzehnte = game.pyramid[5][2]
        val neunzehnte = game.pyramid[5][3]
        val zwanzigste = game.pyramid[5][4]
        val einundzwanzigste = game.pyramid[5][5]
        val zweiundzwanzigste = game.pyramid[6][0]
        val dreiundzwanzigste = game.pyramid[6][1]
        val vierundzwanzigste = game.pyramid[6][2]
        val funfundzwanzigste = game.pyramid[6][3]
        val sechsundzwanzigste = game.pyramid[6][4]
        val siebenundzwanzigste = game.pyramid[6][5]
        val achtundzwanzigste = game.pyramid[6][6]

        //cards on table
        initializeCard(kartenErstReihe1, erste, true )

        initializeCard(kartenZweiteReihe1, zweite, true)
        initializeCard(kartenZweiteReihe2, dritte, true)

        initializeCard(kartenDritteReihe1, virte, true )
        initializeCard(kartenDritteReihe2, fuenfte, false)
        initializeCard(kartenDritteReihe3, sechste, true)

        initializeCard(kartenVierteReihe1, siebte, true)
        initializeCard(kartenVierteReihe2, achte, false)
        initializeCard(kartenVierteReihe3, neunte, false)
        initializeCard(kartenVierteReihe4, zehn, true)

        initializeCard(kartenFunfteReihe1, elfte, true)
        initializeCard(kartenFunfteReihe2, zwoelfte, false)
        initializeCard(kartenFunfteReihe3, dreizehnte, false)
        initializeCard(kartenFunfteReihe4, vierzehnte, false)
        initializeCard(kartenFunfteReihe5, fuenfzehnte, true)

        initializeCard(kartenSechsteReihe1, sechzehnte, true)
        initializeCard(kartenSechsteReihe2, siebzehnte, false)
        initializeCard(kartenSechsteReihe3, achtzehnte, false)
        initializeCard(kartenSechsteReihe4, neunzehnte, false)
        initializeCard(kartenSechsteReihe5, zwanzigste, false)
        initializeCard(kartenSechsteReihe6, einundzwanzigste, true)

        initializeCard(kartenSiebteReihe1, zweiundzwanzigste, true)
        initializeCard(kartenSiebteReihe2, dreiundzwanzigste, false)
        initializeCard(kartenSiebteReihe3, vierundzwanzigste, false)
        initializeCard(kartenSiebteReihe4, funfundzwanzigste, false)
        initializeCard(kartenSiebteReihe5, sechsundzwanzigste, false)
        initializeCard(kartenSiebteReihe6, siebenundzwanzigste, false)
        initializeCard(kartenSiebteReihe7, achtundzwanzigste, true)

    }

    override fun refreshAfterPlayerAction(nextPlayer: Player) {

        val game = rootService.currentGame
        checkNotNull(game) { "No started game found." }
        currentPlayer.text = game.currentPlayer.playerName
        playerScore.text = game.currentPlayer.points.toString()

    }

    init {

        background = ColorVisual(108, 168, 59)
        addComponents(
            currentPlayer, actualPlayer,pass,
            playerScore, scoreLabel, kartenErstReihe1, kartenZweiteReihe1, kartenZweiteReihe2,kartenDritteReihe1,
            kartenDritteReihe2,kartenDritteReihe3,
            kartenVierteReihe4,kartenVierteReihe3,kartenVierteReihe2,kartenVierteReihe1,drawstack2,drawstack1,
            karteaufdecken ,karte,cardinstack
            ,kartenFunfteReihe1 ,kartenFunfteReihe2,kartenFunfteReihe3,kartenFunfteReihe4,kartenFunfteReihe5
            ,kartenSechsteReihe1,kartenSechsteReihe2,kartenSechsteReihe3,kartenSechsteReihe4,kartenSechsteReihe5,
            kartenSechsteReihe6
            ,kartenSiebteReihe1,kartenSiebteReihe2,kartenSiebteReihe3,kartenSiebteReihe4,kartenSiebteReihe5,
            kartenSiebteReihe6,
            kartenSiebteReihe7,gesammltekarte
        )

    }
}
