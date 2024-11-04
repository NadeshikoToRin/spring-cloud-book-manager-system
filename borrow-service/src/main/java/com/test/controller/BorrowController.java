package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.entity.UserBorrowDetail;
import com.test.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    //指定服务降级方法
//    @HystrixCommand(fallbackMethod = "onError")
    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
        return borrowService.getUserBorrowDetailById(uid);
    }

    //备选方案，返回空列表
//    UserBorrowDetail onError(int uid) {
//        return new UserBorrowDetail(null, Collections.emptyList());
//    }
}
