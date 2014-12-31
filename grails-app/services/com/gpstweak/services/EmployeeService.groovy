package com.gpstweak.services

import com.gpstweak.domain.Employee
import grails.transaction.Transactional
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.Transaction
import org.hibernate.cfg.AnnotationConfiguration
import org.hibernate.SessionFactory

@Transactional
public class EmployeeService {

    private static SessionFactory factory

    public  void doStuff() {
        try{
            factory = new AnnotationConfiguration().configure().buildSessionFactory()
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex)
            throw new ExceptionInInitializerError(ex)
        }

        /* Add few employee records in database */
        try {
            Integer empID1 = addEmployee("Zara", "Ali", 1000)
            Integer empID2 = addEmployee("Daisy", "Das", 5000)
            Integer empID3 = addEmployee("John", "Paul", 10000)
            /* List down all the employees */
            listEmployees()
            /* Update employee's records */
            updateEmployee(empID1, 5000)
            /* Delete an employee from the database */
            deleteEmployee(empID2)
            /* List down new list of the employees */
            listEmployees()
        } catch (Exception ex) {
            ex.printStackTrace()
        } finally {
            factory.close()
        }
    }
    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, int salary){
        Session session = factory.openSession()
        Transaction tx = null
        Integer employeeID = null
        try{
            tx = session.beginTransaction()
            Employee employee = new Employee()
            employee.setFirstName(fname)
            employee.setLastName(lname)
            employee.setSalary(salary)
            employeeID = (Integer) session.save(employee)
            tx.commit()
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback()
            e.printStackTrace()
        }finally {
            session.close()
        }
        return employeeID
    }
    /* Method to READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession()
        Transaction tx = null
        try{
            tx = session.beginTransaction()
            List employees = session.createQuery("FROM Employee").list()
            employees.each {
                System.out.print("ID: " + it.id)
                System.out.print("First Name: " + it.getFirstName())
                System.out.print(" Last Name: " + it.getLastName())
                System.out.println(" Salary: " + it.getSalary())
            }
            tx.commit()
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback()
            e.printStackTrace()
        }finally {
            session.close()
        }
    }
    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession()
        Transaction tx = null
        try{
            tx = session.beginTransaction()
            Employee employee = (Employee)session.get(Employee.class, EmployeeID)
            employee.setSalary( salary )
            session.update(employee)
            tx.commit()
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback()
            e.printStackTrace()
        }finally {
            session.close()
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession()
        Transaction tx = null
        try{
            tx = session.beginTransaction()
            Employee employee =
                (Employee)session.get(Employee.class, EmployeeID)
            session.delete(employee)
            tx.commit()
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback()
            e.printStackTrace()
        }finally {
            session.close()
        }
    }
}