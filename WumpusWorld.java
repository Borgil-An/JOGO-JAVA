package wumpusworld;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class WumpusWorld {

    private  Jogador PLAYER;
    private  Wumpus WUMPUS;
    private  Wompers WOMPERS;
    private  TabuleiroVisual tabuleiroVisual; 
    private  JogadorVisual PLAYERVISUAL;
    private  Tabuleiro tabuleiro;
    private  int[] posicao1 = new int [2];
    private  int[] posicao2 = new int [2];
    private  JFrame frame;
       
    public WumpusWorld(){
        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("Menu Inicial");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setSize(400, 200);
            menuFrame.setLayout(new GridLayout(4, 1));
            
            Random random = new Random();
            
            
            int linhaWumpus, colunaWumpus;
            linhaWumpus = random.nextInt(14);
            colunaWumpus = random.nextInt(14);    
            posicao1[0] = linhaWumpus;
            posicao1[1] = colunaWumpus;
            
            linhaWumpus = random.nextInt(14);
            colunaWumpus = random.nextInt(14);    
            posicao2[0] = linhaWumpus;
            posicao2[1] = colunaWumpus;
            
            int tamanhoTabuleiro = 15;
            tabuleiro  = criarTabuleiro();
            

            tabuleiroVisual = new TabuleiroVisual(tamanhoTabuleiro, tabuleiro);

            
            tabuleiroVisual.inicializarJogador(PLAYER);

            JButton jogarButton = new JButton("Jogar");
            JButton debugButton = new JButton("Debugar");
            JButton jogabilidadeButton = new JButton("Jogabilidade");
            JButton sairButton = new JButton("Sair");

            jogarButton.addActionListener(e -> iniciarJogo(tabuleiro));
            debugButton.addActionListener(e -> iniciarModoDebug()); 
            jogabilidadeButton.addActionListener(e -> mostrarJogabilidade());
            sairButton.addActionListener(e -> System.exit(0));

            menuFrame.add(jogarButton);
            menuFrame.add(debugButton);
            menuFrame.add(jogabilidadeButton);
            menuFrame.add(sairButton);

            menuFrame.setVisible(true);
        });
    }
    
    public static void main(String[] args) {
        WumpusWorld wumpus = new WumpusWorld();
    }

    private  Tabuleiro criarTabuleiro() {
        int tamanhoTabuleiro = 15;
        tabuleiroVisual = new TabuleiroVisual(tamanhoTabuleiro, tabuleiro);
        Random random = new Random();

        return new Tabuleiro(tamanhoTabuleiro, posicao1, posicao2);
    }

    private  Jogador criarJogador(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, WumpusWorld wumpusWorld) {
        return new Jogador(tabuleiroVisual, tabuleiro, wumpusWorld);
    }
    
    private  Wumpus criarMonstro1(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, int[] posicao){
        return new Wumpus(tabuleiroVisual, tabuleiro, posicao) {};
    }
    private  Wompers criarMonstro2(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, int[] posicao){
        return new Wompers(tabuleiroVisual, tabuleiro, posicao) {};
    }
    
    
    private  void iniciarJogo(Tabuleiro tabuleiro) {
    frame = new JFrame("World of Wumpuscraft");

    PLAYER = criarJogador(tabuleiroVisual, tabuleiro, this);
    WUMPUS = criarMonstro1(tabuleiroVisual, tabuleiro, posicao1);
    WOMPERS = criarMonstro2(tabuleiroVisual, tabuleiro, posicao2);

    int tamanhoTabuleiro = 15;
    //Tabuleiro tabuleiro = criarTabuleiro();

    int[] posicaoPLAYER = PLAYER.getPosicao();
    int[] posicaoAleatoriaWumpus = WUMPUS.getPosicao();
    int[] posicaoAleatoriaWompers = WOMPERS.getPosicao();

    tabuleiroVisual.inicializarJogador(PLAYER);
    tabuleiroVisual.inicializarMonstro1(WUMPUS); // Inicialize o Wumpus
    tabuleiroVisual.inicializarMonstro2(WOMPERS); // Inicialize o Wompers
    tabuleiroVisual.atualizarPosicoesVisiveis(tabuleiroVisual);

    SwingUtilities.invokeLater(() -> {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PLAYERVISUAL = new JogadorVisual(PLAYER, tabuleiro, tabuleiroVisual, WUMPUS, WOMPERS);

        frame.add(PLAYERVISUAL, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);

        // Inicie o loop principal após a exibição da interface do jogador
        iniciarLoopPrincipal(PLAYER, tabuleiro, tabuleiroVisual, WUMPUS, WOMPERS, PLAYERVISUAL);
    });
}


    private  void iniciarModoDebug() {
        
        iniciarJogo(tabuleiro);
        tabuleiroVisual.ativarModoDebug(true); // Ativar modo de depuração
       
        
    }

    private  void iniciarLoopPrincipal(Jogador PLAYER, Tabuleiro tabuleiro, TabuleiroVisual tabuleiroVisual, Monstro WUMPUS, Monstro WOMPERS, JogadorVisual jogadorVisual) {
      
   
        
        
        
    
      
    }
    
    
    
    private  void mostrarJogabilidade() {
        String mensagem = "Como jogar:\n" +
                "1. Você começa na posição (A,1) com 100 de vida, um arco e uma lanterna.\n" +
                "2. Seu objetivo é encontrar o ouro e retornar à posição inicial.\n" +
                "3. Evite cair em buracos e ser devorado por monstros.\n" +
                "4. Sua lanterna tem usos limitados, então tome cuidado\n" +
                "5. Colete madeira para fazer flechas ou tampar buracos\n" +
                "6. Você pode somente se movimentar uma casa verticalmente ou horizontalmente\n" +
                "7. Pode disparar uma flecha em uma das casas à sua frente, porém elas podem errar\n" +
                "8. Boa sorte!";

        JOptionPane.showMessageDialog(null, mensagem, "Jogabilidade", JOptionPane.INFORMATION_MESSAGE);
    }

    void reiniciarJogo() {
       frame.dispose();
        Random random = new Random();
            
            
            int linhaWumpus, colunaWumpus;
            linhaWumpus = random.nextInt(14);
            colunaWumpus = random.nextInt(14);    
            posicao1[0] = linhaWumpus;
            posicao1[1] = colunaWumpus;
            
            linhaWumpus = random.nextInt(14);
            colunaWumpus = random.nextInt(14);    
            posicao2[0] = linhaWumpus;
            posicao2[1] = colunaWumpus;
            
            int tamanhoTabuleiro = 15;
            tabuleiro  = criarTabuleiro();
            

            tabuleiroVisual = new TabuleiroVisual(tamanhoTabuleiro, tabuleiro);

            
            tabuleiroVisual.inicializarJogador(PLAYER);
    }

    
      
}

