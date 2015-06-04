package com.example.gohorse.pokefight.model;

import com.example.gohorse.pokefight.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 04/06/2015.
 */
public class Person {
    public String name;
    public String age;
//    int photoId;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
//        this.photoId = photoId;
    }


    private List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old"));
        persons.add(new Person("Lavery Maiss", "25 years old"));
        persons.add(new Person("Lillie Watts", "35 years old"));
    }
}