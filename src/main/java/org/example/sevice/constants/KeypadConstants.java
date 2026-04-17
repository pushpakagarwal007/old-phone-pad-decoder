package org.example.sevice.constants;

import java.util.Map;

public class KeypadConstants {

    public static final Map<Character, String> KEYPAD_MAP = Map.of(
            '2', "ABC",
            '3', "DEF",
            '4', "GHI",
            '5', "JKL",
            '6', "MNO",
            '7', "PQRS",
            '8', "TUV",
            '9', "WXYZ",
            '0', " "
    );

    public static final char TERMINATE = '#';
    public static final char BACKSPACE = '*';
    public static final char PAUSE = ' ';
}
