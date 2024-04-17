package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Author;
import com.example.demo.services.mappers.AuthorMapper;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.services.abstracts.AuthorService;
import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.requests.author.DeleteAuthorRequest;
import com.example.demo.services.dtos.requests.author.UpdateAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;
import com.example.demo.services.dtos.responses.author.DeleteAuthorResponse;
import com.example.demo.services.dtos.responses.author.GetAllAuthorResponse;
import com.example.demo.services.dtos.responses.author.UpdateAuthorResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AddAuthorResponse add(AddAuthorRequest request) {

        Author author = AuthorMapper.INSTANCE.authorToAddAuthorRequest(request);

        Author saved = authorRepository.save(author);

        AddAuthorResponse response = AuthorMapper.INSTANCE.addAuthorResponseToAuthor(saved);
        return response;
    }

    @Override
    public UpdateAuthorResponse update(UpdateAuthorRequest request) {
        Author author = AuthorMapper.INSTANCE.authorToUpdateAuthorRequest(request);
        Author updated = authorRepository.save(author);

        UpdateAuthorResponse response = AuthorMapper.INSTANCE.updateAuthorResponseToAuthor(updated);
        return response;
    }

    @Override
    public DeleteAuthorResponse delete(DeleteAuthorRequest request) {

        Author findId = authorRepository.findById(request.getId()).orElseThrow(()-> new BusinessException("id bulunmadı"));
        authorRepository.delete(findId);

        DeleteAuthorResponse response = AuthorMapper.INSTANCE.deleteAuthorResponseToAuthor(findId);
        return response;
    }

    @Override
    public List<GetAllAuthorResponse> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<GetAllAuthorResponse> result = new ArrayList<>();

        for (Author author:authors){
            GetAllAuthorResponse dto = AuthorMapper.INSTANCE.getAllAuthorResponseToAuthor(author);
            result.add(dto);
        }
        return result;
    }
}
