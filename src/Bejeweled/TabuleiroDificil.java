package Bejeweled;

import java.util.Random;
import java.util.Scanner;

public class TabuleiroDificil {

    private int tabuleiroDificil[][] = new int[8][8];
    private int tabuleiroAux[][] = new int[8][8];
    private int i;
    private int j;
    private Random random = new Random();
    private int pontos;
    private int jogadas;
    private int verifica;
    private int encerraJogo = 0;

    public int getEncerraJogo() {
        return encerraJogo;
    }
    public void setEncerraJogo(int encerraJogo) {
        this.encerraJogo = encerraJogo;
    }
    public int getPontos() {
        return pontos;
    }

    public int getJogadas() {
        return jogadas;
    }

    public int getTabuleiroDificil(int i, int j) {
        int valor = tabuleiroDificil[i][j];

        return valor;
    }

    public void copiarTabuleiroAux() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiroAux[i][j] = tabuleiroDificil[i][j];
            }

        }
    }

    public void jogoDificil() {
        
        copiarTabuleiroAux();

        do {
            sequenciaLinha();
            sequenciaColuna();
            descerPeca();
            gerarPecas();
            copiarTabuleiro();

        } while (verificaTabuleiro() == true);

        System.out.println();
        verificaJogadas();
        if (jogadas == 0) {
            encerraJogo = 1;
        }

        System.out.println("");
        imprimeTabuleiro();
        
    }

    public void imprimeTabuleiro() {

        for (i = 0; i < 8; i++) {
            System.out.println(" ");
            for (j = 0; j < 8; j++) {
                System.out.print(tabuleiroDificil[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Pontua��o: " + pontos);
        System.out.println("_______________");
    }

    public void sequenciaLinha() {
        jogadas = 0;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                int valor = tabuleiroDificil[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = j + 1;

                while (posicaoSeguinte < 8 && tabuleiroDificil[i][posicaoSeguinte] == valor) {
                    repeticoes++;
                    posicaoSeguinte++;
                }
                if (repeticoes >= 3) {
                    pontos++;
                    pontos = pontos + 99;
                    apagaRepLinha(i, j, repeticoes);
                }
            }
        }
    }

    public void sequenciaColuna() {
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                int valor = tabuleiroDificil[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = i + 1;

                while (posicaoSeguinte < 8 && tabuleiroDificil[posicaoSeguinte][j] == valor) {
                    repeticoes++;
                    posicaoSeguinte++;
                }
                if (repeticoes >= 3) {
                    pontos++;
                    pontos = pontos + 99;
                    apagaRepColuna(i, j, repeticoes);

                }
            }
        }

        for (i = 0; i < 8; i++) {
            System.out.println(" ");
            for (j = 0; j < 8; j++) {
                System.out.print(tabuleiroAux[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Pontua��o: " + pontos);
        System.out.println("_______________");
    }

    private void apagaRepColuna(int i, int j, int repeticoes) {
        for (int ii = i; ii < (i + repeticoes); ii++) {
            tabuleiroAux[ii][j] = 0;
        }
    }

    private void apagaRepLinha(int i, int j, int repeticoes) {
        for (int jj = j; jj < (j + repeticoes); jj++) {
            tabuleiroAux[i][jj] = 0;
        }
    }

    public void descerPeca() {
        for (i = 7; i > 0; i--) {
            for (j = 0; j < 8; j++) {
                int valor = tabuleiroAux[i][j];
                int posicaoSeguinte = i - 1;

                if (valor == 0) {
                    while (posicaoSeguinte >= 0 && tabuleiroAux[posicaoSeguinte][j] == 0) {
                        posicaoSeguinte--;
                    }

                    if (posicaoSeguinte != -1) {
                        int novoValor = tabuleiroAux[posicaoSeguinte][j];
                        tabuleiroAux[posicaoSeguinte][j] = 0;
                        tabuleiroAux[i][j] = novoValor;
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Queda!");
        for (int ii = 0; ii < 8; ii++) {
            System.out.println(" ");
            for (int jj = 0; jj < 8; jj++) {
                System.out.print(tabuleiroAux[ii][jj] + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Pontua��o: " + pontos);
        System.out.println("_______________");
    }

    public void gerarPecas() {
        for (i = 7; i >= 0; i--) {
            for (j = 0; j < 8; j++) {
                int valor = tabuleiroAux[i][j];

                if (valor == 0) {
                    verifica++;
                    tabuleiroAux[i][j] = random.nextInt(7) + 1;
                    do {
                        if ((i == 0 && j > 1) || (i == 1 && j > 1)) {

                            tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                            valor = tabuleiroDificil[i][j];
                            if (valor == tabuleiroDificil[i][j - 1] && valor == tabuleiroDificil[i][j - 2]) {
                                valor = 0;
                            }

                        }
                        if ((j == 0 && i > 1) || (j == 1 && i > 1)) {

                            tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                            valor = tabuleiroDificil[i][j];
                            if (valor == tabuleiroDificil[i - 1][j]) {
                                valor = 0;
                            }

                        }

                        if (i > 1 && j > 1) {

                            tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                            valor = tabuleiroDificil[i][j];
                            if ((valor == tabuleiroDificil[i][j - 1] && valor == tabuleiroDificil[i][j - 2]) || (valor == tabuleiroDificil[i - 1][j] && valor == tabuleiroDificil[i - 2][j])) {
                                valor = 0;
                            }
                        }

                    } while (valor == 0);
                }
            }
        }

        System.out.println();
        System.out.println("Novas Pe�as");
        for (int ii = 0; ii < 8; ii++) {
            System.out.println(" ");
            for (int jj = 0; jj < 8; jj++) {
                System.out.print(tabuleiroAux[ii][jj] + " ");
            }
        }
        System.out.println();

    }

    public void copiarTabuleiro() {
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tabuleiroDificil[i][j] = tabuleiroAux[i][j];
            }

        }
    }

    public boolean verificaTabuleiro() {

        if (verifica == 0) {
            return false;
        } else {
            verifica = 0;
            return true;
        }
    }

    public int verificaJogadas() {
        /* por acabar */
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                int valor = tabuleiroDificil[i][j];
                int repeticoesI = 1;
                int repeticoesJ = 1;
                int posicaoSeguinteI = j + 1;
                int posicaoSeguinteJ = i + 1;

                while (posicaoSeguinteI < 8 && tabuleiroDificil[i][posicaoSeguinteI] == valor) {
                    repeticoesI++;
                    posicaoSeguinteI++;
                }
                while (posicaoSeguinteJ < 8 && tabuleiroDificil[posicaoSeguinteJ][i] == valor) {
                    repeticoesJ++;
                    posicaoSeguinteJ++;
                }
                if (repeticoesI == 2) {
                    if (i > 1) {
                        if (tabuleiroDificil[i - 2][j] == valor) {
                            jogadas++;
                        }
                        if (j > 0 && tabuleiroDificil[i - 2][j - 1] == valor) {
                            jogadas++;
                        }
                        if (j < 7 && tabuleiroDificil[i - 2][j + 1] == valor) {
                            jogadas++;
                        }
                    }
                    if (i < 5) {
                        if (tabuleiroDificil[i + 3][j] == valor) {
                            jogadas++;
                        }
                    }
                }
                if (repeticoesJ == 2) {
                    if (j > 1) {
                        if (tabuleiroDificil[i][j - 2] == valor) {
                            jogadas++;
                        }
                    }
                    if (j < 5) {
                        if (tabuleiroDificil[i][j + 3] == valor) {
                            jogadas++;
                        }
                    }
                }
            }
        }

        System.out.println("Jogadas possiveis: " + jogadas);
        if (jogadas == 0) {
            System.out.println("Fim do Jogo!");
            System.out.println("Pontua��o: " + pontos + " Pontos!");
            System.exit(0);
        }
        return jogadas;
    }

    public void jogada(int px1, int py1, int px2, int py2) {
        int valor = tabuleiroDificil[px2][py2];
        int desfaz = 0;
        tabuleiroDificil[px2][py2] = tabuleiroDificil[px1][py1];
        tabuleiroDificil[px1][py1] = valor;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valor2 = tabuleiroDificil[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = i + 1;

                while (posicaoSeguinte < 8 && tabuleiroDificil[posicaoSeguinte][j] == valor2) {
                    repeticoes++;
                    posicaoSeguinte++;
                }
                if (repeticoes >= 3) {

                    desfaz = 1;
                    System.out.println("desfaz");
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valor3 = tabuleiroDificil[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = j + 1;

                while (posicaoSeguinte < 8 && tabuleiroDificil[i][posicaoSeguinte] == valor3) {
                    repeticoes++;
                    posicaoSeguinte++;
                }
                if (repeticoes >= 3) {
                    desfaz = 1;
                }
            }
        }
        if (desfaz == 0) {
            System.out.println("desfaz2");
            int valor2 = tabuleiroDificil[px1][py1];
            tabuleiroDificil[px1][py1] = tabuleiroDificil[px2][py2];
            tabuleiroDificil[px2][py2] = valor2;
        }
        imprimeTabuleiro();

    }

    TabuleiroDificil() {

        int valor = 1;

        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                tabuleiroAux[i][j] = tabuleiroDificil[i][j];
                do { // while valor==0
                    if ((i == 0 && j > 1) || (i == 1 && j > 1)) {

                        tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                        tabuleiroAux[i][j] = tabuleiroDificil[i][j];
                        valor = tabuleiroDificil[i][j];
                        if (valor == tabuleiroDificil[i][j - 1] && valor == tabuleiroDificil[i][j - 2]) {
                            valor = 0;
                        }

                    }
                    if ((j == 0 && i > 1) || (j == 1 && i > 1)) {

                        tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                        tabuleiroAux[i][j] = tabuleiroDificil[i][j];
                        valor = tabuleiroDificil[i][j];
                        if (valor == tabuleiroDificil[i - 1][j]) {
                            valor = 0;
                        }

                    }

                    if (i > 1 && j > 1) {

                        tabuleiroDificil[i][j] = random.nextInt(7) + 1;
                        tabuleiroAux[i][j] = tabuleiroDificil[i][j];
                        valor = tabuleiroDificil[i][j];
                        if ((valor == tabuleiroDificil[i][j - 1] && valor == tabuleiroDificil[i][j - 2]) || (valor == tabuleiroDificil[i - 1][j] && valor == tabuleiroDificil[i - 2][j])) {
                            valor = 0;
                        }
                    }

                } while (valor == 0);

            }
        }

    }
}
