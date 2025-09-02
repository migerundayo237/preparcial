package Parte2;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Plataforma pl = new Plataforma();

        // Películas (IDs autogenerados: 1, 2, ...)
        Pelicula p1 = new Pelicula("Interestelar", "Sci-Fi", 169);
        Pelicula p2 = new Pelicula("Coco", "Animación", 105);
        pl.agregarPelicula(p1);
        pl.agregarPelicula(p2);

        // Suscriptores: tipo=true => BASICO, false => PREMIUM
        Suscriptor s1 = new Suscriptor("S1", "Ana Gómez", "ana@mail.com", true, 0.0);
        Suscriptor s2 = new Suscriptor("S2", "Luis Pérez", "luis@mail.com", false, 0.0);
        pl.agregarSuscriptor(s1);
        pl.agregarSuscriptor(s2);

        // Reproducciones del mes actual
        pl.addReproduccion("S1", p1.getId(), 120); // Básico: cuenta 1 reproducción ($2)
        pl.addReproduccion("S1", p2.getId(), 60);  // Básico: otra reproducción ($2) => total $4

        pl.addReproduccion("S2", p1.getId(), 169); // Premium: minutos suman
        pl.addReproduccion("S2", p2.getId(), 100); // Premium: total 269 min * $0.05 = $13.45

        // Pago de S2 antes del cierre
        pl.registrarPago("S2", 5.00);

        // Cierre de mes
        LocalDate hoy = LocalDate.now();
        int mes = hoy.getMonthValue();
        int anio = hoy.getYear();
        pl.cerrarMes(mes, anio);

        // Reportes
        System.out.println(pl.reporteMensual("S1", mes, anio));
        System.out.println(pl.reporteMensual("S2", mes, anio));
    // TODO Auto-generated method stub

	}

}
