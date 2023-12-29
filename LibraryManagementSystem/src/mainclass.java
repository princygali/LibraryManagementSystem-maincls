 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    String name;
    List<String> issuedBooks = new ArrayList<>();
    List<String> returnedBooks = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }
}

class Book {
    String title;
    boolean available = true;

    public Book(String title) {
        this.title = title;
    }
}

public class mainclass {
    static Map<String, User> users = new HashMap<>();
    static Map<String, Book> books = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Librarian\n2. Student/User\n3. Exit");
            System.out.print("Choose user type: ");
            int userType = scanner.nextInt();

            switch (userType) {
                case 1:
                    librarianFunctions(scanner);
                    break;
                case 2:
                    userFunctions(scanner);
                    break;
                case 3:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void librarianFunctions(Scanner scanner) {
        System.out.println("\nLibrarian Functions:");
        System.out.println("1. Add User\n2. Add Book\n3. Issue Book\n4. Return Book");
        System.out.println("5. View Users\n6. View Books\n7. View Issued Books\n8. View Returned Books");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addUser(scanner);
                break;
            case 2:
                addBook(scanner);
                break;
            case 3:
                issueBook(scanner);
                break;
            case 4:
                returnBook(scanner);
                break;
            case 5:
                viewUsers();
                break;
            case 6:
                viewBooks();
                break;
            case 7:
                viewIssuedBooks();
                break;
            case 8:
                viewReturnedBooks();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addUser(Scanner scanner) {
        System.out.print("Enter user name: ");
        String userName = scanner.next();
        User newUser = new User(userName);
        users.put(userName, newUser);
        System.out.println("User added successfully.");
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String bookTitle = scanner.next();
        Book newBook = new Book(bookTitle);
        books.put(bookTitle, newBook);
        System.out.println("Book added successfully.");
    }

    private static void issueBook(Scanner scanner) {
        System.out.print("Enter user name: ");
        String userName = scanner.next();
        System.out.print("Enter book title: ");
        String bookTitle = scanner.next();

        User user = users.get(userName);
        Book book = books.get(bookTitle);

        if (user != null && book != null && book.available) {
            user.issuedBooks.add(bookTitle);
            book.available = false;
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Invalid user or book not available.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter user name: ");
        String userName = scanner.next();
        System.out.print("Enter book title: ");
        String bookTitle = scanner.next();

        User user = users.get(userName);
        Book book = books.get(bookTitle);

        if (user != null && book != null && !book.available) {
            user.returnedBooks.add(bookTitle);
            book.available = true;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid user or book not issued.");
        }
    }

    private static void viewUsers() {
        System.out.println("\nList of Users:");
        for (String userName : users.keySet()) {
            System.out.println(userName);
        }
    }

    private static void viewBooks() {
        System.out.println("\nList of Books:");
        for (String bookTitle : books.keySet()) {
            System.out.println(bookTitle);
        }
    }

    private static void viewIssuedBooks() {
        System.out.println("\nList of Issued Books:");
        for (User user : users.values()) {
            for (String bookTitle : user.issuedBooks) {
                System.out.println("User: " + user.name + ", Book: " + bookTitle);
            }
        }
    }

    private static void viewReturnedBooks() {
        System.out.println("\nList of Returned Books:");
        for (User user : users.values()) {
            for (String bookTitle : user.returnedBooks) {
                System.out.println("User: " + user.name + ", Book: " + bookTitle);
            }
        }
    }

    private static void userFunctions(Scanner scanner) {
        System.out.println("\nUser Functions:");
        System.out.println("1. View Available Books\n2. View Issued Books\n3. View Returned Books");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewBooks();
                break;
            case 2:
                viewUserIssuedBooks(scanner);
                break;
            case 3:
                viewUserReturnedBooks(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewUserIssuedBooks(Scanner scanner) {
        System.out.print("Enter your name: ");
        String userName = scanner.next();

        User user = users.get(userName);

        if (user != null && !user.issuedBooks.isEmpty()) {
            System.out.println("\nList of Issued Books:");
            for (String bookTitle : user.issuedBooks) {
                System.out.println(bookTitle);
            }
        } else {
            System.out.println("No books issued or invalid user.");
        }
    }

    private static void viewUserReturnedBooks(Scanner scanner) {
        System.out.print("Enter your name: ");
        String userName = scanner.next();

        User user = users.get(userName);

        if (user != null && !user.returnedBooks.isEmpty()) {
            System.out.println("\nList of Returned Books:");
            for (String bookTitle : user.returnedBooks) {
                System.out.println(bookTitle);
            }
        } else {
            System.out.println("No books returned or invalid user.");
        }
    }
}


