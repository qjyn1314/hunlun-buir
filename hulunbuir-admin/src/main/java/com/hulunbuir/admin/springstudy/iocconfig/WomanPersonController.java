package com.hulunbuir.admin.springstudy.iocconfig;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 21:53
 */
@ToString
@RestController
public class WomanPersonController {

    @Autowired
    private DiyValue diyValue;

    @Autowired
    private DiyManPerson diyManPerson;

    @Resource
    private DiyValue diyValueAutowired;

    @Autowired
    public WomanPersonController(@Autowired DiyManPerson diyManPerson) {
        this.diyManPerson = diyManPerson;
    }

    public DiyManPerson getDiyManPerson() {
        return diyManPerson;
    }

    @Autowired
    public void setDiyManPerson(DiyManPerson diyManPerson) {
        this.diyManPerson = diyManPerson;
    }
}
