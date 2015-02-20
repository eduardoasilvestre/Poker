package core;

public class Card {
    
    private String face;//face da carta
    private String suit;//naipe da carta
    private int numero;//numero ascedente indicando carta menor ou maior
    
    //inicializa a face e o naipe da carta
    public Card( String cardFace, String cardSuit, int cardNumero ) {
        face = cardFace;
        suit = cardSuit;
        numero = cardNumero;
    }
    
    //construtor que nao faz nada, para criar um objeto sem usar parametros no construtor
    public Card () {
        
    }
    
    //mostra a  descricao de uma carta
    public String showCard() {
        return face + " of " + suit;
    }
    
    //obtem a face de uma carta
    public String getFace() {
        return face;
    }
    
    //obtem o naipe de uma carta
    public String getSuit() {
        return suit;
    }
    
    //obtem um nro que corresponde a carta
    public int getNumero() {
        return numero;
    }
    
}
