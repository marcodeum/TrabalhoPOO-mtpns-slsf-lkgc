package Model;

import java.util.Arrays;

public class Board {
    private final Symbol[][] grid;
    private final int size = 3;

    public Board() {
        grid = new Symbol[size][size];
        limpar();
    }

    public synchronized boolean marcar(int linha, int coluna, Symbol s) {
        if (!posicaoValida(linha, coluna)) return false;
        if (!estaVazio(linha, coluna)) return false;
        grid[linha][coluna] = s;
        return true;
    }

    public boolean estaVazio(int linha, int coluna) {
        if (!posicaoValida(linha, coluna)) return false;
        return grid[linha][coluna] == Symbol.EMPTY;
    }

    public boolean estaCheio() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (grid[i][j] == Symbol.EMPTY) return false;
        return true;
    }

    public GameResult verificarResultado() {
        // Linhas
        for (int i = 0; i < size; i++) {
            if (grid[i][0] != Symbol.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2])
                return grid[i][0] == Symbol.X ? GameResult.X_VENCEU : GameResult.O_VENCEU;
        }
        // Colunas
        for (int j = 0; j < size; j++) {
            if (grid[0][j] != Symbol.EMPTY && grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j])
                return grid[0][j] == Symbol.X ? GameResult.X_VENCEU : GameResult.O_VENCEU;
        }
        // Diagonais
        if (grid[0][0] != Symbol.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
            return grid[0][0] == Symbol.X ? GameResult.X_VENCEU : GameResult.O_VENCEU;
        if (grid[0][2] != Symbol.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
            return grid[0][2] == Symbol.X ? GameResult.X_VENCEU : GameResult.O_VENCEU;

        if (estaCheio()) return GameResult.EMPATE;
        return GameResult.EM_ANDAMENTO;
    }

    public void limpar() {
        for (int i = 0; i < size; i++)
            Arrays.fill(grid[i], Symbol.EMPTY);
    }

    public Symbol[][] getGridCopy() {
        Symbol[][] copy = new Symbol[size][size];
        for (int i = 0; i < size; i++)
            System.arraycopy(grid[i], 0, copy[i], 0, size);
        return copy;
    }

    private boolean posicaoValida(int l, int c) {
        return l >= 0 && l < size && c >= 0 && c < size;
    }
}