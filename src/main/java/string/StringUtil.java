package string;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * 字符串工具类
 * @author liuht
 * @version 1.0
 * @date 2020/4/7
 */
public class StringUtil implements Serializable {

        /***/
        private static final long serialVersionUID = 1L;
        public static final String DEFAULT_CHART = StandardCharsets.UTF_8.name();

        /**
         * 将时间字符串转化为Long型数字
         * @param time HH:mm
         * @return
         */
        public static Long timeStrToLong(String time){
            String str=time.replaceAll(":", "");
            return toLong(str);
        }

        /**
         * 过滤空NULL
         * @param o
         * @return
         */
        public static String filterNull(Object o) {
            return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "" ;
        }

        /**
         * 是否为空
         * @param o
         * @return
         */
        public static boolean isEmpty(Object o) {
            if (o == null) {
                return true;
            }
            if ("".equals(filterNull(o.toString()))) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 是否不为空
         * @param o
         * @return
         */
        public static boolean isNotEmpty(Object o) {
            if (o == null) {
                return false;
            }
            if ("".equals(filterNull(o.toString()))) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * 是否可转化为数字
         * @param o
         * @return
         */
        public static boolean isNumber(Object o) {
            try {
                new BigDecimal(o.toString());
                return true;
            } catch (Exception e) {
            }
            return false;
        }

        /**
         * 是否可转化为Long型数字
         * @param o
         * @return
         */
        public static boolean isLong(Object o) {
            try {
                new Long(o.toString());
                return true;
            } catch (Exception e) {
            }
            return false;
        }

        /**
         * 转化为Long型数字, 不可转化时返回0
         * @param o
         * @return
         */
        public static Long toLong(Object o) {
            if (isLong(o)) {
                return new Long(o.toString());
            } else {
                return 0L;
            }
        }

        /**
         * 转化为int型数字, 不可转化时返回0
         * @param o
         * @return
         */
        public static int toInt(Object o) {
            if (isNumber(o)) {
                return new Integer(o.toString());
            } else {
                return 0;
            }
        }
        /**
         * 替换字符串,支持字符串为空的情形
         * @param strData
         * @param regex
         * @param replacement
         * @return
         */
        public static String replace(String strData, String regex, String replacement) {
            return strData == null ? "" : strData.replaceAll(regex, replacement);
        }

        /**
         * 字符串转为HTML显示字符
         * @param strData
         * @return
         */
        public static String string2HTML(String strData){
            if( strData == null || "".equals(strData) ){
                return "" ;
            }
            strData = replace(strData, "&", "&amp;");
            strData = replace(strData, "<", "&lt;");
            strData = replace(strData, ">", "&gt;");
            strData = replace(strData, "\"", "&quot;");
            return strData;
        }
        /**
         * 从指定位置截取指定长度的字符串
         * @param
         * @return
         */
        public static String getMiddleString(String input, int index, int count) {
            if (isEmpty(input)) {
                return "";
            }
            count = (count > input.length() - index + 1) ? input.length() - index + 1 : count;
            return input.substring(index - 1, index + count - 1);
        }

        /**
         * 将"/"替换成"\"
         * @param strDir
         * @return
         */
        public static String changeDirection(String strDir) {
            String s = "/";
            String a = "\\";
            if (strDir != null && !" ".equals(strDir)) {
                if (strDir.contains(s)) {
                    strDir = strDir.replace(s, a);
                }
            }
            return strDir;
        }

        /**
         * 去除字符串中 头和尾的空格，中间的空格保留
         *
         * @Title: trim
         * @Description: TODO
         * @return String
         * @throws
         */
        public static String trim(String s) {
            int i = s.length();// 字符串最后一个字符的位置
            int j = 0;// 字符串第一个字符
            int k = 0;// 中间变量
            char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
            while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
                ++j;// 确定字符串前面的空格数
            while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
                --i;// 确定字符串后面的空格数
            return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
        }

        /**
         * 得到大括号中的内容
         * @param str
         * @return
         */
        public static String getBrackets(String str) {
            int a = str.indexOf("{");
            int c = str.indexOf("}");
            if (a >= 0 && c >= 0 & c > a) {
                return (str.substring(a + 1, c));
            } else {
                return str;
            }
        }

        /**
         * 去掉字符串中、前、后的空格
         */
        public static String extractBlank(String name) {
            if (name != null && !"".equals(name)) {
                return name.replaceAll(" +", "");
            } else {
                return name;
            }
        }

        /**
         * 将null换成""
         * @param str
         * @return
         */
        public static String convertStr(String str) {
            return str != null && !"null".equals(str) ? str.trim() : "";
        }

        /**
         * 字符串转换unicode
         */
        public static String string2Unicode(String string) {
            StringBuffer unicode = new StringBuffer();
            for (int i = 0; i < string.length(); i++) {
                // 取出每一个字符
                char c = string.charAt(i);
                // 转换为unicode
                unicode.append("\\u" + Integer.toHexString(c));
            }
            return unicode.toString();
        }
        /**
         * unicode 转字符串
         */
        public static String unicode2String(String unicode) {
            StringBuffer string = new StringBuffer();
            String[] hex = unicode.split("\\\\u");
            for (int i = 1; i < hex.length; i++) {
                // 转换出每一个代码点
                int data = Integer.parseInt(hex[i], 16);
                // 追加成string
                string.append((char) data);
            }
            return string.toString();
        }


        /**
         * 以指定的分隔符来进行字符串元素连接
         * <p>
         * 例如有字符串数组array和连接符为逗号(,)
         * <code>
         * String[] array = new String[] { "hello", "world", "qiniu", "cloud","storage" };
         * </code>
         * 那么得到的结果是:
         * <code>
         * hello,world,qiniu,cloud,storage
         * </code>
         * </p>
         *
         * @param array  需要连接的对象数组
         * @param sep    元素连接之间的分隔符
         * @param prefix 前缀字符串
         * @return 连接好的新字符串
         */
        public static String join(Object[] array, String sep, String prefix) {
            if (array == null) {
                return "";
            }
            int arraySize = array.length;
            if (arraySize == 0) {
                return "";
            }
            if (sep == null) {
                sep = "";
            }
            if (prefix == null) {
                prefix = "";
            }
            StringBuilder buf = new StringBuilder(prefix);
            for (int i = 0; i < arraySize; i++) {
                if (i > 0) {
                    buf.append(sep);
                }
                buf.append(array[i] == null ? "" : array[i]);
            }
            return buf.toString();
        }

        public static boolean isNullOrEmpty(String s) {
            return s == null || "".equals(s);
        }

        public static boolean isBlank(String value) {
            int strLen;
            if (value == null || (strLen = value.length()) == 0) {
                return true;
            }
            for (int i = 0; i < strLen; i++) {
                if ((Character.isWhitespace(value.charAt(i)) == false)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isBlankLoop(String ...values) {
            int strLen;
            if (values == null || (strLen = values.length) == 0) {
                return true;
            }
            for (String value: values) {
                if (value == null || (strLen = value.length()) == 0) {
                    continue;
                }
                for (int i = 0; i < strLen; i++) {
                    if ((Character.isWhitespace(value.charAt(i)) == false)) {
                        return false;
                    }
                }
            }
            return true;
        }
}
