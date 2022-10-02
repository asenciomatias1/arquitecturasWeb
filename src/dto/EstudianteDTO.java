package dto;

import java.io.Serializable;

public class EstudianteDTO implements Serializable{

	private int libreta_universitaria;
	private String nombre;
	private String apellido;
	private String genero;
	private int edad;
	private int documento;
	private String ciudad;
	
	public EstudianteDTO() {
		super();
	}

	public EstudianteDTO(String nombre, String apellido, int edad, String genero, int documento, String ciudad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.documento = documento;
		this.ciudad = ciudad;
	}

	public int getLibreta_universitaria() {
		return libreta_universitaria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getGenero() {
		return genero;
	}

	public int getEdad() {
		return edad;
	}

	public int getDocumento() {
		return documento;
	}

	public String getCiudad() {
		return ciudad;
	}

	@Override
	public String toString() {
		return "EstudianteDTO [libreta_universitaria=" + libreta_universitaria + ", nombre=" + nombre + ", apellido="
				+ apellido + ", genero=" + genero + ", edad=" + edad + ", documento=" + documento + ", ciudad=" + ciudad
				+ "]";
	}
	
	
}
