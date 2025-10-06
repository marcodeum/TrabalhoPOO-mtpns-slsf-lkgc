package View;

import Model.Board;
import Model.Symbol;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public void mostrarTabuleiro(Board board) {
        Symbol[][] g = board.getGridCopy();
        System.out.println();
        System.out.println("   0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + g[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    public int[] lerLinhaColuna(String nomeJogador) {
        while (true) {
            System.out.print(nomeJogador + " - Linha, Coluna: ");
            try {
                String line = scanner.nextLine();
                String[] parts = line.trim().split("\s+|,|;");
                if (parts.length < 2) {
                    System.out.println("Entrada inválida. Digite: linha coluna (ex: 0 2)");
                    continue;
                }
                int l = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                if (l < 0 || l > 2 || c < 0 || c > 2) {
                    System.out.println("Coordenadas fora do intervalo [0..2]. Tente novamente.");
                    continue;
                }
                return new int[]{l, c};
            } catch (NumberFormatException ex) {
                System.out.println("Entrada não numérica. Digite números entre 0 e 2.");
            } catch (InputMismatchException ex) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n=== JOGO DA VELHA ===");
        System.out.println("1) Humano vs Humano");
        System.out.println("2) Humano vs Computador");
        System.out.println("3) Computador vs Computador");
        System.out.println("0) Sair");
        System.out.print("Escolha: ");
        while (true) {
            String line = scanner.nextLine();
            try {
                int op = Integer.parseInt(line.trim());
                if (op == 0 || op == 1 || op == 2 || op == 3) return op;
            } catch (NumberFormatException ignored) {}
            System.out.print("Opção inválida. Escolha 0,1,2 ou 3: ");
        }
    }

    public String lerNome(String prompt) {
        System.out.print(prompt + ": ");
        String n = scanner.nextLine().trim();
        if (n.isEmpty()) return "Jogador";
        return n;
    }

    public boolean perguntarRepetir() {
        System.out.print("Jogar novamente? (S/N): ");
        while (true) {
            String s = scanner.nextLine().trim().toUpperCase();
            if (s.isEmpty()) continue;
            char c = s.charAt(0);
            if (c == 'S') return true;
            if (c == 'N') return false;
            System.out.print("Responda S ou N: ");
        }
    }

    public void mostrarPlacar(String p1, int v1, String p2, int v2, int empates) {
        System.out.println("\n--- Placar da Sessão ---");
        System.out.println(p1 + ": " + v1);
        System.out.println(p2 + ": " + v2);
        System.out.println("Empates: " + empates);
        System.out.println("-------------------------\n");
    }
}