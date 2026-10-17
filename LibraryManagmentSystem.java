import java.util.ArrayList;
import java.util.Scanner;

// ===================== BOOK CLASS =====================
class Book {

    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(int bookId, String title, String author) 
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Display book details
    public void displayBook()
    {
        System.out.println
        (
            "ID: " + bookId +
            " | Title: " + title +
            " | Author: " + author +
            " | Status: " +
            (isAvailable ? "Available" : "Issued")
        );
    }
}


// ===================== LIBRARY CLASS =====================
class Library
{

    private ArrayList<Book> books;

    // Constructor
    public Library()
    {
        books = new ArrayList<>();
    }

    // Add book
    public boolean addBook(Book book)
    {

        for (Book b : books)
        {
            if (b.getBookId() == book.getBookId())
            {
                return false;
            }
        }

        books.add(book);
        return true;
    }

    // View all books
    public void viewBooks()
    {

        if (books.isEmpty())
        {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n========== ALL BOOKS ==========");

        for (Book book : books)
        {
            book.displayBook();
        }
    }

    // Search book by ID
    public Book searchById(int id)
    {

        for (Book book : books)
        {

            if (book.getBookId() == id)
            {
                return book;
            }
        }

        return null;
    }

    // Search book by title
    public void searchByTitle(String title) 
    {

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle()
                    .toLowerCase()
                    .contains(title.toLowerCase())) 
            {

                book.displayBook();
                found = true;
            }
        }

        if (!found) 
        {
            System.out.println("Book not found.");
        }
    }

    // Issue book
    public void issueBook(int id)
    {

        Book book = searchById(id);

        if (book == null) 
        {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) 
        {
            System.out.println("Book is already issued.");
            return;
        }

        book.setAvailable(false);

        System.out.println("Book issued successfully.");
    }

    // Return book
    public void returnBook(int id) 
    {

        Book book = searchById(id);

        if (book == null) 
        {
            System.out.println("Book not found.");
            return;
        }

        if (book.isAvailable()) 
        {
            System.out.println("Book is already available.");
            return;
        }

        book.setAvailable(true);

        System.out.println("Book returned successfully.");
    }

    // Remove book
    public void removeBook(int id) 
    {

        Book book = searchById(id);

        if (book == null) 
        {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) 
        {
            System.out.println("Cannot remove an issued book.");
            return;
        }

        books.remove(book);

        System.out.println("Book removed successfully.");
    }
}


// ===================== MAIN CLASS =====================
public class LibraryManagmentSystem 
{

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        Library library = new Library();

        int choice;

        do 
        {

            System.out.println("\n======================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Remove Book");
            System.out.println("7. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) 
            {

                // ================= ADD BOOK =================
                case 1:

                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    Book book = new Book(id, title, author);

                    if (library.addBook(book)) 
                    {
                        System.out.println("Book added successfully.");
                    } 
                    else 
                    {
                        System.out.println("Book ID already exists.");
                    }

                    break;


                // ================= VIEW BOOKS =================
                case 2:

                    library.viewBooks();

                    break;


                // ================= SEARCH BOOK =================
                case 3:

                    System.out.println("\n1. Search by ID");
                    System.out.println("2. Search by Title");

                    System.out.print("Enter search choice: ");
                    int searchChoice = sc.nextInt();
                    sc.nextLine();

                    if (searchChoice == 1) 
                    {

                        System.out.print("Enter Book ID: ");
                        int searchId = sc.nextInt();

                        Book foundBook = library.searchById(searchId);

                        if (foundBook != null) 
                        {
                            foundBook.displayBook();
                        } 
                        else 
                        {
                            System.out.println("Book not found.");
                        }

                    } 
                    else if (searchChoice == 2) 
                    {

                        System.out.print("Enter Book Title: ");
                        String searchTitle = sc.nextLine();

                        library.searchByTitle(searchTitle);

                    } 
                    else 
                    {

                        System.out.println("Invalid search choice.");
                    }

                    break;


                // ================= ISSUE BOOK =================
                case 4:

                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();

                    library.issueBook(issueId);

                    break;


                // ================= RETURN BOOK =================
                case 5:

                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();

                    library.returnBook(returnId);

                    break;


                // ================= REMOVE BOOK =================
                case 6:

                    System.out.print("Enter Book ID to remove: ");
                    int removeId = sc.nextInt();

                    library.removeBook(removeId);

                    break;


                // ================= EXIT =================
                case 7:

                    System.out.println("Thank you for using Library Management System!");

                    break;

                // ================= INVALID CHOICE =================
                default:

                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }
}