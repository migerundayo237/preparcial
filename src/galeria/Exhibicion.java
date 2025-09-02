package galeria;

import java.util.Arrays;

public class Exhibicion {
	private String titulo;
	private String curador;
	private Obra[] obras;

	public Exhibicion(String titulo, String curador, Obra[] obras) {
		this.titulo = titulo;
		this.curador = curador;
		this.obras = obras;
	}

	public String getCurador() {
		return curador;
	}

	public void setCurador(String curador) {
		this.curador = curador;
	}

	public String getTitulo() {
		return titulo;
	}

	public Obra[] getObras() {
		return obras;
	}
	
	public void addObra(Obra o) {
		if(obras != null) obras = Arrays.copyOf(obras, obras.length + 1);
		else obras = new Obra[1];
		obras[obras.length - 1] = o;
	}
	
	public void eliminarObra(String titulo) {
		int indexAEliminar = devolverIndexObra(titulo);
		Obra[] newObras = new Obra[obras.length - 1];
		System.arraycopy(obras, 0, newObras, 0, indexAEliminar);
		System.arraycopy(obras, indexAEliminar + 1, newObras, indexAEliminar, obras.length - indexAEliminar - 1);
		obras = newObras;
	}
	
	public void actualizarObra(String anio, String titulo) {
		int indexObra = devolverIndexObra(titulo);
		obras[indexObra].setAnio(anio);
	}
	
	public int devolverIndexObra(String titulo) {
		if(obras == null) return -1;
		int index = 0;
		while(index < obras.length && !titulo.equalsIgnoreCase(obras[index].getTitulo())) index++;
		return (index == obras.length)? -1 : index;
	}
}
