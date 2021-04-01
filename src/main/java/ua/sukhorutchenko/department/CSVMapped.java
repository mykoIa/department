package ua.sukhorutchenko.department;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.springframework.web.multipart.MultipartFile;
import ua.sukhorutchenko.department.model.Employee;

public class CSVMapped {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Employee> saveEmployee(MultipartFile file) {
        try {
            Reader readerFileCSV = new InputStreamReader(file.getInputStream());
            CSVReader reader = new CSVReader(readerFileCSV, ';', '"', 1);
            CsvToBean csv = new CsvToBean();
            return csv.parse(setColumnMapping(), reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
