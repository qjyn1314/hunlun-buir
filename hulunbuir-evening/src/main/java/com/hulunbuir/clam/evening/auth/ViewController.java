package com.hulunbuir.clam.evening.auth;

import com.calm.security.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/28 11:31
 */
@Slf4j
@Controller
public class ViewController extends AuthService{

    @GetMapping({"/page/**.html", "/page/*/**.html","/auth/register"})
    public String initView() {
        return handleView();
    }

}