package br.com.devdojo.demo.repository;

import br.com.devdojo.demo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);

//    Student findOne(Long id);
//    void delete(Long id);

}
