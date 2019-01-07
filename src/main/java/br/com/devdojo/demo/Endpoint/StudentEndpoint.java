package br.com.devdojo.demo.Endpoint;

import br.com.devdojo.demo.error.CustomErrorType;
import br.com.devdojo.demo.model.Student;
import br.com.devdojo.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        //System.out.println("----"+dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}") //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> student = studentDAO.findById(id);
        if (student == null)
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}") //@RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping //@RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Student student) {
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

