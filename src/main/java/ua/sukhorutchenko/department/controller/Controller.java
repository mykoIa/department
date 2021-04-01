package ua.sukhorutchenko.department.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.sukhorutchenko.department.CSVMapped;
import ua.sukhorutchenko.department.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private List<Employee> listEmployee = new ArrayList<>();

    @PostMapping("/upload")
    public Object CSVFileWithUserData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "file is empty";
        }
        listEmployee = CSVMapped.saveEmployee(file);
        return "200";
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Employee> getAllEmployee() {
        return listEmployee;
    }

    @GetMapping("/get-max-employee-salary")
    @ResponseBody
    public Employee getMaxSalaryInDepartment(@RequestParam("department") String department) {
        return listEmployee.stream().filter(employee -> employee.getDepartment().toLowerCase().equals(department.toLowerCase()))
                .max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary())).get();
    }

    @GetMapping("/get-all-sorted")
    @ResponseBody
    public List<Employee> sortedBySalary() {
        return listEmployee.stream()
                .sorted(Comparator.comparing(Employee::getDepartment).thenComparing(Employee::getSalary, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
