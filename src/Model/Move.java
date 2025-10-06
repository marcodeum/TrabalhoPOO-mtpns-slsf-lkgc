package Model;

public final class Move {
    private final int linha;
    private final int coluna;

    public Move(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}