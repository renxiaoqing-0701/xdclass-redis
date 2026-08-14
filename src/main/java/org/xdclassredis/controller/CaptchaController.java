package org.xdclassredis.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xdclassredis.util.CommonUtil;
import org.xdclassredis.util.JsonData;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/captcha")
public class CaptchaController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Producer captchaProducer;


    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @GetMapping("/get_captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        String captchaText = captchaProducer.createText();
        String key = getCaptchaKey(request);
        redisTemplate.opsForValue().set(key,captchaText,5, TimeUnit.MINUTES);
        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getCaptchaKey(HttpServletRequest request){
        String ip = CommonUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "user-service:captcha:"+CommonUtil.MD5(ip+userAgent);
        return key;
    }


    /**
     * 支持手机号、邮箱发送验证码
     * @return
     */
    @GetMapping("/send_code")
    public JsonData sendRegisterCode(@RequestParam(value = "to", required = true)String to,
                                     @RequestParam(value = "captcha", required = true)String  captcha,
                                     HttpServletRequest request){
        String key = getCaptchaKey(request);
        String cacheCaptcha = redisTemplate.opsForValue().get(key);
        if(captcha!=null && cacheCaptcha!=null && cacheCaptcha.equalsIgnoreCase(captcha)) {
            redisTemplate.delete(key);
            //TODO 发送验证码
            return JsonData.buildSuccess("验证码发送成功");
        }else {
            return JsonData.buildError("图形验证码错误");
        }
    }
}
