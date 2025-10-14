package principal;
import java.time.LocalDate;
import java.util.Scanner;

import entidades.*;

public class main {

	public static void main(String[] args) {
		
		Scanner read = new Scanner(System.in);
		
		System.out.println("Bienbenido/a.\nSeleccione la acción que desa hacer:");
		
		int menu;
		
		
		// Aún probando el menu y viendo cómo lo podría hacer.
		do {
			System.out.println("2 - Iniciar Sesión\n1 - Ver Espectáculos\n0 - Salir");
			menu = read.nextInt();
			switch (menu) {
				case 2:
					System.out.println("Ini");
					break;
					
				case 1:
					System.out.println("Estos son los espectáculos programados:");
					break;
					
				case 0:
					System.out.println("Sali");
					break;
					
				default:
					System.out.println("Ha introducido un valor incorrecto. Por favor, vuelva a  intentarlo.");
					
			}
			
		} while (menu!=0);
		
		
	}

}
