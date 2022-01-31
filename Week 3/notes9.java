//Abstract class and inheritance
// Shape

interface IShape {
    double area();
    double distTo();
    IShape grow(int inc);
    boolean biggerThan(IShape that);
}

abstract class AShape implements IShape {
    // Posn pinhole
    // String color
    // circle and rect and square inherit from AShape
    // interfaces are for PROMISES, abstracts are when you see you're implementing these promises over and over again and want to consolidate into one line of code.
    // essentially, abstracts are for organization.
    // abstract classes do not have to fulfill the promises made by interfaces.
}

class Circle implements IShape {
    Posn center;
    int radius;
}

class Square extends Rect {
    // has Posn topLeft
    // has int width, height
    // YOU STILL NEED THIS IN YOUR CONSTRUCTOR

    Square(Posn topLeft, int sideLen) {
        super(topLeft, sideLen, sideLen); // this says "i would like to invoke the constructor of my upper class (Rect)""
        // you need to have them in the right order, 
        // such that it matches its parent (Rect)
    }

    // not even gonna mention the area method in Rect. doesn't matter

    public IShape grow(int inc) {
        return new Square(this.topLeft, this.width * inc);
        // you can basically paint over the parent methods
        // thats why we use this.wdith
        // this process is called OVER RIDING

        // why don't we use this.sideLen? well, where is that defined? It's not!!!
        // thats why we use this.width, which is coincidentally equal to this.sideLen
        // (not coincidental lol, the constructor directs this)
    }
}

class Rect implements IShape {
    Posn topLeft;
    int width, height;

    Rect(Posn topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public double area() {
        return this.width * this.height;
        // this, the pronoun, could be any of its subclasses
        // at compile time, its a Rect
        // but at run time, its a Square
    }

    public IShape grow(int inc) {
        return new Rect(this.topLeft, this.width * inc, this.height * inc);
    }
}

//Squares and rectangles are sometimes distinct, but they have an overlap:
// they have Posn lopLeft in common
// a square is a type of rectangle
// all squares are rectangles, not all rectangles are squares

// What if we say that a Square is a Rect, kind of like how we were saying
// Circles, Squares, and Rects are IShape?
// the way we think about this is the following:

// extends Rect
// everything Rect has, Square has also
// Square gets all the fields, all the behaviors
// if this is the case, we have to be SUPER CAREFUL
// how many fields does Square have now??? 4?? 5???

// there are 5. two of them are named topLeft, this is super confusing.
// so we need to delete the fields in common between the two
// in the more specific field.
// So, we delete the topLeft in Square because it takes it from Rect.
// we don't need sideLength anymore either in Square.

// We need to support this with the word SUPER.

// INHERITENCE
//subclass inherits from the super class.
// derived class inherits from the base class.
///      these two statements are synonymous


// is it legal to do this?
// Rect r = new Square([Some posn], [some sideLen])
// YES!


// when you write out super in your constructor, make it the 
// FIRST LINE OF CODE IN YOUR CONSTRUCTOR.
// then all of your specific fields afterwards.

// Abstract classes exist to consolidate repetitive code.