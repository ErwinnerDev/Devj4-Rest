package com.baufest.ingreso.strings;



import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepeatedCharacters {

    /**
     * El metodo debe retornar un booleano indicando si el parametro `cadena` cumple con alguna de las siguientes propiedades:
     * 1- Todos los caracteres aparecen la misma cantidad de veces.
     *     Ejemplos: "aabbcc", "abcdef", "aaaaaa"
     * 2- Todos los caracteres aparecen la misma cantidad de veces, a excepcion de 1, que aparece un vez mas o una vez menos.
     *     Ejemplos: "aabbccc", "aabbc", "aaaaccccc"
     * @param cadena la cadena a evaluar
     * @return booleano indicando si la cadena cumple con las propiedades
     */
	public Boolean isValid(String cadena) {
		
		Stream<Character> 					characterStream 		= null;
		Map<Character, Long> 				mapAgrupacionCaracter 	= null;
    	Map<Long, Long> 					mapAgrupador			= null; 
    	ArrayList<Long> 					listAgrupadorValues		= null;
    	ArrayList<Long> 					listAgrupadorKey		= null;
    	try {
    			
    		if(cadena==null) {return false;}
    		else if(cadena.isEmpty()){return false;}
    		
    		//Se divide la cadena en caracteres
        	characterStream = cadena.chars().mapToObj(c -> (char) c);
        	//Se crean grupos de caracteres<caracater,Repeticones>
        	mapAgrupacionCaracter=characterStream.collect(Collectors.groupingBy(chr -> chr, Collectors.counting()));
        	//mapAgrupacionCaracter.forEach((k,v)-> System.out.println(k+"-"+v));
        	
        	//Se crean grupos de el numero de agrupacionesCaracteres <agrupacionesCracteres,Repeticiones> 
        	mapAgrupador = mapAgrupacionCaracter.values().stream().collect(Collectors.groupingBy(x -> x,Collectors.counting()));
        	//mapAgrupador.forEach((k,v)-> System.out.println(k+"--"+v));
        	
        	//se hace un cast de un Map a un List
        	listAgrupadorValues= new ArrayList<>(mapAgrupador.values());
        	if(listAgrupadorValues.size()==1) { //Todos los caracteres aparecen la misma cantidad de veces
        		return true;
        	}
        	else if(listAgrupadorValues.size()>2) {// hay mas de dos caracteres que difieren en apariciones
        		return false;
        	}else if(listAgrupadorValues.size()==2) {
        		if(listAgrupadorValues.get(0)>1 && listAgrupadorValues.get(1)>1) {// Los caracteres aparecen la misma cantidad de veces, a excepcion de >1
        			return false;
        		}
        		listAgrupadorKey=new ArrayList<>(mapAgrupador.keySet());
        		return isDiferenciaAceptable(listAgrupadorKey.get(0),listAgrupadorKey.get(1));
        	}else{
        		return false;
        	}
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}finally {
			characterStream 		= null;
			mapAgrupacionCaracter 	= null;
	    	mapAgrupador			= null; 
	    	listAgrupadorValues		= null;
	    	listAgrupadorKey		= null;
		}
    }
	
	private boolean isDiferenciaAceptable(Long valor1,Long valor2) {
		Long resultado =valor1-valor2;
		if(resultado==1 || resultado==-1) {return true;}
		else {return false;}
	}

}
