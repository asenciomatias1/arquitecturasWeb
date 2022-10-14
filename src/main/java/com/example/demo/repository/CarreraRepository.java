package com.example.demo.repository;

import com.example.demo.model.Carrera;
import com.example.demo.model.ReporteDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT c FROM Carrera c where c.nombre = :name")
    public List<Carrera> findAllByName(String name);

    @Query("SELECT c FROM Carrera c where c.cantEstudiantes > 0")
    public List<Carrera> findAllWithEstudiantes();

    /*
    @Modifying
    @Query(value="SELECT c.nombre, m.anio_cursada, c.cantEstudiantes as inscriptos, 0 as graduados "
            + "FROM Carrera c INNER JOIN Matricula m ON c.id = m.fk_carrera " + "GROUP BY c.id, m.anio_cursada "
            + " HAVING m.anio_cursada != 0" + " UNION "
            + "SELECT c.nombre, m.anio_cursada, 0 as inscriptos, count(m.graduado) as graduados "
            + "FROM Carrera c INNER JOIN Matricula m ON c.id = m.fk_carrera " + "GROUP BY c.id, m.graduado "
            + " HAVING m.graduado != 0 " + "ORDER BY nombre, anio_cursada ASC",nativeQuery=true)
    public List<ReporteDTO> generarReporte();
    */
    
    @Modifying
    @Query(value="SELECT c.cantEstudiantes FROM Carrera c",nativeQuery=true)
    public List<ReporteDTO> generarReporte();
    
    /*
    public List<ReporteDTO> asdad() {
        String sql = "SELECT c.nombre, m.anio_cursada, c.cantEstudiantes as inscriptos, 0 as graduados "
                + "FROM Carrera c INNER JOIN Matricula m ON c.id = m.fk_carrera " + "GROUP BY c.id, m.anio_cursada "
                + " HAVING m.anio_cursada != 0" + " UNION "
                + "SELECT c.nombre, m.anio_cursada, 0 as inscriptos, count(m.graduado) as graduados "
                + "FROM Carrera c INNER JOIN Matricula m ON c.id = m.fk_carrera " + "GROUP BY c.id, m.graduado "
                + " HAVING m.graduado != 0 " + "ORDER BY nombre, anio_cursada ASC";

        Query query = em.createNativeQuery(sql);
        List<Object[]> reportes = query.getResultList();
        List<ReporteDTO> reporte = reportes.stream()
                .map(o -> new ReporteDTO((String) o[0], (int) o[1], (int) o[2], (BigInteger) o[3]))
                .collect(Collectors.toList());
        return reporte;
    }
    */
}
