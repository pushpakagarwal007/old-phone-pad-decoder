package org.example.sevice;

import org.example.sevice.constants.KeypadConstants;
import org.example.sevice.exception.InvalidInputException;

public class OldPhonePadService {

    /**
     * Converts keypad input string into readable text.
     * Supports:
     * - Multi-press keypad logic
     * - Pause using space
     * - Backspace using '*'
     * - Termination using '#'
     */
    public String convert(String input) {

        validateInput(input);

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < input.length()) {
            char current = input.charAt(i);

            if (current == KeypadConstants.TERMINATE) {
                break;
            }

            if (current == KeypadConstants.BACKSPACE) {
                handleBackspace(result);
                i++;
                continue;
            }

            if (current == KeypadConstants.PAUSE) {
                i++;
                continue;
            }

            int count = countConsecutive(input, i, current);
            i += count;

            appendCharacter(result, current, count);
        }

        return result.toString();
    }

    // ================= HELPER METHODS =================

    private void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidInputException("Input cannot be null or empty");
        }
    }

    private void handleBackspace(StringBuilder result) {
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
    }

    private int countConsecutive(String input, int index, char digit) {
        int count = 0;

        while (index < input.length() && input.charAt(index) == digit) {
            count++;
            index++;
        }

        return count;
    }

    private void appendCharacter(StringBuilder result, char digit, int count) {
        String letters = KeypadConstants.KEYPAD_MAP.get(digit);

        if (letters == null) {
            // Ignore unsupported characters like '1' or alphabets
            return;
        }

        char selected = letters.charAt((count - 1) % letters.length());
        result.append(selected);
    }
}
