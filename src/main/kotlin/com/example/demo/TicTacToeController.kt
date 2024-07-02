package com.example.demo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tictactoe")
class TicTacToeController(private val service: TicTacToe) {
    private var board = Board()

    @PostMapping("/move")
    fun makeMove(@RequestParam row: Int, @RequestParam col: Int, @RequestParam player: Player): String {
        service.placeMarker(board, row, col, player)
    }

    @GetMapping("/check-win")
    fun checkWin(@RequestParam player: Player): Boolean {
        return service.checkWin(board, player)
    }

    @GetMapping("/check-draw")
    fun checkDraw(): Boolean {
        return service.checkDraw(board)
    }

    @PostMapping("/reset")
    fun resetBoard() {
        board = Board()
    }
}

