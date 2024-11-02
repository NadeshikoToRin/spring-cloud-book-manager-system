package com.test.service.impl;

import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.mapper.BorrowMapper;
import com.test.service.BorrowService;
import com.test.service.client.BookClient;
import com.test.service.client.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private UserClient userClient;

    @Resource
    private BookClient bookClient;


    @Override
    public UserBorrowDetail getUserBorrowDetailById(int uid) {
        List<Borrow> borrows = borrowMapper.getBorrowsByUid(uid);

        User user = userClient.findUserById(uid);

        List<Book> bookList = borrows
                .stream()
                .map(borrow -> bookClient.findBookById(Math.toIntExact(borrow.getBid())))
                .collect(Collectors.toList());

        return new UserBorrowDetail(user,bookList);
    }
}
