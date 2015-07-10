package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CIServerValidator implements IParameterValidator {

    private static List<String> CI_SERVERS = new ArrayList<>();

    static {
        CI_SERVERS.add("jenkins");
        CI_SERVERS.add("teamcity");
    }

    public void validate(String name, String value) throws ParameterException {
        if (!(CI_SERVERS.contains(value.toLowerCase()))) {
            System.out.println(String.format("CI server '%s' not in list: %s", value, CI_SERVERS.toString()));
            throw new ParameterException(String.format("CI server '%s' not in list: %s", value, CI_SERVERS.toString()));
        }
    }
}