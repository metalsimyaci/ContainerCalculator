package com.hasanural.containercalculator.Utilities;

import android.content.res.Resources;

import java.lang.reflect.Field;

public final class Helper {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isEmptyOrWhitespace(String s) {
        if( s==null)
            return true;

        return isEmpty(s) || isWhitespace(s);

    }
    public static boolean isEmpty(String s)
    {
        return s.trim().isEmpty();
    }
    public static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static Integer tryParse(Object obj) {
        Integer retVal;
        try {
            retVal = Integer.parseInt((String) obj);
        } catch (NumberFormatException nfe) {
            retVal = 0;
        }
        return retVal;
    }
    public static String toString(Integer obj) {
        String retVal;
        try {
            retVal = Integer.toString(obj);
        } catch (NumberFormatException nfe) {
            retVal = null;
        }
        return retVal;
    }
    public static String toString(Double obj) {
        String retVal;
        try {
            retVal = Double.toString(obj);
        } catch (NumberFormatException nfe) {
            retVal = null;
        }
        return retVal;
    }

    public static String hexString(Resources res) {
        Object resImpl = getPrivateField("android.content.res.Resources", "mResourcesImpl", res);
        Object o = resImpl != null ? resImpl : res;
        return "@" + Integer.toHexString(o.hashCode());
    }

    public static Object getPrivateField(String className, String fieldName, Object object) {
        try {
            Class c = Class.forName(className);
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(object);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
