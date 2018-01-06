package org.ottolini.awesome.endpoint;

import org.ottolini.awesome.error.CustomErrorType;
import org.ottolini.awesome.error.ResourceNotFoundException;
import org.ottolini.awesome.model.Student;
import org.ottolini.awesome.repository.StudentRepository;
import org.ottolini.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController // = @Controller + @ResponseBody
@RequestMapping("students")
public class StudentEndpoint {

    private DateUtil dateUtil;
    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(DateUtil dateUtil, StudentRepository studentDAO) {
        this.dateUtil = dateUtil;
        this.studentDAO = studentDAO;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    //TODO Test @ResponseStatus
    public ResponseEntity<?> listAll() {
        //this line tests dependency injection
        System.out.println("---------- " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        verifyIfStudentExists(id);
        Student student = studentDAO.findOne(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path="/findByName/{name}")
    @GetMapping(path="/findByName/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable("name") String name){
            //List<Student> students = studentDAO.findByName(name);
            List<Student> students = studentDAO.findByNameIgnoreCaseContaining(name);

            if (students.size() == 0)
                return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(students.get(0), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST, path = "/{id}")
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExists(student.getId());
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.NO_CONTENT);
    }

    //@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfStudentExists(id);
        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    private void verifyIfStudentExists(Long id){
        if (studentDAO.findOne(id) == null){
            throw new ResourceNotFoundException("Student not found for id: " + id);
        }
    }

}
