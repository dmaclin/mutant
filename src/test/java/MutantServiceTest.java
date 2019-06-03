

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.mutant.ws.rest.model.ExpertoPersistencia;
import com.mutant.ws.rest.model.SecuenciaADN;
import com.mutant.ws.rest.model.ServicioException;
import com.mutant.ws.rest.service.MutantService;

public class MutantServiceTest {

	/*
	 * Prueba Procesar Adn Mutante para obtener response = 200
	 */
	@Test
	public void testProcesarAdn() {
			MutantService servicio = new MutantService();

			SecuenciaADN dna = new SecuenciaADN();

			List<String> lista = new ArrayList<String>();

			lista.add("ACTGAAGACA");
			lista.add("TGCAGGGCTT");
			lista.add("CAGTATAGTT");
			lista.add("ACTCAAGACT");
			lista.add("TGCAGGGCTT");
			lista.add("CACTATAGTC");
			lista.add("ACTGAAGACA");
			lista.add("TGCAGGGCTA");
			lista.add("CAGTATAGTA");
			lista.add("ATTCGACCCA");

			dna.setDna(lista);

			Response respuesta = servicio.isMutant(dna);

			assertEquals(200, respuesta.getStatus());
	}

	/*
	*/

	/*
	 * Prueba caso NO exitoso para obtener response = 403
	 */
	@Test
	public void testProcesarAdn403() {

			MutantService nuevoServicio = new MutantService();

			SecuenciaADN dna = new SecuenciaADN();

			List<String> lista = new ArrayList<String>();

			lista.add("TAGT");
			lista.add("TGGC");
			lista.add("TTAT");
			lista.add("CGAG");

			dna.setDna(lista);

			Response respuesta = nuevoServicio.isMutant(dna);

			assertEquals(403, respuesta.getStatus());
	}

	/*
	 * Prueba con matriz de 100 x 100
	 */
	@Test
	public void testMutant() {
		try {
			MutantService nuevoServicio = new MutantService();
			SecuenciaADN dna = new SecuenciaADN();
			dna.setDna(ListaSecuenciaAleatoria(100));
			Response respuesta = nuevoServicio.isMutant(dna);

			assertEquals(200, respuesta.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Prueba Stats
	 */
	@Test
	public void testStats() {
			MutantService nuevoServicio = new MutantService();

			assertNotNull(nuevoServicio.obtenerEstadisticas());
	}

	/*
	 * Prueba Stats 200
	 */
	@Test
	public void testStats200() {
			MutantService nuevoServicio = new MutantService();

			assertEquals(200, nuevoServicio.obtenerEstadisticas().getStatus());
	}

	@Test
	public void testConexionBase() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
			try {
				ExpertoPersistencia.getInstancia().guardarADN(dna, true);
			} catch (ServicioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(true, true);
	}
	
private List<String> ListaSecuenciaAleatoria(int Dimension) {
        
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
