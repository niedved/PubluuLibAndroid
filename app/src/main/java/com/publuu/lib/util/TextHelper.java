package com.publuu.lib.util;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michal Kiermaszek on 19.07.2018.
 */
public class TextHelper {


    private static String BoldOpen = "<b>";
    private static String BoldClose = "</b>";
    private static String BoldRegex = "\\*\\*";
    private static String NewLineRegex = "(\r\n|\n)";
    private static String NewLine = "<br />";

    public static Spanned Bold(String s) {
        s = ReplaceB(s);
        s = ReplaceBR(s);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(s);
        }
    }

    private static String ReplaceBR(String s) {
        return s.replaceAll(NewLineRegex, NewLine);
    }

    private static String ReplaceB(String s) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(BoldRegex);
        Matcher m = p.matcher(s);
        int count = 1;
        while (m.find()) {
            if (count % 2 == 1) {
                //open
                m.appendReplacement(sb, BoldOpen);
            } else {
                //close
                m.appendReplacement(sb, BoldClose);
            }
            count++;
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
