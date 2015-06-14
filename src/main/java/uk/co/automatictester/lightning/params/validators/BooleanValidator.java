package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class BooleanValidator implements IParameterValidator {

    public void validate(String name, String value) throws ParameterException {
        if (!("true".equals(value) || "false".equals(value))) {
            throw new ParameterException("Parameter " + name + " should be boolean (true or false)");
        }
    }
}
