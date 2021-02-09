package com.hulunbuir.admin.springstudy.txconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/8 21:42
 */
@Repository
public class StuInfoMapper {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void insertStuInfo(StuInfo stuInfo) {
        String stuInfoInsertSql = "INSERT INTO `stuinfo`(`id`, `stuName`, `gender`, `seat`, `age`) VALUES (null, ?, ?, ?, ?)";
        jdbcTemplate.update(stuInfoInsertSql,stuInfo.getStuName(),stuInfo.getGender(),stuInfo.getSeat(),stuInfo.getAge());
    }
}
