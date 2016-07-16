package com.rakesh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rakesh.bean.Employee;
import com.rakesh.util.FacSessionFactory;

public class ModelDao implements ModelInterface {

	@Override
	public void insertEmployeeDetails(Employee employee) {
		Session session = FacSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> listEmployees = new ArrayList<Employee>();
		Session session = FacSessionFactory.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			Criteria cr = session.createCriteria(Employee.class);
			listEmployees = cr.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return listEmployees;
	}

	@Override
	public Employee getEmployeeData(int employeeID) {
		Session session = FacSessionFactory.getSessionFactory().openSession();
		Employee employeeToUpdate = new Employee();
		;
		try {
			session.beginTransaction();
			Criteria cr = session.createCriteria(Employee.class);
			cr.add(Restrictions.eq("employee_id", employeeID));

			Object result = cr.uniqueResult();
			if (result != null) {
				employeeToUpdate = (Employee) result;
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeToUpdate;
	}

	@Override
	public void updateEmployee(Employee employeeToUpdate) {

		Session session = FacSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(employeeToUpdate);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public JSONArray exportToJson(List<Employee> employeeList) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;
		ListIterator<Employee> itr = employeeList.listIterator();
		while (itr.hasNext()) {
			Employee singleEmployee = itr.next();
			jsonObject = new JSONObject();
			try {
				jsonObject.put("employee_id", singleEmployee.getEmployee_id());
				jsonObject.put("first_name", singleEmployee.getFirst_name());
				jsonObject.put("last_name", singleEmployee.getLast_name());
				jsonObject.put("employment_start_date", singleEmployee.getEmployment_start_date());
				jsonObject.put("employee_type", singleEmployee.getEmployee_type());
				jsonObject.put("salary", singleEmployee.getSalary());
				jsonObject.put("salary_based", singleEmployee.getSalary_based());

				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonArray;
	}

	@Override
	public void importToDatabase(JSONArray jsonArrayToDatabase) {

		JSONObject jsonObjectToDatabase = null;
		Employee employeeToDatabase = null;

		for (int i = 0; i < jsonArrayToDatabase.length(); i++) {
			Session session = FacSessionFactory.getSessionFactory().openSession();
			try {
				jsonObjectToDatabase = new JSONObject();
				jsonObjectToDatabase = jsonArrayToDatabase.getJSONObject(i);

				employeeToDatabase = new Employee();
				employeeToDatabase.setEmployee_id(0);
				employeeToDatabase.setFirst_name(jsonObjectToDatabase.getString("first_name"));
				employeeToDatabase.setLast_name(jsonObjectToDatabase.getString("last_name"));
				employeeToDatabase.setEmployment_start_date(jsonObjectToDatabase.getString("employment_start_date"));
				employeeToDatabase.setEmployee_type(jsonObjectToDatabase.getString("employee_type"));
				employeeToDatabase.setSalary_based(jsonObjectToDatabase.getString("salary_based"));
				employeeToDatabase.setSalary(jsonObjectToDatabase.getInt("salary"));

				session.beginTransaction();
				session.save(employeeToDatabase);
				session.getTransaction().commit();
			} catch (JSONException | HibernateException e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	}

}
