package com.example.hello.controllers;

import com.example.hello.models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import com.example.hello.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(){

        return "home" ;
    }

    @GetMapping("/hello")
    public String helloDynamic(@RequestParam(defaultValue = "my friend") String name, Model model){
        model.addAttribute("name", name);
        return "hello" ;
    }

    @GetMapping("/gallery")
    public String gallery( Model model){

        List<String> imageNamesList = IntStream.rangeClosed(1, 36)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        List imageList = new ArrayList<String>();
        for(String t : imageNamesList){
            String tempObj = String.format("/images/%s.jpg" , t) ;
            imageList.add(tempObj) ;
        }


        model.addAttribute("imageList", imageList);
        return "gallery" ;

    }


}


