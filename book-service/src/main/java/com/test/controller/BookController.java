package com.test.controller;

import com.test.entity.Book;
import com.test.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/book/{bid}")
    public Book getBookId(@PathVariable("bid") int bid) {
        return bookService.getBookById(bid);
    }

}
