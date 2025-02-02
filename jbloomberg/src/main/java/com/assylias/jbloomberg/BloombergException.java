package com.assylias.jbloomberg;

/**
 * Exception thrown to signal that an error occurred while calling the Bloomberg API.<br>
 * This can happen for example if there is no Bloomberg terminal installed on the machine, if the user is not logged on,
 * if the connection gets shutdown, if a malformed query is sent to the API etc.
 */
public class BloombergException extends RuntimeException {

    private static final long serialVersionUID = -4933393519048184988L;

    public BloombergException(String message) {
        super(message);
    }

    public BloombergException(String message, Throwable cause) {
        super(message, cause);
    }
}
