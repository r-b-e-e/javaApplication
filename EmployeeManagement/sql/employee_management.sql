drop table if exists employee_management.Employee;

create table employee_management.Employee(
	employee_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    employment_start_date VARCHAR(255) NOT NULL,
    employee_type ENUM('Full-Time', 'Part-Time', 'Intern', 'Contract') NOT NULL,
    salary INT NOT NULL,
	salary_based ENUM('per-hour', 'per-annum') NOT NULL
);