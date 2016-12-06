package com.akshay.sectionrecycler;

import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {


    public static boolean isYesterday(long timestamp) {
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, -1); // yesterday

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(timestamp); // your date

        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isThisMonth(long timestamp) {
        Calendar c1 = Calendar.getInstance(); // today

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(timestamp); // your date

        return (!DateUtils.isToday(timestamp) && !isYesterday(timestamp))
                && (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
    }

    public static ArrayList<Message> stubMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Cheese"));
        messages.add(new Message("Milk", getYesterday()));
        messages.add(new Message("Dinner", getThisMonth()));
        messages.add(new Message("Pasta", getOlder()));
        return messages;
    }

    private static long getOlder() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.OCTOBER, 2);
        return c1.getTimeInMillis();
    }

    private static long getYesterday() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.NOVEMBER, 4);
        return c1.getTimeInMillis();
    }

    private static long getThisMonth() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.NOVEMBER, 2);
        return c1.getTimeInMillis();
    }

    public static List<SimpleSectionedRecyclerViewAdapter.Section> buildSections(ArrayList<Message> sCheeseStrings) {
        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();
        int todayPos = 0, yesterdayPos = 0, monthPos = 0, olderPos = 0;

        for (int i = 0; i < sCheeseStrings.size(); i++) {
            Message message = sCheeseStrings.get(i);
            switch (message.getSectionType()) {
                case Message.TODAY:
                    todayPos++;
                    break;
                case Message.YESTERDAY:
                    yesterdayPos++;
                    break;
                case Message.MONTH:
                    monthPos++;
                    break;
                case Message.OLDER:
                    olderPos++;
                    break;
            }
        }
        if (todayPos > 0)
            sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, "Today"));
        if (yesterdayPos > 0)
            sections.add(new SimpleSectionedRecyclerViewAdapter.Section(todayPos, "Yesterday"));
        if (monthPos > 0)
            sections.add(new SimpleSectionedRecyclerViewAdapter.Section(todayPos + yesterdayPos, "This Month"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(todayPos + yesterdayPos + monthPos, "Older"));
        return sections;
    }
}
