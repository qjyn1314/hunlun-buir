package com.hulunbuir.datasource.config;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.hulunbuir.datasource.support.DataSourceConstants;
import com.hulunbuir.parent.tool.JasyptUtil;
import org.springframework.context.annotation.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 从数据源中获取 配置信息
 *
 * @author wangjunming
 * @since 2020/10/30 9:21
 */
@Configuration
public class JdbcDynamicDataSourceProvider extends AbstractJdbcDataSourceProvider {

    private final DynamicDataSourceProperties properties;

    public JdbcDynamicDataSourceProvider(DynamicDataSourceProperties dynamicDataSourceProperties) {
        super(dynamicDataSourceProperties.getDriverClassName(), dynamicDataSourceProperties.getUrl(), dynamicDataSourceProperties.getUsername(), JasyptUtil.decyptPwd(dynamicDataSourceProperties.getPassword()));
        this.properties = dynamicDataSourceProperties;
    }

    /**
     * 执行语句获得数据源参数
     *
     * @param statement 语句
     * @return 数据源参数
     * @throws SQLException sql异常
     */
    @Override
    protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery(properties.getQueryDsSql());
        Map<String, DataSourceProperty> map = new HashMap<>(8);
        while (rs.next()) {
            String name = rs.getString(DataSourceConstants.DS_NAME);
            String username = rs.getString(DataSourceConstants.DS_USER_NAME);
            String password = rs.getString(DataSourceConstants.DS_USER_PWD);
            String url = rs.getString(DataSourceConstants.DS_JDBC_URL);
            DataSourceProperty property = new DataSourceProperty();
            property.setDriverClassName(DataSourceConstants.DS_DRIVER);
            property.setUsername(username);
            property.setPassword(JasyptUtil.decyptPwd(password));
            property.setUrl(url);
            map.put(name, property);
        }
        // 添加默认主数据源
        DataSourceProperty property = new DataSourceProperty();
        property.setUrl(properties.getUrl());
        property.setUsername(properties.getUsername());
        property.setPassword(JasyptUtil.decyptPwd(properties.getPassword()));
        property.setDriverClassName(properties.getDriverClassName());
        map.put(DataSourceConstants.DS_MASTER, property);
        return map;
    }

}
