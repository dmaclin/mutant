package com.mutant.ws.rest.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpertoPersistencia {
	//private static ExpertoPersistencia instancia;
	private static final String BASE_DE_DATOS = "https://mutantrest-242718.firebaseio.com";
	private static final String AUTH_DATABASE = "{\r\n" + 
			"  \"type\": \"service_account\",\r\n" + 
			"  \"project_id\": \"mutantrest-242718\",\r\n" + 
			"  \"private_key_id\": \"d263beaee5cc21cb6c9da672749fa1539f674ce6\",\r\n" + 
			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDuiLS82eEyNjgW\\nMC8jKvlxZJW8Pf+cv1Z2IgPJ0aWfhIXi3ZmexsmTOfQizBcK2KjcysMZB8JTlixV\\nZUhzp8hDVRv7jG0F+UhEz6Ts08zEUmnrAFt7iYgcD2TTnmgmZJ1ntUUHC2bZRX/6\\nMogA3n/Qx3Wzb4entdaofRrUKKQzJjb4rPNcP9twEN8zN9Hf3epvOgFrs4GqKmNX\\n7mFJ65s6PvDGqzsuFwYgUKxsVN6BRmkldxrdt0FKTw12DEEXOzywGAzCEqV28Ir+\\nPmC+VC4hOeUi0Jq6PAKbrcIWcrtpf48YbMYKrRzqLagNz6SupeuXueg40BIGGUyX\\n+ohbGUs7AgMBAAECggEABYUDTaVrCRrpFFZcGN+7Mu0Fa/wxSILC8Ovbk5V21Cig\\nYn4HOn3WF0KNjCqV0+nsimNkvVUJIZ8Feppj4U0+HJTBF4KJ2UwJ6JG3esVx5Q8V\\nJVt2QncNv7x5dE4jkdPdMpOmu8F/SULK8dKug/qiEYY0ColnpbFW/MIcV1Fzbz2F\\nMf9OYvonuocVsgH28nYc15b++lgYSQZciXRYOO6Re+G176afBbWRGKTjXP3LIW0f\\nv+Hcnw6qXxN74lOo15trWT97kaHawBKv1d6MQFb35UoFVRA5PfGY8a2lRF/JCs4K\\nHjDp/83vIWILAK8DGyNLp3mQmHiis+RBThebSDodUQKBgQD+67NGtcXqzzO+mtYe\\nAtScgmSzK98rU8yB9xd4fCKxDW8cE0I9Wy19w+ZR6LkwB6yDKJivNFGJfnVpFKxF\\n2QivB/kMxESzHvkHrYPWzYWLT58eYmn2OZBvpPprjBzrwO2R44w9IqluVTLWMcgo\\nDS2xmgR3kWWORkwAxC1f8pjYCwKBgQDviz6nHbu9s4x7pwqFHZiSmLV5PQgj19Gs\\nZzCaF4FwIlASl8zQMICQ2azHaq6m7QYpARH9x7wTgOxNZBGKbtrdOOdodxtzPfsS\\ny03jrpi+926ctCceWuJJkby3WXS3rBn6d3eWX8vRImm/QI2OqwYlb116cFhWloj6\\nvXLzQt/nkQKBgQC81ZaYw6AXv2NiR0pjMs+SHFlCDDal3s54W7RMMRB1vTDYSD/i\\nOX9qUUp01IMR3C1jfxgTeRsJUd7hd0nh99Kh7bMETQNi+Ieas2MdJGNPfiytklNR\\n7PRcb4Z9Um4+CU0yq6+/vcRqkbw1xOyzFogJrsCuXcsv6Ez2qFvAXnHwXwKBgFGS\\nHDKySFUwCB2vY0CzNFpvUBTJ+LSj8I2B0iYW7QfvV08NjDw02AL7bP+3T2QegKOD\\n7sl5s4z8sPsloWd/ml4M25hSPvG+YHok6xAmS3N0NKF+I3gNwNMGornY54sIOwx0\\nlC+LzY8Y5eOvqsSzbnNe+fkQurQ5rMmdq8A0vKRRAoGBAJnOInP0Wy2C5PZ1lbnx\\nRZLg3EGOW2FRCCiONXjLAGH8DdQ5qRKKZzAULyJhVMvAx1EQfcQ3+f+hMHWfzgaq\\nU7d3EwP1KGsuR/ZfBmZfLtePdkKwtfxcYJUsPy4dgMtAl1QqFIK1wfgHwXD4pnU8\\n5H+4jOFWEDgpkW0PCy9fvfb6\\n-----END PRIVATE KEY-----\\n\",\r\n" + 
			"  \"client_email\": \"firebase-adminsdk-3enzw@mutantrest-242718.iam.gserviceaccount.com\",\r\n" + 
			"  \"client_id\": \"110699880658628203219\",\r\n" + 
			"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n" + 
			"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n" + 
			"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n" + 
			"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-3enzw%40mutantrest-242718.iam.gserviceaccount.com\"\r\n" + 
			"}";
	final Semaphore semaphore = new Semaphore(0);
	private static InputStream serviceAccount;
	 
	private static FirebaseOptions options;
	
	static {
			serviceAccount = new ByteArrayInputStream(AUTH_DATABASE.getBytes());
			try {
				options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				    .setDatabaseUrl(BASE_DE_DATOS)
				    .build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FirebaseApp.initializeApp(options);
	}

	public void guardarADN(String[] dna, boolean isMutante) throws ServicioException {
		try {
			SecuenciaADN secuencia = new SecuenciaADN();
			secuencia.setDna(Arrays.asList(dna));
			secuencia.setMutante(isMutante);
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			DatabaseReference ref = database.getReference("");
			DatabaseReference secuenciasADNRef = ref.child("SecuenciasADN");
			secuenciasADNRef.push().setValueAsync(secuencia);
		}catch(Exception e) {
			e.printStackTrace();
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
			ex.printStackTrace();
			throw new ServicioException("Error Firebase al intentar obtener estadisticas");
		}
		return listener.getEstadistica();
	}
}
