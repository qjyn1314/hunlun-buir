package com.hulunbuir.clam.parent.exception;

import java.io.Serializable;

/**
 * <p>
 * Explain:系统中的异常类，
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 11:41
 */
public class HulunBuirException extends Exception implements Serializable {

    private HulunBuirException() {
    }

    private HulunBuirException(String message) {
        super(message);
    }

    public static HulunBuirException build(String message) throws HulunBuirException {
        throw new HulunBuirException(message);
    }


}
