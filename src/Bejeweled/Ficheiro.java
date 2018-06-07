package Bejeweled;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Ficheiro {
	
	static String[][] data;// Criámos a matriz data para o trabalhar com o
							// ficheiro camiões.txt
	public static void guardarDados(String ficheiro, String[][] matriz) {
		/* transforma uma matriz em ficheiro */

		try {
			File file = new File(ficheiro);
			PrintWriter wr1 = new PrintWriter(file);
			// escreve cada valor da matriz no ficheiro, seguido de ";"
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < 2; j++) {
					wr1.print(matriz[i][j] + ";");

				}
				wr1.println(); // muda de linha
			}
			wr1.close();
		} catch (Exception e) {
			System.out.println(" 2Ocorreu um erro durante a escrita no ficheiro! [DEBUG: " + e.getMessage() + "]");
		}
	}

	public static String[][] carregaFicheiro(String ficheiro) {
		/* cria uma matriz com todos os valores de um ficheiro */

		String[][] dados = new String[][] {};
		try {
			File file = new File(ficheiro);
			Scanner sc1 = new Scanner(file);

			// estrutura de repetição que conta o número de linhas
			int numLinhas = 0;
			while (sc1.hasNextLine()) {
				numLinhas++;
				sc1.nextLine();
			}
			sc1.close();

			sc1 = new Scanner(file);

			// estrutura que lê o conteúdo do ficheiro e carrega os dados para a
			// matriz
			dados = new String[numLinhas+1][2];
			for (int i = 0; sc1.hasNextLine(); i++) {
				String line = sc1.nextLine(); // lê o conteúdo da linha
				dados[i][0] = String.valueOf((line.split(";")[0]));
				dados[i][1] = String.valueOf((line.split(";")[1]));
				
			}
			sc1.close();
		} catch (FileNotFoundException e) {
			System.out.println(" 3Ocorreu um erro durante a leitura do ficheiro! [DEBUG: " + e.getMessage() + "]");
		}

        return dados;
    }

    public void escrever(String nome,int pontos) {
       data = carregaFicheiro("ScoreBoard.txt");
       int linha = data.length-1;
        System.out.println("print:");
       data[linha][1]= String.valueOf(pontos);
       data[linha][0]=nome;
       guardarDados("ScoreBoard.txt", data);
    }
        
        

}
	