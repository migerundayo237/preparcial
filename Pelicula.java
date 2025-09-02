package Parte2;

public class Pelicula {
	 private int id;
	    private String titulo;
	    private String genero;
	    private int duracion; 
	    public static int contador = 1;

	    public Pelicula(String titulo, String genero, int duracion) {
	        this.id = contador++;
	        this.titulo = titulo;
	        this.genero = genero;
	        this.duracion = duracion;
	    }

	    public int getId() { return id; }
	    public String getTitulo() { return titulo; }
	    public String getGenero() { return genero; }
	    public int getDuracion() { return duracion; }
}
