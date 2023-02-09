package ru.itis.aisd.lab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String str = read("transport.json");
        System.out.println(str);

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); ++i) {
            switch (str.charAt(i)) {
                case '{':
                    if (!stack.isEmpty() && stack.peek() == '"')
                        break;
                    stack.push(str.charAt(i));
                    break;
                case '[':
                    if (!stack.isEmpty() && stack.peek() == '"')
                        break;
                    stack.push(str.charAt(i));
                    break;
                case '"':
                    if (!stack.isEmpty() && stack.peek() == '"') {
                        stack.pop();
                    } else {
                        stack.push(str.charAt(i));
                    }
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '"')
                        break;
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        System.out.println("Problem in " + i);
                        return;
                    }
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '"')
                        break;
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        System.out.println("Problem in " + i);
                        return;
                    }
                    break;
            }
        }
    }

    public static String read(String fileName) {
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
