package Model;

public enum Symbol {
    X, O, EMPTY;

    @Override
    public String toString() {
        if (this == EMPTY) return " ";
        return name();
    }
}