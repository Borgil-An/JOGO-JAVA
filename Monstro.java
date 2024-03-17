package wumpusworld;


public abstract class Monstro {
    private int[] posicao;
    private Tabuleiro tabuleiro;
    private TabuleiroVisual tabuleiroVisual;
    private boolean vida;
    

    public Monstro(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, int[] posicao) {
        this.posicao = posicao;
        this.tabuleiro = tabuleiro;
        this.tabuleiroVisual = tabuleiroVisual;
        this.vida = true;
    }

    public int[] getPosicao() {
        return posicao;
    }
    
    public int getPosicaoLinha() {
    return posicao[0];
}

    public int getPosicaoColuna() {
    return posicao[1];
}

    public void moverMonstro() {
    }
    
    public void setPosicao(int [] posicao) {
        this.posicao = posicao;
    }
    
    public Tabuleiro getTabuleiro(){
        return tabuleiro;
    }

    public void setMorto(){
        vida = false;
    }
    
    public boolean getVida(){
        return vida;
    }

    public TabuleiroVisual getTabuleiroVisual() {
        return tabuleiroVisual;
    }

   
    

}
