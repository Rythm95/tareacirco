package entidades;

public abstract class Persona {
	protected Long idPersona;
	protected String email;
	protected String nombre;
	protected String nacionalidad;

	public Persona(Long id, String email, String nombre, String nacionalidad) {
		super();
		this.idPersona = id;
		this.email = email;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public Long getId() {
		return idPersona;
	}

	public void setId(Long id) {
		this.idPersona = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

}
