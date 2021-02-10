class Employee {

    String name;
    int salary;
    String address;

    String status = "unknown";

    Employee() {
        name = status;
        salary = 0;
        address = status;
    }

    Employee(String name, int salary) {
        this();
        this.name = name;
        this.salary = salary;
    }

    Employee(String name, int salary, String address) {
        this(name, salary);
        this.address = address;
    }
}