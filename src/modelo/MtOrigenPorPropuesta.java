package modelo;
import java.util.ArrayList;

public class MtOrigenPorPropuesta extends Material{
	ArrayList<Propuesta> propuestas = new ArrayList<>();

	//Constructores
	public MtOrigenPorPropuesta(){};
	
	public MtOrigenPorPropuesta(String jornada, String titulo, String categoria, String descripcion, String fuente,
			String enlaceDoc, ArrayList<Propuesta> propuestas) {
		super(jornada, titulo, categoria, descripcion, fuente, enlaceDoc);
		this.propuestas = propuestas;
	}

	//Getters y Setters
	public ArrayList<Propuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(ArrayList<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	//Metodos
	public boolean esPrioritario() {
		return cantPropuestas() >= 4;
	}
	
	private int cantPropuestas() {
		return getPropuestas().size();
	}
	
}
