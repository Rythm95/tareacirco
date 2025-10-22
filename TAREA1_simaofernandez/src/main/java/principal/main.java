package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

import entidades.*;


/*
 * Cosas que faltan:
 * usar paises.xml
 * configurar los menus de coordinacion y artistas
 * inicio de sesión para coordinacion y artistas
 * asignar coordinadores a espectaculos
 * guardar la sesión
 * poner mi nombre por algún sitio
 * Se asigna una persona de tipo Coordinador como responsable del
 *   nuevo Espectáculo. Si se tiene sesión como Administrador, se le
 *   mostrarán las personas
 * NO añadir vista completa de espectaculos
 */

public class main {

	static private Scanner read = new Scanner(System.in);
	static private Sesion sesion = new Sesion("Invitado", Perfil.INVITADO);
	static private TreeSet<Espectaculo> espectaculos = new TreeSet<>();
	static private TreeSet<Credenciales> credenciales = new TreeSet<>();

	public static void main(String[] args) {

		// Cargar los datos de espectaculos.dat a la lista
		espectaculos = cargarEspectaculos();

		System.out.println("Bienbenido/a.\nSeleccione la acción que desa hacer:");
		boolean salir = false;
		do {
			switch (sesion.getPerfil()) {
			case INVITADO:

				salir = menuInvitado();
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

		} while (!salir);

	}

	private static TreeSet<Espectaculo> cargarEspectaculos() {
		File espFile = new File("ficheros/espectaculos.dat");

		if (!espFile.exists())
			return espectaculos;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(espFile))) {
			espectaculos = (TreeSet<Espectaculo>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer los espectáculos");
		}
		return espectaculos;

	}

	private static void guardarEspectaculos() {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ficheros/espectaculos.dat"))) {
			oos.writeObject(espectaculos);
			System.out.println("El espectáculo se ha creado correctamente.");
		} catch (IOException e) {
			System.out.println("Error al crear el espectáculo.");
		}

	}

	private static void verEspectaculos() {
		espectaculos = cargarEspectaculos();
		for (Espectaculo e : espectaculos) {
			System.out.println(e.toString());
		}
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



	private static void gestionEspectaculos() {
		int menu = 0;

		do {
			System.out.println("¿Cómo desea grestionar los Espectaculos?");
			System.out.println(
					"3 - Crear o modificar espectáculo\n2 - Crear o modificar número\n1 - Asignar artistas\n0 - Cancelar");
			menu = read.nextInt();

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
			System.out.println("2 - Crear espectáculo\n1 - Modificar espectáculo existente\n0 - Cancelar");
			menu = read.nextInt();
			read.nextLine();
			switch (menu) {
			// Crear espectáculo
			case 2:
				System.out.println("Introduzca un nombre único para el espectáculo.");
				String name = read.nextLine(); // No debe superar los 25 char.
				if (name.length() > 25) {
					System.out.println("El nombre no debe superar los 25 caracteres.");
					break; // Este break en principio funciona??
				}
				LocalDate dateSt;
				System.out.println("Introduzca la fecha inicial del espectáculo. (yyyy-mm-dd)");
				try {
					dateSt = LocalDate.parse(read.next());
					read.nextLine();
				} catch (DateTimeParseException e) {
					System.out.println("La fecha no es válida.");
					break;
				}
				LocalDate dateEn;
				System.out.println("Introduzca la fecha final del espectáculo. (yyyy-mm-dd)");
				try {
					dateEn = LocalDate.parse(read.next());
					read.nextLine();

					if (dateEn.isBefore(dateSt)) {
						System.out.println("La fecha final no puede ser anterior a la fecha inicial.");
						break;
					} else if (dateSt.plusYears(1).isBefore(dateEn)) {
						System.out.println("La duración del espectáculo no debe ser superior a un año.");
						break;
					}

				} catch (DateTimeParseException e) {
					System.out.println("La fecha no es válida.");
					break;
				}

				espectaculos = cargarEspectaculos();

				// Verificar si ya hay un espectáculo con el mismo nombre
				boolean repetido = false;
				for (Espectaculo e : espectaculos) {
					if (e.getNombre().equalsIgnoreCase(name)) {
						System.out.println("Ya existe un espectáculo con ese nombre.");
						repetido = true;
						break;
					}

				}

				if (repetido)
					break;

				// Id incremental
				Long id = (espectaculos.isEmpty() ? 1 : espectaculos.last().getId() + 1);

				Espectaculo esp = new Espectaculo(id, name, dateSt, dateEn);
				espectaculos.add(esp);
				guardarEspectaculos();

				break;

			// Modificar espectáculo
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
		do {
			System.out.println("1 - Registrar Persona\n0 - Cancelar");
			menu = read.nextInt();

			switch (menu) {
			// Registrar persona
			case 1:
				read.nextLine();
				System.out.println("Introduce los datos de la persona que quieres registrar:");
				System.out.print("Nombre real: ");
				String name = read.nextLine().trim();
				System.out.print("Email: ");
				String email = read.next().trim();
				// Validar email
				if (!Pattern.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", email)) {
					System.out.println("El email no es válido.");
					menu = 0;
					break;
				}
				read.nextLine();
				System.out.print("Nacionalidad: "); // Usar paises.xml
				String nacionalidad = read.nextLine();
				String perfCA;
				do {
					System.out.print("Perfil (COORDINACION o ARTISTA): ");
					perfCA = read.next();
					if (!perfCA.equalsIgnoreCase("COORDINACION") && !perfCA.equalsIgnoreCase("ARTISTA")) {
						System.out.println("Valor de Perfil incorrecto. Vuelva a intentarlo.");
					}
				} while (!perfCA.equalsIgnoreCase("COORDINACION") && !perfCA.equalsIgnoreCase("ARTISTA"));
				Perfil perfil = Perfil.valueOf(perfCA.toUpperCase());
				Persona persona;
				if (perfil == Perfil.COORDINACION) {

					char esSenior;
					boolean senior = false;
					LocalDate seniorFecha = null;
					do {
						System.out.print("¿Es senior? [Y/N]: ");
						esSenior = read.next().toUpperCase().charAt(0);
						switch (esSenior) {
						case 'Y':
							senior = true;
							System.out.println("\n¿Desde qué fecha? (yyyy-mm-dd)");
							try {
								seniorFecha = LocalDate.parse(read.next());
								read.nextLine();
							} catch (DateTimeParseException e) {
								System.out.println("La fecha no es válida.");
								esSenior = 'X'; // Se repite el switch
								break;
							}
							break;

						case 'N':
							senior = false;
							break;

						default:
							System.out.println("Se ha introducido un valor no válido. Vuelva a intentarlo.\n");
						}
					} while (esSenior != 'Y' && esSenior != 'N');

					persona = new Coordinacion(personaID(), email, name, nacionalidad, perfilID(perfil), senior, seniorFecha);
				} else {
					char tieneApodo;
					String apodo = null;
					do {
						System.out.print("¿Tiene apodo? [Y/N]: ");
						tieneApodo = read.next().toUpperCase().charAt(0);
						read.nextLine();
						switch (tieneApodo) {
						case 'Y':

							System.out.print("Apodo: ");
							apodo = read.nextLine().trim();
							break;

						case 'N':
							break;

						default:
							System.out.println("Se ha introducido un valor no válido. Vuelva a intentarlo.\n");
						}
					} while (tieneApodo != 'Y' && tieneApodo != 'N');

					List<Especialidad> listaEspecialidades;
					boolean listError = true;
					do {
						System.out.println(
								"Especialidades (ACROBACIA,HUMOR,MAGIA,EQUILIBRIO,MALABARISMO Separadas por ',')");

						String[] especialidades = read.nextLine().toUpperCase().split(",");
						listaEspecialidades = new ArrayList<>();
						listError=false;
						for (String es : especialidades) {
							try {
								listaEspecialidades.add(Especialidad.valueOf(es.trim()));
							} catch (IllegalArgumentException e) {
								System.out.println("La especialidad no es válida. Vuelva a intentarlo.");
								listError = true;
								break;
							}
						}
					} while (listError);

					persona = new Artista(personaID(), email, name, nacionalidad, perfilID(perfil), apodo,
							listaEspecialidades);
				}
				String user = "";
				System.out.println();
				do {
					System.out.print("Nombre de usuario: ");
					user = read.nextLine().toLowerCase();
					if (user == "")
						System.out.println("\nEl nombre de usuario no debe estar vacío.");
					if (user.length() < 2) {
						System.out.println("\nEl nombre de usuario debe contener más de 2 caracteres");
						user = "";
					}
					if (user.contains(" ")) {
						System.out.println("\nEl nombre de usuario no debe contener espacios");
						user = "";
					}
					if (!Pattern.matches("^[a-z]+$", user)) {
						System.out.println(
								"\nEl nombre de usuario no debe contener números ni letras con tíldes o dieresis");
						user = "";
					}
				} while (user == "");

				String password = "";
				System.out.println();
				do {
					System.out.print("Contraseña: ");
					password = read.nextLine();
					if (password == "")
						System.out.println("\nLa contraseña no debe estar vacía.");
					if (password.length() < 2) {
						System.out.println("\nLa contraseña debe contener más de 2 caracteres");
						password = "";
					}
					if (password.contains(" ")) {
						System.out.println("\nLa contraseña no debe contener espacios");
						password = "";
					}
				} while (password == "");

				if (existeUsuario(user)) {
					System.out.println("Ya hay un usuario regirstrado con ese nombre.");
					return;
				}

				if (existeEmail(email)) {
					System.out.println("Ya hay un usuario regirstrado con ese email.");
					return;
				}

				Credenciales credenciales = new Credenciales(personaID(), user, password, perfil);
				saveCredenciales(persona,credenciales);

				break;
			case 0:
				System.out.println("Operación cancelada.");
				break;

			default:
				System.out.println("Se ha introducido un valor no válido. Vuelva a intentarlo.\n");

			}
		} while (menu != 0);
	}

	private static void saveCredenciales(Persona p, Credenciales c) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/credenciales.txt", true))) {
			bw.write(p.getidPersona() + "|" + c.getNombre() + "|" + c.getPassword() + "|" + p.getEmail() + "|"
					+ p.getNombre() + "|" + p.getNacionalidad() + "|" + c.getPerfil());
			bw.newLine();
			System.out.println("La persona se ha registrado con éxito.");
		} catch (IOException e) {
			System.out.println("Error al guardar credenciales.");
		}
	}

	private static Long personaID() {
		Long idMax = 0L;
		try (BufferedReader br = new BufferedReader(new FileReader("ficheros/credenciales.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] creds = line.split("\\|");
				Long id = Long.parseLong(creds[0]);
				if (id > idMax)
					idMax = id;
			}
		} catch (IOException e) {
		}
		return idMax + 1;
	}

	// Obtiene el ID correspondiente de Artista o Coordinador
	private static Long perfilID(Perfil perfil) {
		Long idMax = 0L;
		Long id = 0L;
		try (BufferedReader br = new BufferedReader(new FileReader("ficheros/credenciales.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] creds = line.split("\\|");
				if (creds.length > 6 && creds[6].equalsIgnoreCase(perfil.toString()))
					id++;
				if (id > idMax)
					idMax = id;
			}
		} catch (IOException e) {
		}
		return idMax + 1;

	}

	private static TreeSet<Credenciales> cargarCredenciales() {
		File creFile = new File("ficheros/credenciales.txt");

		if (!creFile.exists())
			return credenciales;

		try (BufferedReader br = new BufferedReader(new FileReader(creFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] creds = line.split("\\|");
				if (creds.length >= 4) {
					Long id = Long.parseLong(creds[0]);
				}
			}

		} catch (IOException e) {
			System.out.println("Error al leer las credenciales.");
		}
		return credenciales;
		
		
	}
	
//\\// Menus
	private static boolean menuInvitado() {

		int menu;
		System.out.println("2 - Iniciar Sesión\n1 - Ver Espectáculos\n0 - Salir");
		menu = read.nextInt();
		switch (menu) {
		case 2:
			System.out.println("Ini");
			inicioSesion();
			break;

		case 1:
			System.out.println("Estos son los espectáculos programados:\n");
			verEspectaculos();
			break;

		case 0:
			System.out.println("¡Adiós!");
			return true;

		default:
			System.out.println("Ha introducido un valor incorrecto. Por favor, vuelva a  intentarlo.");

		}
		return false;
	}

	private static void menuAdmin() {

		int menu;
		System.out.println("Seleccione la acción que desea realizar.");
		System.out.println(
				"4 - Gestionar Personas\n3 - Gestionar Espectáculos\n2 - Ver Espectáculos\n1 - Ver Espectáculo completo\n0 - Cerrar Sesión");
		menu = read.nextInt();
		switch (menu) {

		// Gestionar persona
		case 4:

			gestionPersonas();
			System.out.println();
			break;

		case 3:
			System.out.println();
			gestionEspectaculos();
			System.out.println();
			break;

		case 0:
			sesion.setPerfil(Perfil.INVITADO);
			System.out.println("Su sesión ha sido cerrada");

			return;

		default:
			System.out.println("Ha introducido un valor incorrecto. Por favor, vuelva a  intentarlo.");

		}
	}
	
	private static void menuCoordinacion() {
		
	}
	
	private static void menuArtista() {
		
	}
	
//\\// Validaciones
	private static boolean existeUsuario(String user) {

		try (BufferedReader br = new BufferedReader(new FileReader("ficheros/credenciales.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] creds = line.split("\\|");
				if (creds.length > 1 && creds[1].equalsIgnoreCase(user))
					return true;
			}
		} catch (IOException e) {
			// Si da error probablemente no esté repetido.
		}
		return false;
	}

	private static boolean existeEmail(String email) {

		try (BufferedReader br = new BufferedReader(new FileReader("ficheros/credenciales.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] creds = line.split("\\|");
				if (creds.length > 3 && creds[3].equalsIgnoreCase(email))
					return true;
			}
		} catch (IOException e) {
			// Si da error probablemente no esté repetido.
		}
		return false;
	}

}
