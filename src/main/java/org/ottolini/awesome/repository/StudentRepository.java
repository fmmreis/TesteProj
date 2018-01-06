package org.ottolini.awesome.repository;

import org.ottolini.awesome.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //Hibernate: select student0_.id as id1_0_, student0_.student_name as student_2_0_ from students student0_ where student0_.student_name=?
    List<Student> findByName(String name);

    //Hibernate: select student0_.id as id1_0_, student0_.student_name as student_2_0_ from students student0_ where upper(student0_.student_name) like upper(?)
    List<Student> findByNameIgnoreCaseContaining(String name);
}
