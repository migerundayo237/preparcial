package Parte2;

import java.time.LocalDate;

public class Transaccion {
	private LocalDate fecha;
	private boolean tipo;
	private double valor;
	
	
	public Transaccion(boolean tipo, double valor) {
		this.fecha = LocalDate.now();
		this.tipo = tipo;
		this.valor = valor;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public boolean isTipo() {
		return tipo;
	}


	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
}
