package voiceQuestions;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javazoom.jl.player.Player;

public class ReminderApp {
    private static final int POPUP_INTERVAL = 45 * 60 * 1000; // 45 minutes in milliseconds

    // Text-to-Speech Function
    public static void speak(String text) {
        System.setProperty("freetts.voices", 
            "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        Voice voice = VoiceManager.getInstance().getVoice("kevin16"); // Change "kevin" for different style

        if (voice != null) {
            voice.allocate();
            voice.setRate(140);  // Adjust speed (Default: 150)
            voice.setPitch(100);  // Adjust pitch (Default: 100)
            voice.setVolume(3.0f);  // Adjust volume (Default: 1.0)

            voice.speak(text);
            voice.deallocate();
        } else {
            System.out.println("Voice not found!");
        }
    }

    // Read & Ask Questions
    public static void askQuestion() {
        String filePath = "C:\\Users\\Admin\\Desktop\\questions.txt"; // Update the path

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lines.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The file is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Random random = new Random();
            while (true) {
                String randomQuestion = lines.get(random.nextInt(lines.size()));

                // Speak the question
                speak(randomQuestion);

                // UI Customization
                UIManager.put("OptionPane.background", new Color(44, 62, 80)); // Dark Blue-Grey
                UIManager.put("Panel.background", new Color(44, 62, 80));
                UIManager.put("OptionPane.messageForeground", Color.WHITE);
                UIManager.put("Button.background", new Color(52, 152, 219)); // Light Blue
                UIManager.put("Button.foreground", Color.BLACK);
                UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));

                // Display Question
                Object[] options = {"Yes", "No"};
                int response = JOptionPane.showOptionDialog(
                    null,  
                    "<html><h2 style='color:white; font-family:Arial;'>" + randomQuestion + "</h2></html>", 
                    "Question", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, options, options[0]
                );

                // Handle Response
                if (response == JOptionPane.YES_OPTION) {
                    String feedback = "Nice! You need to study more.";
//                    speak(feedback);
                    JOptionPane.showMessageDialog(null, "<html><h2 style='color:white;'>" + feedback + "</h2></html>");
//                    System.exit(0);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MP3 Player for Audio Reminders
    static class MP3Player {
        public static void playGreeting(String timeOfDay) {
            new Thread(() -> {
                try {
                    String audioFile = "C:\\Users\\Admin\\Desktop\\Qspiders\\QSpiders-Full-Stack-Java\\Robowaves_internship_Practice\\res\\greetings\\" + timeOfDay.toLowerCase() + ".mp3";
                    FileInputStream fis = new FileInputStream(audioFile);
                    Player player = new Player(fis);
                    player.play();
                    fis.close();
                } catch (Exception e) {
                    System.err.println("Error playing audio: " + e.getMessage());
                }
            }).start();
        }
    }

    // Show Time-Based Greetings
    private static void showTimeBasedGreeting() {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        String greeting;
        String emoji;
        String audioGreeting;

        if (hour >= 5 && hour < 12) {
            greeting = "Good morning, Sir! Have a nice day!";
            emoji = "🌅";
            audioGreeting = "morning";
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good afternoon, Sir! Hope you're having a great day!";
            emoji = "☀️";
            audioGreeting = "afternoon";
        } else if (hour >= 17 && hour < 22) {
            greeting = "Good evening, Sir! Hope you had a wonderful day!";
            emoji = "🌅";
            audioGreeting = "evening";
        } else {
            greeting = "You need to sleep, Sir! Have a restful night!";
            emoji = "🌙";
            audioGreeting = "night";
        }

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, greeting + " " + emoji, "Welcome", JOptionPane.INFORMATION_MESSAGE);
//            speak(greeting);
            MP3Player.playGreeting(audioGreeting);
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        showTimeBasedGreeting();
        askQuestion();

        // Set up reminder popup every 45 minutes
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "It's time to take a break, Sir! Relax and refresh yourself. 😊☕", "Reminder", JOptionPane.INFORMATION_MESSAGE);
                    speak("It's time to take a break, Sir! Relax and refresh yourself.");
                    MP3Player.playGreeting("reminder");
                });
            }
        }, POPUP_INTERVAL, POPUP_INTERVAL);
    }
}
