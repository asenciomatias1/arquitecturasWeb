package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private int duracion;
    @Column
    private int cantEstudiantes;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_carrera")
    private List<Matricula> matriculas;

    public Carrera() {
        super();
        matriculas = new ArrayList<Matricula>();
    }

    public Carrera(String nombre, int duracion) {
        super();
        this.nombre = nombre;
        this.duracion = duracion;
        cantEstudiantes = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCantEstudiantes() {
        return cantEstudiantes;
    }

    public void setCantEstudiantes(int cantEstudiantes) {
        this.cantEstudiantes = cantEstudiantes;
    }
    
    public void aumentarCantEstudiantes() {
    	this.cantEstudiantes++;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Carrera [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", cantEstudiantes="
                + cantEstudiantes + ", matriculas=" + "]\n";
    }

}
