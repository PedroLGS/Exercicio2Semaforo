package controller;
import java.util.concurrent.Semaphore;

public class CozinhaController extends Thread {
	private int i;
	private Semaphore semaforo;
	String prato;
	
	public CozinhaController(int threadId, Semaphore semaforo) {
		this.i = threadId;
		this.semaforo = semaforo;
	}
	public void run() {
		preparando();
		entrega();
	}
	private void entrega() {
		if (i % 2 == 0) {
			prato = "Lasanha a bolonhesa";	
		}
		else {
			prato = "Sopa de cebola";
		}
		try {
			semaforo.acquire();
			System.out.println(prato +" está sendo entregue.");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		System.out.println(prato +" foi entregue.");
	}
	private void preparando() {
		int threadid = 0;
		int cont = 0;
		
		if (i % 2 == 0) {
			prato = "Lasanha a bolonhesa";
			System.out.println("Iniciando o cozimento de uma Lasanha a bolonhesa.");
			threadid = (int) ((int) ((Math.random() * 601) + 600));
		}
		else {
			prato = "Sopa de cebola"; 
			System.out.println("Iniciando o cozimento de uma Sopa de cebola.");
			threadid = (int) ((int) ((Math.random() * 301) + 500));
		}
		while (cont < threadid) {
			try {
				if (cont + 100 <= threadid)
					cont += 100;
				else {
					cont += threadid - cont;
				}
				System.out.println("Prato " + i + " - " + prato + " " + (cont * 100) / threadid + "%");
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(prato+" está pronto para entrega.");
		}
	}
}