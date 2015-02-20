package core;

public class Jogador {
    private String nome;
    private int saldo;
    private final int MAX_FICHAS = 500;
    private Card a[];//contera as cartas na mao do jogador
    private Card c;//uma carta auxiliar
    private boolean estouNaJogada;
    
    //o construtor do jogador
    public Jogador ( String nome ) {
        this.nome = nome;
        saldo = MAX_FICHAS;
        estouNaJogada = false;//no comeco o jogador nao esta em uma jogada
    }
    
    //cartas na mao do jogador
    public void naMao( DeckOfCards b ) {
        a = new Card[5];
        c = new Card();
        for ( int i = 0; i < 5; i++ ) {
            c = b.dealCard();//c contera uma carta do baralho
            a[i] = new Card(c.getFace(),c.getSuit(),c.getNumero());//preenche as cartas na mao do jogador
            
        }
    }
    
    //retorna o nome do jogador
    public String getNome() {
        return nome;
    }
    
    //retorna o saldo do jogador
    public int getSaldo() {
        return saldo;
    }
    
    //retorna as cartas que o jogador tem
    public Card [] getCartas () {
        return a;
    }
    
    public void setCartas( Card b[] ) {
        a = b;
        
    }
    
    public boolean getEstouNaJogada() {
        return estouNaJogada;
    }
    
    //altera o status se um jogador vai estar na jogada ou nao
    public void setEstouNaJogada( boolean recebe ) {
        estouNaJogada = recebe;
    }
    
    public int getMAX_FICHAS() {
        return MAX_FICHAS;
    }
    
    //aumenta o saldo do jogador
    public void setAumentaSaldo( int valor ) {
        saldo += valor;
    }
    
    //diminui o saldo do jogador
    public void setDiminuiSaldo ( int valor ) {
        saldo -= valor;
    }
    
    //vai ordenar as cartas na mao do jogador
    public void toCommand ( Card a[] ) {
        Card d;
        
        for (int i = 1; i < a.length; i++ ) {
            for (int j = 0; j < a.length - 1; j++) {
                if ( a[j].getNumero() > a[j+1].getNumero() ) {
                    d = a[j];
                    a[j] = a[j+1];
                    a[j+1] = d;  
                }
            }
        }
    }
    
}
