package com.test.service.impl;

import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.mapper.BorrowMapper;
import com.test.service.BorrowService;
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
    private RestTemplate template;

    @Override
    public UserBorrowDetail getUserBorrowDetailById(int uid) {
        List<Borrow> borrows = borrowMapper.getBorrowsByUid(uid);

        User user = template.getForObject("http://userservice/user/"+uid, User.class);

        List<Book> bookList = borrows
                .stream()
                .map(borrow -> template
                        .getForObject("http://bookservice/book/"+borrow.getBid(),Book.class))
                .collect(Collectors.toList());

        return new UserBorrowDetail(user,bookList);
    }
}
