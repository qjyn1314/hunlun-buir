package com.hulunbuir.clam.parent.exception;

import java.io.Serializable;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/21 12:33
 */
public class BuirException extends RuntimeException implements Serializable {

    private BuirException() {
    }

    private BuirException(String message) {
        super(message);
    }

    public static BuirException build(String message) throws BuirException {
        throw new BuirException(message);
    }

}
