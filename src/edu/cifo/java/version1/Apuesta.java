package edu.cifo.java.version1;

/**
 * Clase que modela una apuesta.
 * Una apuesta consiste en:
 * 		unos numeros que forman la combinacion 
 * 		1 numero complementario
 * 		1 numero de reintegro
 */
public class Apuesta {
	private Short[] combinacion=new Short[LEN_APUESTA];
	private Short complementario;
	private Short reintegro;
	
	public static final short LEN_APUESTA=6;
	
	/* 
	 * Constructores
	 */
	
	public Apuesta() {}
	
	// Este constructor lo utiliza la apuesta
	public Apuesta(Short[] combinacion, Short reintegro) {				
		this.combinacion = combinacion;
		this.reintegro = reintegro;
	}
	
	/*
	 *  getters y setters
	 */
	
	public Short[] getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(Short[] combinacion) {
		this.combinacion = combinacion;
	}

	public short getComplementario() {
		return complementario;
	}

	public void setComplementario(Short complementario) {
		this.complementario = complementario;
	}

	public Short getReintegro() {
		return reintegro;
	}

	public void setReintegro(Short reintegro) {
		this.reintegro = reintegro;
	}
	
	public String toString() {
		StringBuilder tmp=new StringBuilder();		
		for (int i = 0; i < LEN_APUESTA; i++) {
			tmp.append(this.combinacion[i]);
			tmp.append(" ");
	    }
		return tmp.toString();
	}
}
