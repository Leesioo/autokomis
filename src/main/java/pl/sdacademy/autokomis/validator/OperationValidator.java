package pl.sdacademy.autokomis.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.sdacademy.autokomis.dto.OperationDto;

import java.math.BigDecimal;

@Component
public class OperationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OperationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OperationDto operationDto = (OperationDto) o;

        if (operationDto.getCustomerId() == null || operationDto.getCustomerId() == 0) {
            errors.rejectValue("customerId", "Nie określono kontrahenta", new Object[]{"Nie określono kontrahenta"}, "Nie określono kontrahenta");
        }

        switch (operationDto.getOperationType()) {
            case 2:
                if (operationDto.getOperationValue().compareTo(BigDecimal.valueOf(5000)) == -1) {
                    errors.rejectValue("operationValue", "Wartość nie może być mniejsza niż 5000", new Object[]{"Wartość nie może być mniejsza niż 5000"}, "Wartość nie może być mniejsza niż 5000");
                }
                break;
        }
    }
}
