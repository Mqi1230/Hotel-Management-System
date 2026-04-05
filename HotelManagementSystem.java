import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.InputMismatchException;

public class HotelManagementSystem {

    private static Scanner scanner = new Scanner(System.in);

    // Data storage for hotel entities
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static ArrayList<InventoryItem> inventory = new ArrayList<>();


    public static void main(String[] args) {
        // Start the menu system
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Customer Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Inventory Management");
            System.out.println("4. Room Management");
            System.out.println("5. Reservation Management");
            System.out.println("6. Hotel Information");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    customerManagementMenu();
                    break;
                case 2:
                    employeeManagementMenu();
                    break;
                case 3:
                    inventoryManagementMenu();
                    break;
                case 4:
                    roomManagementMenu();
                    break;
                case 5:
                    reservationManagementMenu();
                    break;
                case 6:
                    hotelInformationMenu();
                    break;
                case 7:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Customer Management menu
    private static void customerManagementMenu() {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Search Customer");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    removeCustomer();
                    break;
                case 5:
                    searchCustomer();
                case 6:
                	return;  // Go back to main menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }


    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Room Type: ");
        String roomType = scanner.nextLine();
        System.out.print("Enter Check In Date: ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter Check Out Date: ");
        String checkOutDate = scanner.nextLine();

        Customer newCustomer = new Customer(id, name, contactInfo,address,roomType,checkInDate,checkOutDate);
        customers.add(newCustomer);
        System.out.println("Customer added successfully!");
    }

    private static void viewCustomer() {
        System.out.println("\n--- View Customers ---");
        System.out.println("1. View Customers (A-Z)");
        System.out.println("2. View Customers (Z-A)");
        System.out.println("3. Back to Customer Management Menu");
        System.out.print("Enter your choice: ");

        int choice; // Declare choice variable here
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            return; // Exit the method on invalid input
        }

        switch (choice) {
            case 1: // View customers in ascending order (A-Z)
                System.out.println("\n--- Customers (A-Z) ---");
                if (customers.isEmpty()) {
                    System.out.println("No customers available.");
                } else {
                    customers.stream()
                             .sorted(Comparator.comparing(Customer::getName)) // Sort by name (A-Z)
                             .forEach(Customer::displayCustomerDetails); // Call display method
                }
                break;

            case 2: // View customers in descending order (Z-A)
                System.out.println("\n--- Customers (Z-A) ---");
                if (customers.isEmpty()) {
                    System.out.println("No customers available.");
                } else {
                    customers.stream()
                             .sorted(Comparator.comparing(Customer::getName).reversed()) // Sort by name (Z-A)
                             .forEach(Customer::displayCustomerDetails); // Call display method
                }
                break;

            case 3: // Back to menu
                return;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void updateCustomer() {
        System.out.print("Enter Customer ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer = findCustomerById(id);
        if (customer != null) {
            System.out.print("Enter new Name: ");
            customer.setName(scanner.nextLine());
            System.out.print("Enter new Contact Info: ");
            customer.setContactInfo(scanner.nextLine());
            System.out.print("Enter new Address: ");
            customer.setAddress(scanner.nextLine());
            System.out.print("Enter new Room Type: ");
            System.out.print("Enter new Check In Date: ");
            customer.setCheckInDate(scanner.nextLine());
            System.out.print("Enter new Check Out Date: ");
            customer.setCheckOutDate(scanner.nextLine());
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void removeCustomer() {
        System.out.print("Enter Customer ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer = findCustomerById(id);
        if (customer != null) {
            customers.remove(customer);
            System.out.println("Customer removed successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }


    private static void searchCustomer() {
        System.out.print("Enter Customer ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer = findCustomerById(id);
        if (customer != null) {
            customer.displayCustomerDetails(); // Ensure this method is implemented in the Customer class
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Employee Management menu
    private static void employeeManagementMenu() {
        System.out.println("\n--- Employee Management ---");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Remove Employee");
        System.out.println("5. Search Employee");
        System.out.println("6. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addEmployee();
                break;
            case 2:
                viewEmployee();
                break;
            case 3:
                updateEmployee();
                break;
            case 4:
                removeEmployee();
                break;
            case 5:
            	searchEmployee();
            	break;
            case 6:
                return;  // Go back to main menu
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Position: ");
        String position = scanner.nextLine();

        Employee newEmployee = new Employee(id, name, position);
        employees.add(newEmployee);
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployee() {
        System.out.println("\n--- View Employee ---");
        System.out.println("1. View Employee (A-Z)");
        System.out.println("2. View Employee (Z-A)");
        System.out.println("3. Back to Employee Management Menu");
        System.out.print("Enter your choice: ");

        int choice; // Declare choice variable here
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            return; // Exit the method on invalid input
        }

        switch (choice) {
            case 1: // View employee in ascending order (A-Z)
                System.out.println("\n--- Employee (A-Z) ---");
                if (employees.isEmpty()) {
                    System.out.println("No employees available.");
                } else {
                    employees.stream()
                             .sorted(Comparator.comparing(Employee::getName)) // Sort by name (A-Z)
                             .forEach(Employee::displayEmployeeDetails); // Call display method
                }
                break;

            case 2: // View employees in descending order (Z-A)
                System.out.println("\n--- Employees (Z-A) ---");
                if (employees.isEmpty()) {
                    System.out.println("No employees available.");
                } else {
                    employees.stream()
                             .sorted(Comparator.comparing(Employee::getName).reversed()) // Sort by name (Z-A)
                             .forEach(Employee::displayEmployeeDetails); // Call display method
                }
                break;

            case 3: // Back to menu
                return;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = findEmployeeById(id);
        if (employee != null) {
            System.out.print("Enter new name: ");
            employee.setName(scanner.nextLine());
            System.out.print("Enter new position: ");
            employee.setPosition(scanner.nextLine());
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void removeEmployee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                return employee;
            }
        }
        return null;
    }
    
    private static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employee.displayEmployeeDetails(); // Ensure this method is implemented in the Customer class
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Inventory Management menu
    private static void inventoryManagementMenu() {
        System.out.println("\n--- Inventory Management ---");
        System.out.println("1. Add Inventory Item");
        System.out.println("2. View Inventory Items");
        System.out.println("3. Update Inventory");
        System.out.println("4. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addInventoryItem();
                break;
            case 2:
                viewInventoryItems();
                break;
            case 3:
                updateInventoryItem();
                break;
            case 4:
                return;  // Go back to main menu
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void addInventoryItem() {
        System.out.print("Enter Item ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        InventoryItem newItem = new InventoryItem(id, name, quantity);
        inventory.add(newItem);
        System.out.println("Inventory item added successfully!");
    }

    private static void viewInventoryItems() {
        System.out.println("\n--- Inventory Items ---");
        for (InventoryItem item : inventory) {
            item.displayItemDetails();
        }
    }

    private static void updateInventoryItem() {
        System.out.print("Enter Item ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        InventoryItem item = findInventoryItemById(id);
        if (item != null) {
            System.out.print("Enter new name: ");
            item.setName(scanner.nextLine());
            System.out.print("Enter new quantity: ");
            item.setQuantity(scanner.nextInt());
            System.out.println("Inventory item updated successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static InventoryItem findInventoryItemById(int id) {
        for (InventoryItem item : inventory) {
            if (item.getItemId() == id) {
                return item;
            }
        }
        return null;
    }

    // Room Management menu
    private static void roomManagementMenu() {
        System.out.println("\n--- Room Management ---");
        System.out.println("1. Add Room");
        System.out.println("2. View Room Details");
        System.out.println("3. Update Room Availability");
        System.out.println("4. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addRoom();
                break;
            case 2:
                viewRoomDetails();
                break;
            case 3:
                updateRoomAvailability();
                break;
            case 4:
                return;  // Go back to main menu
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void addRoom() {
        System.out.print("Enter Room Number: ");
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Room Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Room Price: ");
        double price = scanner.nextDouble();

        Room newRoom = new Room(number, type, price);
        rooms.add(newRoom);
        System.out.println("Room added successfully!");
    }

    private static void viewRoomDetails() {
        System.out.print("Enter Room Number to view: ");
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room room = findRoomByNumber(number);
        if (room != null) {
            room.displayRoomDetails();
        } else {
            System.out.println("Room not found.");
        }
    }

    private static void updateRoomAvailability() {
        System.out.print("Enter Room Number to update availability: ");
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room room = findRoomByNumber(number);
        if (room != null) {
            System.out.print("Enter new availability (true/false): ");
            room.setAvailable(scanner.nextBoolean());
            System.out.println("Room availability updated successfully!");
        } else {
            System.out.println("Room not found.");
        }
    }

    private static Room findRoomByNumber(int number) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == number) {
                return room;
            }
        }
        return null;
    }

    // Reservation Management menu
    private static void reservationManagementMenu() {
        System.out.println("\n--- Reservation Management ---");
        System.out.println("1. Create Reservation");
        System.out.println("2. View Reservation");
        System.out.println("3. Update Reservation");
        System.out.println("4. Cancel Reservation");
        System.out.println("5. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createReservation();
                break;
            case 2:
                viewReservation();
                break;
            case 3:
                updateReservation();
                break;
            case 4:
                cancelReservation();
                break;
            case 5:
                return;  // Go back to main menu
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void createReservation() {
        System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        Customer customer = findCustomerById(customerId);
        Room room = findRoomByNumber(roomNumber);
        if (customer != null && room != null) {
            Booking newBooking = new Booking(id, customer, room);
            bookings.add(newBooking);
            System.out.println("Reservation created successfully!");
        } else {
            System.out.println("Invalid customer or room.");
        }
    }

    private static void viewReservation() {
        System.out.print("Enter Reservation ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Booking booking = findBookingById(id);
        if (booking != null) {
            booking.displayBookingDetails();
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static void updateReservation() {
        System.out.print("Enter Reservation ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Booking booking = findBookingById(id);
        if (booking != null) {
            System.out.print("Enter new Room Number: ");
            int roomNumber = scanner.nextInt();
            Room room = findRoomByNumber(roomNumber);
            if (room != null) {
                booking.setRoom(room);
                System.out.println("Reservation updated successfully!");
            } else {
                System.out.println("Room not found.");
            }
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter Reservation ID to cancel: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Booking booking = findBookingById(id);
        if (booking != null) {
            bookings.remove(booking);
            System.out.println("Reservation canceled successfully!");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static Booking findBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == id) {
                return booking;
            }
        }
        return null;
    }
    

    // Hotel Information menu
    private static void hotelInformationMenu() {
        System.out.println("\n--- Hotel Information ---");
        System.out.println("1. View Hotel Information");
        System.out.println("2. Update Hotel Information");
        System.out.println("3. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewHotelInformation();
                break;
            case 2:
                updateHotelInformation();
                break;
            case 3:
                return;  // Go back to main menu
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void viewHotelInformation() {
        System.out.println("\n--- Hotel Information ---");
        System.out.println("Hotel Name: Grand Paradise");
        System.out.println("Location: Paradise Avenue, City");
        System.out.println("Total Rooms: " + rooms.size());
        System.out.println("Staff Members: " + employees.size());
        System.out.println("Total Customers: " + customers.size());
    }

    private static void updateHotelInformation() {
        System.out.print("Enter new Hotel Name: ");
        String hotelName = scanner.nextLine();
        System.out.print("Enter new Hotel Location: ");
        String location = scanner.nextLine();

        System.out.println("Hotel information updated successfully!");
    }

    // Classes for Hotel Entities

    static class Customer {
        private int customerId;
        private String name;
        private String contactInfo;
        private String address;
        private String roomType;
        private String checkInDate;
        private String checkOutDate;

        public Customer(int customerId, String name, String contactInfo, String address, String roomType, String checkInDate, String checkOutDate) {
            this.customerId = customerId;
            this.name = name;
            this.contactInfo = contactInfo;
            this.address = address;
            this.roomType = roomType;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        public String getContactInfo() {
            return contactInfo;
        }
        
        public String getAddress() {
        	return address;
        }
        
        public String getroomType() {
        	return roomType;
        }
        
        public String getcheckInDate() {
        	return checkInDate;
        }
        
        public String getcheckOutDate() {
        	return checkOutDate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
        }
        
        public void setAddress(String address) {
        	this.address = address;
        }
        
        public void setRoomType(String roomType) {
        	this.roomType = roomType;
        }
        
        public void setCheckInDate(String checkInDate) {
        	this.checkInDate = checkInDate;
        }
        
        public void setCheckOutDate(String checkOutDate) {
        	this.checkOutDate = checkOutDate;
        }

        public void displayCustomerDetails() {
            System.out.println("Customer ID: " + customerId);
            System.out.println("Name: " + name);
            System.out.println("Contact Info: " + contactInfo);
            System.out.println("Address: " + address);
            System.out.println("Room Type: " + roomType);
            System.out.println("Check In Date: " + checkInDate);
            System.out.println("Check Out Date: " + checkOutDate);
        }
    }

    static class Employee {
        private int employeeId;
        private String name;
        private String position;

        public Employee(int employeeId, String name, String position) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void displayEmployeeDetails() {
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Name: " + name);
            System.out.println("Position: " + position);
        }
    }

    static class Room {
        private int roomNumber;
        private String roomType;
        private double price;
        private boolean available;

        public Room(int roomNumber, String roomType, double price) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.price = price;
            this.available = true;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public double getPrice() {
            return price;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public void displayRoomDetails() {
            System.out.println("Room Number: " + roomNumber);
            System.out.println("Room Type: " + roomType);
            System.out.println("Price: $" + price);
            System.out.println("Available: " + (available ? "Yes" : "No"));
        }
    }

    static class Booking {
        private int bookingId;
        private Customer customer;
        private Room room;

        public Booking(int bookingId, Customer customer, Room room) {
            this.bookingId = bookingId;
            this.customer = customer;
            this.room = room;
        }

        public int getBookingId() {
            return bookingId;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public void displayBookingDetails() {
            System.out.println("Booking ID: " + bookingId);
            System.out.println("Customer: " + customer.getName());
            System.out.println("Room: " + room.getRoomType() + " - " + room.getRoomNumber());
        }
    }

    

    static class InventoryItem {
        private int itemId;
        private String name;
        private int quantity;

        public InventoryItem(int itemId, String name, int quantity) {
            this.itemId = itemId;
            this.name = name;
            this.quantity = quantity;
        }

        public int getItemId() {
            return itemId;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void displayItemDetails() {
            System.out.println("Item ID: " + itemId);
            System.out.println("Item Name: " + name);
            System.out.println("Quantity: " + quantity);
        }
    }
}



