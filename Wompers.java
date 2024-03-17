package wumpusworld;

import java.util.Random;


public class Wompers extends Monstro {

    public Wompers(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, int[] posicao) {
        super(tabuleiroVisual, tabuleiro, posicao);
    }
    
   
    
@Override
public void moverMonstro() {
    if (super.getVida()) {
        Random random = new Random();
        int linhaAtual = super.getPosicaoLinha();
        int colunaAtual = super.getPosicaoColuna();

        while (true) {
            int dir = random.nextInt(8); // 8 direções possíveis

            int novaLinha = linhaAtual;
            int novaColuna = colunaAtual;

            if (dir == 0) { // Mova-se 2 casas para cima e 1 para a esquerda
                novaLinha -= 2;
                novaColuna -= 1;
            } else if (dir == 1) { // Mova-se 2 casas para cima e 1 para a direita
                novaLinha -= 2;
                novaColuna += 1;
            } else if (dir == 2) { // Mova-se 2 casas para baixo e 1 para a esquerda
                novaLinha += 2;
                novaColuna -= 1;
            } else if (dir == 3) { // Mova-se 2 casas para baixo e 1 para a direita
                novaLinha += 2;
                novaColuna += 1;
            } else if (dir == 4) { // Mova-se 1 casa para cima e 2 para a esquerda
                novaLinha -= 1;
                novaColuna -= 2;
            } else if (dir == 5) { // Mova-se 1 casa para cima e 2 para a direita
                novaLinha -= 1;
                novaColuna += 2;
            } else if (dir == 6) { // Mova-se 1 casa para baixo e 2 para a esquerda
                novaLinha += 1;
                novaColuna -= 2;
            } else if (dir == 7) { // Mova-se 1 casa para baixo e 2 para a direita
                novaLinha += 1;
                novaColuna += 2;
            }

            // Verifique se a nova posição está dentro dos limites do tabuleiro
            if (novaLinha >= 0 && novaLinha <= 14 && novaColuna >= 0 && novaColuna <= 14) {
                int[] newPosicao = { novaLinha, novaColuna };
                super.setPosicao(newPosicao);
                super.getTabuleiroVisual().colisaoJogador(newPosicao, 50);
                super.getTabuleiro().setFedor(linhaAtual, colunaAtual, false, 0);
                super.getTabuleiro().setFedor(novaLinha, novaColuna, true, 1);
                break;
            }
        }
    }
}
}
    
    
