package galeria;

public abstract class Obra {
	protected String titulo;
	protected String artista;
	protected String anio;

	public Obra(String titulo, String artista, String anio) {
		this.titulo = titulo;
		this.artista = artista;
		this.anio = anio;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getArtista() {
		return artista;
	}
	
	public abstract String getInfo();
}
