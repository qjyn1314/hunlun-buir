package com.hulunbuir.admin.worktest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/2 20:59
 */
public class ControllerTest {

    /**传输过来的数据*/
    static List<SupplierPo> supplierPos = new ArrayList<>();
    static {
        SupplierPo supplierPo = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo);
        SupplierPo supplierPo0 = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo0);
        SupplierPo supplierPo1 = new SupplierPo("15321355716","A0002","username002");
        supplierPos.add(supplierPo1);
        SupplierPo supplierPo2 = new SupplierPo("15321355717","A0003","username003");
        supplierPos.add(supplierPo2);
        SupplierPo supplierPo3 = new SupplierPo("15321355718","A0004","username004");
        supplierPos.add(supplierPo3);
        SupplierPo supplierPo4 = new SupplierPo("15321355719","A0005","username005");
        supplierPos.add(supplierPo4);
        SupplierPo supplierPo5 = new SupplierPo("15321355714","A0006","username006");
        supplierPos.add(supplierPo5);
        SupplierPo supplierPo6 = new SupplierPo("15321355713","A0007","username007");
        supplierPos.add(supplierPo6);
        SupplierPo supplierPo9 = new SupplierPo("15321355723","A0007","username007");
        supplierPos.add(supplierPo9);
        SupplierPo supplierPo7 = new SupplierPo("15321355712","A0008","username008");
        supplierPos.add(supplierPo7);
        SupplierPo supplierPo8 = new SupplierPo("15321355711","A0008","username008");
        supplierPos.add(supplierPo8);
    }
    /**数据库已有的供应商的数据*/
    static List<SupplierPo> supplierPoes = new ArrayList<>();
    static {
        SupplierPo supplierPo = new SupplierPo("15321355715","A0001","username001");
        supplierPoes.add(supplierPo);
        SupplierPo supplierPo1 = new SupplierPo("15321355716","A0002","username002");
        supplierPoes.add(supplierPo1);
        SupplierPo supplierPo2 = new SupplierPo("15321355717","A0003","username003");
        supplierPoes.add(supplierPo2);
        SupplierPo supplierPo3 = new SupplierPo("15321355718","A0004","username004");
        supplierPoes.add(supplierPo3);
        SupplierPo supplierPo4 = new SupplierPo("15321355737","A0003","username003");
        supplierPoes.add(supplierPo4);
        SupplierPo supplierPo5 = new SupplierPo("15321355748","A0004","username004");
        supplierPoes.add(supplierPo5);
    }
    /**通过手机号查询出来的用户*/
    static List<UsersPo> usersPos = new ArrayList<>();
    /**供应商与用户的关联表数据*/
    static List<UserSupplierPo> userSupplierPos = new ArrayList<>();

    static {
        UsersPo usersPo = new UsersPo(12L,"15321355715","username001");
        usersPos.add(usersPo);
        UsersPo usersPo1 = new UsersPo(13L,"15321355716","username001");
        usersPos.add(usersPo1);
        UsersPo usersPo2 = new UsersPo(14L,"15321355717","username001");
        usersPos.add(usersPo2);
        UsersPo usersPo3 = new UsersPo(15L,"15321355235","username001");
        usersPos.add(usersPo3);
    }

    static {
        UserSupplierPo userSupplierPo = new UserSupplierPo(12L,"A0001");
        userSupplierPos.add(userSupplierPo);
        UserSupplierPo userSupplierPo2 = new UserSupplierPo(13L,"A0002");
        userSupplierPos.add(userSupplierPo2);
    }

    public static void main(String[] args) {
//        final Map<String, List<SupplierPo>> phoneMaps = supplierPos.stream().collect(Collectors.groupingBy(SupplierPo::getPhone));
//        final Map<String, List<SupplierPo>> supplierMaps = supplierPos.stream().collect(Collectors.groupingBy(SupplierPo::getSupplierCode));
//        final List<String> phones = usersPos.stream().map(UsersPo::getPhone).collect(Collectors.toList());
//        final List<Long> userIds = userSupplierPos.stream().map(UserSupplierPo::getUserId).collect(Collectors.toList());

//        final long count1 = supplierPos.stream().distinct().count();
//
//        final long count = supplierPos.size();
//        System.out.println(count1);
//        System.out.println(count);

        System.out.println(randomStr(64));
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 指定长度
     * @author wangjunming
     * @since 2021/3/16 20:23
     * @return java.lang.String
     */
    public static String randomStr(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }


}
