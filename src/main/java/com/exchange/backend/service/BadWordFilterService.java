package com.exchange.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by greenlucky on 3/14/17.
 */
@Service
public class BadWordFilterService implements ApplicationListener<ApplicationReadyEvent> {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BadWordFilterService.class);
    private static int largestWordLength = 0;
    private static Map<String, String[]> words = new HashMap<String, String[]>();

    @Override
     public void onApplicationEvent(final ApplicationReadyEvent event) {
        loadConfigs();
    }

    public static void loadConfigs() {
        try {

            ClassPathResource cpr = new ClassPathResource("config/Word_Filter - Sheet1.csv");

            BufferedReader reader = new BufferedReader(new InputStreamReader(cpr.getInputStream()));

            String line = "";
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] content = null;
                    content = line.split(",");
                    if (content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    String[] ignoreInCombinationWithWords = new String[]{};
                    if (content.length > 1) {
                        ignoreInCombinationWithWords = content[1].split("_");
                    }

                    if (word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }
                    words.put(word.replaceAll(" ", ""), ignoreInCombinationWithWords);
            }
            System.out.println("Loaded " + counter + " words to filter out");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Iterates over a String input and checks whether a cuss word was found in a list.
     * Then checks if the word should be ignored (e.g. bass contains the word *ss).
     * @param input
     * @return
     */
    public static ArrayList<String> badWordsFound(String input) {
        if (input == null) {
            return new ArrayList<>();
        }
        // remove leetspeak
        input = input.replaceAll("1", "i");
        input = input.replaceAll("!", "i");
        input = input.replaceAll("3", "e");
        input = input.replaceAll("4", "a");
        input = input.replaceAll("@", "a");
        input = input.replaceAll("5", "s");
        input = input.replaceAll("7", "t");
        input = input.replaceAll("0", "o");
        input = input.replaceAll("9", "g");

        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

        // iterate over each letter in the word
        for (int start = 0; start < input.length(); start++) {
            // from each letter, keep going to find bad words until either
            // the end of the sentence is reached, or the max word length is reached.
            for (int offset = 1; offset < (input.length() + 1 - start) && offset < largestWordLength; offset++) {
                String wordToCheck = input.substring(start, start + offset);
                if (words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for (int s = 0; s < ignoreCheck.length; s++) {
                        if (input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if (!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }

        return badWords;

    }

    public static String filterText(String input) {
        ArrayList<String> badWords = badWordsFound(input);
        if (badWords.size() > 0) {
            return badWords.toString();
        }
        return null;
    }

}
