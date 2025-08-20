import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class BookSorter {
    
    public static void main(String[] args) {
        // Create 5 Book objects with diverse data
        Set<Book> booksByName = new TreeSet<>();
        
        booksByName.add(new Book("The Great Gatsby", 180, "F. Scott Fitzgerald", LocalDate.of(1925, 4, 10)));
        booksByName.add(new Book("To Kill a Mockingbird", 324, "Harper Lee", LocalDate.of(1960, 7, 11)));
        booksByName.add(new Book("1984", 328, "George Orwell", LocalDate.of(1949, 6, 8)));
        booksByName.add(new Book("Pride and Prejudice", 432, "Jane Austen", LocalDate.of(1813, 1, 28)));
        booksByName.add(new Book("The Catcher in the Rye", 277, "J.D. Salinger", LocalDate.of(1951, 7, 16)));
        
        // Additional books for more comprehensive testing
        booksByName.add(new Book("Animal Farm", 95, "George Orwell", LocalDate.of(1945, 8, 17)));
        booksByName.add(new Book("Brave New World", 268, "Aldous Huxley", LocalDate.of(1932, 8, 30)));
        
        System.out.println("================================================================================");
        System.out.println("📖 BOOK LIBRARY MANAGEMENT SYSTEM 📖");
        System.out.println("================================================================================");
        
        // 1. Display books sorted by name (A to Z) - Natural ordering
        System.out.println("\n🔤 BOOKS SORTED BY NAME (A-Z) - Using Comparable Interface:");
        System.out.println("--------------------------------------------------------------------------------");
        booksByName.forEach(System.out::println);
        
        // 2. Sort books by page count using TreeSet with custom comparator
        Set<Book> booksByPageCount = new TreeSet<>(new PageCountComparator());
        booksByPageCount.addAll(booksByName);
        
        System.out.println("\n📄 BOOKS SORTED BY PAGE COUNT (Ascending):");
        System.out.println("--------------------------------------------------------------------------------");
        booksByPageCount.forEach(System.out::println);
        
        // 3. Sort books by publication date (newest first)
        Set<Book> booksByDate = new TreeSet<>(new PublicationDateComparator());
        booksByDate.addAll(booksByName);
        
        System.out.println("\n📅 BOOKS SORTED BY PUBLICATION DATE (Newest First):");
        System.out.println("--------------------------------------------------------------------------------");
        booksByDate.forEach(System.out::println);
        
        // 4. Sort books by author name
        Set<Book> booksByAuthor = new TreeSet<>(new AuthorNameComparator());
        booksByAuthor.addAll(booksByName);
        
        System.out.println("\n✍️  BOOKS SORTED BY AUTHOR NAME:");
        System.out.println("--------------------------------------------------------------------------------");
        booksByAuthor.forEach(System.out::println);
        
        // 5. Statistical Analysis
        System.out.println("\n📊 LIBRARY STATISTICS:");
        System.out.println("--------------------------------------------------------------------------------");
        
        int totalBooks = booksByName.size();
        int totalPages = booksByName.stream().mapToInt(Book::getPageCount).sum();
        double averagePages = booksByName.stream().mapToInt(Book::getPageCount).average().orElse(0.0);
        Book shortestBook = booksByName.stream().min(Comparator.comparing(Book::getPageCount)).orElse(null);
        Book longestBook = booksByName.stream().max(Comparator.comparing(Book::getPageCount)).orElse(null);
        
        System.out.printf("📚 Total Books: %d\n", totalBooks);
        System.out.printf("📄 Total Pages: %d\n", totalPages);
        System.out.printf("📊 Average Pages: %.1f\n", averagePages);
        System.out.printf("📘 Shortest Book: %s (%d pages)\n", 
                         shortestBook != null ? shortestBook.getBookName() : "N/A", 
                         shortestBook != null ? shortestBook.getPageCount() : 0);
        System.out.printf("📗 Longest Book: %s (%d pages)\n", 
                         longestBook != null ? longestBook.getBookName() : "N/A", 
                         longestBook != null ? longestBook.getPageCount() : 0);
        
        // 6. Classic Books Analysis
        System.out.println("\n📜 CLASSIC BOOKS ANALYSIS (50+ years old):");
        System.out.println("--------------------------------------------------------------------------------");
        
        List<Book> classicBooks = booksByName.stream()
                                             .filter(Book::isClassic)
                                             .collect(Collectors.toList());
        
        if (classicBooks.isEmpty()) {
            System.out.println("No classic books found in the library.");
        } else {
            System.out.printf("Found %d classic book(s):\n", classicBooks.size());
            classicBooks.forEach(book -> 
                System.out.printf("📜 %s by %s (%d years old)\n", 
                                book.getBookName(), book.getAuthorName(), book.getBookAge()));
        }
        
        // 7. Reading Time Estimation
        System.out.println("\n⏰ ESTIMATED READING TIMES:");
        System.out.println("--------------------------------------------------------------------------------");
        booksByName.forEach(book -> 
            System.out.printf("📖 %-25s: %s\n", book.getBookName(), book.getEstimatedReadingTime()));
        
        // 8. Books by specific author
        System.out.println("\n🔍 BOOKS BY GEORGE ORWELL:");
        System.out.println("--------------------------------------------------------------------------------");
        booksByName.stream()
                   .filter(book -> book.getAuthorName().contains("George Orwell"))
                   .forEach(System.out::println);
        
        // 9. Books with more than 300 pages
        System.out.println("\n📚 THICK BOOKS (300+ pages):");
        System.out.println("--------------------------------------------------------------------------------");
        booksByName.stream()
                   .filter(book -> book.getPageCount() >= 300)
                   .sorted(Comparator.comparing(Book::getPageCount).reversed())
                   .forEach(System.out::println);
        
        System.out.println("\n================================================================================");
        System.out.println("📖 Book sorting operations completed successfully! 📖");
        System.out.println("================================================================================");
    }
}