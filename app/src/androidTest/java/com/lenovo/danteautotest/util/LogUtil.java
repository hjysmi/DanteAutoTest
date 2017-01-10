package com.lenovo.danteautotest.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/1/10.
 */

public class LogUtil {

    /**
     * 打印log
     * @param str
     */
    public static void d(String str) {
        // 取得当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String datestr = calendar.get(Calendar.YEAR) + ":" +(calendar.get(Calendar.MONTH)+1) + ":" +calendar.get(Calendar.DATE) + ":" +calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + calendar.get(Calendar.MILLISECOND) + ":";

        FileWriter fwlog = null;
        StringBuffer log_data = new StringBuffer("/mnt/sdcard/log_");
        log_data.append(datestr);
        log_data.append(".txt");
        try {
            fwlog = new FileWriter(log_data.toString(), true);
            fwlog.write(datestr + str + "\r\n");
            System.out.println(datestr + str);
            fwlog.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fwlog) {
                    fwlog.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
