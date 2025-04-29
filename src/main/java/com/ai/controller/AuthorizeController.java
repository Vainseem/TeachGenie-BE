package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.vo.request.ConfirmResetVO;
import com.ai.entity.vo.request.EmailRegisterVO;
import com.ai.entity.vo.request.EmailResetVO;
import com.ai.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AccountService accountService;

    @GetMapping("/ask-code")
    public RestBean<?> askVerifyCode(
            @RequestParam @Email String email,
            @RequestParam @Pattern(regexp = "(register|reset)") String type,
            HttpServletRequest request) {
        return this.messageHandle(() -> accountService.registerEmailVerifyCode(type, email, request.getRemoteAddr()));
    }

    @PostMapping("/register")
    public RestBean<?> register(@RequestBody @Valid EmailRegisterVO vo) {
        return this.messageHandle(vo, accountService::registerEmailAccount);
    }

    private RestBean<?> messageHandle(Supplier<String> action) {
        String message = action.get();
        return message == null ? RestBean.success() : RestBean.failure(400, message);
    }

    @PostMapping("/reset-confirm")
    public RestBean<?> resetConfirm(@RequestBody @Valid ConfirmResetVO vo) {
        return this.messageHandle(vo, accountService::resetConfirm);
    }

    @PostMapping("/reset-password")
    public RestBean<?> resetConfirm(@RequestBody @Valid EmailResetVO vo) {
        return this.messageHandle(vo, accountService::resetEmailAccountPassword);
    }

    private <T> RestBean<?> messageHandle(T vo, Function<T, String> function) {
        return messageHandle(() -> function.apply(vo));
    }
}
