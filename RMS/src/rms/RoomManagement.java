package rms;

import java.io.*;
import java.util.*;
import org.json.*;

public class RoomManagement {
    static Scanner input = new Scanner(System.in);
    private static String filePath = "src\\rms\\data.json";

    public static void main(String[] args) {
        int menu = 1;
        
        do {
            displayMenu();
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1:
                addSchedule();
                    break;
                case 2:
                updateSchedule();
                    break;
                case 3:
                showRoomSchedule();
                    break;
                case 4:
                deleteSchedule();
                    break;
                default:
                    menu = 0;
                    break;
            }
        } while (menu == 1);
    }

    public static void addSchedule() {
        System.out.println("\n== Add Schedule ==");
        String date = getInput("Enter Date: ");
        String room = getInput("Enter Room: ");
        String section = getInput("Enter Section: ");
        String time = getInput("Enter Time: ");

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

    public static void deleteSchedule() {
        System.out.println("\n== Delete Schedule ==");
        String date = getInput("Enter Date: ");
        String room = getInput("Enter Room: ");
        String section = getInput("Enter Section: ");

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

    public static void updateSchedule() {
        System.out.println("\n== Update Schedule ==");
        String date = getInput("Enter Date: ");
        String room = getInput("Enter Room: ");
        String section = getInput("Enter Section: ");
        String time = getInput("Enter Time: ");

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

    public static void showRoomSchedule() {
        boolean viewRoomInfo = false;
        System.out.println("\n== Show Schedule ==");
        String date = getInput("Enter Date: ");
        String room = getInput("Enter Room: ");
        String view = getInput("Is this for room info? ");

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

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    private static void displayMenu() {
        String[] mainMenu = {"\n1. Add Schedule",
                            "2. Edit Schedule",
                            "3. Show Schedule",
                            "4. Delete Schedule"
        };
        for (String string : mainMenu) {
            System.out.println(string);
        }
    }
}
