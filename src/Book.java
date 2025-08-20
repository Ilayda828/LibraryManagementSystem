import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Book class that implements Comparable interface for natural ordering by book name
 * Contains book information: name, pages, author, publication date
 */
class Book implements Comparable<Book> {
    private String bookName;
    private int pageCount;
    private String authorName;
    private LocalDate publicationDate;
    
    // Constructor
    public Book(String bookName, int pageCount, String authorName, LocalDate publicationDate) {
        this.bookName = bookName;
        this.pageCount = pageCount;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
    }
    
    // Getters
    public String getBookName() { return bookName; }
    public int getPageCount() { return pageCount; }
    public String getAuthorName() { return authorName; }
    public LocalDate getPublicationDate() { return publicationDate; }
    
    // Setters
    public void setBookName(String bookName) { this.bookName = bookName; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }
    
    /**
     * Natural ordering: Sort books alphabetically by name (A to Z)
     * Implements Comparable interface requirement
     */
    @Override
    public int compareTo(Book other) {
        return this.bookName.compareToIgnoreCase(other.bookName);
    }
    
   //Override equals method for proper Set behavior 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(bookName, book.bookName) && 
               Objects.equals(authorName, book.authorName);
    }
   // Override hashCode for proper Set behavior
   
    @Override
    public int hashCode() {
        return Objects.hash(bookName, authorName);
    }
    
    /**
     * Enhanced toString method with formatted output
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("📚 %-25s | 📄 %4d pages | ✍️  %-20s | 📅 %s", 
                           bookName, pageCount, authorName, publicationDate.format(formatter));
    }
    
    /**
     * Get book age in years
     */
    public int getBookAge() {
        return LocalDate.now().getYear() - publicationDate.getYear();
    }
    
    /**
     * Check if book is a classic (older than 50 years)
     */
    public boolean isClassic() {
        return getBookAge() >= 50;
    }
    

     // Get reading time estimation (assuming 2 minutes per page)
    public String getEstimatedReadingTime() {
        int totalMinutes = pageCount * 2;
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        
        if (hours == 0) {
            return minutes + " minutes";
        } else {
            return hours + " hours " + minutes + " minutes";
        }
    }
}

 //Custom comparator for sorting books by page count
class PageCountComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b1.getPageCount(), b2.getPageCount());
    }
}

/**
 * Custom comparator for sorting books by publication date (newest first)
 */
class PublicationDateComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b2.getPublicationDate().compareTo(b1.getPublicationDate());
    }
}

/**
 * Custom comparator for sorting books by author name
 */
class AuthorNameComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthorName().compareToIgnoreCase(b2.getAuthorName());
    }
}