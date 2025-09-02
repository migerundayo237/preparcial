package galeria;

public class Pintura extends Obra {
	private String tecnica;

	public Pintura(String titulo, String artista, String anio, String tecnica) {
		super(titulo, artista, anio);
		this.tecnica = tecnica;
	}

	public String getTecnica() {
		return tecnica;
	}
	
	@Override
	public String getInfo() {
		return "Pintura: " + titulo + " por " + artista + ", " + tecnica;
	}
}
