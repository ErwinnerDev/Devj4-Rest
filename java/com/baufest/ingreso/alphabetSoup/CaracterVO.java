package com.baufest.ingreso.alphabetSoup;

public class CaracterVO {
	private char valor;
	private int posicion;
	
	public CaracterVO() {
	}

	public CaracterVO(char valor, int posicion) {
		super();
		this.valor = valor;
		this.posicion = posicion;
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
