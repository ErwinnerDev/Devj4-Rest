package com.baufest.ingreso.alphabetSoup;

import java.util.Arrays;
import java.util.List;

public class BuscadorMatriz {
	
	//poner la Intefaz

	
	public BuscadorMatriz() {
	} 

	public Integer[] buscarIzquierda(char soup[][],Integer[] posicionMatriz,char letra) {
		List<Integer> coordenadas = Arrays.asList(posicionMatriz);
		if(coordenadas.get(1)>0) {
			if(soup[coordenadas.get(0)][coordenadas.get(1)-1]==letra) {
				return new Integer[]{coordenadas.get(0),(coordenadas.get(1)-1)};
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	public Integer[] buscarDerecha(char soup[][],Integer[] posicionMatriz,char letra) {
		List<Integer> coordenadas = Arrays.asList(posicionMatriz);
		if(coordenadas.get(1)<soup[0].length-1) {
			if(soup[coordenadas.get(0)][coordenadas.get(1)+1]==letra) {
				return new Integer[]{coordenadas.get(0),(coordenadas.get(1)+1)};
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public Integer[] buscarArriba(char soup[][],Integer[] posicionMatriz,char letra) {
		List<Integer> coordenadas = Arrays.asList(posicionMatriz);
		if(coordenadas.get(0)>0) {
			if(soup[coordenadas.get(0)-1][coordenadas.get(1)]==letra) {
				return new Integer[]{(coordenadas.get(0)-1),coordenadas.get(1)};
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public Integer[] buscarAbajo(char soup[][],Integer[] posicionMatriz,char letra) {
		List<Integer> coordenadas = Arrays.asList(posicionMatriz);
		if(coordenadas.get(0)<soup.length-1) {
			if(soup[coordenadas.get(0)+1][coordenadas.get(1)]==letra) {
				return new Integer[]{(coordenadas.get(0)+1),coordenadas.get(1)};
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

}
