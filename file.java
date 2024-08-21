import java.util.*;

class Employee {
    public int EID;
    public String Ename;
    public String EAddress;
    public int ESalary;

    private static int Counter;

    static {
        Counter = 0;
    }

    public Employee(String B, String C, int D) {
        this.EID = ++Counter;
        this.Ename = B;
        this.EAddress = C;
        this.ESalary = D;
    }

    void DisplayInfo() {
        System.out.println(EID + "\t" + Ename + "\t\t" + EAddress + "\t\t" + ESalary);
    }
}

class MarvellousDBMS {
    public LinkedList<Employee> lobj;

    public MarvellousDBMS() {
        System.out.println("Marvellous DBMS started successfully...");
        lobj = new LinkedList<Employee>();
    }

    // protected void finalize() {
    //     System.out.println("Deallocating all resources of DBMS");
    //     lobj = null;
    // }

//Explicit Cleanup Method
    protected void  cleanup() {
        System.out.println("Deallocating all resources of DBMS");
        lobj = null;
        }
// Insert into Employee values


    public void InsertIntoTable(String name, String Address, int Salary) 
    {
        Employee eobj = new Employee(name, Address, Salary);
        lobj.add(eobj);
    }

// Select * from emplyee;


    public void SelectStar() {
        System.out.println("Data from the Employee Database:");

        System.out.println("\n----------------------------------------------------------");
        System.out.println("EID\tEname\t\tEaddress\t\tEsalary");
        System.out.println("----------------------------------------------------------");

        for (Employee eref : lobj) {
            eref.DisplayInfo();
        }

        System.out.println("----------------------------------------------------------");
    }

// Select * from emplyee ID


    public void SelectSpecific(int ID) {
        System.out.println("Information of Employee whose EID is: " + ID);

        for (Employee eref : lobj) {
            if (eref.EID == ID) {
                eref.DisplayInfo();
                break;
            }
        }
    }

// Select * from emplyee Name


    public void SelectSpecific(String Name) {
        System.out.println("Information of Employee whose Name is: " + Name);

        for (Employee eref : lobj) {
            if (Name.equals(eref.Ename)) {
                eref.DisplayInfo();
            }
        }
    }

// delete from Emplyee ID


    public void DeleteFrom(int ID) {
        Iterator<Employee> itr = lobj.iterator();
        boolean bflag = false;

        while (itr.hasNext()) {
            Employee eref = itr.next();
            if (eref.EID == ID) {
                itr.remove();
                bflag = true;
                break;
            }
        }

        if (!bflag) {
            System.out.println("Unable to delete the element from database as given ID is not present");
        }
    }

 // delete from Emplyee Name


    public void DeleteFrom(String Name) {
        Iterator<Employee> itr = lobj.iterator();
        boolean bflag = false;

        while (itr.hasNext()) {
            Employee eref = itr.next();
            if (Name.equals(eref.Ename)) {
                itr.remove();
                bflag = true;
                break;
            }
        }

        if (!bflag) {
            System.out.println("Unable to delete the element from database as given name is not present");
        }
    }

// Select Sum(ESalary) from Employee;


    public void AggregateSum() {
        int iSum = 0;

        for (Employee eref : lobj) {
            iSum += eref.ESalary;
        }

        System.out.println("Summation of salary: " + iSum);
    }

// Select Max(ESalary) from Employee;


    public void AggregateMax() {
        int iMax = 0;

        for (Employee eref : lobj) {
            if (eref.ESalary > iMax) {
                iMax = eref.ESalary;
            }
        }

        System.out.println("Maximum salary: " + iMax);
    }

// Select Min(ESalary) from Employee;


    public void AggregateMin() {
        if (lobj.isEmpty()) {
            System.out.println("No employees in the database.");
            return;
        }

        int iMin = lobj.get(0).ESalary;

        for (Employee eref : lobj) {
            if (eref.ESalary < iMin) {
                iMin = eref.ESalary;
            }
        }

        System.out.println("Minimum salary: " + iMin);
    }

// Select Avg(ESalary) from Employee;


    public void AggregateAvg() {
        int iSum = 0;

        for (Employee eref : lobj) {
            iSum += eref.ESalary;
        }

        System.out.println("Average salary: " + (iSum / lobj.size()));
    }

// Select Count(ESalary) from Employee;


    public void AggregateCount() {
        System.out.println("Number of Employees: " + lobj.size());
    }

// Function to update employee details by EID


    public void UpdateEmployee(int ID, String newName, String newAddress, int newSalary) {
        System.out.println("Updating Employee with EID: " + ID);
        for (Employee eref : lobj) {
            if (eref.EID == ID) {
                if (newName != null && !newName.isEmpty()) {
                    eref.Ename = newName;
                }
                if (newAddress != null && !newAddress.isEmpty()) {
                    eref.EAddress = newAddress;
                }
                if (newSalary > 0) {
                    eref.ESalary = newSalary;
                }
                System.out.println("Employee details updated successfully.");
                eref.DisplayInfo();
                return;
            }
        }
        System.out.println("Employee with EID " + ID + " not found.");
    }

// Function to list employees within a specific salary range


    public void ListEmployeesBySalaryRange(int minSalary, int maxSalary) {
        System.out.println("Employees with salary between " + minSalary + " and " + maxSalary + ":");
        for (Employee eref : lobj) {
            if (eref.ESalary >= minSalary && eref.ESalary <= maxSalary) {
                eref.DisplayInfo();
            }
        }
    }

// Function to list employees within a specific salary range


    public void SortEmployeesBy(String criteria) {
        lobj.sort((e1, e2) -> {
            switch (criteria.toLowerCase()) {
                case "eid":
                    return Integer.compare(e1.EID, e2.EID);
                case "name":
                    return e1.Ename.compareTo(e2.Ename);
                case "salary":
                    return Integer.compare(e1.ESalary, e2.ESalary);
                default:
                    throw new IllegalArgumentException("Invalid criteria: " + criteria);
            }
        });
        System.out.println("Employees sorted by " + criteria + ":");
        SelectStar();
    }

// Function to find employees living in a specific address


    public void FindEmployeesByAddress(String address) {
        System.out.println("Employees living in " + address + ":");
        for (Employee eref : lobj) {
            if (address.equals(eref.EAddress)) {
                eref.DisplayInfo();
            }
        }
    }

}

class file {
    public static void main(String Arg[]) {
        System.out.println("-------- EmpDBMS (Employee Database Management System) --------");

        Scanner sobj = new Scanner(System.in);

        MarvellousDBMS mobj = new MarvellousDBMS();

        int iOption = 0;
        int Salary;
        int EID;

        String Name;
        String Address;

        while (true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("Please select your choice based on your requirement:");
            System.out.println("----------------------------------------------------------");

            System.out.println("01 : Insert new record into the table");
            System.out.println("02 : Display all records");
            System.out.println("03 : Display all records having specific EID");
            System.out.println("04 : Display all records having specific Name");
            System.out.println("05 : Delete the record based on EID");
            System.out.println("06 : Delete the record based on Employee name");
            System.out.println("07 : Update an employee record");
            System.out.println("08 : List employees by salary range");
            System.out.println("09 : Sort employees");
            System.out.println("10 : Find employees by address");
            System.out.println("11 : Display Sum of all salary");
            System.out.println("12 : Display Average of all salary");
            System.out.println("13 : Display Minimum from all salary");
            System.out.println("14 : Display Maximum from all salary");
            System.out.println("15 : Display the Count of records");
            System.out.println("16 : Display Help");
            System.out.println("17 : Display About");
            System.out.println("18 : Terminate the DBMS");
            System.out.println("----------------------------------------------------------");

            try {
                iOption = Integer.parseInt(sobj.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
                continue;
            }

            switch (iOption) {
                case 1:
                    System.out.println("Please enter the information of the employee:");

                    System.out.println("Please enter Employee name:");
                    Name = sobj.nextLine();

                    System.out.println("Please enter Employee Address:");
                    Address = sobj.nextLine();

                    System.out.println("Please enter Employee Salary:");
                    try {
                        Salary = Integer.parseInt(sobj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, salary must be a number.");
                        break;
                    }

                    mobj.InsertIntoTable(Name, Address, Salary);

                    break;

                case 2:
                    mobj.SelectStar();
                    break;

                case 3:
                    System.out.println("Please enter the Employee ID whose information you want to display:");
                    try {
                        EID = Integer.parseInt(sobj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, EID must be a number.");
                        break;
                    }

                    mobj.SelectSpecific(EID);
                    break;

                case 4:
                    System.out.println("Please enter the Employee name whose information you want to display:");
                    Name = sobj.nextLine();

                    mobj.SelectSpecific(Name);
                    break;

                case 5:
                    System.out.println("Please enter the Employee ID whose information you want to delete:");
                    try {
                        EID = Integer.parseInt(sobj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, EID must be a number.");
                        break;
                    }

                    mobj.DeleteFrom(EID);
                    break;

                case 6:
                    System.out.println("Please enter the Employee name whose information you want to delete:");
                    Name = sobj.nextLine();

                    mobj.DeleteFrom(Name);
                    break;

                case 7:
                    System.out.println("Please enter the Employee ID whose information you want to update:");
                    try {
                        EID = Integer.parseInt(sobj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, EID must be a number.");
                        break;
                    }

                    System.out.println("Please enter new Employee name (or press Enter to skip):");
                    Name = sobj.nextLine();

                    System.out.println("Please enter new Employee Address (or press Enter to skip):");
                    Address = sobj.nextLine();

                    System.out.println("Please enter new Employee Salary (or press Enter to skip):");
                    String salaryInput = sobj.nextLine();
                    Salary = salaryInput.isEmpty() ? -1 : Integer.parseInt(salaryInput);

                    mobj.UpdateEmployee(EID, Name, Address, Salary);
                    break;

                case 8:
                    System.out.println("Please enter the minimum salary:");
                    int minSalary, maxSalary;
                    try {
                        minSalary = Integer.parseInt(sobj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, salary must be a number.");
                        break;
                    }

                    System.out.println("Please enter the maximum salary:");
                    try {
                        maxSalary = Integer.parseInt(sobj.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, salary must be a number.");
                        break;
                    }

                    mobj.ListEmployeesBySalaryRange(minSalary, maxSalary);
                    break;

                case 9:
                    System.out.println("Please enter the criteria to sort by (eid, name, salary):");
                    String criteria = sobj.nextLine();
                    try {
                        mobj.SortEmployeesBy(criteria);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    System.out.println("Please enter the address to search:");
                    Address = sobj.nextLine();
                    mobj.FindEmployeesByAddress(Address);
                    break;

                case 11:
                    mobj.AggregateSum();
                    break;

                case 12:
                    mobj.AggregateAvg();
                    break;

                case 13:
                    mobj.AggregateMin();
                    break;

                case 14:
                    mobj.AggregateMax();
                    break;

                case 15:
                    mobj.AggregateCount();
                    break;

                case 16:
                    System.out.println("This project implements the DBMS for employee records");
                    System.out.println("We implement all SQL queries");
                    System.out.println("All data gets stored in Data structures");
                    System.out.println("Complete implementation is on Primary storage");
                    break;

                case 17:
                    System.out.println("This project developed by Shivranjan Pathak");
                    System.out.println("All rights reserved.");
                    break;

                case 18:
                    System.out.println("Thank you for using the EmpDBMS (Employee Database Management System)");
                    mobj = null;
                    System.gc();
                    sobj.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
}
