package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher scholastic = new Publisher("Scholastic", "557 Broadway", "New York", "NY", "10012");
        publisherRepository.save(scholastic);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(scholastic);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3453645");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(scholastic);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        scholastic.getBooks().add(ddd);
        scholastic.getBooks().add(noEJB);
        publisherRepository.save(scholastic);

        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println(("Number of Books(Publisher): " + scholastic.getBooks().size()));

        //Publisher natureAmerica = new Publisher("Nature America", "One New York Plaza, Suite 4500", "New York", "NY", "10004");
        //publisherRepository.save(natureAmerica);
    }
}
