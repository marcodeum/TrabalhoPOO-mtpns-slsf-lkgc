package Controller;

import Model.*;
import View.ConsoleView;

public class Game {
    private final Player p1;
    private final Player p2;
    private final Board board;
    private final ConsoleView view;

    public Game(Player p1, Player p2, Board board, ConsoleView view) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
        this.view = view;
    }

    public GameResult jogar(boolean xComeca) {
        board.limpar();
        Player vez = xComeca ? p1 : p2;
        while (true) {
            view.mostrarTabuleiro(board);
            Move mv = vez.escolherJogada(board, view);
            if (mv == null) return GameResult.EMPATE;
            boolean ok = board.marcar(mv.getLinha(), mv.getColuna(), vez.getSymbol());
            if (!ok) {
                view.mostrarMensagem("Jogada inválida. Tente novamente.");
                continue;
            }
            GameResult res = board.verificarResultado();
            if (res != GameResult.EM_ANDAMENTO) {
                view.mostrarTabuleiro(board);
                return res;
            }
            vez = (vez == p1) ? p2 : p1;
        }
    }
}