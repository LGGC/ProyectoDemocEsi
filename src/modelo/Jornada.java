package modelo;
import java.util.ArrayList;

public class Jornada {
	private String referenteInstitucional;
	private String objetivo;
	private String titulo;
	private ArrayList<Material> materiales = new ArrayList<>();
	
	public Jornada(String referente, String objetivo, String titulo) {
		this.referenteInstitucional = referente;
		this.objetivo = objetivo;
		this.titulo = titulo;
	}
	public Jornada() {
	}
	public Jornada(String referente, String objetivo, String titulo, ArrayList<Material> materiales) {
		this.referenteInstitucional = referente;
		this.objetivo = objetivo;
		this.titulo = titulo;
		this.materiales = materiales;
	}
	public boolean esPrioritaria() {
		return procentajePrioritarias() >= 0.5f;
	}
	private float procentajePrioritarias() {
		if (cantMateriales() != 0) {
		return cantMaterialesPrioritarios()/cantMateriales();
		}
		return 0f;
	}
	private int cantMaterialesPrioritarios() {
		int i = 0;
		for (Material m : getMateriales()) {
			if(m.esPrioritario()) {
				i++;
			}
		}
		return i;
	}
	private int cantMateriales() {
		return getMateriales().size();
	}
	
	public String getReferenteInstitucional() {
		return referenteInstitucional;
	}
	public void setReferenteInstitucional(String referenteInstitucional) {
		this.referenteInstitucional = referenteInstitucional;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ArrayList<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}
}