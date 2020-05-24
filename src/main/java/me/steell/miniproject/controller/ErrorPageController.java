package me.steell.miniproject.controller;

import org.springframework.http.HttpStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class ErrorPageController implements org.springframework.boot.web.servlet.error.ErrorController {


    public String handleError(HttpServletRequest request){
        System.out.println("############### error EEEEEEEEEEEEEEEEEEEE ## ");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            Integer statusCode = Integer.valueOf( status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "views/error/404";
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "views/error/500";
            }
        }
        return "views/error/404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}