package core;
import java.util.Random;

public class DeckOfCards {
    private Card deck[];
    private int currentCard;
    private final int NUMBER_OF_CARDS = 32;
    private Random randomNumbers;
        
    //contrutor de DeckOfCards
    public DeckOfCards() {
        
        String faces[]=  {"Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
        String suits[] = {"Clubs","Spades","Hearts","Diamonds"};
        
        deck = new Card[NUMBER_OF_CARDS];//cria um array de Card
        currentCard = 0;//primeira carta a ser distribuida sera deck[0]
        randomNumbers = new Random();//cria um objeto de Random
        int count = 0;
        int aux = 1;
        //preenche o baralho com objetos do tipo Card
        
            for ( int a = 0; a < 8; a++ )
               for ( int b = 0; b < 4; b ++ ) {
                  deck[count] = new Card (faces[a], suits[b], aux );
                  count++;
                  aux++;
               }
    }
       
    
    
    //embaralha as cartas
    public void shuffle() {
        currentCard = 0;//depois de embaralhar a distribuicao deve comecar em deck[0]
        for ( int first = 0; first < deck.length; first++ ) {
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
            //compara carta atual com carta aleatoriamente selecionada
            Card temp = deck[first];//carta temporaria vai ser a carta na posicao first
            deck[first] = deck[second];//a primeira carta vai ser a carta na posicao second
            deck[second] = temp;//carta second vai ser a carta temp (temp era a carta que estava em first)
        }
    }
    
    //distribui uma carta
    public Card dealCard() {
        if ( currentCard < deck.length )//se todas as cartas nao estiverem sido distribuidas
            return deck[currentCard++];//retorna a carta atual depois incrementa
        return null;//retorna null para indicar que todas as cartas foram distribuidas
    }
    
}
