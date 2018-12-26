package com.bing.utils.utilstool;

import com.bing.constant.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 图形验证码
 */
@org.springframework.stereotype.Component
public class CaptchaUtil {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  private static final int WIDTH = 80;
  private static final int HEIGHT = 26;
  private static final String[] strArr = {"3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
          "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};

  public void getCaptchaName(HttpServletResponse response, String phone) {
    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, 1);
    String vCode = drawGraphic(image);
    vCode = vCode.toUpperCase();
    vCode = MD5Util.md5(vCode);
    stringRedisTemplate.opsForValue().set(Constant.CAPTCHA + phone, vCode);
    stringRedisTemplate.expire(Constant.CAPTCHA + phone, 60, TimeUnit.SECONDS);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    response.setContentType("image/jpg");
    ServletOutputStream sos = null;
    try {
      sos = response.getOutputStream();
      ImageIO.write(image, "jpg", sos);
      return;
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (sos != null) {
        try {
          sos.close();
        } catch (IOException e) {
        }
      }
    }
  }

  private String drawGraphic(BufferedImage image) {
    Graphics g = image.createGraphics();
    Random random = new Random();
    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, 80, 26);
    g.setFont(new Font("Times New Roman", 0, 18));
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
      int x = random.nextInt(80);
      int y = random.nextInt(26);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }

    String sRand = "";
    for (int i = 0; i < 4; i++) {
      String rand = String.valueOf(strArr[random.nextInt(strArr.length)]);
      sRand = sRand + rand;

      g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

      g.drawString(rand, 16 * i + 11, 19);
    }
    g.dispose();
    return sRand;
  }

  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

  public boolean validate(String captcha, String captchaName) {
    String md5val = MD5Util.md5(captcha.toUpperCase());
    boolean result = md5val.equals(stringRedisTemplate.opsForValue().get(Constant.CAPTCHA + captchaName));
    stringRedisTemplate.delete(Constant.CAPTCHA + captchaName);
    return result;
  }

}
