package nl.klev.eleasebackend.utilities;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorReport {
    public static String reportError(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fe : bindingResult.getFieldErrors()) {
            stringBuilder.append(fe.getField() + " : ");
            stringBuilder.append(fe.getDefaultMessage());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
