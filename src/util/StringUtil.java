package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {

    public static void main(String[] args) {
        System.out.println(StringUtil.BuildRow(new String[]{"Hello", "Kapoor, Vaibhav", "What's goin' on?"}, ","));
    }

    public static Map<String, String> rowToJavaMap(String header, String row, String delim) {
        Map<String, String> map = new HashMap<String, String>();
        String key, value;

        String[] headerparts = StringUtil.SplitRow(header, delim);
        String[] rowparts = StringUtil.SplitRow(row, delim);

        for (int i = 0; i < headerparts.length; i++) {
            key = headerparts[i];

            if (i < rowparts.length) {
                value = rowparts[i];
            } else {
                value = "";
            }

            if (key.equalsIgnoreCase("CUSIP")) {
                value = value.replaceAll("\"", "").replaceAll("'", "");
            }
            map.put(key, value);
        }

        return map;
    }

    public static String[] SplitRow(String row, String delim) {
        String[] raw = row.split(delim, -1);
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        Boolean buildingSubElement = false;
        for (String element : raw) {
            // strip elements of "unnecessary" surrounding quotes
            if (element.startsWith("\"") && element.endsWith("\"")) {
                element = element.length() > 2 ? element.substring(1, element.length() - 1) : "";
            }

            int indexOfQuote = element.indexOf("\"");
            if (!buildingSubElement) {
                if (indexOfQuote < 0) {
                    result.add(element);
                } else {
                    sb.append(element.substring(1));
                    buildingSubElement = true;
                }
            } else {
                if (indexOfQuote < 0) {
                    sb.append(delim);
                    sb.append(element);
                } else {
                    sb.append(delim);
                    sb.append(element.length() > 0 ? element.substring(0, element.length() - 1) : "");
                    buildingSubElement = false;
                    result.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static String BuildRow(String[] row, String delim) {
        StringBuilder sb = new StringBuilder();
        for (String element : row) {
            if (element.indexOf(delim) < 0) {
                sb.append(element);
                sb.append(delim);
            } else {
                sb.append("\"");
                sb.append(element);
                sb.append("\"");
                sb.append(delim);
            }
        }

        if (row.length > 0) {
            // remove the last occurence of the delim
            sb.replace(sb.length() - delim.length(), sb.length(), "");
        }
        return sb.toString();
    }
}
