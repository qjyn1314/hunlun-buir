package com.hulunbuir.clam.admin.mybatis;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/13 16:22
 */
public class Bankmoney {

    private Integer id;

    private String username;

    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Bankmoney{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                '}';
    }
}
