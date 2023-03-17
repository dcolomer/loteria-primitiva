package edu.cifo.java.version2;

import static edu.cifo.java.version1.Sorteo.*;
import edu.cifo.java.version1.Apuesta;

/**
 * Clase principal donde se realiza el juego.
 * Se crea un boleto con 5 apuestas y se juegan un
 * cierto numero de veces. Si finalmente hay aciertos
 * se muestran por pantalla.
 * Para esto se llaman a los servicios 'sortear()' y 
 * 'comprobarApuestas()' de la clase Sorteo
 */
public class Juego {
	
	private final short NUM_APUESTAS=2;
	private final int NUM_SORTEOS=10000;
	private Apuesta[] apuestas=new Apuesta[NUM_APUESTAS];
	
	public static void main(String[] args) {
		new Juego();
	}
	
	// Constructor
	
	public Juego() {
		// Definimos nuestras 5 combinaciones
		Short[] apuesta1={4,8,12,23,34,44};
		Short[] apuesta2={14,18,22,24,32,48};
		/*Short[] apuesta3={1,4,5,40,41,42};
		Short[] apuesta4={20,21,36,37,44,45};
		Short[] apuesta5={1,10,15,32,38,43};*/
		
		/* Creamos objetos de la clase Apuesta, la cual
		 	requiere el array de Short y el reintegro */
		apuestas[0]=new Apuesta(apuesta1,(short)3);
		apuestas[1]=new Apuesta(apuesta2,(short)3);
		/*apuestas[2]=new Apuesta(apuesta3,(short)3);
		apuestas[3]=new Apuesta(apuesta4,(short)3);
		apuestas[4]=new Apuesta(apuesta5,(short)3);*/
		
		jugar(); // Método que invoca al sorteo y a la comprobacion de premios
	}
	
	private void jugar() {
		
		System.out.println("Iniciando juego...\n");
		
		Sorteo sorteo=new SorteoInventadoImpl();
		
		System.out.println("Apuestas realizadas...	");
		sorteo.mostrarApuestas(apuestas);
		System.out.println();
		
		for (int i=0;i<NUM_SORTEOS;i++) {
			//System.out.print("\nSorteando...");	
			sorteo.sortear();
			//sorteo.mostrarSorteo();System.out.println();
			//System.out.println("\nComprobando apuestas...");
			if (sorteo.comprobarApuestas(apuestas, VER_DESDE_4_ACIERTOS, i+1)) {
				System.out.print("\nSORTEO: ");
				sorteo.mostrarSorteo();	
				System.out.println("\n__________________________________________\n");
			}
			
		}			
		sorteo.mostrarEstadisticas(NUM_SORTEOS);
		System.out.print("\nPrograma finalizado");
	}

}
