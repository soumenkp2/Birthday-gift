package com.example.shoukhin.bgift;

import java.util.Calendar;

final class Constants {
    private static final int TARGET_MONTH = 11; // 0 based index
    private static final int TARGET_DATE = 29;
    private static final int TARGET_HOUR = 1; // 24 Hour Format
    private static final int TARGET_MINUTE = 0;
    private static final int TARGET_SECOND = 0;

    public static final String CHANNEL_ID = "100";

    static Calendar getTargetCalendar() {
        Calendar targetDateTime = Calendar.getInstance();
        targetDateTime.set(Calendar.DAY_OF_MONTH, Constants.TARGET_DATE);
        targetDateTime.set(Calendar.MONTH, Constants.TARGET_MONTH);
        targetDateTime.set(Calendar.HOUR_OF_DAY, Constants.TARGET_HOUR);
        targetDateTime.set(Calendar.MINUTE, Constants.TARGET_MINUTE);
        targetDateTime.set(Calendar.SECOND, Constants.TARGET_SECOND);
        return targetDateTime;
    }
}
