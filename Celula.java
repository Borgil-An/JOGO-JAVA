package wumpusworld;


public class Celula {

    
    private boolean ouro;
    private boolean buraco;
    private boolean madeira;
    private boolean jogador;
    private boolean monstro;
    private int tipoMonstro;
    private boolean foiVisitado;
    

  
    public Celula() {
        
        ouro = false;
        
        monstro = false;
        buraco = false;
        madeira = false;
        jogador = false;
        tipoMonstro = 0;
        foiVisitado = false;
        
    }

    public boolean temOuro() {
        return ouro;
    }

    public void setOuro(boolean ouro) {
        this.ouro = ouro;
    }

    public boolean temBuraco() {
        return buraco;
    }

    public void setBuraco(boolean buraco) {
        this.buraco = buraco;
    }

    public boolean temMadeira() {
        return madeira;
    }

    public void setMadeira(boolean madeira) {
        this.madeira = madeira;
    }

    public void removerObjeto() {
        ouro = false;
        buraco = false;
        madeira = false;
        jogador = false;
        tipoMonstro = 6;
    }

    public int getTipoObjeto() {
        if (ouro) {
            return 0; // Ouro
        } else if (buraco) {
            return 3; // Buraco
        } else if (madeira) {
            return 5; // Madeira
        } else if (jogador) {
            return 4; // Jogador
        } else if (tipoMonstro > 0) {
            return tipoMonstro; // Monstro com tipo específico (1 ou 2)
        } else {
            return 6; // Nenhum objeto
        }
    }

    public boolean temObjeto() {
        return ouro || buraco || madeira || jogador || tipoMonstro > 0;
    }
    
   

    public void setJogador(boolean jogador) {
        this.jogador = jogador;
    }

    public void setTipoMonstro(int tipoMonstro) {
        this.tipoMonstro = tipoMonstro;
    }
    
    public boolean temMonstro(){
        return monstro;
    }
    
    public void setMonstro(boolean monstro){
        this.monstro = monstro;
    }

    

    public void removerBuraco() {
    buraco = false;
    }

    public void removerMonstro() {
       monstro = false; 
    }

    

    public void temJogador(boolean par) {
        jogador = par;
    }

    int getTipoMonstro() {
        return tipoMonstro;
    }
    
    

    
    
}
