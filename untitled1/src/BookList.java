import java.util.Arrays;

public class BookList {
    private Book[] books;

    public BookList(){

    }

    public void addBook(Book book){
        if (books == null){
            books = new Book[0];
        }
        books = Arrays.copyOf(books,books.length+1);
        books[books.length-1] = new Book(book.getIsbn(),book.getTitle(),book.getAuthor());
    }

    public Book searchBook(String isbn){
        return null;
    }

    public void showAllBooks(){
        if (books != null){

            Arrays.stream(books).forEach(books -> System.out.printf(books.getIsbn(), System.out.printf(books.getTitle()), System.out.printf(books.getAuthor())));

        }
        else{
            System.out.println("No Books to Show");
        }

    }
}

