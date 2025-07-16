package org.example;

import org.example.Library;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        Library libdemo = new Library();
        libdemo.someLibraryMethod();
    }
}
