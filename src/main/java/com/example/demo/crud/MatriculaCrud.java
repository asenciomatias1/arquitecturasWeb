package com.example.demo.crud;

import com.example.demo.model.Matricula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface MatriculaCrud extends CrudRepository<Matricula, Long> {

    //List<Matricula> findByName(String name);
}
