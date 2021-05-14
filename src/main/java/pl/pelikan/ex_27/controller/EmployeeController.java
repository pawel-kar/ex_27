package pl.pelikan.ex_27.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pelikan.ex_27.model.Employee;
import pl.pelikan.ex_27.model.EmployeeRepository;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    private String redirectToHomeSite() {
        return "index";
    }

    @GetMapping("/showEmployees")
    public String showEmployees(Model model) {
        List<Employee> employees;
        employees = employeeRepository.findAll();
        model.addAttribute("employeeList", employees);
        return "employeeList";
    }

    @GetMapping("/add")
    public String redirectToAddForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployeeForm(Employee employee) {
        employeeRepository.save(employee);
        return "index";
    }
}
