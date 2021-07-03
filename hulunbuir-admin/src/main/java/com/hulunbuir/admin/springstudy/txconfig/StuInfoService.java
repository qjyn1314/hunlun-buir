package com.hulunbuir.admin.springstudy.txconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/8 21:40
 */
@Slf4j
@Service
public class StuInfoService {

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertStuInfo(StuInfo stuInfo){
        log.info("进入service接口...");
        stuInfoMapper.insertStuInfo(stuInfo);
        log.info("调用StuInfoMapper接口之后...");
//        Integer i = 10/0;
    }


}
