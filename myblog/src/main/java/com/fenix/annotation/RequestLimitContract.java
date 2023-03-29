package com.fenix.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RequestLimitContract {

    private static final Logger logger = LoggerFactory.getLogger("requestLimitLogger");
    private Map<String , Integer> redisTemplate = new HashMap<>();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint , RequestLimit limit) throws RequestLimitException {
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
                redisTemplate.put(key, 1);
            } else {
                redisTemplate.put(key, redisTemplate.get(key) + 1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                //创建一个定时器
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        redisTemplate.remove(key);
                    }
                };
                //这个定时器设定在time规定的时间之后会执行上面的remove方法，也就是说在这个时间后它可以重新访问
                timer.schedule(timerTask, limit.time());
                try{
                    String sql = "";
                    // 此位置逻辑待修改，可修改为每次只留一个
//                    sql = "DELETE FROM ip_access_num WHERE access_ip = ?;";
//                    jdbcTemplate.update(sql, new String[]{ip});
                    sql = "UPDATE ip_access_num SET is_ban = 0 WHERE access_ip = ?;";
                    jdbcTemplate.update(sql, new String[]{ip});
                }catch (Exception e){
                    System.out.println("Update error!");
                    System.out.println(e);
                }
            }
            if (count > limit.count()) {
                try{
                    String insertIP = ip;
                    String accessNumber = String.valueOf(limit.count());
                    String sql = "INSERT INTO ip_access_num (access_ip, access_number, is_ban) VALUES (?,?,?)";
                    jdbcTemplate.update(sql, new String[]{insertIP, accessNumber, "1"});
                }catch (Exception e){
                    System.out.println("Insert error!");
                    System.out.println(e);
                }
                logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        }catch (RequestLimitException e){
            throw e;
        }catch (Exception e){
            logger.error("发生异常",e);
        }
    }
}


// @before 注解代表在请求发送到控制器之前会先来到这里执行相应的内容，
// within里面的书写表示写在控制器上方并且有对应注解的控制器会来到这里。
// 在获得ip和对应的url之后将它作为一个key，存放到map中，
// 假如map中已经存在了这个key，那么多产生一个时间处理器，
// 当时间超过注解书写的单位时间之后又会将这个key从map中移走。
// 假如访问的次数超过了限制，将会抛出异常说明访问次数过多