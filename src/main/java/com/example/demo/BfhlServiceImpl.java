package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class BfhlServiceImpl implements BfhlService {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^-?\\d+$");

    @Override
    public BfhlResponse process(BfhlRequest request) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId("john_doe_17091999");
        response.setEmail("john@xyz.com");
        response.setRollNumber("ABCD123");
        response.setOddNumbers(new ArrayList<>());
        response.setEvenNumbers(new ArrayList<>());
        response.setAlphabets(new ArrayList<>());
        response.setSpecialCharacters(new ArrayList<>());
        response.setSum("0");
        response.setConcatString("");

        if (request == null || request.getData() == null) {
            return response;
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int sum = 0;
        List<Character> concatCharacters = new ArrayList<>();

        for (String item : request.getData()) {
            if (item == null) {
                continue;
            }

            String value = item.trim();
            if (value.isEmpty()) {
                continue;
            }

            if (isNumeric(value)) {
                int numericValue = Integer.parseInt(value);
                sum += numericValue;
                if (numericValue % 2 == 0) {
                    evenNumbers.add(value);
                } else {
                    oddNumbers.add(value);
                }
            } else if (containsOnlyLetters(value)) {
                String uppercaseValue = value.toUpperCase(Locale.ROOT);
                alphabets.add(uppercaseValue);
                for (char ch : uppercaseValue.toCharArray()) {
                    concatCharacters.add(ch);
                }
            } else {
                specialCharacters.add(value);
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcatString(buildConcatString(concatCharacters));
        return response;
    }

    private boolean isNumeric(String value) {
        return NUMERIC_PATTERN.matcher(value).matches();
    }

    private boolean containsOnlyLetters(String value) {
        return !value.isEmpty() && value.chars().allMatch(Character::isLetter);
    }

    private String buildConcatString(List<Character> characters) {
        StringBuilder builder = new StringBuilder();
        for (int index = characters.size() - 1; index >= 0; index--) {
            char ch = characters.get(index);
            boolean uppercase = (characters.size() - 1 - index) % 2 == 0;
            builder.append(uppercase ? Character.toUpperCase(ch) : Character.toLowerCase(ch));
        }
        return builder.toString();
    }
}
