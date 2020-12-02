package com.hulunbuir.clam.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    /**数据库已有的的数据*/
    static List<SupplierPo> supplierPoes = new ArrayList<>();

    static {
        SupplierPo supplierPo = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo);
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


    public static void main(String[] args) {
        final Map<String, List<SupplierPo>> phoneMaps = supplierPos.stream().collect(Collectors.groupingBy(SupplierPo::getPhone));
        final Map<String, List<SupplierPo>> supplierMaps = supplierPos.stream().collect(Collectors.groupingBy(SupplierPo::getSupplierCode));







    }

}
