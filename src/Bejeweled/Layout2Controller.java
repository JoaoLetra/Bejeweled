/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bejeweled;

import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.JOptionPane;
import java.io.File;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author joao_
 */
public class Layout2Controller implements Initializable {


    Tabuleiro meuTabuleiro = new Tabuleiro();
    TabuleiroDificil meuTabuleiroDificil = new TabuleiroDificil();
    Ficheiro meuFicheiro = new Ficheiro();

    int px1, py1, px2, py2;
    boolean som = true;

    int selOneOrTwo = 1;

    private final Button[][] btn = new Button[8][8];
    int modo = 0;
    boolean key = false;
    String nome;

    URL resource1 = getClass().getResource("sound1.mp3");
    URL resource2 = getClass().getResource("sound2.mp3");

    @FXML
    public GridPane tableJogo;
    @FXML
    private Button btnFacil;
    @FXML
    private Button btnDificil;
    @FXML
    private Button soundBtn;

    @FXML
    private Label txtPontos;
    
    @FXML
    private Label lable5;
    @FXML
    private Label label6;

    @FXML
    private TextField idJogador;

    @FXML
    private ListView listView1;
    @FXML
    private AnchorPane scene1;

    @FXML
    private AnchorPane scene2;
    @FXML
    private HBox hbox;

    @FXML
    private AnchorPane scene3;
    @FXML
    private AnchorPane scene4;
    @FXML
    private ImageView soundIcon;
    @FXML
    private ImageView soundIcon2;
    @FXML
    private ImageView noSoundIcon;
    @FXML
    private Button soundBtn1;
    @FXML
    private ImageView noSoundIcon2;

    URL resource = getClass().getResource("sound3.mp3");
    Media media = new Media(resource.toString());
    MediaPlayer sound3 = new MediaPlayer(media);

    Media media2 = new Media(resource2.toString());
    MediaPlayer sound2 = new MediaPlayer(media2);
    Media media1 = new Media(resource1.toString());
    MediaPlayer sound = new MediaPlayer(media1);
    @FXML
    private VBox vBoxCreditos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sound3.play();

        jogo();

    }

    private void jogo() { //esqueleto do jogo

        try {

            File file = new File("ScoreBoard.txt"); // cria o ficheiro scoreboard.txt
            FileWriter fr1 = new FileWriter(file, true);

            fr1.close();

        } catch (Exception e) {
            System.out.println();
            System.out.println(" Ocorreu um erro durante a escrita no ficheiro! [DEBUG: " + e.getMessage() + "]");
            System.out.println();
        }

        if (modo == 0) { //modo de iniciar novo jogo

            for (int i = 0; i < 8; i++) { //loop que cria os botões na grid pane
                for (int j = 0; j < 8; j++) {

                    btn[i][j] = new Button();//cria os botões
                    btn[i][j].setPadding(Insets.EMPTY);
                    btn[i][j].setMinSize(100, 100);
                    btn[i][j].setMaxSize(100, 100);
                    btn[i][j].setVisible(false); //o botão fica invisivel 
                    btn[i][j].setOnAction(this::handleBtn);

                    tableJogo.add(btn[i][j], j, i);//adiciona o botão à grid pane

                }

            }

        }

        switch (modo) {// escolhe o modo facil ou dificil
            case 1:
                meuTabuleiro.jogoFacil();

                for (int i = 0; i < 8; i++) { // introduz as imagens
                    for (int j = 0; j < 8; j++) {

                        int X = meuTabuleiro.getTabuleiro(i, j);
                        String imagem = String.valueOf(X);
                        btn[i][j].setVisible(true);
                        btn[i][j].setStyle("-fx-background-color: transparent;");

                        Rectangle rectangle = new Rectangle(0, 0, 100, 100);
                        Image img = new Image("/Images/gem" + (imagem) + ".png");
                        rectangle.setFill(new ImagePattern(img));
                        final Pane rectPane = new Pane();
                        rectPane.setMinSize(100, 100);
                        rectPane.setPrefSize(100, 100);
                        rectPane.setMaxSize(100, 100);
                        rectPane.getChildren().add(rectangle);
                        btn[i][j].setGraphic(rectPane);
                        final RotateTransition rotate = new RotateTransition(Duration.seconds(10), rectangle);
                        rotate.setByAngle(360);
                        rotate.setCycleCount(Animation.INDEFINITE);
                        rotate.setInterpolator(Interpolator.LINEAR);

                        btn[i][j].setGraphic(rectPane);

                        // make the rectangle rotate when the mouse hovers over the button
                        btn[i][j].setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                rotate.play();

                            }
                        });
                        btn[i][j].setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                rotate.stop();
                                rectangle.setRotate(0);
                            }
                        });

//                        btn[i][j].setStyle("-fx-background-color: transparent;");
//                        btn[i][j].setStyle("-fx-background-image: url('/testing/gem" + (imagem) + ".png')");
                    }
                }

                txtPontos.setText(String.valueOf(meuTabuleiro.getPontos())); //indica a pontuação

                lable2.setText("Jogadas Possiveis: " + String.valueOf(meuTabuleiro.getJogadas())); // indica as jogadas possiveis

                System.out.println("escreve pontos");

                break;

            case 2:
                meuTabuleiroDificil.jogoDificil();

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {

                        int X = meuTabuleiroDificil.getTabuleiroDificil(i, j);
                        String imagem = String.valueOf(X);
                        btn[i][j].setVisible(true);
                        btn[i][j].setStyle("-fx-background-image: url('/Images/gem" + (imagem) + ".png')");
                        btn[i][j].setOnAction(this::handleBtn);//acção de jogo

                    }

                }
                lable2.setVisible(true);
                lable2.setText("Jogadas Possiveis: " + String.valueOf(meuTabuleiroDificil.getJogadas()));
                txtPontos.setText(String.valueOf(meuTabuleiroDificil.getPontos()));
                break;

        }

        if (meuTabuleiro.getEncerraJogo() == 1) {
            sound2.stop();
            sound2.play();
            lable2.setText("Fim do Jogo!");

            for (int i = 0; i < 8; i++) { //coloca os botoes invisiveis
                for (int j = 0; j < 8; j++) {

                    btn[i][j].setVisible(false);
                }

            }

            int pontuacao = 0;
            switch (modo) {
                case 1:
                    pontuacao = meuTabuleiro.getPontos();
                    break;
                case 2:
                    pontuacao = meuTabuleiroDificil.getPontos();
                    break;
            }

            meuFicheiro.escrever(nome, pontuacao); // adiciona os dados ao ficheiro 

            String novaMatriz[][] = meuFicheiro.carregaFicheiro("ScoreBoard.txt"); //cria uma matriz com os dados do ficheiro

            System.out.println("lenght: " + novaMatriz.length);
            for (int i = 0; i < novaMatriz.length - 2; i++) {
                for (int j = 0; j < novaMatriz.length - 2 - i; j++) {

                    if (Integer.valueOf(novaMatriz[j][1]) < Integer.valueOf(novaMatriz[j + 1][1])) {
                        String var = novaMatriz[j][1];
                        String var2 = novaMatriz[j][0];
                        novaMatriz[j][0] = novaMatriz[j + 1][0];
                        novaMatriz[j][1] = novaMatriz[j + 1][1];
                        novaMatriz[j + 1][1] = var;
                        novaMatriz[j + 1][0] = var2;
                    }
                }
            }

            ArrayList<String> listaScores = new ArrayList<>();

            for (int i = 0; i < 10; i++) {//adiciona os dados da matriz à lista que vai ser lida
                String nome = novaMatriz[i][0];
                int x = nome.length();
                if (x < 5) {
                    listaScores.add(novaMatriz[i][0] + "\t\t\t\t" + novaMatriz[i][1]);
                } else {
                    listaScores.add(novaMatriz[i][0] + "\t\t\t" + novaMatriz[i][1]);
                }
            }

            label6.setText(" Fez " + String.valueOf(pontuacao) + " Pontos!");

            listView1.setVisible(true);
            listView1.setItems(FXCollections.observableArrayList(listaScores));

            scene3.setVisible(true);

            
            sound3.stop();

        }
    }

    @FXML
    private Label lable2;

    @FXML
    private void onExit(ActionEvent event) {
        System.exit(0); // encerra o Jogo
    }

    void handleBtn(ActionEvent event) {// função que movimenta os botões
 sound.stop();
        sound.play();
        int selJogada = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (event.getSource() == btn[i][j]) {

                    if (selOneOrTwo == 1) {//seleciona o primeiro botão
                        //btn[i][j].setStyle("-fx-border-color:  black;");
                        btn[i][j].setStyle("-fx-background-color: #ff5310  ;");

                        System.out.print("First button clicked: ");
                        px1 = i;
                        py1 = j;
                        System.out.println("px1=" + px1 + " py1=" + py1);
                        selOneOrTwo = 2;
                    } else if (selOneOrTwo == 2) {//seleciona o segundo botão

                        System.out.print("Second button clicked: ");
                        btn[px1][px1].setStyle("-fx-background-color: transparent;");
                        px2 = i;
                        py2 = j;
                        System.out.println("px2=" + px2 + " py2=" + py2);

                        if (i > 0 && i < 7 && j > 0 && j < 7) {//quadrado do meio

                            if (px2 == (px1 + 1) && py2 == py1 || px2 == (px1 - 1) && py2 == py1 || py2 == (py1 + 1) && px2 == px1 || py2 == (py1 - 1) && px2 == px1) {

                                selJogada = 1;
                            } else {
                                btn[px1][px1].setStyle("-fx-background-color: transparent;");
                                lable2.setText("Jogada inválida!");

                            }
                        }

                        // linha da cima
                        // Queremos tratar da movimentações da linha i = 0, onde o i é constante e o j variavel, mas não incluimos os vertices
                        if (j > 0 && j < 7 && i == 0) {
                            // Quando selecionamos 1 peça e depois uma a sua direita vamos ter que px2 == px1 pois i mantem valor de 0, 
                            // contudo py2 é igual ao local original (1a peça escolhida py1) mais uma casa. Tambem inclui movimentação de troca com a peça à esquerda e a baixo.
                            if (py2 == (py1 + 1) && px2 == px1 || py2 == (py1 - 1) && px2 == px1 || px2 == (px1 - 1) && py2 == py1) {
                                selJogada = 1;
                            }
                        }
                        // linha da esquerda
                        // Queremos tratar da movimentações da linha j = 0, onde o j é constante e o i variavel, mas não incluimos os vertices
                        if (i > 0 && i < 7 && j == 0) {
                            // Quando selecionamos 1 peça e depois uma abaixo vamos ter que py2 == py1 pois j mantem valor de 0, 
                            // contudo px2 é igual ao local original (1a peça escolhida px1) mais uma casa.Tambem inclui movimentação de troca com a peça à direita e a cima.
                            if (px2 == (px1 + 1) && py2 == py1 || px2 == (px1 - 1) && py2 == py1 || py2 == (py1 - 1) && px2 == px1) {
                                selJogada = 1;
                            }
                        }
                        // linha da direita
                        /////
                        if (j == 7 && i > 0 && i < 7) {
                            if (px2 == (px1 + 1) && py2 == py1 || px2 == (px1 - 1) && py2 == py1 || py2 == (py1 + 1) && px2 == px1) {
                                selJogada = 1;
                            }
                        }
                        // linha de baixo
                        /////
                        if (i == 7 && j > 0 && j < 7) {
                            if (py2 == (py1 + 1) && px2 == px1 || py2 == (py1 - 1) && px2 == px1 || px2 == (px1 + 1) && py2 == py1) {
                                selJogada = 1;
                            }
                        }
                        // canto superior esquerdo
                        if (i == 0 && j == 0) {
                            if (px2 == (px1 - 1) && py2 == py1 || py2 == (py1 - 1) && px2 == px1) {
                                selJogada = 1;
                            }
                        }
                        // canto superior direito
                        if (i == 0 && j == 7) {
                            if (px2 == (px1 - 1) && py2 == py1 || py2 == (py1 + 1) && px2 == px1) {
                                selJogada = 1;
                            }
                        }
                        // canto inferior esquerdo
                        if (i == 7 && j == 0) {
                            if (px2 == (px1 + 1) && py2 == py1 || py2 == (py1 - 1) && px2 == px1) {
                                selJogada = 1;
                            }
                        }
                        // canto inferior direito
                        if (i == 7 && j == 7) {
                            if (px2 == (px1 + 1) && py2 == py1 || py2 == (py1 + 1) && px2 == px1) {
                                selJogada = 1;
                            }
                        }

                        ////////////////////////////////////
                        selOneOrTwo = 1;
                    }

                }
            }
        }

        if (selJogada == 1) {//seleciona o local onde vai ser feita a jogada

            switch (modo) {
                case 1:
                    transition(px1, py1, px2, py2);

                    //Efeito para ver as peças a trocar
                    meuTabuleiro.jogada(px1, py1, px2, py2);

                case 2:
                    meuTabuleiroDificil.jogada(px1, py1, px2, py2);
            }

            jogo();
        }
    }

    public void transition(int px1, int py1, int px2, int py2) {
        Button bt = new Button();

        bt.setPadding(Insets.EMPTY);
        bt.setMinSize(100, 100);
        bt.setMaxSize(100, 100);
        bt.setVisible(false); //o botão fica invisivel 

        tableJogo.add(bt, px1, py1);

        TranslateTransition translateTransition
                = new TranslateTransition(Duration.millis(2000), btn[px1][py1]);
        TranslateTransition translateTransition2
                = new TranslateTransition(Duration.millis(2000), btn[px2][py2]);

        translateTransition.setFromX(0);
        translateTransition.setToX(100);
        translateTransition2.setFromX(0);
        translateTransition2.setToX(-100);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

    }

    @FXML
    void handleBtnDificil(ActionEvent event) {

        scene1.setVisible(false);
        scene2.setVisible(true);

        modo = 2;
        jogo();

    }

    public void janela() {
        nome = JOptionPane.showInputDialog(
                null,// parent window
                "Insira o seu nome:", // message
                "Bem Vindo!", // window title
                JOptionPane.PLAIN_MESSAGE);// message type

    }

    @FXML
    void handleBtnFacil(ActionEvent event) {
        janela();

        scene1.setVisible(false);
        scene2.setVisible(true);

        modo = 1;
        jogo();

    }

    @FXML
    void handleMove(ActionEvent event) {
        meuTabuleiro.setEncerraJogo(1);

    }

    @FXML
    void newGame(ActionEvent event) {
        try {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    btn[i][j].setVisible(false);
                }
            }
            sound3.play();
            scene1.setVisible(true);
            scene2.setVisible(false);
            scene3.setVisible(false);
            scene4.setVisible(false);
            meuTabuleiro = new Tabuleiro();
            meuTabuleiroDificil = new TabuleiroDificil();

        } catch (Exception e) {
            System.out.println("erro");
        }
    }
    private Label lable4;

    @FXML
    private void salvaFicheiro(ActionEvent event) {
        /*String nomeFicheiro=idJogador.getText();
        System.out.println("GRAVA!! "+nomeFicheiro);*/
        tableJogo.setVisible(true);
        lable5.setVisible(false);
        idJogador.setVisible(false);
        jogo();

    }

    @FXML
    private void about(ActionEvent event) {
        scene2.setVisible(false);
        scene3.setVisible(false);
        scene4.setVisible(true);
        // lable3.setVisible(true);
        
        TranslateTransition starWars = new TranslateTransition(Duration.millis(6000),vBoxCreditos);
        starWars.setFromY(520);
        starWars.setToY(0);
        starWars.play();
        
 
    }

    @FXML
    private void highScores(ActionEvent event) {
        scene2.setVisible(false);
        scene3.setVisible(true);
        scene4.setVisible(false);
        String novaMatriz[][] = meuFicheiro.carregaFicheiro("ScoreBoard.txt"); //cria uma matriz com os dados do ficheiro
        String matrizScores[][] = new String[10][2];
        ArrayList<String> listaScores = new ArrayList<>();

        String nome2 = novaMatriz[0][0];
        String score2 = novaMatriz[0][1];

        for (int i = 1; i < novaMatriz.length - 1; i++) {
            if (Integer.valueOf(novaMatriz[i][1]) > Integer.valueOf(score2)) {

                nome2 = novaMatriz[i][0];
                score2 = novaMatriz[i][1];
            }
        }

        for (int i = 0; i < novaMatriz.length - 1; i++) {//adiciona os dados da matriz à lista que vai ser lida
            listaScores.add(novaMatriz[i][0] + "\t\t" + novaMatriz[i][1]);
        }

        listView1.setVisible(true);
        listView1.setItems(FXCollections.observableArrayList(listaScores));
    }

    @FXML
    private void sound(ActionEvent event) {

        if (som == true) {
            soundIcon.setVisible(false);
            noSoundIcon.setVisible(true);
            soundIcon2.setVisible(false);
            noSoundIcon2.setVisible(true);

            sound3.setMute(true);
            sound2.setMute(true);
            sound.setMute(true);
            som = false;

        } else {
            soundIcon.setVisible(true);
            noSoundIcon.setVisible(false);
            soundIcon2.setVisible(true);
            noSoundIcon2.setVisible(false);
            //mediaP.play();
            sound3.setMute(false);
            sound2.setMute(false);
            sound.setMute(false);
            som = true;
        }

    }

}
