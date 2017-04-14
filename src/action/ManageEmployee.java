package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import model.Employee;

public class ManageEmployee extends ActionSupport{
	private static SessionFactory factory=new Configuration().configure().buildSessionFactory();
	List<Employee> employeeList = new ArrayList<Employee>();
	Employee employee = new Employee();
	private HttpServletRequest request;
	private EmployeeDAO employeedao=new EmployeeDAO();
//	factory = new Configuration().configure().buildSessionFactory();

//	public static void main(String[] args) {
//		try {
//			factory = new Configuration().configure().buildSessionFactory();
//		} catch (Throwable ex) {
//			// TODO: handle exception
//			System.err.println("Failed to create sessionFactory object." + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//		
//	      ManageEmployee ME = new ManageEmployee();
//
//	      /* Add few employee records in database */
//	      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
//	      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
//	      Integer empID3 = ME.addEmployee("John", "Paul", 10000);
//
//	      /* List down all the employees */
//	      ME.listEmployees();
//
//	      /* Update employee's records */
//	      ME.updateEmployee(empID1, 5000);
//
//	      /* Delete an employee from the database */
//	      ME.deleteEmployee(empID2);
//
//	      /* List down new list of the employees */
//	      ME.listEmployees();
//	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

//	public String addEmployee(String firstName, String lastName, Integer salary) {
//		Session session = factory.openSession();
//		Transaction tx = null;
//		Integer employeeID = null;
//		try {
//			tx = session.beginTransaction();
//			Employee employee = new Employee(firstName, lastName, salary);
//			employeeID = (Integer) session.save(employee);
//			tx.commit();
//		} catch (HibernateException e) {
//			// TODO: handle exception
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		return SUCCESS;
//	}
//	
	
	

	

	
//	public String loadEmployeeList(){
//		return SUCCESS;
//	}
	
	public String addEmployee() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(employee);
//			System.out.println(employee.getFirstName());
//			System.out.println(employee.getLastName());
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SUCCESS;
	}

	public String listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			List employees = session.createQuery("FROM Employee").list();
//			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
//				Employee employee = (Employee) iterator.next();
//				System.out.print("First Name: " + employee.getFirstName());
//				System.out.print("  Last Name: " + employee.getLastName());
//				System.out.println("  Salary: " + employee.getSalary());
//			}
			employeeList=session.createQuery("FROM Employee").list();
			tx.commit();
	

		} catch (HibernateException e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SUCCESS;
	}
	
	public String delete(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		//System.out.println(request.getParameter("id"));
		employeedao.deleteEmployee(Integer.parseInt((request.getParameter("id"))));
		return SUCCESS;
	}
	
	public String updateDetails(){
		Employee selectedEmployee;
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		selectedEmployee=employeedao.getEmployeeByID(Integer.parseInt((request.getParameter("id"))));
		request.setAttribute("selectedEmployee", selectedEmployee);
		return SUCCESS;
	}
	
	public String update(){

//		System.out.println(employee.getFirstName());
//		System.out.println(employee.getLastName());
		employeedao.updateEmployee(employee);
		return SUCCESS;
	}
	

}
