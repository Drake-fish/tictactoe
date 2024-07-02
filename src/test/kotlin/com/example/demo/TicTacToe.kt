package com.example.demo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TicTacToeTests {
	private val service = TicTacToe()
	@Test
	fun `It should put marker on the board`() {
		val board = Board()
		service.placeMarker(board = board, x=0, y=0, player = Player.X)
		assertEquals(Player.X, board.grid[0][0])
	}

	@Test
	fun `It should throw an error if the space is taken`() {
		val board = Board()
		service.placeMarker(board, 0, 0, Player.X)

		val exception = assertThrows<IllegalArgumentException> {
			service.placeMarker(board, 0, 0, Player.O)
		}
		assertEquals("Cell is already occupied", exception.message)
	}

	@Test
	fun `It should detect if there is a win by column`() {
		val board = Board(arrayOf(
			arrayOf(Player.X, null, null),
			arrayOf(Player.X, null, null),
			arrayOf(Player.X, null, null)
		))
		assertTrue(service.checkWin(board = board, player = Player.X))
	}

	@Test
	fun `It should detect if there is a win by row` () {
		val board = Board(arrayOf(
			arrayOf(Player.X, Player.X, Player.X),
			arrayOf(Player.O, null, null),
			arrayOf(Player.O, null, null)
		))
		assertTrue(service.checkWin(board = board, player = Player.X))
	}

	@Test
	fun `It should detect if there is a win by diagonal` () {
		val board = Board(arrayOf(
			arrayOf(Player.X, Player.O, Player.X),
			arrayOf(Player.O, Player.X, null),
			arrayOf(Player.O, null, Player.X)
		))
		assertTrue(service.checkWin(board = board, player = Player.X))
	}

	@Test
	fun `should detect draw`() {
		val board = Board(arrayOf(
			arrayOf(Player.X, Player.O, Player.X),
			arrayOf(Player.O, Player.X, Player.O),
			arrayOf(Player.O, Player.X, Player.O)
		))
		assertTrue(service.checkDraw(board))
	}
}
