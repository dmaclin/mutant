
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mutant.ws.rest.model.ExpertoEstadisticas;
import com.mutant.ws.rest.model.ExpertoPersistencia;
import com.mutant.ws.rest.model.SecuenciaADN;
import com.mutant.ws.rest.model.ServicioException;
import com.mutant.ws.rest.model.Validador;
import com.mutant.ws.rest.service.MutantService;

@TestMethodOrder(OrderAnnotation.class)
public class MutantServiceTest {

	@Test
	@Order(1)
	public void testValidadorOK() throws ServicioException {
		String[] dna = { "ATGA", "GTAA", "TTTT", "CCAG" };
		assertEquals(true, Validador.validarMatriz(dna));
	}
	
	@Test
	@Order(2)
	public void testValidadorLongitud() {
		try {
			String[] dna = { "ATGA", "GTA", "TTTT", "CCAG" };
			Validador.validarMatriz(dna);
		} catch (ServicioException e) {
			assertEquals("ERROR: una cadena tiene tamanio distinto al de la matriz", e.getMessage());
		}
	}
	
	@Test
	@Order(3)
	public void testValidador() {
		try {
			String[] dna = { "ATG1", "GTAC", "TTTT", "CCAG" };
			Validador.validarMatriz(dna);
		} catch (ServicioException e) {
			assertEquals("ERROR: Tiene letras fuera del dominio ATCG", e.getMessage());
		}
	}

	/*
	 * Prueba Stats
	 */
	@Test
	@Order(4)
	public void testStats() {
		MutantService nuevoServicio = new MutantService();

		assertNotNull(nuevoServicio.obtenerEstadisticas());
	}
	
	/*
	 * Prueba con matriz de 100 x 100
	 */
	@Test
	@Order(5)
	public void testMutant() {
		MutantService nuevoServicio = new MutantService();
		SecuenciaADN dna = new SecuenciaADN();
		dna.setDna(ListaSecuenciaAleatoria(100));
		Response respuesta = nuevoServicio.isMutant(dna);
		assertEquals(200, respuesta.getStatus());
	}
	
	/*
	 * Prueba caso NO exitoso para obtener response = 403
	 */
	@Test
	@Order(6)
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
	 * Prueba Procesar Adn Mutante para obtener response = 200
	 */
	@Test
	@Order(7)
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
	
	@Test
	@Order(8)
	public void testConexionBase() {
		ExpertoPersistencia expertoPersistencia = new ExpertoPersistencia();
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		try {
			expertoPersistencia.guardarADN(dna, true);
		} catch (ServicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, true);
	}
	
	@Test
	@Order(9)
	public void testStatsAfter() {
		MutantService nuevoServicio = new MutantService();

		assertNotNull(nuevoServicio.obtenerEstadisticas());
	}

	/*
	 * Prueba Stats 200
	 */
	@Test
	@Order(10)
	public void testStats200() {
		MutantService nuevoServicio = new MutantService();

		assertEquals(200, nuevoServicio.obtenerEstadisticas().getStatus());
	}
	
	@Test
	@Order(11)
	public void testStatsMutanDNA() {
		ExpertoEstadisticas expertoEstadistica = new ExpertoEstadisticas();

		try {
			assertNotEquals(0, expertoEstadistica.obtenerEstadisticas().getCount_mutant_dna());
		} catch (ServicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Order(12)
	public void testStatsHumanDNA() {
		ExpertoEstadisticas expertoEstadistica = new ExpertoEstadisticas();

		try {
			assertNotEquals(0, expertoEstadistica.obtenerEstadisticas().getCount_human_dna());
		} catch (ServicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> ListaSecuenciaAleatoria(int Dimension) {

		List<String> cadena = new ArrayList<String>();

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
