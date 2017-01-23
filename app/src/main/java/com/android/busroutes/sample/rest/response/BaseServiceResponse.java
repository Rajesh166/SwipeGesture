package com.android.busroutes.sample.rest.response;

import java.util.List;
import java.util.Map;

public class BaseServiceResponse {

    private ResponseStatus status;
    private Map<String, String> inputErrors;
    private Map<String, String> outputErrors;
    private Map<String, String> outputInfo;
    private List<String> debugContext;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    /**
     * Returns true if the response contains an input or an output error.
     *
     * @return true if the response contains an input or an output error
     */
    public boolean hasAnyError() {
        return hasAnyInputError() || hasAnyOutputError();
    }

    /**
     * Returns true if the response contains input validation messages.
     *
     * @return true if the response contains input validation messages
     */
    public boolean hasAnyInputError() {
        return inputErrors != null && inputErrors.size() > 0;
    }

    /**
     * Returns true if the response contains an input validation message for
     * the supplied field.
     *
     * @param fieldName the name of the field
     * @return true if the response contains an input validation message for
     * the supplied field
     */
    public boolean hasInputError(final String fieldName) {
        return nullSafeContains(String.class, fieldName, inputErrors);
    }

    /**
     * Returns true if the response contains output errors.
     *
     * @return true if the response contains output errors
     */
    public boolean hasAnyOutputError() {
        return outputErrors != null && outputErrors.size() > 0;
    }

    /**
     * Returns true if the response contains the supplied output error code.
     *
     * @param errorCode the error code to check for
     * @return true if the response contains the supplied output error code
     */
    public boolean hasOutputError(final String errorCode) {
        return nullSafeContains(String.class, errorCode, outputErrors);
    }

    /**
     * Returns true if the response contains the supplied output info code.
     *
     * @param infoCode the info code to check for
     * @return true if the response contains the supplied output info code
     */
    public boolean hasOutputInfo(final String infoCode) {
        return nullSafeContains(String.class, infoCode, outputInfo);
    }

    private <T> boolean nullSafeContains(final Class<?> T, final T key, final Map<T, String> map) {

        boolean contained = false;

        if (map != null) {
            contained = map.containsKey(key);
        }

        return contained;
    }

    /**
     * Returns a Map of request parameter validation errors. The key is the
     * parameter name and the value is a localized description of what a legal
     * value for the parameter is.
     *
     * @return map of input validation errors
     */
    public Map<String, String> getInputErrors() {
        return inputErrors;
    }

    public void setInputErrors(Map<String, String> inputErrors) {
        this.inputErrors = inputErrors;
    }

    /**
     * Returns a Map of processing errors. The key is the error code and the
     * value is the localized error description.
     *
     * @return map of output errors
     */
    public Map<String, String> getOutputErrors() {
        return outputErrors;
    }

    public void setOutputErrors(Map<String, String> outputErrors) {
        this.outputErrors = outputErrors;
    }

    /**
     * Returns a Map of info codes and messages pertaining to the processing
     * of a request.
     *
     * @return map of output info
     */
    public Map<String, String> getOutputInfo() {
        return outputInfo;
    }

    public void setOutputInfo(Map<String, String> outputInfo) {
        this.outputInfo = outputInfo;
    }

}
