package com.ai.service.impl;

import com.ai.entity.dto.Account;
import com.ai.entity.vo.request.ConfirmResetVO;
import com.ai.entity.vo.request.EmailRegisterVO;
import com.ai.entity.vo.request.EmailResetVO;
import com.ai.mapper.AccountMapper;
import com.ai.service.AccountService;
import com.ai.utils.Const;
import com.ai.utils.FlowUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Resource
    FlowUtils flowUtils;

    @Resource
    AmqpTemplate amqpTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .authorities(account.getRole())
                .build();
    }

    @Override
    public String registerEmailVerifyCode(String type, String email, String ip) {
        synchronized (ip.intern()) {
            if (!this.verifyLimit(ip)) {
                return "请求过于频繁,请稍后再试";
            }
            Random random = new Random();
            int code = random.nextInt(899999) + 100000;
            Map<String, Object> data = Map.of("type", type, "email", email, "code", code);
            amqpTemplate.convertAndSend("mail", data);
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA + email, String.valueOf(code), 1, TimeUnit.MINUTES);
            return null;
        }
    }

    @Override
    public String registerEmailAccount(EmailRegisterVO vo) {
        String email = vo.getEmail();
        String username = vo.getUsername();
        String role = vo.getRole();
        String key = Const.VERIFY_EMAIL_DATA + email;
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) {
            return "请先获取验证码";
        }
        if (!code.equals(vo.getCode())) {
            return "验证码错误，请重新输入";
        }
        if (this.existsAccountByEmail(email)) {
            return "该邮箱已被注册";
        }
        if (this.existsAccountByUsername(username)) {
            return "该用户名已被注册";
        }
        String password = encoder.encode(vo.getPassword());
        String uid = getUid(email);
        Account account = new Account(null, username, password, email, role, new Date(), uid);
        if (this.save(account)) {

            stringRedisTemplate.delete(key);
            return null;
        } else {
            return "内部错误，请联系管理员";
        }

    }

    @Override
    public String resetConfirm(ConfirmResetVO vo) {
        String email = vo.getEmail();
        String code = stringRedisTemplate.opsForValue().get(Const.VERIFY_EMAIL_DATA + email);
        if (code == null) {
            return "请先获取验证码";
        }
        if (!code.equals(vo.getCode())) {
            return "验证码错误，请重新输入";
        }
        return null;
    }

    @Override
    public String resetEmailAccountPassword(EmailResetVO vo) {
        String email = vo.getEmail();
        String verify = this.resetConfirm(new ConfirmResetVO(vo.getEmail(), vo.getCode()));
        if (verify != null) {
            return verify;
        }
        String password = encoder.encode(vo.getPassword());
        boolean update = this.update().eq("email", email).set("password", password).update();
        if (update) {
            stringRedisTemplate.delete(Const.VERIFY_EMAIL_DATA + email);
        }
        return null;
    }

    @Override
    public Account findAccountByNameOrEmail(String text) {
        return this.query()
                .eq("username", text)
                .or()
                .eq("email", text)
                .one();
    }

    private boolean verifyLimit(String ip) {
        return flowUtils.limitOnceCheck(Const.VERIFY_EMAIL_LIMIT + ip, 60);
    }

    private boolean existsAccountByEmail(String email) {
        return this.baseMapper.exists(Wrappers.<Account>query().eq("email", email));
    }

    private boolean existsAccountByUsername(String username) {
        return this.baseMapper.exists(Wrappers.<Account>query().eq("username", username));
    }


    public static String getUid(String email) {
        String base64String = Base64.getEncoder().encodeToString(email.getBytes());
        return base64String.substring(0, 10);
    }
}
