package com.baufest.ingreso.alphabetSoup;

import java.util.List;

public class DetalleCaracterEncontradoVO {
	private int posicionCaracater;
	private char caracter;
	private Integer[] posicionMatriz;
	private String orientacionMovido;
	private List<String> orientacionesErroneas;
	
	public DetalleCaracterEncontradoVO() {
	}

	public DetalleCaracterEncontradoVO(int posicionCaracater
									, char caracter
									, Integer[] posicionMatriz
									, String orientacionMovido
									, List<String> orientacionesErroneas) {
		this.posicionCaracater = posicionCaracater;
		this.posicionMatriz = posicionMatriz;
		this.caracter = caracter;
		this.orientacionMovido = orientacionMovido;
		this.orientacionesErroneas = orientacionesErroneas;
	}

	public int getPosicionCaracater() {
		return posicionCaracater;
	}

	public void setPosicionCaracater(int posicionCaracater) {
		this.posicionCaracater = posicionCaracater;
	}

	public Integer[] getPosicionMatriz() {
		return posicionMatriz;
	}

	public char getCaracter() {
		return caracter;
	}

	public void setCaracter(char caracter) {
		this.caracter = caracter;
	}

	public void setPosicionMatriz(Integer[] posicionMatriz) {
		this.posicionMatriz = posicionMatriz;
	}



	public String getOrientacionMovido() {
		return orientacionMovido;
	}

	public void setOrientacionMovido(String orientacionMovido) {
		this.orientacionMovido = orientacionMovido;
	}

	public List<String> getOrientacionesErroneas() {
		return orientacionesErroneas;
	}

	public void setOrientacionesErroneas(List<String> orientacionesErroneas) {
		this.orientacionesErroneas = orientacionesErroneas;
	}

	

}
