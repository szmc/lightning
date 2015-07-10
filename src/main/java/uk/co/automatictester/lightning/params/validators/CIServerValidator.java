package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;
import java.util.List;

public class CIServerValidator implements IParameterValidator {

    private static List<String> CI_SERVERS = new ArrayList<>();

    static {
        CI_SERVERS.add("jenkins");
        CI_SERVERS.add("teamcity");
    }

    public void validate(String name, String value) throws ParameterException {
        if (!(CI_SERVERS.contains(value.toLowerCase()))) {
            throw new ParameterException(String.format("CI server '%s' not in list: %s", value, CI_SERVERS.toString()));
        }
    }
}
