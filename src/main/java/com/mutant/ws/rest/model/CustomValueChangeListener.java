package com.mutant.ws.rest.model;

import java.util.concurrent.Semaphore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CustomValueChangeListener implements ValueEventListener{
	private Estadistica estadistica;
	Semaphore semaforo;
	public CustomValueChangeListener(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	public void onDataChange(DataSnapshot snapshot) {
		
		if(snapshot.getChildrenCount() > 0) {
			estadistica = new Estadistica();
			snapshot.getChildren().forEach((DataSnapshot sec)->{ 
				SecuenciaADN adn = (SecuenciaADN) sec.getValue(SecuenciaADN.class);
				if(adn.isMutante()) {
					estadistica.sumarMutante();
				}else {
					estadistica.sumarHumano();
				} 
			  });
			estadistica.calcularProporcion();
		}
		//ExpertoPersistencia.getInstancia().setFlagCorte(true);
		semaforo.release();
	}
	
	public void onCancelled(DatabaseError error) {
		semaforo.release();
		System.out.println("Error de Lectura: " + error.getCode());
	}
	
	public Estadistica getEstadistica(){
		return estadistica;
	}

}
