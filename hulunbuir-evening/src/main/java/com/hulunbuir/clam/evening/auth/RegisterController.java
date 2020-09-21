package com.hulunbuir.clam.evening.auth;

import com.hulunbuir.clam.evening.persistence.service.ISysUserService;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/18 10:40
 */
@Slf4j
@RestController
public class RegisterController {

    @Autowired
    private ISysUserService userService;

    /**
     * 用户注册
     *
     * @author wangjunming
     * @since 2020/9/18 10:42
     */
    @PostMapping("/auth/register")
    public JsonResult registerUser(@RequestParam String username, @RequestParam String password) throws HulunBuirException {
        userService.registerUser(username, password);
        return JsonResult.success();
    }

}
