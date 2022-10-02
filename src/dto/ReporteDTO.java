package dto;

public class ReporteDTO {

	private String carrera;
	private int anio;
	private int inscriptos;
	private int egresados;
	
	public ReporteDTO() {
		super();
	}
	
	public ReporteDTO(String carrera, int anio, int inscriptos, int egresados) {
		this.carrera = carrera;
		this.anio = anio;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
	}

	public String getCarrera() {
		return carrera;
	}

	public int getAnio() {
		return anio;
	}

	public int getInscriptos() {
		return inscriptos;
	}

	public int getEgresados() {
		return egresados;
	}

	@Override
	public String toString() {
		return "ReporteDTO [carrera=" + carrera + ", anio=" + anio + ", inscriptos=" + inscriptos + ", egresados="
				+ egresados + "]";
	}
	
	
}
