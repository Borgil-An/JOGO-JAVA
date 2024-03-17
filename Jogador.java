package wumpusworld;

import javax.swing.JOptionPane;

public class Jogador {  // Atributos do jogador
    private int vida;
    private int flechas;
    private int danoFlechas;
    private int madeira;
    private int ouro;
    private boolean lanternaAtiva;
    private int usosLanterna;
    private int cargaLanterna;
    private int[] posicao;
    private boolean[][] espacosExplorados;
    private boolean possuiArco;
    private TabuleiroVisual tabuleiroVisual;
    private boolean[][] posicoesVisiveis;
    private Tabuleiro tabuleiro;
    private boolean vitoria;
    
    private WumpusWorld wumpusWorld;

    
    
    public Jogador(TabuleiroVisual tabuleiroVisual, Tabuleiro tabuleiro, WumpusWorld wumpusWorld){
        vida = 100; // Vida inicial e máxima = 100
        flechas = 1; // Flechas iniciais = 1
        danoFlechas = 100;
        madeira = 0; // Madeiras no começo zeradas
        ouro = 0; // O ouro é o objetivo da partida, logicamente começa em 0
        lanternaAtiva = false; // Lanterna desligada sem comando do jogador
        usosLanterna = 0; // Começa com todas cargas de lanterna disponiveis
        cargaLanterna = 2;
        posicao = new int[]{14,0}; // Posiçao inicial A-1
        possuiArco = false; // O jogador vai receber o seu arco.
        this.tabuleiroVisual = tabuleiroVisual;
        posicoesVisiveis = new boolean[tabuleiroVisual.getTamanho()][tabuleiroVisual.getTamanho()];
        espacosExplorados = new boolean[tabuleiroVisual.getTamanho()][tabuleiroVisual.getTamanho()];
        this.tabuleiro = tabuleiro;
        vitoria = false;
        this.wumpusWorld = wumpusWorld;
        
        adicionarArco();
        adicionarLanterna();
    }
    
    public int getVida(){ // Metodos para obter e atualizar os atributos do jogador com base no jogo
        return vida;
    }
    
    public void reduzirVida(int quantidade){
        vida -= quantidade;
    }
    
  
    public int getFlechas(){
        return flechas;
    }
    
    public void usarFlechas(){
        flechas--;
    }
    
    public int getDanoFlechas(){
        return danoFlechas;
    }
    
    public int getMadeira(){
        return madeira;
    }
    
    public int getUsosLanterna(){
        return usosLanterna;
    }
    
    public void adicionarMadeira(){
        madeira++;
    }
    
    public void criarFlecha(int madeiraUtilizada){
        if(madeiraUtilizada > 0 && madeira >= madeiraUtilizada){
            madeira -= madeiraUtilizada;
            flechas++;
        }
    }
    
    public int getOuro(){
        return ouro;
    }
    
    public boolean isLanternaAtiva(){
        return lanternaAtiva;
    }
    
    public int getCargaLanterna(){
        return cargaLanterna;
    }
    
    public boolean ligarLanterna(){
        if (usosLanterna < 2){
            lanternaAtiva = true;
            usosLanterna++;
            cargaLanterna--;
            return true;
        }
        return false;
    }
    
    public int[] getPosicao(){
        return posicao;
    }
    
     public void moverPara(int novaLinha, int novaColuna) {
        int linhaAtual = posicao[0];
        int colunaAtual = posicao[1];

        // Verifique se a nova posição é válida (não colide com paredes e dentro dos limites do tabuleiro)
        if (novaLinha >= 0 && novaLinha < tabuleiro.getTamanho() &&
            novaColuna >= 0 && novaColuna < tabuleiro.getTamanho()){
            
            posicao[0] = novaLinha;
            posicao[1] = novaColuna;
            
            tabuleiro.tiraRastro(linhaAtual, colunaAtual, false);
            tabuleiro.tiraRastro(novaLinha, novaColuna, true);
            tabuleiro.getPosicao();
     }
    }



    private void adicionarArco() {
        if (!possuiArco) {
             possuiArco = true; 
        }
    }

    private void adicionarLanterna() {
         if (!lanternaAtiva) {
            
            lanternaAtiva = true;
         }
    }
    
    public void coletar(int linhaAtual, int colunaAtual) {
        Celula celula = tabuleiro.getMatriz()[linhaAtual][colunaAtual];
        if(celula.temOuro() == true){
            ouro++;
            tabuleiroVisual.removerOuro(linhaAtual, colunaAtual);

        }else if (celula.temMadeira() == true){
            madeira++;
            tabuleiroVisual.removerMadeira(linhaAtual, colunaAtual);
            
        }
    }
    
    
    
    public void cairEmBuraco() {
        vida -= 100;
    }
    
    public void checarVitoria() {
    int[] posicaoAtual = getPosicao();
    int linhaAtual = posicaoAtual[0];
    int colunaAtual = posicaoAtual[1];
    
        
    if (ouro > 0 && linhaAtual == 14 && colunaAtual == 0 && vida > 0){
        JOptionPane.showMessageDialog(null, "Parabéns você venceu");
        int opcao = JOptionPane.showConfirmDialog(
            null,
            "Deseja jogar novamente?",
            "Fim de Jogo",
            JOptionPane.YES_NO_OPTION
        );
        if (opcao == JOptionPane.YES_OPTION) {

            wumpusWorld.reiniciarJogo();
        } else {
            // Encerrar o programa
            System.exit(0);
        

    }
    
    }
    }
    
    public void verificarPercepcoes() {
    int[] posicaoAtual = getPosicao();
    int linhaAtual = posicaoAtual[0];
    int colunaAtual = posicaoAtual[1];

    // Verifique as posições adjacentes para buracos e monstros
     if (linhaAtual > 0) {
        Celula celula = tabuleiro.getMatriz()[linhaAtual - 1][colunaAtual];
        if (celula.temBuraco() == true) {
            
            JOptionPane.showMessageDialog(null, "Você sente uma brisa.");
        }  if (celula.temMonstro() != false) {
           
            JOptionPane.showMessageDialog(null, "Você sente um fedor.");
           
        }
    }

     if (linhaAtual < 14) {
        Celula celula = tabuleiro.getMatriz()[linhaAtual + 1][colunaAtual]; 
        if (celula.temBuraco() == true) {
            JOptionPane.showMessageDialog(null, "Você sente uma brisa.");
        }  if (celula.temMonstro() != false) {
            
            JOptionPane.showMessageDialog(null, "Você sente um fedor.");
            
        }
    }

     if (colunaAtual > 0) {
        Celula celula = tabuleiro.getMatriz()[linhaAtual][colunaAtual - 1]; 
        if (celula.temBuraco()==true) {
            JOptionPane.showMessageDialog(null, "Você sente uma brisa.");
        }  if (celula.temMonstro() != false) {
            
            JOptionPane.showMessageDialog(null, "Você sente um fedor.");
            
        }
    }

    if (colunaAtual < 14) {
        Celula celula = tabuleiro.getMatriz()[linhaAtual][colunaAtual + 1]; 
        if (celula.temBuraco()==true) {
            JOptionPane.showMessageDialog(null, "Você sente uma brisa.");
        }  if (celula.temMonstro() != false) {
            
            JOptionPane.showMessageDialog(null, "Você sente um fedor.");
           
        }
    }

   // Verifique a posição atual para ouro e madeira
    Celula celulaAtual = tabuleiro.getMatriz()[linhaAtual][colunaAtual];
    if (celulaAtual.temOuro() == true) {
        JOptionPane.showMessageDialog(null, "Você encontrou ouro!");
         
    } 
       
        Celula[][] celulaAtual2 = tabuleiro.getMatriz();
     if (celulaAtual2[linhaAtual][colunaAtual].temMadeira()) {
         
         JOptionPane.showMessageDialog(null, "Você encontrou madeira!");
         
    }
}
    

    public boolean isDerrotado() {
    return vida <= 0;
    }   

    boolean isPosicaoVisivel(int linha, int coluna) {
        return posicoesVisiveis[linha][coluna];    
    }

    public int getPosicaoLinha() {
    return posicao[0];
}

public int getPosicaoColuna() {
    return posicao[1];
}

public boolean getVitoria(){
    return vitoria;
}

 void removerMadeira() {
        madeira--;
    }

    void tamparBuraco(int linhaAtual, int colunaAtual) {
        Celula celula = tabuleiro.getMatriz()[linhaAtual][colunaAtual];
        if(celula.temBuraco() == true){
            tabuleiroVisual.removerBuraco(linhaAtual, colunaAtual);
    }
     } 

    public void checarDerrota() {
    if (vida <= 0){
       JOptionPane.showMessageDialog(null, "Você morreu");
        int opcao = JOptionPane.showConfirmDialog(
            null,
            "Deseja jogar novamente?",
            "Fim de Jogo",
            JOptionPane.YES_NO_OPTION
        );
        if (opcao == JOptionPane.YES_OPTION) {

            wumpusWorld.reiniciarJogo();
        } else {
            // Encerrar o programa
            System.exit(0);
        

    }
    }
    }
}
