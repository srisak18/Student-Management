package com.luv2code.cruddemo.daoImpl;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

        // Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // Create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName = :theData", Student.class);

        // Set the parameter
        theQuery.setParameter("theData", lastName);

        // Return the query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {

        // Create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where firstName = :theData", Student.class);

        // Set the parameter
        theQuery.setParameter("theData", firstName);

        // Return the query
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByEmail(String email) {

        // Create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where email like :theData", Student.class);

        // Set the parameter
        theQuery.setParameter("theData", email);

        // Return the query
        return theQuery.getResultList();
    }
}
