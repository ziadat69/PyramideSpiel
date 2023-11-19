package view

import entity.Player
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color



class GameFinishedScene (private val rootService: RootService) : MenuScene(1500,1080),Refreshable{


    private val headlineLabel = Label(
        width = 300, height = 50, posX = 200, posY = 100,
        text = "Game Over",
        font = Font(size = 50)
    )

    private val p1Score = Label(width = 500, height = 35, posX = 800, posY = 200 ,font = Font(30))

    private val p2Score = Label(width = 500, height = 35, posX = 800, posY = 300 ,font = Font(30))



    private val gameResult = Label(width = 600, height = 60, posX = 600, posY = 800, font = Font(45)).apply {
        visual = ColorVisual(Color(255, 0, 0))
    }

    val quit = Button(width = 250, height = 70, posX = 225, posY = 500, text = "Quit" , font = Font(40) ).apply {
        visual = ColorVisual(Color(221, 136, 136))

    }



    init {
        opacity = .5
        addComponents(headlineLabel, p1Score, p2Score, gameResult, quit)
    }

    private var labelList : List<Label> = listOf(p1Score,p2Score)

    private fun setWinner(players: List<Player>){
        var maxOfPoints = players[0].points
        var indexOfMax = 0
        for (i in players.indices){
            labelList[i].text ="${players[i].playerName} scored ${players[i].points} points."
            if(maxOfPoints < players[i].points){
                maxOfPoints = players[i].points
                indexOfMax = i
            }

        }
        if(listOf(players.filter { it.points == maxOfPoints }).size > 1)
        {
            gameResult.text = "NO WINNER"
        }
        else{
            gameResult.text = "${players[indexOfMax].playerName} wins"
        }
    }

    override fun refreshAfterGameEnd() {
        val game = rootService.currentGame
        checkNotNull(game) { "No game running" }
        val players = game.players
        setWinner(players)


    }




}