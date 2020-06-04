package com.hulunbuir.clam.admin.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/13 15:07
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        String resource = "com\\hulunbuir\\clam\\admin\\mybatis\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BankMoneyMapper mapper = sqlSession.getMapper(BankMoneyMapper.class);
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",2);
        List<Bankmoney> bankmonies = mapper.bankMoneyLists(map);
        System.out.println(bankmonies);
    }

}
