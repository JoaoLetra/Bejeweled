package Bejeweled;

import java.io.File;
import java.io.FileWriter;
/** 
 *
 * @author João Letra
 */
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro { // classe para o jogo facil

    public int tabuleiro[][] = new int[8][8];
    private int tabuleiroAux[][] = new int[8][8];
    public static boolean toUpdate = false;

    private Random random = new Random();
    private int pontos;
    private int jogadas = 0;
    private int verifica = 0;
    private int encerraJogo = 0;

    public void imprimeTabuleiro() {

        for (int i = 0; i < 8; i++) {
            System.out.println(" ");
            for (int j = 0; j < 8; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Pontua��o: " + pontos);
        System.out.println("_______________");
    }

    public int getTabuleiro(int i, int j) {
        int valor = tabuleiro[i][j];

        return valor;
    }
    

    public int getJogadas() {
        return jogadas;
    }

    public void setEncerraJogo(int encerraJogo) {
        this.encerraJogo = encerraJogo;
    }

    public int getEncerraJogo() {
        return encerraJogo;
    }

    public void sequenciaLinha() {// procura repetições nas linhas da tabela
        jogadas = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valor = tabuleiro[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = j + 1;

                while (posicaoSeguinte < 8 && tabuleiro[i][posicaoSeguinte] == valor) {
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valor = tabuleiro[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = i + 1;

                while (posicaoSeguinte < 8 && tabuleiro[posicaoSeguinte][j] == valor) {
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

        for (int i = 0; i < 8; i++) {
            System.out.println(" ");
            for (int j = 0; j < 8; j++) {
                System.out.print(tabuleiroAux[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Pontua��o: " + pontos);
        System.out.println("_______________");
    }

    public void jogoFacil() {
        toUpdate = false;

        copiarTabuleiroAux();

        do {
            sequenciaLinha();
            sequenciaColuna();
            descerPeca();
            gerarPecas();
            copiarTabuleiro();

        } while (verificaTabuleiro() == true);
        toUpdate = true;

        System.out.println("acabou o ciclo");
        verificaJogadas();
        if (jogadas == 0) {
            encerraJogo = 1;
        }

        System.out.println("");
        imprimeTabuleiro();

    }

    public int getPontos() {
        return pontos;
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
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
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
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                int valor = tabuleiroAux[i][j];

                if (valor == 0) {
                    verifica++;
                    tabuleiroAux[i][j] = random.nextInt(7) + 1;

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

    public void copiarTabuleiroAux() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiroAux[i][j] = tabuleiro[i][j];
            }

        }
    }

    public void copiarTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = tabuleiroAux[i][j];
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valor = tabuleiro[i][j];
                int repeticoesI = 1;
                int repeticoesJ = 1;
                int posicaoSeguinteI = j + 1;
                int posicaoSeguinteJ = i + 1;

                while (posicaoSeguinteI < 8 && tabuleiro[i][posicaoSeguinteI] == valor) {
                    repeticoesI++;
                    posicaoSeguinteI++;
                }
                while (posicaoSeguinteJ < 8 && tabuleiro[posicaoSeguinteJ][i] == valor) {
                    repeticoesJ++;
                    posicaoSeguinteJ++;
                }
                if (repeticoesI == 2) {
                    if (i > 1) {
                        if (tabuleiro[i - 2][j] == valor) {
                            jogadas++;
                        }
                        if (j > 0 && tabuleiro[i - 2][j - 1] == valor) {
                            jogadas++;
                        }
                        if (j < 7 && tabuleiro[i - 2][j + 1] == valor) {
                            jogadas++;
                        }
                    }
                    if (i < 5) {
                        if (tabuleiro[i + 3][j] == valor) {
                            jogadas++;
                        }
                    }
                }
                if (repeticoesJ == 2) {
                    if (j > 1) {
                        if (tabuleiro[i][j - 2] == valor) {
                            jogadas++;
                        }
                    }
                    if (j < 5) {
                        if (tabuleiro[i][j + 3] == valor) {
                            jogadas++;
                        }
                    }
                }
            }
        }

        System.out.println("Jogadas possiveis: " + jogadas);
        /*if (jogadas == 0) {
            System.out.println("Fim do Jogo!");
            System.out.println("Pontua��o: " + pontos + " Pontos!");
            System.exit(0);
        }*/
        return jogadas;
    }

    public void jogada(int px1, int py1, int px2, int py2) {
        int valor = tabuleiro[px2][py2];
        int desfaz = 0;
        tabuleiro[px2][py2] = tabuleiro[px1][py1];
        tabuleiro[px1][py1] = valor;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valor2 = tabuleiro[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = i + 1;

                while (posicaoSeguinte < 8 && tabuleiro[posicaoSeguinte][j] == valor2) {
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
                int valor3 = tabuleiro[i][j];
                int repeticoes = 1;
                int posicaoSeguinte = j + 1;

                while (posicaoSeguinte < 8 && tabuleiro[i][posicaoSeguinte] == valor3) {
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
            int valor2 = tabuleiro[px1][py1];
            tabuleiro[px1][py1] = tabuleiro[px2][py2];
            tabuleiro[px2][py2] = valor2;
        }
        imprimeTabuleiro();

    }

    public static void leFicheiro() { // cria um ficheiro e
        // insere os valores no

        try {

            File file = new File("Score.txt");
            FileWriter fr1 = new FileWriter(file, true);
            System.out.println();

            System.out.println("Indique o seu nome:");
            fr1.write(new Scanner(System.in).next() + ";0");

            fr1.close();

        } catch (Exception e) {
            System.out.println();
            System.out.println(" Ocorreu um erro durante a escrita no ficheiro! [DEBUG: " + e.getMessage() + "]");
            System.out.println();
        }

    }

    Tabuleiro() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = random.nextInt(7) + 1;
                tabuleiroAux[i][j] = tabuleiro[i][j];
            }
        }
    }

}
