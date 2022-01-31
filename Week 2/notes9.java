//Abstract class and inheritance
// Shape

interface IShape {
    double area();
    double distTo();
    IShape grow(int inc);
    boolean biggerThan(IShape that);
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
        super(topLeft, sideLen, sideLen);
        this.topLeft = topLeft;
        this.sideLength = sideLength;
    }
}

class Rect implements IShape {
    Posn topLeft;
    int width, height;
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
