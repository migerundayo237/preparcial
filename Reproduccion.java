package Parte2;

import java.time.LocalDate;

public class Reproduccion {
	 private LocalDate fecha;
	    private int idPelicula;
	    private int minutosVistos;

	    public Reproduccion(int idPelicula, int minutosVistos) {
	        this.fecha = LocalDate.now();
	        this.idPelicula = idPelicula;
	        this.minutosVistos = minutosVistos;
	    }

	    public LocalDate getFecha() { return fecha; }
	    public int getIdPelicula() { return idPelicula; }
	    public int getMinutosVistos() { return minutosVistos; }
}
