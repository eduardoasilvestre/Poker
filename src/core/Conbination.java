package core;
import javax.swing.JOptionPane;

public class Conbination {
     
     private int retorno;//vai retornar um inteiro diferente em cada combinação possivel
     
     
     public int verificaJogo ( Card a[] ) {
         if ( royal(a) )
             return retorno;
         else if ( quadra(a) )
             return retorno;
         else if ( fullHand(a) )
             return retorno;
         else if ( sequencia(a) )
             return retorno;
         else if ( trinca(a) )
             return retorno;
         else if ( doisPares(a) )
             return retorno;
         else if ( umPar(a) )
             return retorno;
         else {
             retorno = 0 + a[4].getNumero();
             return retorno;
         }
     }
     
     public String typeCombination ( int a ) {
         if ( a > 700 )
             return "ROYAL"; 
         else if ( a > 600 && a < 700 )
             return "QUADRA";
         else if ( a > 500 && a < 600 )
             return "FULL HAND";
         else if ( a > 400 && a < 500 )
             return "SEQUENCIA";
         else if ( a > 300 && a < 400 )
             return "TRINCA";
         else if ( a > 200 && a < 300 )
             return "DOIS PARES";
         else if ( a > 100 && a < 200 )
             return "UM PAR";
         else return "NADA";
     }
     
     
     public boolean royal ( Card a[] ) {
         
         for ( int i = 3; i >= 0; i-- ) {
            if (  ( ( a[4].getNumero() - a[i].getNumero() ) % 4 ) != 0)
                return false;
        }
        retorno = 700 + a[4].getNumero();
        return true;
         
    }
        
     
     public boolean quadra ( Card a[] ) {
        int count = 0;//conta o nro de cartas iguais
               
        for ( int i = 0; i < 5; i++ ) {
            if ( a[2].getFace() == a[i].getFace() )
                count++;
        }
        
        if ( count  == 4 ) {
            if ( a[2].getFace() == a[4].getFace() )
               retorno = 600 + a[4].getNumero();
            else retorno = 600 + a[3].getNumero();
            
            return true;
        }
        else return false;
    }
     
     
     public boolean fullHand ( Card a[] ) {
       //para ocorrer e trinca e par as duas primeiras cartas e as duas ultimas devem ter a mesma face
        if (  (a[0].getFace()  == a[1].getFace() ) && ( a[3].getFace() == a[4].getFace() ) ) {//apos esse teste ja tem dois pares
            if ( a[0].getFace() == a[2].getFace() ) { //se satisfeito tera uma trinca
                retorno = 500 + a[2].getNumero();
                return true;
            }
            else if ( a[2].getFace() == a[3].getFace() ) {//se satisfeito tera uma trinca
                retorno = 500 + a[4].getNumero();
                return true;
            }
        }
        return false;
    }
     
     
     public boolean sequencia ( Card a[] ) {
        int sum = 1;
        for ( int i = 0; i < ( a.length - 1); i++){
           //testa se a primeira carta da mao e de paus e se a carta seguinte esta num intervalo valido
            if ( ( a[i].getSuit() == "Clubs" ) && (a[i+1].getNumero() <=  a[i].getNumero()  + 7 ) && (a[i+1].getNumero() >= (a[i].getNumero())+4) )
                  sum++;
            if ( (a[i].getSuit() == "Spades")&& (a[i+1].getNumero() <= a[i].getNumero()+6) &&(a[i+1].getNumero() >= a[i].getNumero()+3) )
                  sum++;
            if ( (a[i].getSuit() == "Hearts")&& (a[i+1].getNumero() <= a[i].getNumero()+5) &&(a[i+1].getNumero() >= a[i].getNumero()+2) )
                  sum++;
            if ( (a[i].getSuit() == "Diamonds")&& (a[i+1].getNumero() <= a[i].getNumero()+4) &&(a[i+1].getNumero() >= a[i].getNumero()+1) )
                  sum++;
        }
        if ( sum == 5 ) {
             retorno = 400 + a[4].getNumero();
             return true;
        }
        
        return false;
    }
     
     
     public boolean trinca ( Card a[] ) {
        int aux = 0;
        int count;
        //percorre o laco em cada posicao verificando se as outras posicoes contem a mesma face da posicao pelo menos 3 vezes
        for ( int i = 0; i < 3; i++ ) {
            count = 1;
            for (int j = (i + 1); j < 5; j++ ) {
               if ( a[i].getFace() == a[j].getFace() )
                   count++;
               aux = j;
            }
            if ( count == 3) {
                retorno = 300 + a[aux].getNumero();
                return true;
            }
        }
        return false;
    }
     
     
     public boolean doisPares ( Card a[] ) {
        
         if ( a[0].getFace() == a[1].getFace() ) {
             if  ( a[2].getFace() == a[3].getFace() ) {
                 retorno = 200 + a[3].getNumero();
                 return true;
             }
             else if ( a[3].getFace() == a[4].getFace() ) {
                 retorno = 200 + a[3].getNumero();
                 return true;
             }
         }
         else if ( a[1].getFace() == a[2].getFace() ) {
                 if  ( a[3].getFace() == a[4].getFace() ) {
                    retorno = 200 + a[4].getNumero();
                    return true;
                 }
         }
         return false;
    }
     
     
     public boolean umPar ( Card a[] ) {
         
        int count = 1;
        for ( int i = 0; i < 5; i++ ) {
            for ( int j = (i+1); j < 5; j++ ) {
                if ( a[i].getFace() == a[j].getFace() ) {
                    count++;
                    if (count == 2) {
                        retorno = 100 + a[j].getNumero();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
