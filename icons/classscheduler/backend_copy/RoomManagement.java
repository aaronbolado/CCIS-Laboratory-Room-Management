package classscheduler.backend_copy;

import java.io.*;
import org.json.*;

public class RoomManagement {
    private static String filePath = "classscheduler\\backend_copy\\data.json";

    public static void addSchedule(String date, String room, String section, String time) {

        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        // Modify Data: Add a new key-value pair
        JSONObject scheduleData = existingData.getJSONObject("schedule");

        if (scheduleData.has(date)) {
            JSONObject roomArray = scheduleData.getJSONObject(date);

            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);
                if (roomInfo.has(section)) {
                    System.out.println("You are trying to update an existing schedule");
                } else {
                    roomInfo.put(section, time);
                }
            } else {
                JSONObject roomInfo = new JSONObject();
                roomInfo.put(section, time);
                roomArray.put(room, roomInfo);
            }
        } else {
            JSONObject roomArray = new JSONObject();
            JSONObject roomInfo = new JSONObject();
            roomInfo.put(section, time);
            roomArray.put(room, roomInfo);
            scheduleData.put(date, roomArray);
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
                    roomInfo.remove(section);
                } else {
                    System.out.println("Entry does not exist");
                }
            } else {
                System.out.println("Entry does not exist");
            }
        } else {
            System.out.println("Entry does not exist");
        }

        // Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }

    public static void updateSchedule(String date, String room, String section, String time) {

        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        JSONObject scheduleData = existingData.getJSONObject("schedule");

        if (scheduleData.has(date)) {
            JSONObject roomArray = scheduleData.getJSONObject(date);

            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);
                if (roomInfo.has(section)) {
                    roomInfo.put(section, time);
                } else {
                    System.out.println("You are trying to update an entry that does not exist");
                }
            } else {
                System.out.println("You are trying to update an entry that does not exist");
            }
        } else {
            System.out.println("You are trying to update an entry that does not exist");
        }

        // Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }

    public static void showRoomSchedule(String date, String room, String view) {
        boolean viewRoomInfo = false;

        if (view.equalsIgnoreCase("yes")) {
            viewRoomInfo = true;
        }

        // Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        JSONObject scheduleData = existingData.getJSONObject("schedule");

        if (scheduleData.has(date)) {
            JSONObject roomArray = scheduleData.getJSONObject(date);

            if (roomArray.has(room)) {
                JSONObject roomInfo = roomArray.getJSONObject(room);

                // Display values for each room
                if (viewRoomInfo) {
                    for (String key : roomInfo.keySet()) {
                        Object value = roomInfo.get(key);
                        System.out.println("Class: " + key + " Time: " + value);
                    }
                } else {
                    for (String key : roomInfo.keySet()) {
                        System.out.println("Class: " + key);
                    }
                }

            } else {
                System.out.println("Entry does not exist");
            }

        } else {
            System.out.println("Entry does not exist");
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
