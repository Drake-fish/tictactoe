package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException

@SpringBootApplication
class TicTacToe {
	fun placeMarker(board: Board, x: Int, y: Int, player: Player) {
		if (board.grid[x][y] != null) {
			throw IllegalArgumentException("Cell is already occupied")
		}
		board.grid[x][y] = player
		return

//		[     0, 1, 2
//			0[0,0,0]
//			1[0,0,0]
//			2[0,0,0]
//		]
	}

	fun checkWin(board: Board, player: Player): Boolean {
		val winConditions = listOf(
//			rows
			listOf(board.grid[0][0], board.grid[0][1], board.grid[0][2]),
			listOf(board.grid[1][0], board.grid[1][1], board.grid[1][2]),
			listOf(board.grid[2][0], board.grid[2][1], board.grid[2][2]),
			// columns
			listOf(board.grid[0][0], board.grid[1][0], board.grid[2][0]),
			listOf(board.grid[0][1], board.grid[1][1], board.grid[2][1]),
			listOf(board.grid[0][2], board.grid[1][2], board.grid[2][2]),
			// diagonals
			listOf(board.grid[0][0], board.grid[1][1], board.grid[2][2]),
			listOf(board.grid[2][0], board.grid[1][1], board.grid[2][0]),
		)

		return winConditions.any { condition -> condition.all { it == player }}
	}

	fun checkDraw(board: Board): Boolean {
		return board.grid.all { row -> row.all { it != null } }
	}
}

fun main(args: Array<String>) {
	runApplication<TicTacToe>(*args)
}
