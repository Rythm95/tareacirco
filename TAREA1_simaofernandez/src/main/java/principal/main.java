package principal;

import java.time.LocalDate;
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
			sesion = new Sesion("admin", Perfil.ADMIN);
//			O puede ser así??
//			sesion.setNombre("admin");
//			sesion.setPerfil(Perfil.ADMIN);
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
			System.out.println("Estos son los espectáculos programados:");
			break;

		case 0:
			System.out.println("¡Adiós!");

			return;

		default:
			System.out.println("Ha introducido un valor incorrecto. Por favor, vuelva a  intentarlo.");

		}
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
