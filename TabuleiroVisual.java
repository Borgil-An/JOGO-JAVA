package wumpusworld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.JPanel;


public class TabuleiroVisual extends JPanel {
    private int tamanhoTabuleiro;
    private Tabuleiro tabuleiro;
    private Jogador jogador;
    private boolean modoDebug = false;
    private boolean[][] posicoesVisiveis;
    private int[] inteiros;
    private Monstro monstro1;
    private Monstro monstro2;

    public TabuleiroVisual(int tamanhoTabuleiro, Tabuleiro tabuleiro) {
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        this.tabuleiro = tabuleiro;
        this.modoDebug = false;
        setPreferredSize(new Dimension(800, 800));
        posicoesVisiveis = new boolean[tamanhoTabuleiro][tamanhoTabuleiro];

    }

    public void inicializarJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    
    public void inicializarMonstro1(Monstro monstro) {
        this.monstro1 = monstro;
        System.out.println("1: " + Arrays.toString(monstro1.getPosicao()));
    }
    
    public void inicializarMonstro2(Monstro monstro) {
        this.monstro2 = monstro;
        System.out.println("2: " + Arrays.toString(monstro2.getPosicao()));
        

        
    }

    public void ativarModoDebug(boolean ativar) {
        modoDebug = ativar;
        repaint();
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int larguraQuadrado = getWidth() / tamanhoTabuleiro;
    int alturaQuadrado = getHeight() / tamanhoTabuleiro;

    Celula[][] matrizTabuleiro = tabuleiro.getMatriz();

    for (int linha = 0; linha < tamanhoTabuleiro; linha++) {
        for (int coluna = 0; coluna < tamanhoTabuleiro; coluna++) {
            Celula celula = matrizTabuleiro[linha][coluna];
            Color cor = Color.WHITE;

            if (modoDebug || posicoesVisiveis[linha][coluna]) {
                if (celula.temOuro()) {
                    cor = Color.YELLOW;
                } else if (celula.temBuraco()) {
                    cor = Color.BLACK;
                } else if (celula.temMadeira()) {
                    cor = new Color(139, 69, 19);
                } 
            } else if (!posicoesVisiveis[linha][coluna]) {
                cor = Color.GRAY;
            }

            g.setColor(cor);
            g.fillRect(coluna * larguraQuadrado, linha * alturaQuadrado, larguraQuadrado, alturaQuadrado);
            g.setColor(Color.BLACK);
            g.drawRect(coluna * larguraQuadrado, linha * alturaQuadrado, larguraQuadrado, alturaQuadrado);
        }
    }

    
    if (jogador != null) {
        int linhaJogador = jogador.getPosicaoLinha();
        int colunaJogador = jogador.getPosicaoColuna();
        g.setColor(Color.GREEN);
        g.fillRect(colunaJogador * larguraQuadrado, linhaJogador * alturaQuadrado, larguraQuadrado, alturaQuadrado);
        g.setColor(Color.BLACK);
        g.drawRect(colunaJogador * larguraQuadrado, linhaJogador * alturaQuadrado, larguraQuadrado, alturaQuadrado);
    }
    if (monstro1 != null && monstro1.getVida() == true) {
        int linhaMonstro1 = monstro1.getPosicaoLinha();
        int colunaMonstro1 = monstro1.getPosicaoColuna();
        if (posicoesVisiveis[linhaMonstro1][colunaMonstro1] || modoDebug == true){
        g.setColor(Color.MAGENTA);
        g.fillRect(colunaMonstro1* larguraQuadrado, linhaMonstro1 * alturaQuadrado, larguraQuadrado, alturaQuadrado);
        g.setColor(Color.BLACK);
        g.drawRect(colunaMonstro1 * larguraQuadrado, linhaMonstro1 * alturaQuadrado, larguraQuadrado, alturaQuadrado);
        }                   
    } else{
        g.setColor(Color.WHITE);
        }
    if (monstro2 != null && monstro2.getVida() == true) {
        int linhaMonstro2 = monstro2.getPosicaoLinha();
        int colunaMonstro2 = monstro2.getPosicaoColuna();
        if (posicoesVisiveis[linhaMonstro2][colunaMonstro2] || modoDebug == true){
        g.setColor(Color.RED);
        g.fillRect(colunaMonstro2* larguraQuadrado, linhaMonstro2 * alturaQuadrado, larguraQuadrado, alturaQuadrado);
        g.setColor(Color.BLACK);
        g.drawRect(colunaMonstro2 * larguraQuadrado, linhaMonstro2 * alturaQuadrado, larguraQuadrado, alturaQuadrado);
        }                   
    } else{
        g.setColor(Color.WHITE);
    }
    
    
}


    int getTamanho() {
        return tamanhoTabuleiro;
    }


    public void atualizarPosicoesVisiveis(TabuleiroVisual tabuleiroVisual) {
        if (jogador != null) {
            
            boolean[][] posicoesVisiveis = tabuleiro.getPosicoesVisiveis(new int[]{jogador.getPosicaoLinha(), jogador.getPosicaoColuna()}, jogador.isLanternaAtiva());
            for (int linha = 0; linha < tamanhoTabuleiro; linha++) {
                for (int coluna = 0; coluna < tamanhoTabuleiro; coluna++) {
                    this.posicoesVisiveis[linha][coluna] = posicoesVisiveis[linha][coluna];
                }
            }

            repaint();
        }
        
    }

    public void marcarQuadranteComoVisivel(int linha, int coluna) {
    if (linha >= 0 && linha < tamanhoTabuleiro && coluna >= 0 && coluna < tamanhoTabuleiro) {
        posicoesVisiveis[linha][coluna] = true;
        repaint(); 
    }
    }

public void removerOuro(int linhaAtual, int colunaAtual) {
    
    if (linhaAtual >= 0 && linhaAtual < tamanhoTabuleiro && colunaAtual >= 0 && colunaAtual < tamanhoTabuleiro) {
        posicoesVisiveis[linhaAtual][colunaAtual] = false; 
        tabuleiro.getMatriz()[linhaAtual][colunaAtual].setOuro(false); 
        repaint(); 
    }
}

public void removerMadeira(int linhaAtual, int colunaAtual) {
    
    if (linhaAtual >= 0 && linhaAtual < tamanhoTabuleiro && colunaAtual >= 0 && colunaAtual < tamanhoTabuleiro) {
        posicoesVisiveis[linhaAtual][colunaAtual] = false; 
        tabuleiro.getMatriz()[linhaAtual][colunaAtual].setMadeira(false); 
        repaint();
    }
}

    public void pintarQuadranteAnterior(int linhaAnterior, int colunaAnterior) {
    if (linhaAnterior >= 0 && linhaAnterior < tamanhoTabuleiro && colunaAnterior >= 0 && colunaAnterior < tamanhoTabuleiro) {
        posicoesVisiveis[linhaAnterior][colunaAnterior] = true;
        repaint(); 
    }
    }

    void removerBuraco(int linhaAtual, int colunaAtual) {
        if (linhaAtual >= 0 && linhaAtual < tamanhoTabuleiro && colunaAtual >= 0 && colunaAtual < tamanhoTabuleiro) {
        posicoesVisiveis[linhaAtual][colunaAtual] = false; 
        tabuleiro.getMatriz()[linhaAtual][colunaAtual].setBuraco(false); 
        repaint(); 
    }
}

public void colisaoJogador(int[] newPosicao, int dano) {
      
        if(jogador.getPosicao()[0] == newPosicao[0] && jogador.getPosicao()[1] == newPosicao[1]){
            
            jogador.reduzirVida(dano);
            jogador.checarDerrota();
        }
       
             
 }
    
}

    
