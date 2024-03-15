package rms;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.json.*;

public class RoomManagement {
    private static String filePath = "src\\rms\\data.json";

    public static void addSchedule(String date, String room, String section, String time, String selectedStartTime, String selectedEndTime) {

        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        // Modify Data: Add a new key-value pair
        JSONObject scheduleData = existingData.getJSONObject("schedule");

        if (scheduleData.has(date)) {
            JSONObject roomArray = scheduleData.getJSONObject(date);

            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);
                if (roomInfo.has(section)) {
                    JOptionPane.showMessageDialog(null, "You are trying to update an existing schedule","Error: Adding Schedule", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (isTimeRangeAvailable(roomInfo, room, selectedStartTime, selectedEndTime)) {
                        roomInfo.put(section, time);
                        JOptionPane.showMessageDialog(null, "Schedule was successfully added","Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Schedule for that time is already occupied","Error: Adding Schedule", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JSONObject roomInfo = new JSONObject();
                roomInfo.put(section, time);
                roomArray.put(room, roomInfo);
                JOptionPane.showMessageDialog(null, "Schedule was successfully added","Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JSONObject roomArray = new JSONObject();
            JSONObject roomInfo = new JSONObject();
            roomInfo.put(section, time);
            roomArray.put(room, roomInfo);
            scheduleData.put(date, roomArray);
            JOptionPane.showMessageDialog(null, "Schedule was successfully added","Successful", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }
    
    public static void deleteSchedule(String date, String room, String section) {
        
        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);
        
        JSONObject scheduleData = existingData.getJSONObject("schedule");
        
        if (scheduleData.has(date)) {
            JSONObject roomArray = scheduleData.getJSONObject(date);
            
            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);
                if (roomInfo.has(section)) {
                    JOptionPane.showMessageDialog(null, "Section was successfully removed","Successful", JOptionPane.INFORMATION_MESSAGE);
                    roomInfo.remove(section);
                } else {
                    JOptionPane.showMessageDialog(null, "Entry for section does not exist","Error: Deleting Schedule", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Entry for room does not exist","Error: Deleting Schedule", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entry for date does not exist","Error: Deleting Schedule", JOptionPane.ERROR_MESSAGE);
        }

        // Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }

    public static void updateSchedule(String date, String room, String section, String time, String selectedStartTime, String selectedEndTime) {

        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);
    
        // Modify Data: Update an existing key-value pair
        JSONObject scheduleData = existingData.getJSONObject("schedule");
    
        if (scheduleData.has(date)) {
            JSONObject roomArray = scheduleData.getJSONObject(date);
    
            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);
                
                // Check if the specified section exists
                if (roomInfo.has(section)) {
                    
                    // Remove the section temporarily for time range availability check
                    roomInfo.remove(section);
                    
                    if (isTimeRangeAvailable(roomInfo, room, selectedStartTime, selectedEndTime)) {
                        // If the time range is available, update the schedule
                        roomInfo.put(section, time);
                        JOptionPane.showMessageDialog(null, "Schedule was successfully updated", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // If the time range is not available, show an error message
                        JOptionPane.showMessageDialog(null, "Schedule for that time is already occupied", "Error: Updating Schedule", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    // Add the section back after availability check
                    roomInfo.put(section, time);
                    
                } else {
                    // Show an error message if the specified section does not exist
                    JOptionPane.showMessageDialog(null, "The specified section does not exist", "Error: Updating Schedule", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Show an error message if the specified room does not exist for the given date
                JOptionPane.showMessageDialog(null, "The specified room does not exist for the given date", "Error: Updating Schedule", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Show an error message if the specified date does not exist in the schedule
            JOptionPane.showMessageDialog(null, "The specified date does not exist in the schedule", "Error: Updating Schedule", JOptionPane.ERROR_MESSAGE);
        }
    
        // Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }

    public static DefaultListModel<String> updateSectionList(String room, String formattedDate, String view) {
        DefaultListModel<String> sectionListModel = new DefaultListModel<>();
        boolean viewRoomInfo = false;

        if (view.equalsIgnoreCase("yes")) {
            viewRoomInfo = true;
        }

        System.out.println(formattedDate);
        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        JSONObject scheduleData = existingData.getJSONObject("schedule");
        
        if (scheduleData.has(formattedDate)) {
            JSONObject roomArray = scheduleData.getJSONObject(formattedDate);
            
            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);
                
                // Display values for each room
                if (viewRoomInfo) {
                    for (String key : roomInfo.keySet()) {
                        Object value = roomInfo.get(key);
                        sectionListModel.addElement(key + ": " + value);
                    }
                } else {
                    for (String key : roomInfo.keySet()) {
                        sectionListModel.addElement(key);
                    }
                }

            } else {
                sectionListModel.clear();
            }

        } else {
            sectionListModel.clear();
        }

        for (int i = 0; i < sectionListModel.getSize(); i++) {
            System.out.println(sectionListModel.getElementAt(i));
        }

        return sectionListModel;
    }

    public static void updateSectionList(String room, String formattedDate, String view, JTextArea textArea) {
        boolean viewRoomInfo = false;

        if (view.equalsIgnoreCase("yes")) {
            viewRoomInfo = true;
        }

        System.out.println(formattedDate);
        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        JSONObject scheduleData = existingData.getJSONObject("schedule");

        if (scheduleData.has(formattedDate)) {
            JSONObject roomArray = scheduleData.getJSONObject(formattedDate);

            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);

                // Display values for each room
                StringBuilder resultText = new StringBuilder();
                if (viewRoomInfo) {
                    for (String key : roomInfo.keySet()) {
                        Object value = roomInfo.get(key);
                        resultText.append(key).append(": ").append(value).append("\n");
                    }
                } else {
                    for (String key : roomInfo.keySet()) {
                        resultText.append(key).append("\n");
                    }
                }

                // Set the text in the JTextArea
                textArea.setText(resultText.toString());

            } else {
                textArea.setText(""); // Clear the JTextArea if room is not found
            }

        } else {
            textArea.setText(""); // Clear the JTextArea if date is not found
        }
    }

    public static boolean isVacant(String date, String room) {
        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        System.out.println("is in isvacant method");

        // Check if the specified date and room exist in the schedule
        if (existingData.has("schedule")) {

            System.out.println("is in schedule");
            JSONObject scheduleData = existingData.getJSONObject("schedule");
            if (scheduleData.has(date)) {

                System.out.println("is in date");
                JSONObject roomArray = scheduleData.getJSONObject(date);
                if (roomArray.has(room)) {

                    System.out.println("is in room");
                    JSONObject roomInfo = roomArray.getJSONObject(room);

                    // Check for a 3-hour gap at the beginning of the day
                    String firstScheduleEndTime = roomInfo.getString(roomInfo.keys().next());
                    if (isThreeHourGap("07:00am", firstScheduleEndTime)) {
                        return true;
                    }

                    // Check for a 3-hour gap at the end of the day
                    String lastScheduleStartTime = roomInfo.getString(roomInfo.keys().next());
                    if (isThreeHourGap(lastScheduleStartTime, "07:00pm")) {
                        return true;
                    }

                    // Iterate over each schedule entry for the target room
                    java.util.Iterator<String> scheduleIterator = roomInfo.keys();
                    String previousTime = roomInfo.getString(scheduleIterator.next());

                    while (scheduleIterator.hasNext()) {
                        String currentTime = roomInfo.getString(scheduleIterator.next());

                        if (isThreeHourGap(previousTime.split("-")[1], currentTime.split("-")[0])) {
                            // There is a 3-hour gap between schedules
                            return true;
                        }

                        previousTime = currentTime;
                    }
                }
            }
        }

        return false; // Return false if date or room does not exist or no 3-hour gap found
    }

    private static boolean isThreeHourGap(String endTime, String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");

        try {
            // Parse start time and end time from the input strings
            LocalTime endLocalTime = LocalTime.parse(endTime, formatter);
            LocalTime startLocalTime = LocalTime.parse(startTime, formatter);

            // Check if the input strings contain "-"
            if (endTime.contains("-")) {
                String[] endTimeParts = endTime.split("-");
                endLocalTime = LocalTime.parse(endTimeParts[1].trim(), formatter);
            }

            if (startTime.contains("-")) {
                String[] startTimeParts = startTime.split("-");
                startLocalTime = LocalTime.parse(startTimeParts[0].trim(), formatter);
            }

            // Calculate the time difference in hours
            long timeDifference = startLocalTime.until(endLocalTime, ChronoUnit.HOURS);

            System.out.println(timeDifference);
            // Check if the time difference is at least 3 hours
            return timeDifference >= 3;
        } catch (Exception e) {
            // Handle parsing exception (e.g., invalid time format)
            e.printStackTrace();
            // Return false in case of parsing exception
            return false;
        }
    }

    private static boolean isTimeRangeAvailable(JSONObject roomData, String roomName, String selectedStartTime, String selectedEndTime) {
        // Parse the selected start and end times into Date objects for comparison
        Date startTime = parseTime(selectedStartTime);
        Date endTime = parseTime(selectedEndTime);
    
        // Invalid time range (end time is before start time)
        if (startTime.after(endTime)) {
            JOptionPane.showMessageDialog(null, "Schedule format is invalid", "Error: Input invalid", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if any time within the range is already taken
        for (String key : roomData.keySet()) {
            Object value = roomData.get(key);
            String[] parts = value.toString().split("-");
            
            Date existingStartTime = parseTime(parts[0].trim());
            Date existingEndTime = parseTime(parts[1].trim());
    
            if ((startTime.before(existingEndTime) && endTime.after(existingStartTime))) {
                // Time range is not available2
                return false;
            }
        }

        // Time range is available
        return true;
    }

    public static Date parseTime(String time) {
        try {
            return new SimpleDateFormat("hh:mmaa").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject readJsonFromFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            return new JSONObject(tokener);

        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    private static void writeJsonToFile(JSONObject jsonObject, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString(4));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}