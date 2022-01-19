//String is an object in Java, so it will not be considered a elementary data type.
//Data types that have a capitalized letter in them are assumed to be Objects,
// if they are not capitalized, they are assumed to be elementary.
// this means that you technically can make your own data types lowercase, but DO NOT DO THAT.

//The way that structs worked in Dr. Racket is the way classes work in Java.
// For example, you make a class to:

// to represent a book in a bookstore
class Book {
    String title;
    Author author;
    int price;
   
    // the constructor
    Book(String title, Author author, int price) {
      this.title = title;
      this.author = author;
      this.price = price;
    }
  }
   
  // to represent a author of a book in a bookstore
  class Author {
    String name;
    int yob;
   
    // the constructor
    Author(String name, int yob) {
      this.name = name;
      this.yob = yob;
    }
  }

// However, this begs the question: why do you have to write int twice if a variable
// has already been defined as an int? This will be covered later, the answer for now is that they are 
// not mutually exclusive.

//Class names are written in TitleCase, and field names are always written in camelCase.

//The this.something is used to show which object is being initizalized, this one,
// not some other one.
//        this.name = name;
//              ^       ^
//          refers to the name on line 29 where it says Author(String name, int yob)
//                      
//                  refers to the name defined on line 25.



// However, we have not yet defined any actual books or authors.
// Terminology Wise, we say we create INSTANCES of these classes
// and these instances are known as OBJECTS.

// lets build examples of Books:
// examples and tests for the classes that represent
// books and authors

class ExamplesBooks{
    ExamplesBooks() {

    }
   
    Author pat = new Author("Pat Conroy", 1948);
    Book beaches = new Book("Beaches", this.pat, 20);
  }

// the word "new" indicates that we are making new instances of the Book and Author classes.
// notice the use of "this." in the code.
//   it is used to indicate which ExamplesBooks object's pat field is being used to initialize beaches - this one.
// This is called field access.

// The general syntax is someObject.aField,
// where you "obtain the value of someObject, then obtain the value of its field named aField."


// We have to be careful when we contruct objects, there is an order.
//Suppose we wrote our examples this way, defining beaches before defining pat:

class ExamplesBooks2{
    ExamplesBooks2() {}
   
    // BAD IDEA!
    Book beaches = new Book("Beaches", this.pat, 20);
    Author pat = new Author("Pat Conroy", 1948);
    // CRASH
    String beachesAuthorName = this.beaches.author.name;
  }

//Because this.pat has not yet been defined and initialized, its value is null.
// Try to run this, it gives you NullPointerException.

//There are several problems with this:
//  - bad design to do a field of a field
//  - silly to initialize the field beachesAuthorName from some smaller part of beaches
//    because it would be easer to initialize beachesAuthorName first, then pat that, then beaches based on that.
