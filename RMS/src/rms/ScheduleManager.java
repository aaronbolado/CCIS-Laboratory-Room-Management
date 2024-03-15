package rms;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

import org.json.JSONObject;

public interface ScheduleManager {
    void addSchedule(String date, String room, String section, String time, String selectedStartTime, String selectedEndTime);
    void deleteSchedule(String date, String room, String section);
    void updateSchedule(String date, String room, String section, String time, String selectedStartTime, String selectedEndTime);
    DefaultListModel<String> updateSectionList(String room, String formattedDate, String view);
    void updateSectionList(String room, String formattedDate, String view, JTextArea textArea);
    boolean isVacant(String date, String room);
    boolean isTimeRangeAvailable(JSONObject roomData, String roomName, String selectedStartTime, String selectedEndTime);
    java.util.Date parseTime (String time);
}