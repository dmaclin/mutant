package com.mutant.ws.rest.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class ExpertoPersistencia {

	//private static final String BASE_DE_DATOS = "https://mutantrest-242718.firebaseio.com";
	private static final String AUTH_DATABASE = "{\r\n" + 
			"  \"type\": \"service_account\",\r\n" + 
			"  \"project_id\": \"mutantrest-242718\",\r\n" + 
			"  \"private_key_id\": \"a004d86ac01c680386d5e34329d626ecc9382ad6\",\r\n" + 
			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCm6U6mQwPrZakg\\nSEXOfKUU3RgwOI9/GzZq8aktq3rdGZv5+Sy5R3EJj9yZvIguKXJqN1ZviGjOvVHw\\nfh19CarXMRVetaIs0o5s3GV4nMyFc/ZGAaLdDpEC57DOANz1pFOViyF6sJocGkJc\\n2B6cQx7gkAL9k+d3x6C/etuPBjBBXT7PzTYp2kpcPcUPzTIVo2C+HLSdanB67s0w\\n083vTUzy4x00uAMxN9Vvs5KEszjYOswQILepXxFiUaH6WNoiBciV7rlzOq2s22uI\\nG3V73/qURiqBeSSFMcoFJBWLlqPxYM0c6ohIYLf6PYXtsveWQmrnhXDQgXMzsZ2U\\nr719EV5tAgMBAAECggEAE5fnMubChsdSDXdWdHkW0uZqYLy/AZOC9ZZlPvZ2j45i\\nTDuXJ6/OgeoYohZq4QpeA0B4PdMERKKeRA417XftTWIWaV4Gid70t6w+oCQ98PuO\\nISUvhmYfmTjz/RbGWXKfGjqxJSsmiB6ABFCddUf+z6swIq7SWPH++YNVU0qNbxqv\\nyeKtY33ySdfnPEaoHjyONd7S8WIaOc0laKVmHG14VQO9gOLFulISc6mA5xT1KwNI\\n6MH8Lunor7UhRq6GUwTHl6m4M7cT7+5DBcrlqpQWERlm9XB6i9mjxDT94doKhoOn\\nCz1Pa6rpn8SQwzD/0D68QiG6l03U1Rw4uG5RoE4LGQKBgQDTfK48MsnDeHCjCLmE\\nTHbTTP68ZtzO2Vc5RZWIFu9X8yf0ZF0y51W3yDUeGdGBa+gvdK8LklZgl6lRVg5O\\n8toNRgLZn4OWLdu9McR7KkUFTKg/ih7P9LNDo3wR45u9HMWZUcFjo5w11aCAZZ1L\\nToPz7X1o8a3xfzkTX5//1IUjGQKBgQDKCs4j3Lcr9km7Y/H7cFJQapGidEMOcDkk\\nXkjWc5RyLvjgmdbv9tVNydYRb3hZKWgJJL53ec1ZFKtTt71BAKGId+8BuqHqNzDl\\nzFBbdde7VPYnxEBeBT60xfD8RAv1rZN0ld2o4gR63cEZmuS7dEpfjgsI6MDZ2LTf\\n8Ad+rfl0dQKBgGs8amDN4+7A0GRq1R7XYt0rynMfuVENdaPtu6/A6WXwPs82YOeh\\n054X348+yjPU0mvVv7ifYDskS8+ZUjIIBDb8zA8rSh7JbAWYCafmPA8+2oU1dQA/\\ndPKUICI2Bk8zGqLuiKAMYvYNN5BDdimsS05Rmuiuu2Ob/gXr1AZ17XhpAoGBAJaN\\nVKxOdMOyg6C6Sf+l5AB+WMUoQyuIHlxmUXpTp+Mp81RR7mLjmHCdnQFjAdfWzeo6\\nUh9vSMA/cacxfDnqwXOSgyvTqYGA3NPv2lU8y6ORHNfnGlu1JtPHx9oCzrKkRAOV\\nBRYqTVJM2e49iIHSlvT7QGfEIKh0Hdvc3YjdFMxdAoGAfMavuvrQ5dQx+b3mIvVB\\nAzodw0lRH6e6sWwlLTSKfDTRz4axdl9HDc/3h0cpq9B5LaIXsDeUUSdbHtWmQBfj\\nyrL0yTPtFcX39Qa59nMpmWUGrQsCBXE3y0rhonhNGYVIJwifEfSa+tQ9dq+j5TW+\\npo/W5a0hZN35VwMUFPIMZdw=\\n-----END PRIVATE KEY-----\\n\",\r\n" + 
			"  \"client_email\": \"firebase-adminsdk-yjcqu@mutantrest-242718.iam.gserviceaccount.com\",\r\n" + 
			"  \"client_id\": \"105306352516117869276\",\r\n" + 
			"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n" + 
			"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n" + 
			"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n" + 
			"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-yjcqu%40mutantrest-242718.iam.gserviceaccount.com\"\r\n" + 
			"}";
	private static InputStream serviceAccount;
	private static FirebaseOptions options;
	private static Firestore db;
	
	static {
		serviceAccount = new ByteArrayInputStream(AUTH_DATABASE.getBytes());
		try {
			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FirebaseApp.initializeApp(options);
		db = FirestoreClient.getFirestore();
		/*GoogleCredentials credentials;
		try {
			credentials = GoogleCredentials.getApplicationDefault();
			options = new FirebaseOptions.Builder()
				    .setCredentials(credentials)
				    .setProjectId("mutantrest-242718")
				    .build();
			FirebaseApp.initializeApp(options);
			db = FirestoreClient.getFirestore();
			System.out.println("BASEEEEEEEEEEEEE" + db.toString());	
		} catch (IOException e) {
			System.out.println("ERRORRRRRRRRRRRR" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	public void guardarADN(String[] dna, boolean isMutante) throws ServicioException {
		DocumentReference docRef = db.collection("secuenciasADN").document(UUID.randomUUID().toString());

		Map<String, Object> data = new HashMap<>();
		data.put("dna", String.join("", dna));
		data.put("mutante", isMutante);
		ApiFuture<WriteResult> result = docRef.set(data);
		try {
			result.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Estadistica obtenerEstadisticas() throws ServicioException {
		Estadistica estadistica = new Estadistica();
		ApiFuture<QuerySnapshot> query = db.collection("secuenciasADN").get();
		QuerySnapshot querySnapshot = null;
		try {
			querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot document : documents) {
				if (document.getBoolean("mutante")) {
					estadistica.sumarMutante();
				} else {
					estadistica.sumarHumano();
				}
			}
			estadistica.calcularProporcion();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estadistica;
	}

	public void cargarDatosDB() {
		ApiFuture<QuerySnapshot> query = db.collection("secuenciasADN").get();
		QuerySnapshot querySnapshot = null;
		try {
			querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot document : documents) {
				MapaADNEvaluados.guardar(String.join("", document.getString("dna")), document.getBoolean("mutante"));
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
