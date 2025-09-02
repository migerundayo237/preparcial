package streaming;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Plataforma plataforma = new Plataforma(null, null);

        int opcion;
        do {
            System.out.println("\n=== MENU PLATAFORMA STREAMING ===");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Agregar pelicula");
            System.out.println("4. Agregar serie");
            System.out.println("5. Eliminar contenido");
            System.out.println("6. Ver contenido (usuario ve un titulo)");
            System.out.println("7. Actualizar suscripción");
            System.out.println("8. Actualizar rating de contenido");
            System.out.println("9. Mostrar reporte mensual");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre usuario: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Tipo suscripción: ");
                    String tipo = sc.nextLine();
                    plataforma.addUsuario(nombre, email, tipo);
                    System.out.println("Usuario agregado.");
                    break;
                case 2:
                    System.out.print("Nombre usuario a eliminar: ");
                    String nombreDel = sc.nextLine();
                    plataforma.eliminarUsuario(nombreDel);
                    System.out.println("Usuario eliminado.");
                    break;
                case 3:
                    System.out.print("Titulo película: ");
                    String tituloP = sc.nextLine();
                    System.out.print("Genero: ");
                    String generoP = sc.nextLine();
                    System.out.print("Calificación: ");
                    int calP = sc.nextInt();
                    System.out.print("Duración (min): ");
                    int dur = sc.nextInt();
                    plataforma.addPelicula(tituloP, generoP, calP, dur);
                    System.out.println("Película agregada.");
                    break;
                case 4:
                    System.out.print("Titulo serie: ");
                    String tituloS = sc.nextLine();
                    System.out.print("Genero: ");
                    String generoS = sc.nextLine();
                    System.out.print("Calificación: ");
                    int calS = sc.nextInt();
                    System.out.print("Número episodios: ");
                    int eps = sc.nextInt();
                    plataforma.addSerie(tituloS, generoS, calS, eps);
                    System.out.println("Serie agregada.");
                    break;
                case 5:
                    System.out.print("Titulo de contenido a eliminar: ");
                    String tituloDel = sc.nextLine();
                    plataforma.eliminarContenido(tituloDel);
                    System.out.println("Contenido eliminado.");
                    break;
                case 6:
                    System.out.print("Nombre usuario: ");
                    String userWatch = sc.nextLine();
                    System.out.print("Titulo contenido: ");
                    String tituloWatch = sc.nextLine();
                    plataforma.verContenido(userWatch, tituloWatch);
                    System.out.println("El usuario ha visto el contenido.");
                    break;
                case 7:
                    System.out.print("Nombre usuario: ");
                    String userSub = sc.nextLine();
                    System.out.print("Nueva suscripción: ");
                    String newSub = sc.nextLine();
                    plataforma.actualizarSuscripcion(userSub, newSub);
                    System.out.println("Suscripción actualizada.");
                    break;
                case 8:
                    System.out.print("Titulo contenido: ");
                    String cont = sc.nextLine();
                    System.out.print("Nueva calificación: ");
                    int newCal = sc.nextInt();
                    plataforma.actualizarRating(cont, newCal);
                    System.out.println("Rating actualizado.");
                    break;
                case 9:
                    plataforma.mostrarReporteMensual();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}

