package com.hulunbuir.evening;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mybatis的代码生成器
 * 主要参考了： https://baomidou.com/config/generator-config.html#%E5%9F%BA%E6%9C%AC%E9%85%8D%E7%BD%AE
 */
@Slf4j
public class MybatisCodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MybatisCodeGenerator.class);
    // 需要构建引入的表名
    private static final String[] INCLUDE_TABLE = new String[]{
            "vendor_setting_class_conditions",
    };

    /**
     * 构建入口
     */
    public void generator() {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        mpg.setGlobalConfig(globalConfig());
        // 配置数据源
        mpg.setDataSource(dataSource());
        // 配置策略
        mpg.setStrategy(strategy());
        // 包配置
        mpg.setPackageInfo(packageConfig());
        // 自定义配置
        mpg.setCfg(cfg());
        // 注入模板配置
//        mpg.setTemplate(templateConfig());
        // 执行生成
        mpg.execute();
        logger.info("Mybatis Code Generator Success!");
    }

    /**
     * 配置全局配置
     */
    private GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("F:\\idea_workspace\\zhichubao_workspace\\zhichubao_code");
        globalConfig.setFileOverride(true); // 重写文件
        globalConfig.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        globalConfig.setEnableCache(false);// 是否在xml中添加二级缓存配置
        globalConfig.setBaseResultMap(true);// 开启 BaseResultMap
        globalConfig.setBaseColumnList(true);// 开启 baseColumnList
        globalConfig.setAuthor("wangjunming");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        return globalConfig;
    }

    /**
     * 配置数据源
     */
    private DataSourceConfig dataSource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://rm-2ze86ne9n05vf0uw3o.mysql.rds.aliyuncs.com:3306/p2p_test?serverTimezone=GMT%2b8&allowMultiQueries=true");
        dataSourceConfig.setUsername("p2p_test_root");
        dataSourceConfig.setPassword("Zhichubao!@#$%^&*(0");
        return dataSourceConfig;

    }

    /**
     * 策略配置
     */
    private StrategyConfig strategy() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("p2p_app_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 列名生成策略
        strategy.setInclude(INCLUDE_TABLE); // 需要生成的表
//        strategy.setEntityTableFieldAnnotationEnable(true); //是否生成实体时，生成字段注解
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型（默认 false）
        return strategy;
    }

    /**
     * 包名配置
     */
    private PackageConfig packageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.zhichubao.slc");
        packageConfig.setEntity("model.model");
        packageConfig.setMapper("dal.mapper");
        packageConfig.setXml("dal.mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("web.controller");
        return packageConfig;
    }

    /**
     * 自定义配置
     */
    private InjectionConfig cfg() {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        //InjectionConfig cfg = new InjectionConfig() {
        //    @Override
        //    public void initMap() {
        //        Map<String, Object> map = new HashMap<String, Object>();
        //        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
        //        this.setMap(map);
        //    }
        //};

        // 自定义 xxList.jsp 生成
        //List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //focList.add(new FileOutConfig("/template/list.jsp.vm") {
        //    @Override
        //    public String outputFile(TableInfo tableInfo) {
        //         自定义输入文件名称
        //return "D://my_" + tableInfo.getEntityName() + ".jsp";
        //}
        //});

        // 调整 xml 生成目录演示
        //focList.add(new FileOutConfig("/tmp/rfpcore/templates/mapper.xml.vm") {
        //    @Override
        //    public String outputFile(TableInfo tableInfo) {
        //        return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
        //    }
        //});
        //cfg.setFileOutConfigList(focList);
        return null;
    }

    /**
     * 模板配置
     */
    private TemplateConfig templateConfig() {
        //TemplateConfig templateConfig = new TemplateConfig();
        //templateConfig.setXml(null);
        return null;
    }
}
