package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

@Controller
@RequestMapping("/exam01")
public class Controller_01 {

    String savePath = "/Users/juwonkim/Desktop/upload/files/";

    /** http://localhost:8080/exam01/form */
    @GetMapping("/form")
    public String requestForm(){
        return "viewPage";
    }



    /** MultipartHttpServletRequest 요놈 이용.*/
    @PostMapping("/form")
    public String submitForm(
            MultipartHttpServletRequest request,
            Model model
    ){
        String name = request.getParameter("name");
        MultipartFile file = request.getFile("fileImage");
        String fileName = file.getOriginalFilename();
        File safeFile = new File(savePath+name+fileName);
        try{
            file.transferTo(safeFile);
            model.addAttribute("data1", "Multipart 예제");
            model.addAttribute("data2", fileName);
            model.addAttribute("data3", safeFile.getName() );
        }catch (Exception e){
            e.printStackTrace();
        }
        return "viewPage_process";
    }
}
