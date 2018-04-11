package com.cdyoue.oddJobs.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/6/23.
 */
public class TransferHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public TransferHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        List<String> strs = Arrays.stream(values).map(v -> v.replaceAll("%","\\\\%")).collect(Collectors.toList());
        return strs.toArray(new String[strs.size()]);
    }
}
