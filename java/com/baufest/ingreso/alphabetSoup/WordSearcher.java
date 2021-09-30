package com.baufest.ingreso.alphabetSoup;

import java.util.ArrayList;

public class WordSearcher {

    private char soup[][];
    private static final String VAR_IZQUIERDA="izquierda";
    private static final String VAR_DERECHA="derecha";
    private static final String VAR_ARRIBA="arriba";
    private static final String VAR_ABAJO="abajo";
    private static final String VAR_INICIO="inicio";
    private BuscadorMatriz buscadorMatriz=null;
    private ArrayList<DetalleCaracterEncontradoVO> caraterEncontrado= null;

    public WordSearcher(char soup[][]){
        this.soup = soup;
        buscadorMatriz = new BuscadorMatriz();
    }

    /**
     * El objetivo de este ejercicio es implementar una función que determine si una palabra está en una sopa de letras.
     *
     * ### Reglas
     * - Las palabras pueden estar dispuestas direcciones horizontal o vertical, _no_ en diagonal.
     * - Las palabras pueden estar orientadas en cualquier sentido, esto es, de derecha a izquierda o viceversa, y de arriba
     * para abajo o viceversa.
     * - El cambio de dirección puede estar a media palabra, de modo que, por ejemplo, parte de la palabra
     * esté horizontal y de izquierda a derecha, parte esté vertical y de arriba hacia abajo, y otra parte horizontal
     * de derecha a la izquierda.
     *
     * @param word	Palabra a buscar en la sopa de letras.
     *
     * @return {@link Boolean}	true si la palabra se encuentra
     * en la sopa de letras.
     * */
    public boolean isPresent(String word){
    	int contadorEncontrados	= 1;
    	Integer count =1;

    	try {
    		if(word==null) {return false;}
    		if(word.isEmpty()) {return false;}
    		if(soup.length==0) {return false;}

    		caraterEncontrado= new ArrayList<DetalleCaracterEncontradoVO>();
    		for(int fila=0;fila<soup.length;fila++) {
    			for(int columna=0; columna<soup[0].length;columna++) {
    				if(caraterEncontrado.size()==0) {
    					if(word.substring(0,1).equalsIgnoreCase(String.valueOf(soup[fila][columna]))) {//encontro el primer elemento
    						caraterEncontrado.add(new DetalleCaracterEncontradoVO(0,soup[fila][columna],new Integer[]{fila,columna},VAR_INICIO,new ArrayList<String>()));
        				}else if(word.substring(word.length()-1,word.length()).equalsIgnoreCase(String.valueOf(soup[fila][columna]))){ //Encontro el ultimo
        					caraterEncontrado.add(new DetalleCaracterEncontradoVO(word.length()-1,soup[fila][columna],new Integer[]{fila,columna},VAR_INICIO,new ArrayList<String>()));
        				}
    				}else {
    					contadorEncontrados=1;
    					count=1;
    						while(count<word.length()) {
    							if(buscar(caraterEncontrado.get(caraterEncontrado.size()-1).getPosicionMatriz()
    									,buscarSiguienteCarater(word,caraterEncontrado)
    									,caraterEncontrado.get(caraterEncontrado.size()-1).getOrientacionMovido())) {
    								contadorEncontrados++;
    							}else {
    								if(caraterEncontrado.size()>1) {
    									caraterEncontrado.get(caraterEncontrado.size()-2).getOrientacionesErroneas().add((caraterEncontrado.get(caraterEncontrado.size()-1).getOrientacionMovido()));
    									caraterEncontrado.remove(caraterEncontrado.size()-1);
    									count=new Integer(contadorEncontrados);
    									count=count-2;
    									contadorEncontrados--;
    								}else {
    									break;
    								}
    							}
    							count++;
    						}
    					
    					if(contadorEncontrados==word.length()) {
    						return true;
    					}else {
    						caraterEncontrado.clear();
    					}
    				}
    			}
    		}
			return false;
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
    }
    
    private CaracterVO buscarSiguienteCarater(String cadena,ArrayList<DetalleCaracterEncontradoVO> caraterEncontrado) {
    	if(caraterEncontrado.get(0).getPosicionCaracater()==0) {
    		return new CaracterVO(cadena.charAt(caraterEncontrado.get(caraterEncontrado.size()-1).getPosicionCaracater()+1),(caraterEncontrado.get(caraterEncontrado.size()-1).getPosicionCaracater()+1));
    	}else {
    		return new CaracterVO(cadena.charAt(caraterEncontrado.get(caraterEncontrado.size()-1).getPosicionCaracater()-1),(caraterEncontrado.get(caraterEncontrado.size()-1).getPosicionCaracater()-1));
    	}    	
    }
    
    private boolean buscar(Integer[] posicionMatrizActu,CaracterVO caracterBuscar,String orientacionAnterior){
    	Integer[] coordenadasEncontrado=null;
    	try {
    		switch (orientacionAnterior) {
    		case VAR_IZQUIERDA:
    			if(!isOrientacionError(VAR_IZQUIERDA,caraterEncontrado)) {//posiicon2
    				coordenadasEncontrado=buscadorMatriz.buscarIzquierda(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_IZQUIERDA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ARRIBA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarArriba(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ARRIBA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ABAJO,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarAbajo(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ABAJO,new ArrayList<String>()));
        				return true;
        			}
    			}
    			break;
    		case VAR_ARRIBA:
    			if(!isOrientacionError(VAR_IZQUIERDA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarIzquierda(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_IZQUIERDA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ARRIBA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarArriba(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ARRIBA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_DERECHA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarDerecha(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_DERECHA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			break;
    		case VAR_DERECHA:
    			if(!isOrientacionError(VAR_ARRIBA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarArriba(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ARRIBA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_DERECHA,caraterEncontrado)) {//Iz
    				coordenadasEncontrado=buscadorMatriz.buscarDerecha(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_DERECHA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ABAJO,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarAbajo(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ABAJO,new ArrayList<String>()));
        				return true;
        			}
    			}
    			break;
    		case VAR_ABAJO:
    			if(!isOrientacionError(VAR_IZQUIERDA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarIzquierda(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_IZQUIERDA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_DERECHA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarDerecha(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_DERECHA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ABAJO,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarAbajo(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ABAJO,new ArrayList<String>()));
        				return true;
        			}
    			}
    			break;
    		default:
    			//aun no tiene una posicion anterior, por lo tanto es el primer registro a buscar
    			if(!isOrientacionError(VAR_IZQUIERDA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarIzquierda(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_IZQUIERDA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ARRIBA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarArriba(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ARRIBA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_DERECHA,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarDerecha(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_DERECHA,new ArrayList<String>()));
        				return true;
        			}
    			}
    			if(!isOrientacionError(VAR_ABAJO,caraterEncontrado)) {
    				coordenadasEncontrado=buscadorMatriz.buscarAbajo(soup, posicionMatrizActu, caracterBuscar.getValor());
        			if(coordenadasEncontrado!=null) {
        				caraterEncontrado.add(new DetalleCaracterEncontradoVO(caracterBuscar.getPosicion(),caracterBuscar.getValor(),coordenadasEncontrado,VAR_ABAJO,new ArrayList<String>()));
        				return true;
        			}
    			}
    			
    			break;
    		}
        	return false;
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
    }
    
    private boolean isOrientacionError(String orientacion,ArrayList<DetalleCaracterEncontradoVO> datosEncontrados) {

    	return datosEncontrados.get(datosEncontrados.size()-1).getOrientacionesErroneas().stream().anyMatch(x -> x.equals(orientacion));
    	
    }



  
	
}
