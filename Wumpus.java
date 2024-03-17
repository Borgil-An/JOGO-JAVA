package wumpusworld;

import java.util.Random;


public class Wumpus extends Monstro {

    public Wumpus(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, int[] posicao) {
        super(tabuleiroVisual, tabuleiro, posicao);
    }
    
 

@Override
public void moverMonstro(){
     if (super.getVida() == true){
    Random random = new Random();
    int linhaAtual = super.getPosicaoLinha();
    int colunaAtual = super.getPosicaoColuna();
    
    while(true){
        int dir = random.nextInt(4);                 // CIMA BAIXO ESQUERDA DIREITA
       if (dir == 0){
          int[] newPosicao = {linhaAtual-1,colunaAtual};
          if (linhaAtual-1 >= 0){
              super.setPosicao(newPosicao);
              super.getTabuleiro().setFedor(linhaAtual,colunaAtual, false,0);
              super.getTabuleiro().setFedor(newPosicao[0],newPosicao[1], true,1);
          }
       }
        if (dir == 1){
          int[] newPosicao = {linhaAtual+1,colunaAtual};
          if (linhaAtual+1 <= 14){
              super.setPosicao(newPosicao);
              super.getTabuleiro().setFedor(linhaAtual,colunaAtual, false,0);
              super.getTabuleiro().setFedor(newPosicao[0],newPosicao[1], true,1);
          }
        }
        if (dir == 2){
            int[] newPosicao = {linhaAtual, colunaAtual-1};
            if(colunaAtual - 1 >= 0){
                super.setPosicao(newPosicao);
                super.getTabuleiro().setFedor(linhaAtual,colunaAtual, false,0);
              super.getTabuleiro().setFedor(newPosicao[0],newPosicao[1], true,1);
            }
        }
        if (dir == 3){
            int[] newPosicao = {linhaAtual, colunaAtual+1};
            if(colunaAtual + 1 <= 14){
                super.setPosicao(newPosicao);
                super.getTabuleiroVisual().colisaoJogador(newPosicao, 100);
                super.getTabuleiro().setFedor(linhaAtual,colunaAtual, false,0);
                super.getTabuleiro().setFedor(newPosicao[0],newPosicao[1], true,1);
              
            }
        }

      break;   
    }
   
        
}
}
}
