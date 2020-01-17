package com.hulunbuir.clam.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Explain:生成mybatis开发代码
 * </p >
 *
 * @author wangjunming
 * @since 2019-12-31 17:50
 */
@Slf4j
public class MybatisGenerator {


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        F:\IDEA\hulun-buir\hulunbuir-afternoon\src\main\java
//        F:\IDEA\hulun-buir\hulunbuir-evening\src\main\java
        String afternoonurl = "F:\\IDEA\\hulun-buir\\hulunbuir-afternoon\\src\\main\\java";

        String eveningUrl = "F:\\IDEA\\hulun-buir\\hulunbuir-evening\\src\\main\\java";

        gc.setOutputDir(eveningUrl);//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("wangjunming");
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/collection?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(new String[]{"org"});
        //生成实体的@TableFile注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        /*
         * 下面的包名都根据实际情况编写
         * com.hulunbuir.clam.afternoon.persistence
         */

        String afternoonEntity = "com.hulunbuir.clam.afternoon.persistence.entity";
        String afternoonMapper = "com.hulunbuir.clam.afternoon.persistence.mapper";
        String afternoonXml = "com.hulunbuir.clam.afternoon.persistence.mapper.xml";
        String afternoonService = "com.hulunbuir.clam.afternoon.persistence.service";
        String afternoonServiceImpl = "com.hulunbuir.clam.afternoon.persistence.service.impl";
        String afternoonController = "com.hulunbuir.clam.afternoon.controller";


        String eveningEntity = "com.hulunbuir.clam.evening.persistence.entity";
        String eveningMapper = "com.hulunbuir.clam.evening.persistence.mapper";
        String eveningXml = "com.hulunbuir.clam.evening.persistence.mapper.xml";
        String eveningService = "com.hulunbuir.clam.evening.persistence.service";
        String eveningServiceImpl = "com.hulunbuir.clam.evening.persistence.service.impl";
        String eveningController = "com.hulunbuir.clam.evening.controller";

        pc.setEntity(eveningEntity);
        pc.setMapper(eveningMapper);
        pc.setXml(eveningXml);
        pc.setService(eveningService);
        pc.setServiceImpl(eveningServiceImpl);
        pc.setController(eveningController);
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }


}
