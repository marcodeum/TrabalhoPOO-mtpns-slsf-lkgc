package Controller;

import Model.*;
import View.ConsoleView;

public class MatchController {
    private final ConsoleView view;
    private final Board board;

    private int vitoriasP1 = 0;
    private int vitoriasP2 = 0;
    private int empates = 0;

    public MatchController(ConsoleView view) {
        this.view = view;
        this.board = new Board();
    }

    public void iniciar() {
        boolean executar = true;
        boolean xComeca = true;
        while (executar) {
            int op = view.mostrarMenuPrincipal();
            switch (op) {
                case 0: executar = false; break;
                case 1: jogarHumanoVsHumano(xComeca); xComeca = !xComeca; break;
                case 2: jogarHumanoVsComputador(xComeca); xComeca = !xComeca; break;
                case 3: jogarComputadorVsComputador(xComeca); xComeca = !xComeca; break;
            }
        }
        view.mostrarMensagem("Saindo. Obrigado por jogar!");
    }

    private void jogarHumanoVsHumano(boolean xComeca) {
        String n1 = view.lerNome("Nome do jogador X");
        String n2 = view.lerNome("Nome do jogador O");
        Player pX = new HumanPlayer(n1, Symbol.X);
        Player pO = new HumanPlayer(n2, Symbol.O);
        Game game = new Game(pX, pO, board, view);
        executarPartidas(game, pX.getNome(), pO.getNome(), xComeca);
    }

    private void jogarHumanoVsComputador(boolean xComeca) {
        String nHum = view.lerNome("Nome do jogador humano");
        Player pX = xComeca ? new HumanPlayer(nHum, Symbol.X) : new ComputerPlayer("CPU", Symbol.X);
        Player pO = xComeca ? new ComputerPlayer("CPU", Symbol.O) : new HumanPlayer(nHum, Symbol.O);
        Game game = new Game(pX, pO, board, view);
        executarPartidas(game, pX.getNome(), pO.getNome(), xComeca);
    }

    private void jogarComputadorVsComputador(boolean xComeca) {
        Player pX = new ComputerPlayer("CPU_X", Symbol.X);
        Player pO = new ComputerPlayer("CPU_O", Symbol.O);
        Game game = new Game(pX, pO, board, view);
        executarPartidas(game, pX.getNome(), pO.getNome(), xComeca);
    }

    private void executarPartidas(Game game, String nomeX, String nomeO, boolean xComeca) {
        boolean repetir = true;
        while (repetir) {
            GameResult r = game.jogar(xComeca);
            atualizarPlacar(r, nomeX, nomeO);
            mostrarResultado(r);
            repetir = view.perguntarRepetir();
        }
    }

    private void atualizarPlacar(GameResult r, String nomeX, String nomeO) {
        switch (r) {
            case X_VENCEU: vitoriasP1++; break;
            case O_VENCEU: vitoriasP2++; break;
            case EMPATE: empates++; break;
        }
        view.mostrarPlacar(nomeX, vitoriasP1, nomeO, vitoriasP2, empates);
    }

    private void mostrarResultado(GameResult r) {
        switch (r) {
            case X_VENCEU: view.mostrarMensagem("X venceu!"); break;
            case O_VENCEU: view.mostrarMensagem("O venceu!"); break;
            case EMPATE: view.mostrarMensagem("Empate!"); break;
        }
    }
}