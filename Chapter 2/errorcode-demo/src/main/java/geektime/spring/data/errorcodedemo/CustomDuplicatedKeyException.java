package geektime.spring.data.errorcodedemo;

import org.springframework.dao.DuplicateKeyException;

/**
 * 自定义键重复异常
 */
public class CustomDuplicatedKeyException extends DuplicateKeyException {
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
