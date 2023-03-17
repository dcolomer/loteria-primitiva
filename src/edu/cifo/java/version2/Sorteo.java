package edu.cifo.java.version2;

import edu.cifo.java.version1.Apuesta;

public interface Sorteo {

	/** Constantes para indicar al programa a partir
	 de que premio queremos ser informados */
	public static final short VER_DESDE_REINTEGRO = 2;
	public static final short VER_DESDE_3_ACIERTOS = 3;
	public static final short VER_DESDE_4_ACIERTOS = 4;
	public static final short VER_DESDE_5_ACIERTOS = 5;
	public static final short VER_DESDE_5C_ACIERTOS = 6;
	public static final short VER_SOLO_6_ACIERTOS = 7;

	/**
	 * Este método realiza el sorteo, obteniendo 6 numeros
	 * aleatorios, un numero complementario y un reintegro
	 */
	public abstract void sortear();

	/**
	 * Muestra el sorteo por pantalla 
	 */
	public abstract void mostrarSorteo();

	/**
	 * Muestra las apuestas por pantalla 
	 */
	public abstract void mostrarApuestas(Apuesta[] apuestas);

	/**
	 * Mostrar frecuencias de premios y de aparicion de 
	 * numeros en los sorteos
	 */
	public abstract void mostrarEstadisticas(int cantidadSorteos);

	/**
	 * En este método se compara el sorteo obtenido con las
	 * apuestas de los usuarios.
	 * 
	 * @apuestas: Array con las apuestas del jugador
	 * @limite_inferior_premio: indicamos el minimo premio que se 
	 * 							considera para mostrar
	 * @num_sorteo: sirve para mostrar por pantalla el numero del 
	 * 				sorteo
	 * 
	 * Retorno: un booleano para informar si ha habido algun tipo de
	 * 			acierto.
	 */
	public abstract boolean comprobarApuestas(Apuesta[] apuestas,
			short limite_inferior_premio, int num_sorteo);

}