import java.util.Scanner;

public class BookDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        String isbn, title, author, search;
        BookList bookList = new BookList();

        int pil;
        do{
            System.out.println("=============================");
            System.out.printf("%20s %n","     Library Application");
            System.out.println("============================");
            System.out.println("1. show all books");
            System.out.println("2. Add new book");
            System.out.println("3. search book");
            System.out.println("4. exit");
            System.out.println("==============================");
            System.out.print("Choice : ");
            pil = sc.nextInt();
            if(pil == 1){
                bookList.showAllBooks();
            }
            else if(pil == 2){
                System.out.print("New ISBN : ");
                isbn = sc.next();
                System.out.print("New Title : ");
                title = sc.next();
                System.out.print("New Author : ");
                author = sc.next();
                Book book = new Book(isbn,title,author);
                bookList.addBook(book);


            }
            else if(pil == 3){
                System.out.print("search (ISBN) : ");
                search = sc.next();
                bookList.searchBook(search);
            }


        }while(pil != 4);
    }
}
