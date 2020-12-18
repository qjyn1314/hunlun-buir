package com.hulunbuir.admin.supplier;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/18 10:45
 */
public class SupplierTest {


    public static void main(String[] args) {
        final String vendorTypeDesc = VendorTypeEnum.getVendorTypeDesc(100);
        System.out.println(vendorTypeDesc);
        System.out.println(vendorTypeDesc == null);
    }



}
