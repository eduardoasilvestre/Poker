package inputoutput;

import core.Conbination;
import core.Aposta;
import core.Card;
import core.DeckOfCards;
import core.Jogador;

import javax.swing.JOptionPane;

public class Interface {
    private int nroJogadores;//contem o nro de jogadores inicialmente no jogo
    private Jogador jogadores[];//define o array de jogadores
    private DeckOfCards b;//baralho do jogo
    private Card aux[];//captura as cartas do jogador
    private Card other;//usada para imprimir as cartas do jogador
    private Card aux1[];//usado para trocar as cartas dos jogadores
    private Aposta bet[];//contera as apostas dos jogadores
    private Conbination x;
    private int jogadoresPositivos;//contem o nro de jogadores que estao no jogo
        
    //metodo contrutor
    public Interface() {
        nroJogadores = nroJogadores();//nroJogadores contem os jogadores a serem criados
        criaJogadores();
        jogadoresPositivos = nroJogadores;
        do {
           distribuiCartas(nroJogadores);
           statusInicial();
           swapCards();
           interacaoApostas();
           JOptionPane.showMessageDialog(null,"O VENCEDOR DA APOSTA FOI O JOGADOR ==> " + jogadores[jogoGeral()].getNome() + " <== PARABENS");
        }while (jogadoresPositivos != 1);
    }
    
    //captura o nro de jogadores
    public int nroJogadores() {
        boolean aux = true;
        int aux1=0;
        while (aux) {
          aux1 = Integer.parseInt(JOptionPane.showInputDialog("ENTRE COM O NRO DE JOGADORES A SER CRIADO, ESSE NRO DEVE ESTAR ENTRE 2 E 4"));  
          if ( (aux1 == 2) || (aux1 == 3) || (aux1 == 4) )
              aux = false;
        }
        return aux1;
    }
    
    //cria os objetos jogadores
    public void criaJogadores() {
        jogadores = new Jogador[nroJogadores];
        for ( int i = 0; i < nroJogadores; i++ )
            jogadores[i] = new Jogador(JOptionPane.showInputDialog("ENTRE COM O NOME DO JOGADOR " + (i + 1) + " !!!"));
    }
    
    //mostra o estado inicial de como começar o jogo
    public void statusInicial() {
        String tela = "";
        tela += "O JOGO COMEÇA DA SEGUINTE MANEIRA !!!" + "\n\n";
        for ( int i = 0; i < nroJogadores; i++)
            tela += "O JOGADOR  " + jogadores[i].getNome() + "  TÊM " + jogadores[i].getSaldo() + " FICHAS !!!" + "\n";
        
        tela += "\n\n" + "*****     O VALOR MINIMO DAS APOSTAS SÃO 5 FICHAS !!    *****";
        tela += "\n"  + "***** OU SEJA, TODAS AS APOSTAS VALERÃO NO MÍNIMO 5 FICHAS *****";
        tela += "\n\n" + "*****   AGORA OS JOGADORES PODERÃO AUMENTAR AS APOSTAS   *****";
        JOptionPane.showMessageDialog(null,tela);
    }
    
    //distribui cartas aos jogadores
    public void distribuiCartas( int nroJogadores ) {
        b = new DeckOfCards();//cria uma instancia de um novo baralho
        b.shuffle();//b sera uma referencia para um baralho embaralhado
        for ( int i =  0; i < nroJogadores; i++ ) {
            jogadores[i].naMao(b);//coloca as cartas na mao do jogador
            jogadores[i].toCommand(jogadores[i].getCartas());//ordena as cartas na mao do jogador
        }
    }
    
    //imprime as cartas de um jogador especifico
    public void imprimeCartas( int i ) {
        String help = "";//usado para imprimir as cartas dos jogadores
        help += "===>      JOGADOR " +(i+1) + " SUAS CARTAS SAO      <===\n\n";
        aux = new Card[5];
        for ( int w = 0; w < 5; w++ )
           aux[w] = new Card();
        help +="POSICAO DA CARTA"+"                     "+"NOME DA CARTA\n\n";
        aux = jogadores[i].getCartas();
        other = new Card();
        for  ( int j = 0; j < 5; j++ ) {
            other = aux[j];
            help +="              " + (j+1) +"     ---------------------->      "+ other.showCard() + "\n"; 
        }
        help += "\n";
        JOptionPane.showMessageDialog(null,help,"CARTAS DO USUARIO",JOptionPane.INFORMATION_MESSAGE);
    }
    
    //troca as cartas indesejadas dos jogadores
    public void swapCards () {
        
        boolean z;
        int cartasTrocadas;//o jogador pode trocar no maximo 3 cartas
        JOptionPane.showMessageDialog(null,"==>  AGORA IREMOS TROCAR AS CARTAS INDESEJADAS DOS JOGADORES  <==\n\nOBSERVACAO : VOCÊ PODE TROCAR NO MÁXIMO 3 CARTAS !!!\n");
        for ( int i = 0; i < nroJogadores; i++ ) {
            cartasTrocadas = 0;
            z = false;
            //JOptionPane.showMessageDialog(null,"JOGADOR " +(i+1) + " SUAS CARTAS SAO:\n\n");
            imprimeCartas(i);
            //ACIMA IMPRIMIU AS CARTAS DOS JOGADORES COM AS RESPECTIVAS POSICOES
            for ( int j = 0; j < 5; j++ ) {
                
                String option = JOptionPane.showInputDialog(null,"DESEJA TROCAR A CARTA DA POSICAO " + (j+1) + "?\n\nS PARA TROCAR A CARTA  |  N PARA NAO TROCAR.");
                if ( option.toUpperCase().compareTo("S") == 0 ) {
                    if ( cartasTrocadas < 3) {//se cartasTrocadas for igual a 3 ele nao pode mais trocar cartas
                        aux1 = new Card[5];//aux1 objeto que recebera as cartas do jogador
                        for ( int k = 0; k < 5; k++ )
                            aux1[k] = new Card();
                        aux1 = jogadores[i].getCartas();//aux1 recebe as cartas atuais dos jogadores
                        aux1[j] = b.dealCard();//a posicao correta contera a nova carta
                        jogadores[i].setCartas(aux1);//as cartas do jogador seram atualizadas corretamente
                        cartasTrocadas++;//marca o nro de cartas trocadas
                    }
                    else JOptionPane.showMessageDialog(null,"==>  VOCE JA TROCOU AS 3 CARTAS QUE PODERIA  <==");
                    z = true;
                    
                }
            }
            jogadores[i].toCommand(jogadores[i].getCartas());
            if ( z == true ) {//se alguma carta tiver sido trocada do jogador imprime essa msg
                JOptionPane.showMessageDialog(null,"SUAS CARTAS APÓS A TROCA AGORA SAO !!!");
                imprimeCartas(i);
            }
            else JOptionPane.showMessageDialog(null,"NAO FOI TROCADA NENHUMA CARTA DO JOGADOR " + (i+1) + " !!!");
        }
    }
    
    //mostra o status de quem esta apostando atualmente
    public void statusApostas ()  {
        String historico = "";
        historico = "AS APOSTAS ATUALMENTE ESTAO DA SEGUINTE MANEIRA !!!\n\n\n";
        for ( int j = 0; j < nroJogadores; j++ ) {
            if ( jogadores[j].getEstouNaJogada() )
                historico += "O JOGADOR "+ (j+1) +" (NOME = " + jogadores[j].getNome()+ ")" + " ESTÁ APOSTANDO "+ bet[j].getValorAposta() + " FICHAS\n";
        }       
        historico += "\n\n" + "====>   OBSERVAÇÃO MUITO IMPORTANTE: SE UM JOGADOR TIVER AUMENTADO A APOSTA\n";
        historico += "E VOCE PELO MENOS NÃO EMPATAR COM A APOSTA DELE VOCE ESTARA CORRENDO DO JOGO    <====";
        
        JOptionPane.showMessageDialog(null,historico,"STATUS ATUAL DAS APOSTAS",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public int ioAposta ( int i ) {
        String show = "";
        int cap = 0;
        show += "JOGADOR " + (i+1) +" (NOME = " + jogadores[i].getNome()+ ")" + " DESEJA AUMENTAR SUA APOSTA ?\n\n\n";
        show += "*** SIM *** ==> ENTÃO DIGITE O VALOR QUE VOCÊ DESEJA AUMENTAR SUA APOSTA !\n\n";
        show += "*** NÃO *** ==> ENTÃO DIGITE 0 (ZERO) PARA NÃO AUMENTAR SUA APOSTA !\n\n";
        show += "==> SE SUA APOSTA FOR MENOR QUE A APOSTA DE UM OUTRO JOGADOR QUE JA APOSTOU, VOCE ESTARA CORRENDO DA MAO <==\n\n\n";
        show += "==> VOCÊ DEVE TER DINHEIRO SUFICIENTE PARA FAZER A APOSTA SENAO ELA NAO SERA VALIDADA <==";
        cap = Integer.parseInt(JOptionPane.showInputDialog(null,show,"DESEJA AUMENTAR SUA APOSTA",JOptionPane.QUESTION_MESSAGE));
        
        while ( ( jogadores[i].getSaldo() - ( cap + bet[i].getValorAposta() ) ) < 0 ) {
            cap = Integer.parseInt(JOptionPane.showInputDialog(null,show,"VOCE NAO TEM DINHEIRO SUFICIENTE, TENTE UMA APOSTA MENOR",JOptionPane.QUESTION_MESSAGE));
        }
        
        return cap;//retorna um valor coerente de aposta para o jogador
    }
    
    
    public void interacaoApostas () {
        int i = 0;//usado para passar pelos jogadores
        
        int jogadoresNoJogo = nroJogadores;//quando os jogadores forem desisitindo sera decrementado
        
        int aux;//recebe o valor da aposta atual do jogador
        
        int nroDePassadas = 0;
        
        bet = new Aposta[nroJogadores];//criou o array de apostas de cada um dos jogadores
        
        //inicializa as apostas iniciais dos jogadores como sendo 5 arbitrariamente
       for ( int j = 0; j < nroJogadores; j++ )
            bet[j] = new Aposta(5,jogadores[j]);
        
       for ( int last  = 0; last < nroJogadores; last++ ) {
            if ( jogadores[last].getSaldo() >= 5 )
               jogadores[last].setEstouNaJogada(true);
        }
        
        do {
            //só pode fazer apostas se estiver na jogada e no inicio todos os jogadores estao na jogada
            if ( jogadores[i].getEstouNaJogada() ) {
                
                //contem o valor da aposta antes da possivel nova aposta, ou seja, aposta atual do jogador i
                aux = bet[i].getValorAposta();
                
                //mostra os valores atuais das apostas
                statusApostas();
                
                //atualiza o valor da aposta do jogador na posicao i
                bet[i].setValorAposta(ioAposta(i));
                
               //se o jogador nao aumentou a aposta ou aposta menor que maior aposta e alguem ja apostou
                if ( alguemApostou() && ( ( bet[i].getValorAposta() < maiorAposta() ) ))   {
                    jogadores[i].setEstouNaJogada(false);//retira o jogador da jogada
                    bet[i].setValorApostaErrado(5);//o jogador perdera o valor anterior e o valorAposta sera o anterior a apoosta que nao foi aceita
                    jogadoresNoJogo--;
                    JOptionPane.showMessageDialog(null,"APOSTA INSUFICIENTE: JOGADOR " + (i+1) + " CORREU !!! ");
                }
                    
            }
            nroDePassadas++;
            
            //incremeta para o proxixo jogador
            if ( i == nroJogadores - 1 )
                i = 0;
            else i = ((i % nroJogadores) + 1 );
            
        } while ( ( ! ( apostasIguais()  && ( nroDePassadas >= nroJogadores )  ) ) );
    }
    
    //retorna o maior valor de aposta entre os jogadores
    public int maiorAposta() {
        int cap = bet[0].getValorAposta();//o valor de cap e inciado como sendo o valor da aposta do jogador 0
        for ( int i = 1; i < nroJogadores; i++ ) {
            if ( cap < bet[i].getValorAposta() )
                cap = bet[i].getValorAposta();
        }
        return cap;//cap retorna o valor da maior aposta
    }
    
    //se alguem tiver apostado (retorno = true) então o jogador nao vai pular a vez e sim correr
    public boolean alguemApostou() {
        for (int x = 0; x < nroJogadores; x++ ) {
            //se o valor atual for diferente da aposta inicial da aposta retorna true
            if (!(bet[x].getValorAposta()== jogadores[x].getMAX_FICHAS()))
                return true;
        }
        return false; 
    }
    
    //verifica as apostas dos jogadores que estao no jogo, se algum valor de apostas for diferente retorna false, se forem iguais retorna true
    public boolean apostasIguais() {
        boolean a[];
        a = new boolean[nroJogadores];
        
        //se o jogador tiver na jogo coloca true na posicao do array
        for ( int i = 0; i < nroJogadores; i++ ) {
            if ( jogadores[i].getEstouNaJogada() == true )
                a[i] = true;
        }
        
        for ( int i = 0; i < nroJogadores; i++ ) {
           if ( a[i] == true  ) {
               for ( int j = i; j < nroJogadores; j++ ) {
                  if ( a[j] == true ) {
                     if ( ! ( bet[i].getValorAposta() == bet[j].getValorAposta() ) )
                        return false;
                   
                  }
                   
               }
                   
           }
        }        
        return true;
    }
    
    //vai retornar a posicao do jogador vencedor da mao, calcular a combinação e mostrar a combinação
    public int jogoGeral () {
        int incrementa = 0;//conta o nro de jogadores que estao na jogada
    
        int resposta[];//array que contem o nro de jogadores que ainda estao na jogada
        
        int maior = 0;//ajudar a marcar a posicao do maior jogo
        
        int help = 0; //marca a posicao do maior jogo
        
        int marca = 0;
        x = new Conbination();
        
        //marca o nro de jogadores que ficaram na jogada depois do final das apostas
        for ( int j = 0; j < nroJogadores; j++ ) {
           if ( jogadores[j].getEstouNaJogada() )
              incrementa++;//aumenta o nro de jogadores que tem apostas validadas
        }
    
        //e necessario colocar uma variavel de instancia da classe que verificara as apostass
     
        resposta = new int[incrementa];//um array com o nro de jogadores que ainda estao na jogada
     
        //laço vai verificar o jogo de cada um dos jogadores e marcar a posicao do maior jogo
        for ( int i = 0; i < nroJogadores; i++ ) {
            if ( jogadores[i].getEstouNaJogada() ) {
                
                resposta[marca] = x.verificaJogo( jogadores[i].getCartas() );//recebe um nro que vai identificar as cartas dos jogadores
                showCombination ( resposta[marca], i );//resposta[marca] vai passar um inteiro para ser comparado
                if ( maior < resposta[marca] ) {
                   maior = resposta[marca]; //maior sera o valor do maior jogo
                   help = i; //help sera a posicao do maior jogo
                }
                marca++;
            }
        }
        atualizaSaldos(help);        
        return help;
    }
    
    public void showCombination ( int a, int i ) {
        String tela = "";
        tela += "COMBINAÇÃO DO JOGADOR !\n\n";
        tela += "JOGADOR " + (i+1) + " VOCE TEM: " + x.typeCombination(a) + " !!!";
        JOptionPane.showMessageDialog(null,tela,"INFORMACAO DA COMBINAÇÃO",JOptionPane.INFORMATION_MESSAGE);
            
    }
    
    
    //atualiza os saldos dos jogadores de acordo com a vitoria ou derrota na mao
    public int atualizaSaldos( int posicao ) {
        int sum = 0;//contem a soma dos valores a ser repassado ao vencedor da mao
        
        for ( int i = 0; i < nroJogadores; i++ ) {
            if ( i != posicao ) {
                sum += bet[i].getValorAposta();
                jogadores[i].setDiminuiSaldo(bet[i].getValorAposta());
            
            }
        }
        jogadores[posicao].setAumentaSaldo(sum);//atualiza o saldo do jogador vencedor
        JOptionPane.showMessageDialog(null,"O MONTANTE EM DINHEIRO QUE O JOGADOR GANHOU FOI " + sum + " E O VENCEDOR E --> TAM TAM TAM:" );
        return sum;
    }
        
        
        
    
    public static void main( String args[] ){
        new Interface();
    }
}
