package com.example.demo.crud;

import com.example.demo.model.Estudiante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface EstudianteCrud extends CrudRepository<Estudiante, Long> {

    //List<Estudiante> findByName(String name);
}
