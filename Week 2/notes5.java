//Lets learn to represent lists

// Lets make a program that represents a list of books in a bookstore

/* 
               +--------------------------------+
               | ILoBook                        |<----------------------+
               +--------------------------------+                       |
               +--------------------------------+                       |
               | int count()                    |                       |
               | double salePrice(int discount) |                       |
               | ILoBook allBefore(int y)       |                       |
               | ILoBook sortByPrice()          |                       |
               +--------------------------------+                       |
                                |                                       |
                               / \                                      |
                               ---                                      |
                                |                                       |
                  -----------------------------                         |
                  |                           |                         |
+--------------------------------+   +--------------------------------+ |
| MtLoBook                       |   | ConsLoBook                     | |
+--------------------------------+   +--------------------------------+ |
+--------------------------------+ +-| Book first                     | |
| int count()                    | | | ILoBook rest                   |-+
| double salePrice(int discount) | | +--------------------------------+
| ILoBook allBefore(int y)       | | | int count()                    |
| ILoBook sortByPrice()          | | | double salePrice(int discount) |
+--------------------------------+ | | ILoBook allBefore(int y)       |
                                   | | ILoBook sortByPrice()          |
                                   | +--------------------------------+
                                   v
                   +--------------------------------+
                   | Book                           |
                   +--------------------------------+
                   | String title                   |
                   | String author                  |
                   | int year                       |
                   | double price                   |
                   +--------------------------------+
                   | double salePrice(int discount) |
                   +--------------------------------+

*/
//Lets make some examples

/*

//Books
Book htdp = new Book("HtDP", "MF", 2001, 60);
Book lpp = new Book("LPP", "STX", 1942, 25);
Book ll = new Book("LL", "FF", 1986, 10);
 
// lists of Books
ILoBook mtlist = new MtLoBook();
ILoBook lista = new ConsLoBook(this.lpp, this.mtlist);
ILoBook listb = new ConsLoBook(this.htdp, this.mtlist);
ILoBook listc = new ConsLoBook(this.lpp,
                new ConsLoBook(this.ll, this.listb));
ILoBook listd = new ConsLoBook(this.ll,
                  new ConsLoBook(this.lpp,
                    new ConsLoBook(this.htdp, this.mtlist)));



*/

//We want to, in our program:

// - Count how many books we have in this list of books

// - Compute the total sale price of all books in this list of books, at a given discount rate

// - Given a date (year) and this list of books, produce a list of all books in this list that were published before the given year

// - Produce a list of the same books as this list, but sorted by their price

interface ILoBook {
    // count the books in this list
    int count();
    
    // produce a list of all books published before the given date
    // from this list of books
    ILoBook allBefore(int year);
    
    // calculate the total sale price of all books in this list for a given discount
    double salePrice(int discount);
    
    // produce a list of all books in this list, sorted by their price
    ILoBook sortByPrice();
}

// Now, we have to define these methods in the class MtLoBook and ConsLoBook

class MtLoBook implements ILoBook {


    // count the books in this list
    public int count() { 
        return 0; }
    
    // produce a list of all books published before the given date
    // from this empty list of books
    public ILoBook allBefore(int year) { 
        return this; }
    
    // calculate the total sale price of all books in this list for a given discount
    public double salePrice(int discount) { 
        return 0; }

    //have to also put in sortByPrice, it is required because we implmented ILoBook

}

//Notice how the values produced by these methods are the base case values we have been in Dr.Racket
// for the empty lists.
//the count for an empty list is zero
// the sale price of no Books is zero too
// we will return to the sortByPrice method later

//there is more work to do in the ConsLoBook class!

class ConsLoBook implements ILoBook {


    /* 
    TEMPLATE: This is the template for ANY method in this class.
    ---------
    Fields:
    ... this.first ...                             -- Book
    ... this.rest ...                              -- ILoBook
    Methods:
    ... this.count() ...                           -- int
    ... this.salePrice(int discount) ...           -- double
    ... this.allBefore(int year) ...               -- ILoBook
    Methods for Fields:
    ... this.rest.count() ...                      -- int          // produces the count of all books in the rest of this list
    ... this.rest.salePrice(int discount) ...      -- double       // produces the total sale price of all books in the rest of this list
    ... this.rest.allBefore(int year) ...          -- ILoBook      // produces a list of all books in the rest of this list published before the given date

    */

    // count the books in this list
    public int count() {
        return 1 + this.rest.count(); 
    }

    // calculate the total sale price of all books in this list for the given discount
    // so, the method body just adds the value of the first book in the list
    // to the rest of the books in the list
    public double salePrice(int discount) {
        return this.first.salePrice(discount) + this.rest.salePrice(discount);
    }

    // produce a list of all books published before the given date
    // from this non-empty list of books
    ILoBook allBefore(int year) {
        if (this.first.publishedBefore(year)) {
            return new ConsLoBook(this.first, this.rest.allBefore(year));
        }
        else {
            return this.rest.allBefore(year);
        }
    }

}

//for this.rest.allBefore(int year) , all that needs to be done is figure out whether the first
// book of this list belongs in the output list, and either add it to the result or not.
// we cannot access this.first.year because we do not have access to fields of fields.
// so, we add a method to our wish list and delegate the job of deciding this question to the Book class itself

// NEW SYNTAX!!!!! IF STATEMENTS *shits myself*

// we're not done though
// we have a method remaining on our wish list, so we will add this new method that will fix the
// above problem of access the first year to the Book class

// was this book published before the given year?
boolean publishedBefore(int year) {
    return this.year < year;
  }

// it looks like this, lets put it in

class Book {
    String title;
    String author;           
    int year;      
    double price;

    // was this book published before the given year?
    boolean publishedBefore(int year) {
        return this.year < year;
  }

  // there also has to be the double salePrice here by the template at the very top
}


// We have to make examples, ultimately
// tests for the method count
boolean testCount(Tester t) {
    return
    t.checkExpect(this.mtlist.count(), 0) &&
    t.checkExpect(this.lista.count(), 1) &&
    t.checkExpect(this.listd.count(), 3);
  }
   
  // tests for the method salePrice
  boolean testSalePrice(Tester t) {
    return
    // no discount -- full price
    t.checkInexact(this.mtlist.salePrice(0), 0.0, 0.001) &&
    t.checkInexact(this.lista.salePrice(0), 10.0, 0.001) &&
    t.checkInexact(this.listc.salePrice(0), 95.0, 0.001) &&
    t.checkInexact(this.listd.salePrice(0), 95.0, 0.001) &&
    // 50% off sale -- half price
    t.checkInexact(this.mtlist.salePrice(50), 0.0, 0.001) &&
    t.checkInexact(this.lista.salePrice(50), 5.0, 0.001) &&
    t.checkInexact(this.listc.salePrice(50), 47.5, 0.001) &&
    t.checkInexact(this.listd.salePrice(50), 47.5, 0.001);
  }
   
  // tests for the method allBefore
  boolean testAllBefore(Tester t) {
    return
    t.checkExpect(this.mtlist.allBefore(2001), this.mtlist) &&
    t.checkExpect(this.lista.allBefore(2001), this.lista) &&
    t.checkExpect(this.listb.allBefore(2001), this.mtlist) &&
    t.checkExpect(this.listc.allBefore(2001),
       new ConsLoBook(this.lpp, new ConsLoBook(this.ll, this.mtlist))) &&
    t.checkExpect(this.listd.allBefore(2001),
       new ConsLoBook(this.ll, new ConsLoBook(this.lpp, this.mtlist)));
  }


  


// Next on the list: sorting

// the last method to design, which was define in the interface ILoBook, is:
// produce a list of all books in this list, sorted by their price
    ILoBook sortByPrice();

// An empty list is sorted already, so in the class MtLoBook the method becomes:

    // produce a list of all books in this list, sorted by their price
    public ILoBook sortByPrice() {
      return this;
    }

// We do not need to create a new empty list, this one works perfectly well.




    