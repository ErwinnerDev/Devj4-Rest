package com.baufest.ingreso.sorting;


import java.util.Collections;

import java.util.List;

public class Sorting {
	
    /**
     * Se debe ordenar primero por puntuación de manera descendente, luego por nombre de manera ascendente.
     * @param jugadores la lista de jugadores a ordenar
     * @return la lista ordenada de jugadores
     */
	public static List<Jugador> ordenarPorPuntuacionYNombre(List<Jugador> jugadores){
		try {
			Collections.sort(jugadores,
					(jugador1, jugador2) -> {
						//	-1 si x <y;
						//	0 si x == y; 
						//	1  x> y
						int result= Integer.valueOf(jugador2.getPuntuacion()).compareTo(jugador1.getPuntuacion());
						if(result!=0) {
							return result;//uno de los dos es mayor
						}
						if(jugador1.getNombre() == null) {
							return jugador2.getNombre() == null ? 0: -1;//Si ambos son nulos =0, si el nombre a comparar contene info. posicinar primero el null
						}
						
						if(jugador2.getNombre()==null) {
							return 1; // si el campo a comparar es null, se coloca primero
						}
						return jugador1.getNombre().compareToIgnoreCase(jugador2.getNombre());
					});
			return jugadores;
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}

    /**
     * Se debe ordenar primero por puntuación de manera descendente. Cuando 2 jugadores tienen igual cantidad de puntos,
     * el que tiene menos perdidas se lo considerara el mayor. Luego a igual puntos y perdidas se seguirá usando el
     * nombre de manera ascendente.
     * @param jugadores la lista de jugadores a ordenar
     * @return la lista ordenada de jugadores
     */
	public static List<Jugador> ordenarPorPuntuacionPerdidasYNombre(List<Jugador> jugadores){
		try {
			Collections.sort(jugadores,
					(jugador1, jugador2) -> {
						//	-1 si x <y;
						//	0 si x == y; 
						//	1  x> y
						int result= Integer.valueOf(jugador2.getPuntuacion()).compareTo(jugador1.getPuntuacion());
						if(result!=0) {
							return result;
						}
						result= Integer.valueOf(jugador1.getPerdidas()).compareTo(jugador2.getPerdidas());
						if(result!=0) {
							return result;
						}
						
						if(jugador1.getNombre() == null) {
							return jugador2.getNombre() == null ? 0: -1;
						}
						
						if(jugador2.getNombre()==null) {
							return 1;
						}
						return jugador1.getNombre().compareToIgnoreCase(jugador2.getNombre());
						
					});
			return jugadores;
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
}
