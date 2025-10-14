package principal;
import java.time.LocalDate;

import entidades.*;

public class main {

	public static void main(String[] args) {
		
		LocalDate date = LocalDate.of(2020, 1, 8);
		
		new Espectaculo(1L, "Malabarismos", LocalDate.of(2023, 12, 10),LocalDate.of(2024, 1, 6));
		new Espectaculo(2L, "Trapecismo", LocalDate.of(2024, 1, 8),LocalDate.of(2024, 2, 8));
		new Espectaculo(3L, "Monólogos", LocalDate.of(2024, 2, 8),LocalDate.of(2024, 2, 8));
		// test
	}

}
