package view;
import java.util.concurrent.Semaphore;

import controller.CozinhaController;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 1; i < 6; i++) {
			Thread thread = new CozinhaController(i, semaforo);
			thread.start();	
		}	
	}
}