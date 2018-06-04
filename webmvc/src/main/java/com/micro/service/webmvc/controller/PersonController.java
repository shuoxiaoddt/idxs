package com.micro.service.webmvc.controller;

import com.micro.service.webmvc.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 写点注释吧!!
 *
 * @author xiaos
 * @date : 2018/5/9
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @PostMapping(value = "personPropertiesToJson",
            consumes = "application/properties+person",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person personPropertiesToJson(@RequestBody Person person){

        return person;
    }

    @PostMapping(value = "personJsonToProperties",
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                produces = "application/properties+person" )
    public Person personJsonToProperties(@RequestBody Person person){
            return person;
    }
    @GetMapping("/addPerson")
    public Person addPerson(@Valid Person person , BindingResult bindingResult){
        boolean hasErrors = bindingResult.hasErrors();
        if(hasErrors){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            System.out.println(allErrors.get(0).getDefaultMessage());
        }
        return person;
    }
}
