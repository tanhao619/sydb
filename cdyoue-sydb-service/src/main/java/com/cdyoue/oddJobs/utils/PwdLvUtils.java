package com.cdyoue.oddJobs.utils;

import static java.util.regex.Pattern.matches;

/**
 * Created by Tangguang on 2017/6/8.
 */
public class PwdLvUtils {
    public static int getPassWordLv(String str) {

        int lv = 0;
        if (str.length() < 8) {
            return lv;
        } else {
            lv = 1;
            if (matches(".*[a-z].*", str)) {
                lv++;
            }
            if (matches(".*[A-Z].*", str)) {
                lv++;
            }
            if (matches(".*[0-9].*", str)) {
                lv++;
            }
            if (containSpecialChar(str)) {
                lv++;
            }
        }
        return lv;

    }

    public static boolean containSpecialChar(String str) {
        return matches(".*[(\\ )(\\~)(\\!)(\\@)(\\#)(\\$)(\\%)(\\^)(\\&)(\\*)(\\()(\\))(\\-)(\\_)(\\+)(\\=)(\\[)(\\])(\\{)(\\})(\\|)(\\\\)(\\;)(\\:)(\\')(\\\")(\\,)(\\.)(\\/)(\\<)(\\>)(\\?)(\\)]+.*", str);
    }
}
