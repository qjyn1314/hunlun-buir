package com.hulunbuir.admin.springstudy.txconfig;

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
@Service
public class StuInfoService {

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertStuInfo(StuInfo stuInfo){
        stuInfoMapper.insertStuInfo(stuInfo);
//        Integer i = 10/0;
    }


}
