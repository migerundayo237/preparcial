package Parte2;

import java.time.LocalDate;
import java.util.Arrays;

public class Suscriptor {
	private String id;
	private String nombre;
	private String correo;
	private boolean tipo;
	private double saldo;
	private Transaccion[] transacciones;
	private Reproduccion[] reproducciones;
	
	
	public Suscriptor(String id, String nombre, String correo, boolean tipo, double saldo) {
		
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.tipo = tipo;
		this.saldo = saldo;
		this.transacciones = new Transaccion[0];
		this.reproducciones = new Reproduccion[0];
	}
	
	


	public void setCorreo(String correo) {
		this.correo = correo;
	}




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public boolean isTipo() {
		return tipo;
	}


	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}


	public String getId() {
		return id;
	}


	public String getCorreo() {
		return correo;
	}


	public double getSaldo() {
		return saldo;
	}


	public Transaccion[] getTransacciones() {
		return transacciones;
	}


	public Reproduccion[] getReproducciones() {
		return reproducciones;
	}
	
	
	
	public void addReproduccion(int id_pelicula, int minutosVistos) {
		Reproduccion r = new Reproduccion( id_pelicula, minutosVistos);
		reproducciones = Arrays.copyOf(reproducciones, reproducciones.length+1);
		reproducciones[reproducciones.length-1] = r;
	}
	
	
	// calcula EL MES
	public double calcularValor(int mes, int anio) {
	    int minutos = 0;
	    int contador = 0;
	    double total = 0;

	    if (tipo == true) { // true = BASICO
	        for (Reproduccion r : reproducciones) {
	            if (r.getFecha().getMonthValue() == mes && r.getFecha().getYear() == anio) {
	                contador++;
	            }
	        }
	        total = contador * 2.0;
	    } else { // false = PREMIUM
	        for (Reproduccion r : reproducciones) {
	            if (r.getFecha().getMonthValue() == mes && r.getFecha().getYear() == anio) {
	                minutos += r.getMinutosVistos();
	            }
	        }
	        total = minutos * 0.05; // <-- TARIFA correcta
	    }
	    return total;
		
	}
	
	
	
	public void cerrarMes(int mes, int anio) {
	    double cargo = calcularValor(mes, anio);
	    if (cargo > 0) {
	        saldo += cargo; // aumenta deuda
	        Transaccion t = new Transaccion(true, cargo);
	        transacciones = Arrays.copyOf(transacciones, transacciones.length + 1);
	        transacciones[transacciones.length - 1] = t;
	    }
	}
	    
	    public boolean registrarPago(double monto) {
	        if (monto <= 0) return false;
	        saldo -= monto;
	        Transaccion t = new Transaccion(false, monto);
	        transacciones = Arrays.copyOf(transacciones, transacciones.length + 1);
	        transacciones[transacciones.length - 1] = t;
	        return true;
	   
	}

	
	
	
	
}

