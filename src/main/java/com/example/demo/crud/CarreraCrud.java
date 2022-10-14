package com.example.demo.crud;

import com.example.demo.model.Carrera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface CarreraCrud extends CrudRepository<Carrera, Long> {

    //List<Carrera> findByName(String name);
}
