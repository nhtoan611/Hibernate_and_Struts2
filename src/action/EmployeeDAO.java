package action;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Employee;

public class EmployeeDAO {
	private static SessionFactory factory=new Configuration().configure().buildSessionFactory();

//	public void updateEmployee(Integer EmployeeID, String fname, String lname, int salary) {
//		Session session = factory.openSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
//			employee.setFirstName(fname);
//			employee.setLastName(lname);
//			employee.setSalary(salary);
//			session.update(employee);
//			
//			tx.commit();
//		} catch (HibernateException e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//	}
	
	public void updateEmployee(Employee employee){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                   (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	public Employee getEmployeeByID(int employeeID){
		Session session = factory.openSession();
		Employee employee=null;
		try {
			employee=(Employee) (Employee) session.get(Employee.class, employeeID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return employee;
		
	}
}
