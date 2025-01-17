package geektime.spring.springbucks.waiter.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 指明该异常的 HttpStatus
@Getter
@AllArgsConstructor
public class FormValidationException extends RuntimeException {
    private BindingResult result;
}
