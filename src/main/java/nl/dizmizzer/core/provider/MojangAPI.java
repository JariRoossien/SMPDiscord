package nl.dizmizzer.core.provider;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MojangAPI {
    private final static String USERNAME_TO_UUID_ENDPOINT = "https://api.mojang.com/users/profiles/minecraft/";
    private final static String UUID_TO_USERNAME_ENDPOINT = "https://sessionserver.mojang.com/session/minecraft/profile/";

    public static UUID getUUID(String username) {
        try {
            // Create the URL object and set the required properties

            URL url = new URL(USERNAME_TO_UUID_ENDPOINT + username);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // If the response code is not 200 (OK), throw an exception
            if (conn.getResponseCode() != 200) {
                return null;
            }

            // Read the response and parse it as a Map using GSON
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String s;
            StringBuilder output = new StringBuilder();
            while ((s = br.readLine()) != null) {
                output.append(s.replace("\n", ""));
            }
            Map<String, String> json = new Gson().fromJson(output.toString(), Map.class);

            // Close the connection and return the Map
            conn.disconnect();
            return UUID.fromString(addHyphensToUUID(json.get("id")));
        } catch (Exception e) {
            // Print the error and return null
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername(String uuid) {
        try {
            // Create the URL object and set the required properties
            URL url = new URL(UUID_TO_USERNAME_ENDPOINT + uuid);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // If the response code is not 200 (OK), throw an exception
            if (conn.getResponseCode() != 200) {
                return null;
            }

            // Read the response and remove newline characters
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            String response = output.toString().replaceAll("\n", "");

            // Check if the response is a JSON object or array
            if (response.startsWith("{") && response.endsWith("}")) {
                // If it is an object, parse it as a Map using GSON
                Map<String, Object> json = new Gson().fromJson(response, Map.class);
                conn.disconnect();

                // Return the player's name
                return (String) json.get("name");
            } else if (response.startsWith("[") && response.endsWith("]")) {
                // If it is an array, parse it as a List using GSON
                List<Object> json = new Gson().fromJson(response, List.class);
                conn.disconnect();

                // Return the first element in the array (the player's name)
                return (String) json.get(0);
            } else {
                // Otherwise, throw an exception
                throw new RuntimeException("Invalid JSON response");
            }
        } catch (Exception e) {
            // Print the error and return null
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generated by OpenAI.
     * <p>
     * It parses UUIDs like "a375f7d40f8f4806b2301c7442c00e26" to
     * "a375f7d4-0f8f-4806-b230-1c7442c00e26".
     *
     * @param uuid UUID to parse.
     * @return String of valid uuid.
     */
    public static String addHyphensToUUID(String uuid) {
        // First, check if the input string is null or empty. If it is, we cannot
        // add hyphens to it, so we return an empty string.
        if (uuid == null || uuid.isEmpty()) {
            return null;
        }

        // Next, we check if the input string is the correct length for a UUID.
        // If it is not, we cannot add hyphens to it, so we return the input string
        // as is.
        if (uuid.length() != 32) {
            return null;
        }

        // Finally, we add hyphens to the UUID at the appropriate positions and
        // return the result.
        return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20);
    }
}
