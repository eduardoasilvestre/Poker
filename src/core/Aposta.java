package core;

public class Aposta {
    private int valorAposta;//valor da aposta do jogador
    private Jogador a;//jogador que fez a aposta
        
    //construtor de aposta,quando criar a aposta coloca o valor minimo no primeiro new
    public Aposta ( int valorAposta, Jogador a ) {
        this.valorAposta = valorAposta;
        this.a = a;
    }
    
    //retorna o valor atual da aposta do jogador
    public int getValorAposta() {
        return valorAposta;
    }
    
    //retorna o jogador que fez a posta
    public Jogador getJogador() {
        return a;
    }
    
    //atualiza o valor da aposta do jogador
    public void setValorAposta ( int valorAposta ) {
        this.valorAposta += valorAposta;
    }
    
    //se a aposta do jogador tiver sido aumentada e depois ele correr atualiza o valor da aposta para o valor antes de ter apostado
    public void setValorApostaErrado ( int valorAposta ) {
        this.valorAposta = valorAposta;
    }
}
