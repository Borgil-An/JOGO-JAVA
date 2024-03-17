package wumpusworld;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class JogadorVisual extends JPanel {
    private JButton inventoryButton;
    private JButton exitButton;
    private JButton lanternButton;
    private JButton getButton;
    private JButton shootArrowButton;
    private JButton craftArrowButton;
    private JButton coverHoleButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;

    private Jogador jogador;
    private Tabuleiro tabuleiro;
    private TabuleiroVisual tabuleiroVisual;
    private Wumpus wumpus;
    private Wompers wompers;

    public JogadorVisual(Jogador jogador, Tabuleiro tabuleiro, TabuleiroVisual tabuleiroVisual, Wumpus wumpus, Wompers wompers) {
        this.jogador = jogador;
        this.tabuleiro = tabuleiro;
        this.tabuleiroVisual = tabuleiroVisual;
        this.wumpus = wumpus;
        this.wompers = wompers;
        

        // Inicialize os botões aqui
        inventoryButton = new JButton("Inventário");
        shootArrowButton = new JButton("Atirar Flecha");
        craftArrowButton = new JButton("Craftar Flecha");
        coverHoleButton = new JButton("Tampar Buraco");
        lanternButton = new JButton("Usar a Lanterna");
        getButton = new JButton("Pegar Objeto");
        exitButton = new JButton("Sair");
        upButton = new JButton("Cima");
        downButton = new JButton("Baixo");
        leftButton = new JButton("Esquerda");
        rightButton = new JButton("Direita");

        setLayout(new BorderLayout());

        JPanel moveButtonPanel = new JPanel();
        moveButtonPanel.add(upButton);
        moveButtonPanel.add(downButton);
        moveButtonPanel.add(leftButton);
        moveButtonPanel.add(rightButton);

        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.add(inventoryButton);
        actionButtonPanel.add(lanternButton);
        actionButtonPanel.add(getButton);
        actionButtonPanel.add(shootArrowButton);
        actionButtonPanel.add(craftArrowButton);
        actionButtonPanel.add(coverHoleButton);
        actionButtonPanel.add(exitButton);

        add(moveButtonPanel, BorderLayout.NORTH);
        add(tabuleiroVisual, BorderLayout.CENTER);
        add(actionButtonPanel, BorderLayout.SOUTH);

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para exibir o inventário do jogador
                JOptionPane.showMessageDialog(null, "Inventário: \n" +
                        "Vida: " + jogador.getVida() + "\n" +
                        "Flechas: " + jogador.getFlechas() + "\n" +
                        "Madeira: " + jogador.getMadeira() + "\n" +
                        "Lanterna: " + jogador.getCargaLanterna());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        
      shootArrowButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] posicaoAtual = jogador.getPosicao();
        int linhaAtual = posicaoAtual[0];
        int colunaAtual = posicaoAtual[1];

        if (jogador.getFlechas() > 0) {
            String[] opcoes = { "Cima", "Baixo", "Esquerda", "Direita" };
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha a direção para disparar a flecha",
                    "Escolha a Direção",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0:
                    {
                        int linhaAlvo = linhaAtual - 1;
                        if (linhaAtual > 0) {
                            
                            if(tabuleiro.verificarAtaqueFlecha(linhaAlvo, colunaAtual) == 1){
                                wumpus.setMorto();
                            }
                            if(tabuleiro.verificarAtaqueFlecha(linhaAlvo, colunaAtual) == 2){
                                wompers.setMorto();
                            }
                            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAlvo, colunaAtual);
                        }          break;
                    }
                case 1:
                    {
                        int linhaAlvo = linhaAtual + 1;
                        if (linhaAlvo < 14) {
                            
                           if(tabuleiro.verificarAtaqueFlecha(linhaAlvo, colunaAtual) == 1){
                                wumpus.setMorto();
                            }
                            if(tabuleiro.verificarAtaqueFlecha(linhaAlvo, colunaAtual) == 2){
                                wompers.setMorto();
                            } 
                            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAlvo, colunaAtual);
                        }       break;
                    }
                case 2:
                    {
                        int colunaAlvo = colunaAtual - 1;
                        if (colunaAlvo > 0) {
                            
                            if(tabuleiro.verificarAtaqueFlecha(linhaAtual, colunaAlvo) == 1){
                                wumpus.setMorto();
                            }
                            if(tabuleiro.verificarAtaqueFlecha(linhaAtual, colunaAlvo) == 2){
                                wompers.setMorto();
                            }
                            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual, colunaAlvo);
                        }       break;
                    }
                case 3:
                    {
                        int colunaAlvo = colunaAtual + 1;
                        if (colunaAlvo <= 14) {
                            
                            if(tabuleiro.verificarAtaqueFlecha(linhaAtual, colunaAlvo) == 1){
                                wumpus.setMorto();
                            }
                            if(tabuleiro.verificarAtaqueFlecha(linhaAtual, colunaAlvo) == 2){
                                wompers.setMorto();
                            }
                            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual, colunaAlvo);
                            
                        }       break;
                    }
                default:
                    break;
            }

            jogador.usarFlechas();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Você não tem mais flechas disponíveis!",
                    "Sem Flechas",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
    });

        craftArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para craftar flecha
                if (jogador.getMadeira() > 0) {
                    jogador.criarFlecha(1); // Gasta 1 madeira para criar uma flecha
                    JOptionPane.showMessageDialog(null, "Você craftou uma flecha!");
                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem madeira para craftar flechas.");
                }
            }
        });

        coverHoleButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (jogador.getMadeira() > 0) {
            int[] posicaoJogador = jogador.getPosicao();
            int linhaAtual = posicaoJogador[0];
            int colunaAtual = posicaoJogador[1];

            
            if (!tabuleiro.getMatriz()[linhaAtual][colunaAtual].temBuraco()) {
                
                jogador.removerMadeira(); 
                jogador.tamparBuraco(linhaAtual, colunaAtual);
               
                JOptionPane.showMessageDialog(null, "Você tampou o buraco!");
            } else {
                JOptionPane.showMessageDialog(null, "Não há buraco aqui.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem madeira para tampar buracos.");
            jogador.cairEmBuraco();
            jogador.checarDerrota();
        }
    }
});

        upButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] posicaoAtual = jogador.getPosicao();
        int linhaAtual = posicaoAtual[0];
        int colunaAtual = posicaoAtual[1];

        // Verifica se o movimento é válido e não excede uma casa de distância
        if (linhaAtual > 0) {
            int linhaAnterior = linhaAtual;
            int colunaAnterior = colunaAtual;

            wumpus.moverMonstro();
            wompers.moverMonstro();
            jogador.moverPara(linhaAtual - 1, colunaAtual);
            if(tabuleiro.getPosicao()[linhaAtual-1][colunaAtual][3] == 3){
                if (jogador.getMadeira() >= 1){
                    jogador.tamparBuraco(linhaAtual-1, colunaAtual);
                    jogador.removerMadeira();
                }else {
                    jogador.cairEmBuraco();
                    jogador.checarDerrota();
                }
            }
         

            // Atualiza a interface do tabuleiro
            tabuleiroVisual.pintarQuadranteAnterior(linhaAnterior, colunaAnterior); // Pinte o quadrante anterior de branco
            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual - 1, colunaAtual);
            jogador.verificarPercepcoes();
            jogador.checarVitoria();
            
            }
        }
        });
    downButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] posicaoAtual = jogador.getPosicao();
        int linhaAtual = posicaoAtual[0];
        int colunaAtual = posicaoAtual[1];

        
        if ( linhaAtual < 14) {
            int linhaAnterior = linhaAtual;
             wumpus.moverMonstro();
            wompers.moverMonstro();
            jogador.moverPara(linhaAtual + 1, colunaAtual);
            if(tabuleiro.getPosicao()[linhaAtual+1][colunaAtual][3] == 3){
                if (jogador.getMadeira() >= 1){
                    jogador.tamparBuraco(linhaAtual+1, colunaAtual);
                    jogador.removerMadeira();
                }else {
                    jogador.cairEmBuraco();
                    jogador.checarDerrota();
                }
            }
            tabuleiroVisual.pintarQuadranteAnterior(linhaAnterior, colunaAtual);
            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual + 1, colunaAtual);
            jogador.verificarPercepcoes();
            jogador.checarVitoria();
            

            
            }
            
        }
    
            
    });

leftButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] posicaoAtual = jogador.getPosicao();
        int linhaAtual = posicaoAtual[0];
        int colunaAtual = posicaoAtual[1];

        
        if (colunaAtual > 0) {
            int colunaAnterior = colunaAtual;
            wumpus.moverMonstro();
            wompers.moverMonstro();
            jogador.moverPara(linhaAtual, colunaAtual - 1);
            if(tabuleiro.getPosicao()[linhaAtual][colunaAtual-1][3] == 3){
                if (jogador.getMadeira() >= 1){
                    jogador.tamparBuraco(linhaAtual, colunaAtual-1);
                    jogador.removerMadeira();
                }else {
                    jogador.cairEmBuraco();
                    jogador.checarDerrota();
                }
            }
            tabuleiroVisual.pintarQuadranteAnterior(linhaAtual, colunaAnterior);
            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual, colunaAtual - 1);
            jogador.verificarPercepcoes();
            jogador.checarVitoria();
            

            
            
        }
    }
});

rightButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] posicaoAtual = jogador.getPosicao();
        int linhaAtual = posicaoAtual[0];
        int colunaAtual = posicaoAtual[1];
            

        
        if (colunaAtual < 14) {
            
            int colunaAnterior = colunaAtual;
            wumpus.moverMonstro();
            wompers.moverMonstro();
            jogador.moverPara(linhaAtual, colunaAtual + 1);
            if(tabuleiro.getPosicao()[linhaAtual][colunaAtual+1][3] == 3){
                if (jogador.getMadeira() >= 1){
                    jogador.tamparBuraco(linhaAtual, colunaAtual+1);
                    jogador.removerMadeira();
                }else {
                    jogador.cairEmBuraco();
                    jogador.checarDerrota();
                }
            }
            tabuleiroVisual.pintarQuadranteAnterior(linhaAtual, colunaAnterior);
            tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual, colunaAtual + 1);
            jogador.verificarPercepcoes();
            jogador.checarVitoria();
           
            
        }
    }
});

    lanternButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] posicaoAtual = jogador.getPosicao();
            int linhaAtual = posicaoAtual[0];
            int colunaAtual = posicaoAtual[1];

            if (jogador.ligarLanterna()) {
                String[] opcoes = { "Linha", "Coluna" };
                int escolha = JOptionPane.showOptionDialog(
                        null,
                        "Escolha a direção",
                        "Escolha",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]
                );

                if (escolha == 0) {
                    
                    for (int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++) {
                        tabuleiroVisual.marcarQuadranteComoVisivel(linhaAtual, coluna);
                    }
                } else if (escolha == 1) {
                    
                    for (int linha = 0; linha < tabuleiro.getTamanho(); linha++) {
                        tabuleiroVisual.marcarQuadranteComoVisivel(linha, colunaAtual);
                    }
                }
            } else {
                // Se não houver mais cargas de lanterna, mostre uma mensagem ao jogador
                JOptionPane.showMessageDialog(
                        null,
                        "Você não tem mais cargas de lanterna disponíveis!",
                        "Sem Cargas de Lanterna",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    });

    getButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int linhaAtual = jogador.getPosicaoLinha();
        int colunaAtual = jogador.getPosicaoColuna();
        jogador.coletar(linhaAtual, colunaAtual);
    }
    });
 }
    
    
    
    
}
