package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Main {
    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";

    private static final String[] SCOPES = { MESSAGING_SCOPE };

    private static String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("path-to-json-file.json"))
                .createScoped(Arrays.asList(SCOPES));
        googleCredentials.refresh();
        return googleCredentials.getAccessToken().getTokenValue();
    }
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        System.out.println("Access token=" + getAccessToken());
        String contentType = "application/json";


        String MissedCallBody = "{\n"
                + "  \"message\":{\n"
                + "    \"token\":\"cMR4hor3Tf-P6fQj_vAevB:APA91bE0YOJi2WJ503MQ0AU2dT5comC2k2W7wRuisY8cyZEdmFtAavAbddPEpo18-JFAm0QXeTqVzOQUKP47VtAIR5W6JtAtwyHpVhbQSQmi93MgqWMAHEE4GqKdsNfGYzbJdRCQgIUw\",\n"
                + "    \"android\":{\n"
                + "      \"priority\":\"high\",\n"
                + "      \"collapseKey\":\"missedcall\", \n"
                + "      \"ttl\":\"86400s\"\n"
                + "    },\n"
                + "     \"data\" : {\n"
                + "      \"MsgCompleted\":\"Y\",\"Type\":\"MissedCall\",\"Text\":\"Missed call from test\",\"From\":\"+18645489014\",\"TrackId\":\"311431\"\n"
                + "    }\n"
                + "  }\n"
                + "}";
        String incomingCallBody = "{\n"
                + "  \"message\":{\n"
                + "    \"token\":\"cMR4hor3Tf-P6fQj_vAevB:APA91bE0YOJi2WJ503MQ0AU2dT5comC2k2W7wRuisY8cyZEdmFtAavAbddPEpo18-JFAm0QXeTqVzOQUKP47VtAIR5W6JtAtwyHpVhbQSQmi93MgqWMAHEE4GqKdsNfGYzbJdRCQgIUw\",\n"
                + "    \"android\":{\n"
                + "      \"priority\":\"high\",\n"
                + "      \"collapseKey\":\"call\", \n"
                + "      \"ttl\":\"86400s\"\n"
                + "    },\n"
                + "     \"data\" : {\n"
                + "      \"MsgCompleted\":\"Y\",\"Call-id\":\"\",\"Type\":\"IncomingCall\",\"Text\":\"\",\"From\":\"0v_dynxeeeagmag8kg99\",\"TrackId\":\"133564\"\n"
                + "    }\n"
                + "  }\n"
                + "}\n"
                + "";
        String incomingCallBody2 = "{\n" +
                "  \"message\": {\n" +
                "    \"data\": {\n" +
                "      \"message\": \"MsgCompleted: Y, Type: MissedCall, Text: Missed call from test, From: +14245234602, TrackId: 311513\"\n" +
                "    },\n" +
                "    \"android\": {\n" +
                "      \"collapse_key\": \"missedcall\",\n" +
                "      \"priority\": \"high\",\n" +
                "      \"ttl\": \"86400s\"\n" +
                "    },\n" +
                "    \"token\": \"cMR4hor3Tf-P6fQj_vAevB:APA91bE0YOJi2WJ503MQ0AU2dT5comC2k2W7wRuisY8cyZEdmFtAavAbddPEpo18-JFAm0QXeTqVzOQUKP47VtAIR5W6JtAtwyHpVhbQSQmi93MgqWMAHEE4GqKdsNfGYzbJdRCQgIUw\"\n" +
                "  }\n" +
                "}";

        String incomingCallBody3 = "{\n" +
                "  \"message\": {\n" +
                "    \"data\": {\n" +
                "      \"message\": \"{\\\"MsgCompleted\\\":\\\"Y\\\",\\\"Type\\\":\\\"IncomingCall\\\",\\\"Text\\\":\\\"Missed call from test\\\",\\\"From\\\":\\\"+14245234602\\\",\\\"TrackId\\\":\\\"311517\\\"}\"\n" +
                "    },\n" +
                "    \"android\": {\n" +
                "      \"collapse_key\": \"call\",\n" +
                "      \"priority\": \"high\",\n" +
                "      \"ttl\": \"86400s\"\n" +
                "    },\n" +
                "    \"token\": \"cMR4hor3Tf-P6fQj_vAevB:APA91bE0YOJi2WJ503MQ0AU2dT5comC2k2W7wRuisY8cyZEdmFtAavAbddPEpo18-JFAm0QXeTqVzOQUKP47VtAIR5W6JtAtwyHpVhbQSQmi93MgqWMAHEE4GqKdsNfGYzbJdRCQgIUw\"\n" +
                "  }\n" +
                "}";

        String url = "https://fcm.googleapis.com/v1/projects/713878155596/messages:send";
        post(url, contentType, incomingCallBody3);

        //sendUsingFCMSDK();
    }


    private static void sendUsingFCMSDK() throws IOException {
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "cMR4hor3Tf-P6fQj_vAevB:APA91bE0YOJi2WJ503MQ0AU2dT5comC2k2W7wRuisY8cyZEdmFtAavAbddPEpo18-JFAm0QXeTqVzOQUKP47VtAIR5W6JtAtwyHpVhbQSQmi93MgqWMAHEE4GqKdsNfGYzbJdRCQgIUw";

        FileInputStream refreshToken = new FileInputStream("/Users/firebase-adminsdk-g1rsz-456f63d197.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                //.setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("$GOOG up 1.43% on the day")
                        .setBody("$GOOG gained 11.80 points to close at 835.67, up 1.43% on the day.")
                        .build())
//                .putData("score", "850")
//                .putData("time", "2:45")
                .setToken(registrationToken)
                .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder().setTitle("test").setBody("hi").build()).build())
                .setAndroidConfig(AndroidConfig.builder().setNotification(AndroidNotification.builder().setTitle("aa").setBody("fff").build()).build())
                .setFcmOptions(FcmOptions.builder().setAnalyticsLabel("dfdf").build())
                .build();

        Message message2 = Message.builder()
                .putData("data","aaaaaaa")
                .setFcmOptions(FcmOptions.withAnalyticsLabel("MyLabel"))
                .setToken(registrationToken)
                .build();

        try {
            // Send a message to the device corresponding to the provided
            // registration token.
            String response = FirebaseMessaging.getInstance().send(message2);
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }


    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected static HttpURLConnection post(String url, String contentType, String body) throws IOException {

        System.out.println("FCM POST url=" + url);
        HttpURLConnection conn;
        if (url == null || body == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }

        System.out.println("Push body=" + body);
        System.out.println("Access token=" + getAccessToken());
        String accessToken = "Bearer " + getAccessToken();
        byte[] bytes = body.getBytes();
        conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setFixedLengthStreamingMode(bytes.length);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestProperty("Authorization", accessToken);
        OutputStream out = conn.getOutputStream();
        try {
            out.write(bytes);
        } finally {
            out.close();
        }
        int status = conn.getResponseCode();
        System.out.println("Status code=" + status);
        if (status != 200) {
            try {
                String responseBody = getAndClose(conn.getErrorStream());
                System.out.println("Plain post error response: " + responseBody);
            } catch (IOException e) {

            }

        }
        return conn;
    }

    private static String getAndClose(InputStream stream) throws IOException {
        try {
            String string = getString(stream);
            return string;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    protected static String getString(InputStream stream) throws IOException {
        String newLine;
        if (stream == null) {
            return "";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder content = new StringBuilder();
        do {
            if ((newLine = reader.readLine()) == null)
                continue;
            content.append(newLine).append('\n');
        } while (newLine != null);
        if (content.length() > 0) {
            content.setLength(content.length() - 1);
        }
        return content.toString();
    }
}