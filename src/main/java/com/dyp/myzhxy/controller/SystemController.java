package com.dyp.myzhxy.controller;


import com.dyp.myzhxy.util.CreateVerifiCodeImage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage (HttpServletRequest request, HttpServletResponse response){
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();

        //获取code
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        try {
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
