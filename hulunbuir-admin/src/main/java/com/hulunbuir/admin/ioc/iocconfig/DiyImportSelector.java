package com.hulunbuir.admin.ioc.iocconfig;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 22:27
 */

public class DiyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.hulunbuir.admin.ioc.iocconfig.LinuxCondition","com.hulunbuir.admin.ioc.iocconfig.WindowsCondition"};
    }
}
