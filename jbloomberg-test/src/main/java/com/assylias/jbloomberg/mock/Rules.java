package com.assylias.jbloomberg.mock;

public class Rules {
    public static boolean isSecurityError(String security) {
        return security == null || security.startsWith("Z") || security.startsWith("z");
    }

    public static boolean isBadField(String field) {
        return field == null || field.startsWith("Z") || field.startsWith("z");
    }
}
