package wumpusworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Tabuleiro {
    private int tamanho;
    private Celula[][] matriz;
    private int[][][] posicao;
    private List<int[]> posicoesObjetos;

     public Tabuleiro(int tamanho, int[] monstro1, int[] monstro2) {
        this.tamanho = tamanho;
        matriz = new Celula[tamanho][tamanho];
        posicao = new int[tamanho][tamanho][7];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                for (int k = 0; k < 7; k++) {
                    matriz[i][j] = new Celula();
                    posicao[i][j][k] = 0;
                }
            }
        }
        posicoesObjetos = new ArrayList<>();
        posicionarJogador();
        posicionarMonstros(monstro1,monstro2); // Inicialize os monstros
        configurarTabuleiroAleatoriamente();
    }

    private void configurarTabuleiroAleatoriamente() {
        Random random = new Random();

        // Coloque o ouro em uma posição aleatória
        int linhaOuro = random.nextInt(tamanho);
        int colunaOuro = random.nextInt(tamanho);
        matriz[linhaOuro][colunaOuro].setOuro(true);
        posicao[linhaOuro][colunaOuro][0] = 0;

        // Coloque buracos em posições aleatórias
        for (int i = 0; i < 9; i++) {
            int linhaBuraco;
            int colunaBuraco;
            do {
                linhaBuraco = random.nextInt(tamanho);
                colunaBuraco = random.nextInt(tamanho);
            } while (matriz[linhaBuraco][colunaBuraco].temBuraco());
            matriz[linhaBuraco][colunaBuraco].setBuraco(true);
            posicao[linhaBuraco][colunaBuraco][3] = 3;

        }
        

        // Coloque madeiras em posições aleatórias
        for (int i = 0; i < 5; i++) {
            int linhaMadeira;
            int colunaMadeira;
            do {
                linhaMadeira = random.nextInt(tamanho);
                colunaMadeira = random.nextInt(tamanho);
            } while (matriz[linhaMadeira][colunaMadeira].temMadeira());
            matriz[linhaMadeira][colunaMadeira].setMadeira(true);
            posicao[linhaMadeira][colunaMadeira][5] = 5;

        }
    }

    private void posicionarJogador() {
        matriz[14][0].setJogador(true);
        posicao[14][0][4] = 4; 
    }
    
    private void posicionarMonstros(int[] monstro1, int[] monstro2){
        matriz[monstro1[0]][monstro1[1]].setTipoMonstro(1);
        matriz[monstro2[0]][monstro2[1]].setTipoMonstro(2);
        matriz[monstro1[0]][monstro1[1]].setMonstro(true);
        matriz[monstro2[0]][monstro2[1]].setMonstro(true);
        
       
        
        posicao[monstro1[0]][monstro1[1]][1] = 1;
        posicao[monstro2[0]][monstro2[1]][2] = 2;
    }
 
    public int getTamanho() {
        return tamanho;
    }
     
    public int[][][] getPosicao() {
        return posicao;
    }

    public Celula[][] getMatriz() {
        return matriz;
    }

    public boolean[][] getPosicoesVisiveis(int[] posicaoJogador, boolean lanternaAtiva) {
        boolean[][] posicoesVisiveis = new boolean[tamanho][tamanho];

        int linhaJogador = posicaoJogador[0];
        int colunaJogador = posicaoJogador[1];

        if (lanternaAtiva) {
            if (linhaJogador > 0) {
                posicoesVisiveis[linhaJogador - 1][colunaJogador] = true;
            }
            if (linhaJogador < tamanho - 1) {
                posicoesVisiveis[linhaJogador + 1][colunaJogador] = true;
            }
            if (colunaJogador > 0) {
                posicoesVisiveis[linhaJogador][colunaJogador - 1] = true;
            }
            if (colunaJogador < tamanho - 1) {
                posicoesVisiveis[linhaJogador][colunaJogador + 1] = true;
            }
        } else {
            posicoesVisiveis[linhaJogador][colunaJogador] = true;
        }

        return posicoesVisiveis;
    }

    private boolean posicaoValida(int[] indice) {
        int linha = indice[0];
        int coluna = indice[1];
        return linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho && !matriz[linha][coluna].temObjeto();
    }

    public List<int[]> getPosicoesObjetos() {
        return posicoesObjetos;
    }
   
    int[] getObjetosColunas(int coluna) {
    int[] objetosNaColuna = new int[tamanho];

    for (int linha = 0; linha < tamanho; linha++) {
        objetosNaColuna[linha] = posicao[linha][coluna][0];
    }

    return objetosNaColuna;
}

    int[] getObjetosLinhas(int linhaAtual) {
    int[] objetosNaLinha = new int[tamanho];

    for (int coluna = 0; coluna < tamanho; coluna++) {
        objetosNaLinha[coluna] = posicao[linhaAtual][coluna][0];
    }

    return objetosNaLinha;
    }

    public int verificarAtaqueFlecha(int linha, int coluna) {
        
    if (matriz[linha][coluna].temMonstro() == true ) {
        if (matriz[linha][coluna].getTipoMonstro() == 1){
            
        matriz[linha][coluna].setMonstro(false);
        posicao[linha][coluna][1] = 6;
        return 1;
        }
        if (matriz[linha][coluna].getTipoMonstro() == 2){
            
        matriz[linha][coluna].setMonstro(false);
        posicao[linha][coluna][2] = 6;
        return 2;
    }
    }
    return 6;
    }   
    
    
    public void setFedor(int linhaAtual, int colunaAtual, boolean valor, int tipo){
        
       
            matriz[linhaAtual][colunaAtual].setMonstro(valor);
            matriz[linhaAtual][colunaAtual].setTipoMonstro(tipo);
        
    }

   

    void tiraRastro(int novaLinha, int novaColuna, boolean b) {
        matriz[novaLinha][novaColuna].temJogador(b);
    }
    
    
}
