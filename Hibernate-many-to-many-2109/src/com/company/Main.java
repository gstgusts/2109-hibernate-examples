package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	    var repo = new LibraryRepository();

//	    var author = new Author(1,"Arthur","Weiss");
//	    var author2 = new Author(0, "Jānis", "Rainis");
//
//	    Set<Author> authors = new HashSet<>();
//	    authors.add(author);
//        authors.add(author2);
//
//	    var book = new Book(1, "War of worlds", "1111", authors);
//
//	    repo.add(book);

	    var author3 = new Author(0, "Pēteris", "Pirmais");

	    repo.add(author3);
    }
}
