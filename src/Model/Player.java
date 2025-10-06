package Model;

import View.ConsoleView;

public abstract class Player {
    private final String nome;
    private final Symbol symbol;

    public Player(String nome, Symbol symbol) {
        this.nome = nome;
        this.symbol = symbol;
    }

    public String getNome() {
        return nome;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public abstract Move escolherJogada(Board board, ConsoleView view);
}