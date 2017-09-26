package br.com.wsfood.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultado {
	boolean resultado;
	int codigoPessoa;

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}
	
	
	
	
}
