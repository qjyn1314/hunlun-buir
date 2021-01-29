package com.hulunbuir.admin.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/13 15:07
 */
@Slf4j
public class MybatisTest {

    public static void main001(String[] args) throws IOException {
        String resource = "com\\hulunbuir\\admin\\mybatis\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        BankMoneyMapper mapper = sqlSession.getMapper(BankMoneyMapper.class);
        final VendorSettingClassOld vendorSettingClassOld = selectByClassId(mapper, 2L);
        final String jsonString = JSONObject.toJSONString(vendorSettingClassOld, SerializerFeature.PrettyFormat);
        System.out.println(jsonString);
        Map<String,Object> map = new HashMap<>(1);
        final Map<String, Object> stringObjectMap = handleSettingClass(map,vendorSettingClassOld,500);
        final String stringObjectMapjson = JSONObject.toJSONString(stringObjectMap, SerializerFeature.PrettyFormat);
        System.out.println(stringObjectMapjson);

        HashMap<String,Object> mapMoney = new HashMap<>(1);
        mapMoney.put("id",2);
        final List<Bankmoney> bankMonies = mapper.bankMoneyLists(mapMoney);

        System.out.println(bankMonies);
        final String databaseId = sqlSessionFactory.getConfiguration().getDatabaseId();
        System.out.println(databaseId);


        sqlSession.close();
    }

    private static Map<String, Object> handleSettingClass(Map<String, Object> map, VendorSettingClassOld youngSettingClass, Integer m) {
        Integer n = 600;
        map.put(getNameKey(m) + getNameKey(youngSettingClass.getHierarchy()) + getNameKey(n), youngSettingClass.getName());
        Long pid = 0L;
        if (pid.equals(youngSettingClass.getPid())) {
            return map;
        }
        final VendorSettingClassOld children = youngSettingClass.getChildren();
        Map<String, Object> childrenMap = new HashMap<>();
        childrenMap.put(getNameKey(m) + getNameKey(children.getHierarchy()) + getNameKey(n), children.getName());
        String childrenKey = "children";
        childrenMap.put(childrenKey, map);
        if (pid.equals(children.getPid())) {
            return childrenMap;
        }
        return handleSettingClass(childrenMap, children, m);
    }

    public static String getNameKey(Integer hierarchy) {
        switch (hierarchy) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 400:
                return "所在";
            case 500:
                return "申请";
            case 600:
                return "级分类";
            default:
                return "";
        }
    }
    public static VendorSettingClassOld selectByClassId(BankMoneyMapper mapper,Long classId) {
        return handleSettingChild(mapper,classId);
    }

    private static VendorSettingClassOld handleSettingChild(BankMoneyMapper mapper,Long classId) {
        final VendorSettingClassOld settingClassOld = mapper.selectByClassId(classId);
        if(null != settingClassOld && !Long.valueOf("0").equals(settingClassOld.getPid())){
            final VendorSettingClassOld classOld = handleSettingChild(mapper,settingClassOld.getPid());
            settingClassOld.setChildren(classOld);
        }
        return settingClassOld;
    }

    /**
     * 1、其中mybatis中允许增删改查时直接定义以下类型的返回值
     *   Integer   Long  Boolean 这些类型可以直接定义
     * 2、
     *
     * @author wangjunming
     * @since 2021/1/19 11:39
     */
    public static void main(String[] args) throws IOException {
        main001(args);
        String resource = "com\\hulunbuir\\admin\\mybatis\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        BankMoneyMapper mapper = sqlSession.getMapper(BankMoneyMapper.class);
        Bankmoney bankmoney = new Bankmoney();
    }

}
