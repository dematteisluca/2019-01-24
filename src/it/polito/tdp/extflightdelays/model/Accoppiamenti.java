package it.polito.tdp.extflightdelays.model;

public class Accoppiamenti {
	
	String a1;
	String a2;
	Double peso;
	public Accoppiamenti(String a1, String a2, Double peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	

}
