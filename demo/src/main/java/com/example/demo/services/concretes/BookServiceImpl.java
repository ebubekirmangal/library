package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.entities.User;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.abstracts.CategoryService;
import com.example.demo.services.mappers.BookMapper;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.dtos.requests.book.AddBookRequest;
import com.example.demo.services.dtos.requests.book.DeleteBookRequest;
import com.example.demo.services.dtos.requests.book.GetByIdBookRequest;
import com.example.demo.services.dtos.requests.book.UpdateBookRequest;
import com.example.demo.services.dtos.responses.book.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private CategoryRepository categoryRepository;

    private AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public AddBookResponse add(AddBookRequest request) {

        Book book = BookMapper.INSTANCE.bookToAddBookRequest(request);

        int categoryId = request.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new BusinessException("id bulunamadı."));

        int authorId = request.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new BusinessException("id bulunamadı."));

        book.setCategory(category);
        book.setAuthor(author);

        Book saved = bookRepository.save(book);

        AddBookResponse response = BookMapper.INSTANCE.addBookResponsetoBook(saved);



        return response;
    }

    @Override
    public UpdateBookResponse update(UpdateBookRequest request) {

        Book book = BookMapper.INSTANCE.bookToUpdateBookRequest(request);

        Book updated = bookRepository.save(book);

        UpdateBookResponse response = BookMapper.INSTANCE.updateBookResponseToBook(updated);

        return response;
    }

    public DeleteBookResponse delete(DeleteBookRequest request){

        Book requestId = bookRepository.findById(request.getId()).orElseThrow(()-> new BusinessException("id bulunamadı."));
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
           result.add(dto);
       }

       return result;
    }

    @Override
    public GetByIdBookResponse getById(GetByIdBookRequest request) {
        return null;
    }
}
