package entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class Espectaculo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private LocalDate fechaini;
	private LocalDate fechacfin;
	// NO HACE FALTA MODIFICAR ESPECTACULO (aún)

	public Espectaculo(Long id, String nombre, LocalDate fechaini, LocalDate fechacfin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaini = fechaini;
		this.fechacfin = fechacfin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaini() {
		return fechaini;
	}

	public void setFechaini(LocalDate fechaini) {
		this.fechaini = fechaini;
	}

	public LocalDate getFechacfin() {
		return fechacfin;
	}

	public void setFechacfin(LocalDate fechacfin) {
		this.fechacfin = fechacfin;
	}

	@Override
	public String toString() {
		return id+"";
	}

}
