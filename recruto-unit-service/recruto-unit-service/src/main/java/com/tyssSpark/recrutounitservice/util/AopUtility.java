package com.tyssSpark.recrutounitservice.util;

import com.tyssSpark.recrutounitservice.dto.UserAuth;
import com.tyssSpark.recrutounitservice.exception.InvalidataException;
import com.tyssSpark.recrutounitservice.exception.MaliciousCodeFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Component
@Aspect
@EnableAspectJAutoProxy
public class AopUtility {



    @Before(value = "execution(* com.tyssSpark.recrutounitservice.controller.*.*(..))" + "&& args(userAuth)")
    public void Test(UserAuth userAuth) throws Throwable {

        if(userAuth.getUserId()!=null && !userAuth.getUserId().isEmpty()) {
                Object santize = XssCleanUtil.santize(userAuth.getPassword(), true);
        }
        else
            throw new InvalidataException("Invalid data");
    }
}
