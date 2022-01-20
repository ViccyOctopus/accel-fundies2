//Designing methods for different classes
// Modeling a bookstore

class Book {
    String title;
    String author;
    int price; //in dollars

    //the constructor
    Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // TEMPLATE WILL GO HERE

    // METHOD DEFINITIONS WILL GO HERE


}

// What about the signature and purpose statement for when we used Racket?
//This is all going to be part of the METHOD DEFINITION

// For the purpose statement, we will be very caereful to use the pronoun this
//The type of value returned, also called return type
//The method name, where you use camelCase
//Argument list (parenthesized) with the type and name of each argument

//The signature for salePrice
// We know it needs a Book and an int sale rate, but that does not mean it needs two arguments.
// In Java, every METHOD has access to THIS (the pronoun)
//so we can have it look like:

// In Book, at the comment "METHOD DEFINITIONS will go here"
// Compute the sale price of this Book given using
// the given discount rate (as a percentage)

int salePrice(int discount) {
    return this.price - (this.price * discount) /100;
}

//the int before the salePrice is the RETURN TYPE
// This is a RETURN statement, and it goes in what is above.



//we will define a template just once in each class. 

// In Book, at the comment "TEMPLATE will go here"
/* TEMPLATE:
   Fields:
   ... this.title ...         -- String
   ... this.author ...        -- String
   ... this.price ...         -- int
*/


// SO, overall, this is what class Books will look like:

class Book2 {
    String title;
    String author;
    int price; //in dollars

    //the constructor
    Book2(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // In Book, at the comment "TEMPLATE will go here"
    /* TEMPLATE:
       Fields:
       ... this.title ...         -- String
       ... this.author ...        -- String
       ... this.price ...         -- int
    */

    int salePrice(int discount) {
        return this.price - (this.price * discount) /100;
    }

}


//Lets make some tests

// examples and tests for books
class ExamplesBooks {
    ExamplesBooks() {
        
    }
   
    // examples of books
    Book htdp = new Book("HtDP", "Felleisen et al.", 60);
    Book ror = new Book("Realm of Racket", "Bice et al.", 20);
   
    // test the method salePrice for the class Book
    boolean testSalePrice(Tester t) {
      return t.checkExpect(this.htdp.salePrice(30), 42)
          && t.checkExpect(this.ror.salePrice(20), 16);
    }
}

//this.htdp refers to the Book, the price is 60, so this.price is equal to 60.
//so why is boolean our tester type?
// because it checks if our test is true or false
//<3
// The && is for testing multiple checks

//WARNING: because its a boolean, if one fails, THEY ALL FAIL
// so even if it looks like "oh! only one failed!",
// THE OTHERS DIDNT EVEN GET A CHANCE TO RUN D:

//solution: write lots of tests <3

