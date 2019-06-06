package com.mutant.ws.rest.model;

import java.io.ByteArrayInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

import com.google.api.services.storage.model.ServiceAccount;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpertoPersistencia {
	//private static ExpertoPersistencia instancia;
	private static final String BASE_DE_DATOS = "https://mutantrest-242718.firebaseio.com/";
	private static final String AUTH_DATABASE = "{\r\n" + 
			"  \"type\": \"service_account\",\r\n" + 
			"  \"project_id\": \"mutantrest-242718\",\r\n" + 
			"  \"private_key_id\": \"755c7e8fb19639536d43155c5c1192a3f999a2b6\",\r\n" + 
			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDRGtrsw93LskIF\\nvAVe+pt/vgskCxqEYLzCsNfYtREwDCXKR8Few7pBRIKoz4FUmJNbWiwufR1IARGr\\nKMRbkUR8MReIi7pq7YZBc6UtiaIpcmyXZpy0qAR/OAHjr+TM59Yhq6lTMTC0Jy0V\\nRgW0prPSn249YxCvLckv4JOnBuHNERgRNNVNBAWlmdxNwQdPH6pbvzbIpaWvz+mx\\nsGv+nly4tAgaRMJyhTXmQFGDh/QNxbYpH5L0+JZXCiYCEWP5NVmUdThT+qg2Kj8N\\nc3simVjF9BgR5vbtoy140NlF3GPcTGj3/E5HmWiU5wVEsOx9T02pDNdT8+XoRYJ5\\nxK87QqOZAgMBAAECggEAB0QrAyL/yjgqr+sTlzQ3ctoSZkld/adka9IMIB9TpnMn\\nnYa+jxnXVzUHotDaJkvKhBhHNILbwYCrAeL/5swuoG+uyQuM+fIA0jWv8dwIZu8P\\nW7gAvkzf4iFavHQka+PrBp7B2q6ZCguZ9xUotdk8orcwVCEFB4dcaEXiL6HPNype\\nsBDi2DiMif4GpYr3TRj0Q3K9O9GVkCyw9SgvieqWiohQDryyzGSIprONA6lrn0qD\\nrBsflTshtPOnC9kEsIYoM08iONsgfidvJ41UINkiRcSJ3jLKV93/PsgRIVRZLoea\\nrmjbwny+UZMu9NZF8CGr6igo5TqHhHqGaexT9aYXUQKBgQDvqdu33MLF+iBbgh9/\\n5FWjho+uL4EpWbAMA0ZHIysnhYqn7GAidTPXaSGlrz4y4asl2VzGbhbOzMeAqsj1\\n2Kp9BAw3xfJvuUqC+SZGvf7BOH32+dl4QUMa3E0ughf0r3DlZbtIUQT9NvGroUMX\\n0jQdraHaRe043vf71OnxynhOaQKBgQDfW79cr21h80TvjcdYK25xLwd3j8NACpzL\\ngIHP+eKG1pSI2iH/NGdTh8pE5NVyzHSbvFunzJghNLQ1WjLF4vV8RktZua2ild2F\\npXBh/2CPje6sgxdspFPrdhrwWBvjWcoUqcHeUFbBk/FwvINIwbA1v5RLGBEvy+h3\\noQT2k11lsQKBgQCxn7ZUYq2IhYUNzTcDVnuSItzCQDr+JpavkI25k3scY4EMS8hE\\nQZEWibiCT4tNsqth9eMovdbxlhwWZH1kmBxjY2iavX6dBzsOLxILRC1Xj0LslOh0\\nlAnkEnqxiYGvu4d0vR08z69e7CXZCj/rm8M8a7+VQlY63CX24yowJdtcYQKBgQDG\\nCTfbOuNV1HaLm35iy2dJzTPmqz2nu4EX4eG2/8z8Vqcty5IXD2SJqD0L5oTmGydM\\nqa3uUjWUQ4GASG/6Cmgz/rNyY3HzVMMrausAyIfZ4DL9G+IZ/n01qOrrrINVidfl\\niDoPHdY+HikVFt+kE69jiHA4QKeEZFvCLa8T3McTkQKBgDCAneAnkGlpkvB1Cn5Q\\nEQDapqTlVwAqsBGsL6JM/vzdK8/ynnTX6YY+ocYMj8UUbDYrbAl6djzbuJA6VcnR\\nPpY/rP3b3soYgIbP/a98aqJovrhUshB6j/qWy0j7QBmgY9Pl80/BUgWi3WuiNdnb\\n7HEIzNaGgzG4XqFBUNfsM9H8\\n-----END PRIVATE KEY-----\\n\",\r\n" + 
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
			throw new ServicioException("Error Firebase al intentar obtener estadisticas");
		}
		return listener.getEstadistica();
	}
}
