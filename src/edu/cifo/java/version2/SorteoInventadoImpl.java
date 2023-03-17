package edu.cifo.java.version2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import edu.cifo.java.version1.Apuesta;

import static edu.cifo.java.version1.Apuesta.*;

/**
 * Clase que realiza un sorteo y permite comprobar las
 * jugadas de los usuarios con el resultado del sorteo.
 * 
 * El resultado del sorteo lo almacena en un atributo
 * de la clase Apuesta
 */

public class SorteoInventadoImpl implements Sorteo {
	
	private Apuesta sorteo;
	
	/* Mapa para almacenar la frecuencia de aparicion de cada numero
	 Utilizamos la implementacion TreeMap porque ordena el mapa automaticamente*/
	private static Map<Short,Long> mapaFrecuenciaNumeros=new TreeMap<Short,Long>(); 
	
	/* Variables para almacenar la frecuencia de aciertos **/
	private static int cont_premio_cat3=0;
	private static int cont_premio_cat4=0;	
		
	/* (non-Javadoc)
	 * @see edu.cifo.java.version2.Sorteo#sortear()
	 */
	public void sortear() {
		
		Random rnd = new Random();
		
		// Estructura para almacenar cada númeo ordenadamente 		
		ArrayList<Short> sorteo_en_construccion=
			new ArrayList<Short>(LEN_APUESTA-2);
		
		/* En cada iteración conseguimos uno de los 6 números
		 * necesarios del sorteo */
		for (short i = 0; i < LEN_APUESTA-2; i++) {			
															
			/* Bucle infinito. No salimos si el número aleatorio 
			 * obtenido ya existe en la apuesta en curso */			
			while (true) {
				
				// Obtenemos un número aleatorio
				short un_numero=
					(short) ((short) (rnd.nextDouble() * 59)+1);
			
				/* Menor a 0 indica que el numero no está repetido, 
				 * luego lo aceptamos y salimos del while */
				if (Collections.binarySearch(
						sorteo_en_construccion, un_numero)<0) 
				{				
					sorteo_en_construccion.add(un_numero);
					
					// binarySearch requiere una coleccion ordenada
					Collections.sort(sorteo_en_construccion);									
					
					// salir del while!
					break;
				}
			}	
	    }
								
		/* En este punto ya tenemos la combinacion ganadora */
		
		// Mediante setters instanciamos el objeto 'sorteo'
		sorteo=new Apuesta();
		
		/* Nacesitamos un array de Short pero tenemos la 
		 * combinacion ganadora almacenada en un ArrayList.
		 * Mediante el método 'toArray(T[])' obtenemos un array
		 * de Short a partir de la coleccion ArrayList
		 */
		this.sorteo.setCombinacion(
				sorteo_en_construccion.toArray(
						new Short[sorteo_en_construccion.size()]));
		
		short reintegro=
			(short) ((short) (rnd.nextDouble() * 9)+1);
		
		this.sorteo.setReintegro(reintegro);
		
		// Añadimos la cobinacion actual a las estadisticas
		calcEstadisticas(); 
		
	}
	
			
	/* (non-Javadoc)
	 * @see edu.cifo.java.version2.Sorteo#mostrarSorteo()
	 */	
	public void mostrarSorteo() {		
		for (int i = 0; i < LEN_APUESTA-2; i++) {
	        System.out.print(sorteo.getCombinacion()[i]+" ");
	    }
		
		System.out.print(" R: "+sorteo.getReintegro()+" ");
	}
	
	/* (non-Javadoc)
	 * @see edu.cifo.java.version2.Sorteo#mostrarApuestas(edu.cifo.java.version1.Apuesta[])
	 */	
	public void mostrarApuestas(Apuesta[] apuestas) {		
		for (int i = 0; i < apuestas.length; i++) {
			for (int j = 0; j < LEN_APUESTA-2; j++) {
				System.out.print(apuestas[i].getCombinacion()[j]+" ");
			}	
			System.out.println();
	    }		
		System.out.print("R: "+apuestas[0].getReintegro()+" ");
	}
	
	/* (non-Javadoc)
	 * @see edu.cifo.java.version2.Sorteo#mostrarEstadisticas(int)
	 */
	@SuppressWarnings("unchecked")
	public void mostrarEstadisticas(int cantidadSorteos) {
		
		System.out.println("\nSorteos realizados: "+cantidadSorteos+"\n");
		System.out.println("Estadisticas:");
		System.out.println("Premios de 3 aciertos: "+cont_premio_cat3);
		System.out.println("Premios de 4 aciertos: "+cont_premio_cat4);			
		
		System.out.println("\nFrecuencia de aparacion de numeros:");
		
		Iterator<?> it = mapaFrecuenciaNumeros.entrySet().iterator();
		
		System.out.println("\nnum veces");
		System.out.println("--------");
		while (it.hasNext()) {			
			Map.Entry<Short,Long> e = (Map.Entry)it.next();
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		
		System.out.println("\nNumeros que nunca han salido:");
		for (short i=1;i<=59;i++) {			
			if (!mapaFrecuenciaNumeros.containsKey(i))
				System.out.print(i+" ");
		}
		
	}
	
		
	/* (non-Javadoc)
	 * @see edu.cifo.java.version2.Sorteo#comprobarApuestas(edu.cifo.java.version1.Apuesta[], short, int)
	 */
	public boolean comprobarApuestas(Apuesta[] apuestas, 
			short limite_inferior_premio, int num_sorteo) 
	{	
		boolean acertantes=false;
		
		// Para cada una de las apuestas del jugador...
		for (int i=0;i<apuestas.length;i++) {
			
			short aciertos=0;
			
			/* Contabilizar el numero de aciertos para la 
				apuesta en curso */
			for (short j=0;j<LEN_APUESTA-2;j++) {
			
				short guarismo_ap=apuestas[i].getCombinacion()[j];
			
				if (Arrays.binarySearch(sorteo.getCombinacion(),
						guarismo_ap)>=0)
				{
					aciertos++;
				}			
			}
			
			// Actuar según el numero de aciertos
			switch (aciertos) {
				case 0:
				case 1:
				case 2: {
					if (VER_DESDE_REINTEGRO>=limite_inferior_premio) {
						if (apuestas[i].getReintegro()==sorteo.getReintegro()) {						
							acertantes=true;							
							imprimir(num_sorteo, aciertos, apuestas, i, true, false);
						}
					}
					break;	
				}
				case 3:	{
					cont_premio_cat3++;
					if (VER_DESDE_3_ACIERTOS>=limite_inferior_premio) {
						acertantes=true;					
						imprimir(num_sorteo, aciertos, apuestas, i, false, false);
					}												
					break;																					
				}
				case 4:	{
					cont_premio_cat4++;
					if (VER_DESDE_4_ACIERTOS>=limite_inferior_premio) {
						acertantes=true;					
						imprimir(num_sorteo, aciertos, apuestas, i, false, false);	
					}
					break;																				
				}													
			}
			
			if (acertantes)
				System.out.println();
		}
		
		return acertantes;
	}

	
	/*
	 * No aplica para esta implementacion
	 */	
	@SuppressWarnings("unused")
	private Short generarComplementario(Short[] combinacion) {		
		return null;
	}
	
	
	/*
	 * Llevar un control de las veces que aparece cada numero
	 */
	@SuppressWarnings("unchecked")
	private void calcEstadisticas() {
		
		// Creamos una lista a partir del array con la combinacion ganadora
		List<Short> numeros=Arrays.asList(this.sorteo.getCombinacion());
		
		// Ahora iteramos sobre cada elemento de la lista (de la combinacion ganadora)
		for (Short numero:numeros) {
			boolean encontrado=false;
			Iterator<?> it = mapaFrecuenciaNumeros.entrySet().iterator();
			
			// Verificamos si el numero en curso ha aparecido en sorteos anteriores
			
			// Si ha habido sorteos anteriormente
			if (it.hasNext()) {
				Map.Entry<Short,Long> e;
				// Buscamos dentro del mapa hasta encontrar el numero en curso
				while (it.hasNext()) {
					e = (Map.Entry<Short,Long>)it.next();
					// Si lo encontramos...incrementamos el contador
					if (e.getKey()==numero) {
						Long contador=Long.parseLong(e.getValue().toString());
						contador++;
						e.setValue(contador);
						encontrado=true;
						break;
					}					
				}
				// Si no estaba en el mapa...lo añadimos
				if (!encontrado)
					mapaFrecuenciaNumeros.put(numero, (long)1);
			}
			// Si el mapa está vacío es que es la primera vez
			else {
				mapaFrecuenciaNumeros.put(numero, (long)1);
			}
		}
	}
	
	/*
	 * Mostrar informacion al usuario sobre sorteos y premios
	 * @num_sorteo: el numero de sorteo en curso
	 * @aciertos: aciertos para la apuesta en curso
	 * @apuestas: Array con las apuesta del usuario
	 * @apuesta_en_curso: indice para obtener una apuesta del array apuestas
	 * @reint: booleano que indica si hay que imprimir informacion de reintegro
	 * @compl: booleano que indica si hay que imprimir informacion de complementario
	 * 
	 */
	private void imprimir(int num_sorteo, int aciertos, Apuesta[] apuestas, 
			int apuesta_en_curso, boolean reint, boolean compl) 
	{			
		System.out.print("Sorteo num. "+num_sorteo+" ");
						
		if (reint)
			System.out.print(" Premio de REINTEGRO para la apuesta: ");		
		else
			System.out.print(" Premio de "+aciertos+" ACIERTOS para la apuesta: ");
		
		for (short k = 0; k < LEN_APUESTA-2; k++) {
			System.out.print(apuestas[apuesta_en_curso].getCombinacion()[k]+" ");
		}
		
		if (reint)
			System.out.print("R: "+apuestas[apuesta_en_curso].getReintegro()+" ");
		
	}
}
