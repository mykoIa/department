package ua.sukhorutchenko.department;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.springframework.web.multipart.MultipartFile;
import ua.sukhorutchenko.department.model.Employee;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CSVMapped {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Employee> saveEmployee(MultipartFile file) {
        List<Employee> listEmployee = new ArrayList<>();
        try {
            Reader readerFileCSV = new InputStreamReader(file.getInputStream());
            CSVReader reader = new CSVReader(readerFileCSV, ';', '"', 1);
            CsvToBean csv = new CsvToBean();
            listEmployee = csv.parse(setColumnMapping(), reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listEmployee;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumnMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Employee.class);
        String[] columns = new String[]{"name", "department", "salary"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
