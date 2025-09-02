package galeria;

import java.util.Arrays;

public class Galeria {
	private String nombre;
	private String ciudad;
	private Exhibicion[] exhibiciones;
	
	public Galeria(String nombre, String ciudad, Exhibicion[] exhibiciones) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.exhibiciones = exhibiciones;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public Exhibicion[] getExhibiciones() {
		return exhibiciones;
	}
	
	public void addExhibicion(String titulo, String curador, Obra[] obras) {
		Exhibicion e = new Exhibicion(titulo, curador, obras);
		if(exhibiciones != null) exhibiciones = Arrays.copyOf(exhibiciones, exhibiciones.length + 1);
		else exhibiciones = new Exhibicion[1];
		exhibiciones[exhibiciones.length - 1] = e;
	}
	
	public void eliminarExhibicion(String titulo) {
		int indexAEliminar = devolverIndexExhibicion(titulo);
		Exhibicion[] newExhibiciones = new Exhibicion[exhibiciones.length - 1];
		System.arraycopy(exhibiciones, 0, newExhibiciones, 0, indexAEliminar);
		System.arraycopy(exhibiciones, indexAEliminar + 1, newExhibiciones, indexAEliminar, exhibiciones.length - indexAEliminar - 1);
		exhibiciones = newExhibiciones;
	}
	
	public void actualizarExhibicion(String curador, String titulo) {
		int indexExhibicion = devolverIndexExhibicion(titulo);
		exhibiciones[indexExhibicion].setCurador(curador);
	}
	
	public Exhibicion buscarExhibicion(String titulo) {
		int index = devolverIndexExhibicion(titulo);
		return exhibiciones[index];
	}
	
	public int devolverIndexExhibicion(String titulo) {
		if(exhibiciones == null) return -1;
		int index = 0;
		while(index < exhibiciones.length && !titulo.equalsIgnoreCase(exhibiciones[index].getTitulo())) index++;
		return (index == exhibiciones.length)? -1 : index;
	}
	
	public void addPintura(String exhibicion, String titulo, String artista, String anio, String tecnica) {
		Exhibicion e = buscarExhibicion(exhibicion);
		Obra p = new Pintura(titulo, artista, anio, tecnica);
		e.addObra(p);
	}
	
	public void addEscultura(String exhibicion, String titulo, String artista, String anio, String material) {
		Exhibicion e = buscarExhibicion(exhibicion);
		Obra p = new Escultura(titulo, artista, anio, material);
		e.addObra(p);
	}
	
	public void eliminarObra(String exhibicion, String titulo) {
		Exhibicion e = buscarExhibicion(exhibicion);
		e.eliminarObra(titulo);
	}
	
	public void actualizarObra(String exhibicion, String titulo, String anio) {
		Exhibicion e = buscarExhibicion(exhibicion);
		e.actualizarObra(anio, titulo);
	}
	
	public String mostrarCatalogo() {
		StringBuilder catalogo = new StringBuilder();
		for(Exhibicion e : exhibiciones) {
			catalogo.append("\n");
			catalogo.append(e.getTitulo() + ":\n");
			for(Obra o : e.getObras()) {
				catalogo.append(o.getInfo() + "\n");
			}
		}
		return catalogo.toString();
	}
	
    public static void main(String[] args) {
        // Crear galería vacía
        Galeria g = new Galeria("Arte Moderno", "Bogotá", null);

        // Crear obras para primera exhibición
        Obra[] obras1 = {
            new Pintura("La noche estrellada", "Van Gogh", "1889", "Óleo sobre lienzo"),
            new Escultura("El Pensador", "Rodin", "1902", "Bronce")
        };

        // Agregar primera exhibición
        g.addExhibicion("Impresionismo", "María López", obras1);

        // Agregar segunda exhibición vacía
        g.addExhibicion("Renacimiento", "Carlos Ruiz", new Obra[0]);

        // Agregar obras a la segunda exhibición
        g.addPintura("Renacimiento", "La última cena", "Leonardo da Vinci", "1498", "Tempera y óleo");
        g.addEscultura("Renacimiento", "David", "Miguel Ángel", "1504", "Mármol");

        // Mostrar catálogo completo
        System.out.println("=== Catálogo inicial ===");
        System.out.println(g.mostrarCatalogo());

        // Actualizar curador de una exhibición
        g.actualizarExhibicion("Ana Torres", "Impresionismo");

        // Actualizar año de una obra
        g.actualizarObra("Renacimiento", "David", "1501");

        // Eliminar obra
        g.eliminarObra("Impresionismo", "El Pensador");

        // Eliminar exhibición completa
        g.eliminarExhibicion("Renacimiento");

        // Mostrar catálogo después de cambios
        System.out.println("=== Catálogo actualizado ===");
        System.out.println(g.mostrarCatalogo());
    }
}
