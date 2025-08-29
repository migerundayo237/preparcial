package Parte2;

import java.time.LocalDate;
import java.util.Arrays;

public class Plataforma {
	private Suscriptor[] suscriptores;
	private Pelicula[] peliculas;
	public Plataforma() {
		
		this.suscriptores = new Suscriptor[0];
		this.peliculas = new Pelicula[0];
	}
	
	  public int devolverIndexSuscriptor(String id) {
	        int i = 0;
	        while (i < suscriptores.length && !id.equalsIgnoreCase(suscriptores[i].getId())) i++;
	        return (i == suscriptores.length) ? -1 : i;
	    }

	    public void agregarSuscriptor(Suscriptor s) {
	        if (s == null) throw new IllegalArgumentException("Suscriptor nulo");
	        if (devolverIndexSuscriptor(s.getId()) != -1)
	            throw new IllegalArgumentException("Ya existe suscriptor " + s.getId());
	        suscriptores = Arrays.copyOf(suscriptores, suscriptores.length + 1);
	        suscriptores[suscriptores.length - 1] = s;
	    }

	    public void eliminarSuscriptor(String id) {
	        int idx = devolverIndexSuscriptor(id);
	        if (idx == -1) { System.out.println("Suscriptor no encontrado."); return; }
	        Suscriptor[] nuevo = new Suscriptor[suscriptores.length - 1];
	        System.arraycopy(suscriptores, 0, nuevo, 0, idx);
	        System.arraycopy(suscriptores, idx + 1, nuevo, idx, suscriptores.length - idx - 1);
	        suscriptores = nuevo;
	    }

	    public void actualizarSuscriptor(String id, String nuevoNombre, String nuevoCorreo, boolean nuevoPlan) {
	        int idx = devolverIndexSuscriptor(id);
	        if (idx == -1) { System.out.println("Suscriptor no encontrado."); return; }
	        Suscriptor s = suscriptores[idx];
	        if (nuevoNombre != null) s.setNombre(nuevoNombre);
	        if (nuevoCorreo != null) s.setCorreo(nuevoCorreo);
	         s.setTipo(nuevoPlan);
	    }


    public int devolverIndexPelicula(int idPelicula) {
        int i = 0;
        while (i < peliculas.length && idPelicula != peliculas[i].getId()) i++;
        return (i == peliculas.length) ? -1 : i;
    }

    public void agregarPelicula(Pelicula p) {
        if (p == null) throw new IllegalArgumentException("Película nula");
       
        peliculas = Arrays.copyOf(peliculas, peliculas.length + 1);
        peliculas[peliculas.length - 1] = p;
    }

    public void eliminarPelicula(int idPelicula) {
        int idx = devolverIndexPelicula(idPelicula);
        if (idx == -1) { System.out.println("Película no encontrada."); return; }
        Pelicula[] nuevo = new Pelicula[peliculas.length - 1];
        System.arraycopy(peliculas, 0, nuevo, 0, idx);
        System.arraycopy(peliculas, idx + 1, nuevo, idx, peliculas.length - idx - 1);
        peliculas = nuevo;
    }

    
    public boolean addReproduccion(String idSuscriptor, int idPelicula, int minutosVistos) {
        int idxS = devolverIndexSuscriptor(idSuscriptor);
        int idxP = devolverIndexPelicula(idPelicula);
        if (idxS == -1 || idxP == -1) return false;
        if (minutosVistos <= 0) throw new IllegalArgumentException("Minutos vistos debe ser > 0");
        suscriptores[idxS].addReproduccion(idPelicula, minutosVistos);
        return true;
    }

    public boolean addReproduccion(String idSuscriptor, int idPelicula, LocalDate fecha, int minutosVistos) {
        int idxS = devolverIndexSuscriptor(idSuscriptor);
        int idxP = devolverIndexPelicula(idPelicula);
        if (idxS == -1 || idxP == -1) return false;
        if (minutosVistos <= 0) throw new IllegalArgumentException("Minutos vistos debe ser > 0");
    
        suscriptores[idxS].addReproduccion(idPelicula, minutosVistos); 
        return true;
    }

    public boolean registrarPago(String idSuscriptor, double monto) {
        int idxS = devolverIndexSuscriptor(idSuscriptor);
        if (idxS == -1) return false;
        return suscriptores[idxS].registrarPago(monto);
    }

    // cerrarMes: llama cerrarMes(mes, anio) en cada suscriptor
    public void cerrarMes(int mes, int anio) {
        for (Suscriptor s : suscriptores) {
            s.cerrarMes(mes, anio);
        }
    }

    // reporte mensual: imprime reproducciones del mes y transacciones del mes
    public String reporteMensual(String idSuscriptor, int mes, int anio) {
        int idxS = devolverIndexSuscriptor(idSuscriptor);
        if (idxS == -1) return "Suscriptor no encontrado.";
        Suscriptor s = suscriptores[idxS];

        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte ===\n")
          .append("Suscriptor: ").append(s.getId()).append(" - ").append(s.getNombre())
          .append(" | Plan: ").append(s.isTipo() ? "BASICO" : "PREMIUM").append("\n")
          .append("Mes/Año: ").append(String.format("%02d/%d", mes, anio)).append("\n\n");

        // Reproducciones
        sb.append("Reproducciones del mes:\n");
        boolean hayRep = false;
        for (Reproduccion r : s.getReproducciones()) {
            if (r.getFecha().getMonthValue() == mes && r.getFecha().getYear() == anio) {
                String titulo = tituloDe(r.getIdPelicula());
                sb.append(" - ").append(r.getFecha()).append(" | [")
                  .append(r.getIdPelicula()).append("] ").append(titulo)
                  .append(" | ").append(r.getMinutosVistos()).append(" min\n");
                hayRep = true;
            }
        }
        if (!hayRep) sb.append(" (sin reproducciones)\n");

        // Transacciones
        sb.append("\nTransacciones del mes:\n");
        boolean hayTx = false;
        for (Transaccion t : s.getTransacciones()) {
            if (t.getFecha().getMonthValue() == mes && t.getFecha().getYear() == anio) {
                String etiqueta = t.isTipo() ? "CARGO" : "PAGO";
                String signo = t.isTipo() ? "+" : "-";
                sb.append(" - ").append(t.getFecha()).append(" | ")
                  .append(etiqueta).append(" | ")
                  .append(signo).append(String.format("%.2f", t.getValor())).append("\n");
                hayTx = true;
            }
        }
        if (!hayTx) sb.append(" (sin transacciones)\n");

        sb.append(String.format("%nSaldo (deuda) actual: $%.2f%n", s.getSaldo()));
        return sb.toString();
    }
   
    private String tituloDe(int idPelicula) {
        int idx = devolverIndexPelicula(idPelicula);
        return (idx == -1) ? ("Pelicula " + idPelicula) : peliculas[idx].getTitulo();
    }
	
	
	
}
