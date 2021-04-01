## Departments' employees

## Application has four methods
- HTTP endpoint to upload data to this task. <br>
  #### Example
  localhost:8080/ <br>
  You can upload the file using the command in the terminal <br>
  **curl -i -X POST -H "Content-type: multipart/form-data" -F "file=@data.csv" http://locast:8080/upload <br>**
  The file is checked for emptiness
- HTTP endpoint to get all employees. <br>
  **/get-all**
- HTTP endpoint to get employees with the highest salary per department. The endpoint must accept the department as an input parameter. <br>
  **/get-max-employee-salary?department={nameOfDepartment}**
- HTTP endpoint to get all departments where each department contains employees sorted by salary descending.<br>
  **/get-all-sorted**
