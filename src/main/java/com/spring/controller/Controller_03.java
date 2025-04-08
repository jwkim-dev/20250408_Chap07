package com.spring.controller;


import com.spring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/exam03")
public class Controller_03 {
    String savePath = "/Users/juwonkim/Desktop/upload/files/";

    /** localhost:8080/exam03/form */
    @GetMapping("/form")
    public String requestForm(){
        return "viewPage";
    }

    @PostMapping("/form")
    public String submitForm(
            @ModelAttribute Member member,
            Model model
    ){
        String fileName = member.getName();
        MultipartFile file = member.getFileImage();

        /** 중복방지. UUID, System.currentTimeMillis??*/
        long curTimes = System.currentTimeMillis();
        File saveFile = new File(
                savePath+curTimes+"_"+fileName);

        try{
            file.transferTo(saveFile);
            model.addAttribute("data1",
                    "@ModelAttribute 테스트");
            model.addAttribute("data2",
                    fileName);
            model.addAttribute("data3",
                    saveFile.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "viewPage_process";
    }
}
