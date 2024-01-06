package source.backend;

import java.io.*;
import java.util.*;
import org.json.*;

public class RoomManagement {
    static Scanner input = new Scanner(System.in);
    private static String filePath = "source\\backend\\data.json";

    public static void main(String[] args) {
        displayMenu();
        System.out.println("Enter choice: ");
        addSchedule();
        deleteSchedule();
        showSchedule();
    }

    public static void addSchedule () {
        String date = getInput("Enter Date: ");
        String room = getInput("Enter Room: ");
        String section = getInput("Enter Section: ");
        String time = getInput("Enter Time: ");

        // Step 1: Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        // Step 2: Modify Data (Example: Add a new key-value pair)
        JSONObject scheduleData = existingData.getJSONObject("schedule");

        // scheduleData - List of dates
        // roomArray - List of rooms
        // roomInfo - List of schedules per room

        if (scheduleData.has(date)) { // Add room and schedule to date
            // Date already exists, update the information for that date
            JSONObject roomArray = scheduleData.getJSONObject(date);
            
            if (roomArray.has(room)){ // Add a schedule to room
                // Room already exists, update the information for that room
                JSONObject roomInfo = roomArray.getJSONObject(room);
                if (roomInfo.has(section)) {
                    // ?? Warning: You are trying to update an existing schedule.
                    System.out.println("You are trying to update an existing schedule");
                
                } else { // Add the schedule
                    roomInfo.put(section, time);
                }

            } else { // Create a room containing the schedule
                // Room doesn't exist, add a new entry for the room
                JSONObject roomInfo = new JSONObject();
                roomInfo.put(section, time);
                roomArray.put(room, roomInfo);
            }

        } else { // Create a date containing the room and schedule
            // Date doesn't exist, add a new entry for the date
            JSONObject roomArray = new JSONObject();
            JSONObject roomInfo = new JSONObject();
            roomInfo.put(section, time);
            roomArray.put(room, roomInfo);
            scheduleData.put(date, roomArray);
        }

        // Step 3: Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }
    
    public static void deleteSchedule () {
        String date = getInput("Enter Date: ");
        String room = getInput("Enter Room: ");
        String section = getInput("Enter Section: ");

        // Step 1: Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);

        // Step 2: Modify Data (Example: Add a new key-value pair)
        JSONObject scheduleData = existingData.getJSONObject("schedule");

        if (scheduleData.has(date)) { // Add room and schedule to date
            // Date already exists, update the information for that date
            JSONObject roomArray = scheduleData.getJSONObject(date);
            
            if (roomArray.has(room)){ // Add a schedule to room
                // Room already exists, update the information for that room
                JSONObject roomInfo = roomArray.getJSONObject(room);
                if (roomInfo.has(section)) {
                    roomInfo.remove(section);
                
                } else {
                    System.out.println("Entry does not exist");
                }
                
            } else { 
                // Room doesn't exist, error
                System.out.println("Entry does not exist");
            }
            
        } else { 
            // Date doesn't exist, error
            System.out.println("Entry does not exist");
        }

        // Step 3: Write Updated JSON to the Same File
        writeJsonToFile(existingData, filePath);
    }

    // public void editSchedule () {
        
    // }

    public static void showSchedule () {
        // Step 1: Read existing JSON data from the file
        JSONObject existingData = readJsonFromFile(filePath);
        
        // Step 2: Get data from current day

        // Step 3: Display values for each room
        for (String key : existingData.keySet()) {
                Object value = existingData.get(key);
                System.out.println("Key: " + key + ", Value: " + value);
        }
    }

    private static JSONObject readJsonFromFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            // Use try-with-resources to automatically close the FileReader
            JSONTokener tokener = new JSONTokener(fileReader);
            return new JSONObject(tokener);
        
        } catch (IOException e) {
            // Handle the case where the file doesn't exist or cannot be read
            e.printStackTrace();
            return new JSONObject();
        }
    }

    private static void writeJsonToFile(JSONObject jsonObject, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Use try-with-resources to automatically close the FileWriter
            fileWriter.write(jsonObject.toString(4));
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInput (String prompt) {    
        System.out.println(prompt);    
        return input.nextLine();
    }

    private static void displayMenu () {
        String[] mainMenu = {"1. Add Schedule",
                            "2. Edit Schedule",
                            "3. Show Schedule",
                            "4. Delete Schedule"
                        };
        for (String string : mainMenu) {
            System.out.println(string);
        }
    }
}
