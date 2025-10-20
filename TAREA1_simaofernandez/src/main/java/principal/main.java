package principal;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import entidades.*;

public class main {

	static Scanner read = new Scanner(System.in);
	static Sesion sesion = new Sesion("Invitado", Perfil.INVITADO);

	public static void main(String[] args) {

		System.out.println("Bienbenido/a.\nSeleccione la acción que desa hacer:");

		// Aún probando el menu y viendo cómo lo podría hacer.
		do {
			switch (sesion.getPerfil()) {
			case INVITADO:

				menuInvitado();
				System.out.println();
				break;

			case ADMIN:

				menuAdmin();
				System.out.println();
				break;

			case COORDINACION:
				break;

			case ARTISTA:
				break;

			default:
				System.out.println("Ha habido algún error al procesar su sesión. Por favor, vuelva a intentarlo.");
				sesion.setPerfil(Perfil.INVITADO);

			}

		} while (true);

	}

	private static void inicioSesion() {
		String name;
		String password;

		System.out.println("Introduce tu nombre de usuario:");
		name = read.next().trim();
		System.out.println("Introduce tu contraseña");
		password = read.next().trim();

		if (name.equals("admin") && password.equals("admin")) {
			sesion.setNombre(name);
			sesion.setPerfil(Perfil.ADMIN);
			System.out.println("Has iniciado sesión como Administrador.");
		} else
			System.out.println("Usuario o contraseña incorrectos. Vuelva a intentarlo.");

	}

	private static void menuInvitado() {

		int menu;
		System.out.println("2 - Iniciar Sesión\n1 - Ver Espectáculos\n0 - Salir");
		menu = read.nextInt();
		switch (menu) {
		case 2:
			System.out.println("Ini");
			inicioSesion();
			break;

		case 1:
			System.out.println("Estos son los espectáculos programados:");
			// Lista de espectáculos
			break;

		case 0:
			System.out.println("¡Adiós!");
			return;

		default:
			System.out.println("Ha introducido un valor incorrecto. Por favor, vuelva a  intentarlo.");

		}
	}

	private static void menuAdmin() {

		int menu;
		System.out.println("Seleccione la acción que desea realizar.");
		System.out.println(
				"4 - Gestionar Personas\n3 - Gestionar Espectáculos\n2 - Ver Espectáculos\n1 - Ver Espectáculo completo\n0 - Cerrar Sesión");
		menu = read.nextInt();
		switch (menu) {

		case 4:

			gestionPersonas();
			System.out.println();
			break;

		case 3:

			System.out.println();
			break;

		case 0:
			System.out.println("¡Adiós!");

			return;

		default:
			System.out.println("Ha introducido un valor incorrecto. Por favor, vuelva a  intentarlo.");

		}
	}

	private static void gestionEspectaculos() {
		int menu = 0;

		do {
			System.out.println("¿Cómo desea grestionar los Espectaculos?");
			System.out.println(
					"3 - Crear o modificar espectáculo\n2 - Crear o modificar número\n1 - Asignar artistas\n0 - Cancelar");
			switch (menu) {
			case 3:
				cmEspectaculo();
				break;
			case 2: // Sin Implementar
				System.out.println("La función aún no ha sido implementada.");
				break;
			case 1: // Sin Implementar
				System.out.println("La función aún no ha sido implementada.");
				break;
			case 0:
				System.out.println();
				break;
			default:
				System.out.println("Se ha introducido un valor no válido. Vuelva a intentarlo.\n");

			}

		} while (menu != 0);

	}

	private static void cmEspectaculo() {
		int menu = 0;
		do {
			System.out.println("2 - Crear espectáculo\n1 - Modificar espectáculo\n0 - Cancelar");
			switch (menu) {
			case 2:
				System.out.println("Introduzca un ID para el espectáculo.");
				Long id = read.nextLong(); // No se debe repetir
				System.out.println("Introduzca un nombre único para el espectáculo.");
				String name = read.nextLine(); // No debe superar los 25 char.
				if (name.length() > 25) {
					System.out.println("El nombre no debe superar los 25 caracteres.");
					break; // Este break en principio funciona??
				}
				LocalDate dateSt;
				System.out.println("Introduzca la fecha inicial del espectáculo.");
				try {
					dateSt = LocalDate.parse(read.next());
					read.nextLine();
				} catch (DateTimeParseException e) {
					System.out.println("La fecha no es válida.");
					break;
				}
				LocalDate dateEn;
				System.out.println("Introduzca la fecha final del espectáculo.");
				try {
					dateEn = LocalDate.parse(read.next());
					read.nextLine();
					
					if (dateEn.isBefore(dateSt)) {
						System.out.println("La fecha final no puede ser anterior a la fecha inicial.");
						break;
					}
					else if(dateSt.plusYears(1).isAfter(dateEn)){
						System.out.println("La duración del espectáculo no debe ser superior a un año.");
						break;
					}
											
				} catch (DateTimeParseException e) {
					System.out.println("La fecha no es válida.");
					break;
				}
				Espectaculo esp = new Espectaculo(id, name, dateSt, dateEn);
				// Se añadiría a la lista
				System.out.println("El espectáculo se ha creado correctamente.");
				
				break;

			case 1:
				System.out.println("La función aún no ha sido implementada.");
				break;

			case 0:
				System.out.println("Operación cancelada.");
				break;

			default:
				System.out.println("Se ha introducido un valor no válido. Vuelva a intentarlo.\n");

			}
		} while (menu != 0);
	}

	private static void gestionPersonas() {
		int menu = 0;
		System.out.println("¿Cómo desea gestionar las Personas?");
		System.out.println("2 - Registrar Persona\n1 - Agisnar Credenciales\n0 - Cancelar");
		menu = read.nextInt();
		switch (menu) {
		case 1:

			break;

		case 2:

			break;

		}
	}

}
