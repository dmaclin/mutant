package com.mutant.ws.rest.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpertoPersistencia {
	private static ExpertoPersistencia instancia;
	private static final String BASE_DE_DATOS = "https://mutant-65bd8.firebaseio.com/";
	private static final String CLAVE = "mutant-65bd8-firebase-adminsdk-tsqpb-f96eddbb00.json";
	final Semaphore semaphore = new Semaphore(0);
	private FileInputStream serviceAccount;
	private FirebaseOptions options;
	
	private ExpertoPersistencia() {
		try {
			serviceAccount = new FileInputStream(this.getClass().getResource(CLAVE).getFile());
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl(BASE_DE_DATOS)
			    .build();
			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized ExpertoPersistencia getInstancia() {
		if(instancia == null) {
			instancia = new ExpertoPersistencia();
		}
		return instancia;
	}
	
	/*
	public SecuenciaADN buscarSecuencia(SecuenciaADN secuencia) {
		String id = String.join("", secuencia.getDna());
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference secuenciaRef = database.getReference("SecuenciasADN");
		CustomValueChangeListener listener = new CustomValueChangeListener();
		secuenciaRef.child(id).addListenerForSingleValueEvent(listener);
		while(true) {
			if(this.flagCorte) {
				flagCorte = false;
				break;
			}
		}
		return listener.getAdn();
	}*/

	public void guardarADN(String[] dna, boolean isMutante) throws ServicioException {
		try {
			SecuenciaADN secuencia = new SecuenciaADN();
			secuencia.setDna(Arrays.asList(dna));
			secuencia.setMutante(isMutante);
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			DatabaseReference ref = database.getReference("");
			//DatabaseReference secuenciasADNRef = ref.child("SecuenciasADN/"+String.join("", dna));
			DatabaseReference secuenciasADNRef = ref.child("SecuenciasADN");
			//secuenciasADNRef.setValueAsync(secuencia);
			secuenciasADNRef.push().setValueAsync(secuencia);
		}catch(Exception e) {
			throw new ServicioException("Error al intentar persistir los datos");
		}
	}
	
	public Estadistica obtenerEstadisticas() throws ServicioException {
		CustomValueChangeListener listener = new CustomValueChangeListener(semaphore);
		try {
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			DatabaseReference secuenciaRef = database.getReference("SecuenciasADN");
			secuenciaRef.addListenerForSingleValueEvent(listener);
			semaphore.acquire();
		}catch(Exception ex) {
			throw new ServicioException("Error al intentar obtener estadisticas");
		}
		return listener.getEstadistica();
	}

	@Override
	public ExpertoPersistencia clone(){
	    try {
	        throw new CloneNotSupportedException();
	    } catch (CloneNotSupportedException ex) {
	        System.out.println("No se puede clonar un objeto de la clase ExpertoPersistencia");
	    }
	    return null; 
	}
}
