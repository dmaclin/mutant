

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorADN {
    public static List<String> ListaSecuenciaAleatoria(int Dimension) {
        
        List<String> cadena= new ArrayList<String>();     

        Random aleatorio = new Random();

        String letrasADN = "ATCG";
        for (int j = 0; j < Dimension; j++) {
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < Dimension; i++) {
                buffer.append(letrasADN.charAt(aleatorio.nextInt(letrasADN.length())));
            }
            
            cadena.add(buffer.toString());
        }
        
        return cadena;
    }
}
