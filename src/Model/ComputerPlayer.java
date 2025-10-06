package Model;

import View.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random rnd = new Random();

    public ComputerPlayer(String nome, Symbol symbol) {
        super(nome, symbol);
    }

    @Override
    public Move escolherJogada(Board board, ConsoleView view) {
        List<Move> livres = new ArrayList<>();
        Symbol[][] copy = board.getGridCopy();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (copy[i][j] == Symbol.EMPTY) livres.add(new Move(i, j));

        if (livres.isEmpty()) return null;
        Move escolha = livres.get(rnd.nextInt(livres.size()));
        view.mostrarMensagem(getNome() + " jogou: (" + escolha.getLinha() + ", " + escolha.getColuna() + ")");
        return escolha;
    }
}