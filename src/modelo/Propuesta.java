package modelo;
import java.sql.Date;

public class Propuesta {
	private String origen;
	private String categoria;
	private String autor;
	private Date fecha;
	private String titulo;
	private String descripcion;
	private String motivacion;
	private String estado ;
	private String motivoRechazo;
	
	public Propuesta() {
		this.fecha = new Date(System.currentTimeMillis());
	}
	
	public Propuesta(String origen, String categoria, String autor, Date fecha, String titulo, String descripcion,
			String motivacion, String motivoRechazo) {
		this.origen = origen;
		this.categoria = categoria;
		this.autor = autor;
		this.fecha = fecha;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.motivacion = motivacion;
		this.motivoRechazo = motivoRechazo;
	}
	
	public Propuesta(String titulo) {
	}


	public Propuesta(String origen, String categoria, String autor, Date fecha, String titulo, String descripcion,
			String motivacion,String estado ,String motivoRechazo) {
		this.origen = origen;
		this.categoria = categoria;
		this.autor = autor;
		this.fecha = fecha;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.motivacion = motivacion;
		this.estado = estado;
		this.motivoRechazo = motivoRechazo;
	}

	

	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMotivacion() {
		return motivacion;
	}
	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	
	
}
