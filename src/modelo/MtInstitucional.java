package modelo;

public class MtInstitucional extends Material{
	
	//Atributos
	private String procedencia;
	private boolean prioritario;
	
	//Constructores
	public MtInstitucional() {};
	
	public MtInstitucional(String jornada, String titulo, String categoria, String descripcion, String fuente, String enlaceDoc, String procedencia, boolean prioritario) {
		super(jornada, titulo, categoria, descripcion, fuente, enlaceDoc);
		this.procedencia = procedencia;
		this.prioritario = prioritario;
	}

	//Getters y Setters 
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public boolean getPrioritario() {
		return prioritario;
	}
	public void setPrioritario(boolean prioritario) {
		this.prioritario = prioritario;
	}
	
	//Metodos
	public boolean esPrioritario() {
		return getPrioritario();
	}
	
}
