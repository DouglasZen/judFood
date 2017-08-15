package br.com.wsfood.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultado {
	boolean resultado;

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	
	
}
