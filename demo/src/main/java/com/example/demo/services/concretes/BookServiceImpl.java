package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.BookStatus;
import com.example.demo.entities.Category;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.abstracts.AuthorService;
import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.abstracts.CategoryService;
import com.example.demo.services.dtos.requests.book.AddBookRequest;
import com.example.demo.services.dtos.requests.book.UpdateBookRequest;
import com.example.demo.services.dtos.responses.book.*;
import com.example.demo.services.mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
//Todo: kitap eklemesi yapıldığında book status girişide yapılsın
    private BookRepository bookRepository;

    private CategoryService categoryService;

    private AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public AddBookResponse add(AddBookRequest request) {

        Book book = BookMapper.INSTANCE.bookToAddBookRequest(request);

        int categoryId = request.getCategoryId();
        Category category = categoryService.findById(categoryId);

        int authorId = request.getAuthorId();
        Author author = authorService.findById(authorId);

        book.setCategory(category);

        book.setAuthor(author);
        book.setBookStatus(BookStatus.OnTheShelf);
        Book saved = bookRepository.save(book);

        AddBookResponse response = BookMapper.INSTANCE.addBookResponseToBook(saved);

        return response;
    }

    @Override
    public UpdateBookResponse update(UpdateBookRequest request) {

        Book book = BookMapper.INSTANCE.bookToUpdateBookRequest(request);

        Book updated = bookRepository.save(book);

        UpdateBookResponse response = BookMapper.INSTANCE.updateBookResponseToBook(updated);

        return response;
    }

    public DeleteBookResponse delete(int id){

        Book requestId = bookRepository.findById(id).orElseThrow(()-> new BusinessException("id bulunamadı."));
        bookRepository.delete(requestId);

        DeleteBookResponse response = BookMapper.INSTANCE.deleteBookResponseToBook(requestId);
        return response;
    }

    @Override
    public List<GetAllBookResponse> getAll() {
       List<Book> books = bookRepository.findAll();
       List<GetAllBookResponse> result = new ArrayList<>();

       for(Book book:books){
           GetAllBookResponse dto = BookMapper.INSTANCE.getAllBookResponseToBook(book);
           //dto.setIsBorrow(book.getIsBorrow());
           result.add(dto);
       }

       return result;
    }

    @Override
    public GetByIdBookResponse getById(int id) {
        return null;
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow(()-> new BusinessException("id bulunamadı."));
    }
}
