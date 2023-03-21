package com.homecode.customer.web;

import com.homecode.library.model.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ModelNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ModelNotFoundException.class)
    public ModelAndView modelNotFound(ModelNotFoundException mnfe) {
        ModelAndView modelAndView = new ModelAndView("model-not-found");

        modelAndView.addObject("modelId", mnfe.getModelId());

        return modelAndView;
    }

}
