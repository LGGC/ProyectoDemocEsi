package modelo;

public abstract class Material {
	
	//Atributos
	private String jornada;
	private String titulo;
	private String categoria;
	private String descripcion;
	private String fuente;
	private String enlaceDoc;
	
	
	//Constructores
	public Material(){};
	
	public Material(String jornada, String titulo, String categoria, String descripcion, String fuente, String enlaceDoc) {
		this.jornada = jornada;
		this.titulo = titulo;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fuente = fuente;
		this.enlaceDoc = enlaceDoc;
	}

	//Getters y Setters
	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getEnlaceDoc() {
		return enlaceDoc;
	}
	public void setEnlaceDoc(String enlaceDoc) {
		this.enlaceDoc = enlaceDoc;
	}
	
	//Comportamientos
	
	public abstract boolean esPrioritario();
	
}
