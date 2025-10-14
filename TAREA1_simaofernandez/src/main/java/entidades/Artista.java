package entidades;

public class Artista extends Persona {
	private Long idArt;
	private String apodo=null;
	private Especialidad[] especialidades;

	public Artista(Long id, String email, String nombre, String nacionalidad, Long idArt,
			Especialidad[] especialidades) {
		super(id, email, nombre, nacionalidad);
		this.idArt = idArt;
		this.apodo = null;
		this.especialidades = especialidades;
	}
	
	public Artista(Long id, String email, String nombre, String nacionalidad, Long idArt, String apodo,
			Especialidad[] especialidades) {
		super(id, email, nombre, nacionalidad);
		this.idArt = idArt;
		this.apodo = apodo;
		this.especialidades = especialidades;
	}

	public Long getIdArt() {
		return idArt;
	}

	public void setIdArt(Long idArt) {
		this.idArt = idArt;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public Especialidad[] getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Especialidad[] especialidades) {
		this.especialidades = especialidades;
	}

}
