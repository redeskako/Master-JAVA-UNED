package es.uned.master.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import es.uned.master.java.service.EmployeeManager;

public class EmployeeController implements Controller{
	private EmployeeManager employeeManager;

	public EmployeeManager getEmployeeManager(){
		return this.employeeManager;
	}

	public void setEmployeeManager(EmployeeManager employeeManager){
		this.employeeManager = employeeManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
										HttpServletResponse response){
		System.out.println("en EmpleyController");
		ModelAndView mv = new ModelAndView("employeeList");
		mv.addObject("employeeList", this.employeeManager.getEmployeeList());
		return mv;
	}
}