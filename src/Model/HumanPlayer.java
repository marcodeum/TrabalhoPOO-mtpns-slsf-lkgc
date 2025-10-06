package Model;

import View.ConsoleView;

public class HumanPlayer extends Player {
    public HumanPlayer(String nome, Symbol symbol) {
        super(nome, symbol);
    }

    @Override
    public Move escolherJogada(Board board, ConsoleView view) {
        while (true) {
            int[] entrada = view.lerLinhaColuna(getNome());
            int l = entrada[0], c = entrada[1];
            if (!board.estaVazio(l, c)) {
                view.mostrarMensagem("Casa ocupada. Escolha outra.");
                continue;
            }
            return new Move(l, c);
        }
    }
}