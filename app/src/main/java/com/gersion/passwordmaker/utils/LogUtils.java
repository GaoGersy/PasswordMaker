
package com.gersion.passwordmaker.utils;

/**
 * ClassName:LogUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016-7-22改良版，能传入Object类型的数据 <br/>
 * 
 * @author Alpha
 * @version
 */
public final class LogUtils {
    /**
     * Log default tag.
     */
    private static String sTagDefault = "Gersy";

    /**
     * Log toggle for release, default value is false.
     */
    private static boolean sToggleRelease = false;

    /**
     * Log toggle for print Throwable info, default value is true.
     */
    private static boolean sToggleThrowable = true;

    /**
     * Log toggle for print thread name, default value is false.
     */
    private static boolean sToggleThread = false;

    /**
     * Log toggle for print class name and method name, default value is false.
     */
    private static boolean sToggleClassMethod = true;

    /**
     * Log toggle for print file name and code line number, default value is false.
     */
    private static boolean sToggleFileLineNumber = true;

    public static void e(String tag, String msg, Throwable e) {
        printLog(android.util.Log.ERROR, tag, msg, e);
    }

    public static void e(String msg, Throwable e) {
        printLog(android.util.Log.ERROR, null, msg, e);
    }

    public static void e(String msg) {
        printLog(android.util.Log.ERROR, null, msg, null);
    }

    /**
     * DESC : 如果传入的是Object 类型就加上一个空串 . <br/>
     * 
     * @param msg
     */
    public static void e(Object msg) {
        printLog(android.util.Log.ERROR, null, msg + "", null);
    }

    public static void w(String tag, String msg, Throwable e) {
        printLog(android.util.Log.WARN, tag, msg, e);
    }

    public static void w(String msg, Throwable e) {
        printLog(android.util.Log.WARN, null, msg, e);
    }

    public static void w(String msg) {
        printLog(android.util.Log.WARN, null, msg, null);
    }

    /**
     * DESC : 如果传入的是Object 类型就加上一个空串 . <br/>
     * 
     * @param msg
     */
    public static void w(Object msg) {
        printLog(android.util.Log.WARN, null, msg + "", null);
    }

    /**
     * DESC : 如果传入的是Object 类型就加上一个空串 . <br/>
     * 
     * @param msg
     */


    public static void i(String tag, String msg, Throwable e) {
        printLog(android.util.Log.INFO, tag, msg, e);
    }

    public static void i(String msg) {
        printLog(android.util.Log.INFO, null, msg, null);
    }

    /**
     * DESC : 如果传入的是Object 类型就加上一个空串 . <br/>
     * 
     * @param msg
     */
    public static void i(Object msg) {
        printLog(android.util.Log.INFO, null, msg + "", null);
    }

    public static void d(String tag, String msg, Throwable e) {
        printLog(android.util.Log.DEBUG, tag, msg, e);
    }

    public static void d(String msg, Throwable e) {
        printLog(android.util.Log.DEBUG, null, msg, e);
    }

    public static void d(String tag, String msg) {
        printLog(android.util.Log.DEBUG, tag, msg, null);
    }

    public static void d(String msg) {
        printLog(android.util.Log.DEBUG, null, msg, null);
    }

    /**
     * DESC : 如果传入的是Object 类型就加上一个空串 . <br/>
     * 
     * @param msg
     */
    public static void d(Object msg) {
        printLog(android.util.Log.DEBUG, null, msg + "", null);
    }

    public static void v(String tag, String msg, Throwable e) {
        printLog(android.util.Log.VERBOSE, tag, msg, e);
    }

    public static void v(String tag, String msg) {
        printLog(android.util.Log.VERBOSE, tag, msg, null);
    }

    public static void v(String msg) {
        printLog(android.util.Log.VERBOSE, null, msg, null);
    }

    /**
     * DESC : 如果传入的是Object 类型就加上一个空串 . <br/>
     * 
     * @param msg
     */
    public static void v(Object msg) {
        printLog(android.util.Log.VERBOSE, null, msg + "", null);
    }

    private static void printLog(int logType, String tag, String msg, Throwable e) {
        String tagStr = (tag == null) ? sTagDefault : tag;
        if (sToggleRelease) {
            if (logType < android.util.Log.INFO) {
                return;
            }
            String msgStr =
                    (e == null) ? msg : (msg + "\n" + android.util.Log.getStackTraceString(e));

            switch (logType) {
                case android.util.Log.ERROR:
                    android.util.Log.e(tagStr, msgStr);

                    break;
                case android.util.Log.WARN:
                    android.util.Log.w(tagStr, msgStr);

                    break;
                case android.util.Log.INFO:
                    android.util.Log.i(tagStr, msgStr);

                    break;
                default:
                    break;
            }

        } else {
            StringBuilder msgStr = new StringBuilder();

            if (sToggleThread || sToggleClassMethod || sToggleFileLineNumber) {
                Thread currentThread = Thread.currentThread();

                if (sToggleThread) {
                    msgStr.append("<");
                    msgStr.append(currentThread.getName());
                    msgStr.append("> ");
                }

                if (sToggleClassMethod) {
                    StackTraceElement ste = currentThread.getStackTrace()[4];
                    String className = ste.getClassName();
                    msgStr.append("[ 类名：");
                    msgStr.append(className == null ? null
                            : className.substring(className.lastIndexOf('.') + 1));
                    msgStr.append("  方法名：");
                    msgStr.append(ste.getMethodName());
                    msgStr.append(" ] ");
                }

                if (sToggleFileLineNumber) {
                    StackTraceElement ste = currentThread.getStackTrace()[4];
                    msgStr.append("[ 文件名:");
                    msgStr.append(ste.getFileName());
                    msgStr.append("  行数：");
                    msgStr.append(ste.getLineNumber());
                    msgStr.append(" ] ");
                }
            }

            msgStr.append(" 日志内容：");
            msgStr.append(msg);
            if (e != null && sToggleThrowable) {
                msgStr.append('\n');
                msgStr.append(android.util.Log.getStackTraceString(e));
            }

            switch (logType) {
                case android.util.Log.ERROR:
                    android.util.Log.e(tagStr, msgStr.toString());

                    break;
                case android.util.Log.WARN:
                    android.util.Log.w(tagStr, msgStr.toString());

                    break;
                case android.util.Log.INFO:
                    android.util.Log.i(tagStr, msgStr.toString());

                    break;
                case android.util.Log.DEBUG:
                    android.util.Log.d(tagStr, msgStr.toString());

                    break;
                case android.util.Log.VERBOSE:
                    android.util.Log.v(tagStr, msgStr.toString());

                    break;
                default:
                    break;
            }
        }
    }

}
