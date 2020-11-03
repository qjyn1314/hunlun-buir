package com.calm.datasource.support;

/**
 * 数据源相关常量
 *
 * @author wangjunming
 * @since 2020/10/30 9:21
 */
public interface DataSourceConstants {

    /**
     * 数据源名称
     */
    String DS_NAME = "database_name";

    /**
     * 默认驱动
     */
    String DS_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 默认数据源（master）
     */
    String DS_MASTER = "master";

    /**
     * jdbcurl
     */
    String DS_JDBC_URL = "url";

    /**
     * 数据库IP地址
     */
    String DS_IP = "database_ip";

    /**
     * 数据库端口号
     */
    String DS_PORT = "database_port";

    /**
     * 用户名
     */
    String DS_USER_NAME = "username";

    /**
     * 密码
     */
    String DS_USER_PWD = "password";

}
