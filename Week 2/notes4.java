//Whenever we define methods in a class that were declared
//in an interface, we must mark them as public.
//Our code would look like this:

// to represent a geometric shape
interface IShape {
    // to compute the area of this shape
    double area();
    // We'll add more methods later
  }
   
  // to represent a circle
  class Circle implements IShape {
    int x; // represents the center of the circle
    int y;
    int radius;
    String color;
   
    Circle(int x, int y, int radius, String color) {
      this.x = x;
      this.y = y;
      this.radius = radius;
      this.color = color;
    }
   
    /* TEMPLATE
       FIELDS:
       ... this.x ...                   -- int
       ... this.y ...                   -- int
       ... this.radius ...              -- int
       ... this.color ...               -- String
       METHODS
       ... this.area() ...              -- double
    */
   
    // to compute the area of this shape
    public double area() {
      return Math.PI * this.radius * this.radius;
    }

    //measures the distance to the origin
    public double distanceToOrigin() {
      return Math.sqrt(this.x * this.x + this.y * this.y) - this.radius;
    }

  }
   
  // to represent a square
  class Square implements IShape {
    int x; // represents the top-left corner of the square
    int y;
    int size;
    String color;
   
    Square(int x, int y, int size, String color) {
      this.x = x;
      this.y = y;
      this.size = size;
      this.color = color;
    }
   
    /* TEMPLATE
       FIELDS:
       ... this.x ...               -- int
       ... this.y ...               -- int
       ... this.size ...            -- int
       ... this.color ...           -- String
       METHODS:
       ... this.area() ...                  -- double
    */
   
    // to compute the area of this shape
    public double area() {
      return this.size * this.size;
    }

    //measures the distance to the origin
    public double distanceToOrigin() {
      return Math.sqrt(this.x * this.x + this.y * this.y);
    }
  }
   
  class ExamplesShapes {
    ExamplesShapes() {

    }
   
    IShape c1 = new Circle(50, 50, 10, "red");
    IShape s1 = new Square(50, 50, 30, "red");
   
    // test the method area in the classes that implement IShape
    boolean testIShapeArea(Tester t) {
      return
      t.checkInexact(this.c1.area(), 314.15, 0.01) &&
      t.checkInexact(this.s1.area(), 900.0, 0.01);
    }
  }


// Dynamic Dispatch
//How did Java “know” that when we wrote this.c1.area(), 
// we intended to invoke the area method defined in the Circle class,
// and that when we wrote this.s1.area(), we intended to invoke the
// method defined in the Square class? After all, both s1 and c1 
// are declared as having type IShape — and interfaces don’t define
// any method implementations at all!

  //Suppose we had a murkier situation, like this:
// somewhere in our code, in some class...
double twiceTheArea(IShape aShape) {
  return 2 * aShape.area();
}

//From this alone, wtf is aShape? it doesnt know what shape to put in.
// But it does know that aShape is of the IShape interface.
// it actually knows at runtime because it is information
//available only dynamically, and is called dynamic dispatch.


//Designing the distanceToOrigin method
// Look back in the code, i had to write the distanceToOrigin method twice.


/* For both to see here:

public double distanceToOrigin() {
      return Math.sqrt(this.x * this.x + this.y * this.y);
  }

public double distanceToOrigin() {
      return Math.sqrt(this.x * this.x + this.y * this.y) - this.radius;
    }

*/

// well, that kinda sucks, right?
// if only we didnt have to write out something like the pythagorean formula out twice
// what if in the future we add more kinds of IShapes?
// will we have to write the formula again?

// Also confusing: both shapes have x and y fields
// but the fields mean different things:
// the center of the circle vs. the top left corner of the square.

// We can make a HELPER CLASS
// and call it CartPt (for Cartesian Point)

// To represent a 2-d point by Cartesian coordinates
class CartPt {
  int x;
  int y;
  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
 
class Circle implements IShape {
  CartPt center; // NEW!  And its name is far more helpful
  int radius;
  String color;
  Circle(CartPt center, int radius, String color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }
  ...
}

class Square implements IShape {
  CartPt topLeft; // NEW!  And its name is far more helpful
  int size;
  String color;
  Square(CartPt topLeft, int size, String color) {
    this.topLeft = topLeft;
    this.size = size;
    this.color = color;
  }
  ...
}

// Lets see us refactor the distanceToOrigin class as well

class Circle implements IShape {
  ...
  /* TEMPLATE
     FIELDS:
     ... this.center ...                    -- CartPt
     ... this.radius ...                    -- int
     ... this.color ...                     -- String
     METHODS
     ... this.area() ...                    -- double
     ... this.distanceToOrigin() ...        -- double
     METHODS ON FIELDS ----- NEW!
     ... this.center.distanceToOrigin() ... -- double
  */
  public double distanceToOrigin() {
    return this.center.distanceToOrigin() - this.radius;
  }
}

class Square implements IShape {
  ...
  /* TEMPLATE
    FIELDS:
    ... this.topLeft ...                    -- CartPt
    ... this.size ...                       -- int
    ... this.color ...                      -- String
    METHODS
    ... this.area() ...                     -- double
    ... this.distanceToOrigin() ...         -- double
    METHODS ON FIELDS ----- NEW!
    ... this.topLeft.distanceToOrigin() ... -- double
  */
  public double distanceToOrigin() {
    return this.topLeft.distanceToOrigin();
  }
}

// General rule of thumb in programming : dont repeat yourself
// If you see yourself reusing the same thing in multiple places,
// make your program more efficient by separating it into its own
// type of definition so it can be reused easier

//We dont have to make this helper of CartPt because the tester
// library already gives us Posn, and it is a replica (basically)
// of the CartPt helper



// Designing the isBiggerThan method.
// it asks whether this shape's area is greater than the given shape's area
// if only we had something that calculated the area...

// WE DO, LES FUCKING GO
//SIMPLE

public boolean isBiggerThan(IShape that) {
  return this.area() > that.area();
}


// Designing the contains method
//Determining whether a circle contains a point requires comparing the distances
//between two points
//We already have a method that computes the distance to a specific point, the origin
// but we need a more general version that computes the distance to a given point

// distanceTo is the distance formula
class CartPt {
  //...
  double distanceTo(CartPt that) {
    return Math.sqrt(
      (this.x - that.x) * (this.x - that.x)
      + (this.y - that.y) * (this.y - that.y));
  }
}

//Now we can implement contains:

class Circle implements IShape {
  //...
  boolean contains(CartPt point) {
    return this.center.distanceTo(point) < this.radius;
  }
}
//Solving this for Squares is slightly trickier, as we need to compare
// the actual coordinates of the points:

class Square implements IShape {
  //...
  boolean contains(CartPt point) {
    return (this.topLeft.x <= point.x) &&
           (point.x <= this.topLeft.x + this.size) &&
           (this.topLeft.y <= point.y) &&
           (point.y <= this.topLeft.y + this.size);
  }
}



// PUTTING ALL THAT WE LEARNED TOGETHER

class ExamplesShapes {
  ExamplesShapes() {}
 
  CartPt pt1 = new CartPt(0, 0);
  CartPt pt2 = new CartPt(3, 4);
  CartPt pt3 = new CartPt(7, 1);
 
  IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
  IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
  IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");
 
  IShape s1 = new Square(new CartPt(50, 50), 30, "red");
  IShape s2 = new Square(new CartPt(50, 50), 50, "red");
  IShape s3 = new Square(new CartPt(20, 40), 10, "green");
 
  // test the method distanceToOrigin in the class CartPt
  boolean testDistanceToOrigin(Tester t) {
    return
    t.checkInexact(this.pt1.distanceToOrigin(), 0.0, 0.001) &&
    t.checkInexact(this.pt2.distanceToOrigin(), 5.0, 0.001);
  }
 
  // test the method distTo in the class CartPt
  boolean testDistTo(Tester t) {
    return
    t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001) &&
    t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
  }
 
  // test the method area in the class Circle
  boolean testCircleArea(Tester t) {
    return
    t.checkInexact(this.c1.area(), 314.15, 0.01);
  }
 
  // test the method area in the class Square
  boolean testSquareArea(Tester t) {
    return
    t.checkInexact(this.s1.area(), 900.0, 0.01);
  }
 
  // test the method distanceToOrigin in the class Circle
  boolean testCircleDistanceToOrigin(Tester t) {
    return
    t.checkInexact(this.c1.distanceToOrigin(), 60.71, 0.01) &&
    t.checkInexact(this.c3.distanceToOrigin(), 74.40, 0.01);
  }
 
  // test the method distanceToOrigin in the class Square
  boolean testSquareDistanceToOrigin(Tester t) {
    return
    t.checkInexact(this.s1.distanceToOrigin(), 70.71, 0.01) &&
    t.checkInexact(this.s3.distanceToOrigin(), 44.72, 0.01);
  }
 
  // test the method grow in the class Circle
  boolean testCircleGrow(Tester t) {
    return
    t.checkExpect(this.c1.grow(20), this.c2);
  }
 
  // test the method grow in the class Square
  boolean testSquareGrow(Tester t) {
    return
    t.checkExpect(this.s1.grow(20), this.s2);
  }
 
  // test the method isBiggerThan in the class Circle
  boolean testCircleIsBiggerThan(Tester t) {
    return
    t.checkExpect(this.c1.isBiggerThan(this.c2), false) &&
    t.checkExpect(this.c2.isBiggerThan(this.c1), true) &&
    t.checkExpect(this.c1.isBiggerThan(this.s1), false) &&
    t.checkExpect(this.c1.isBiggerThan(this.s3), true);
  }
 
  // test the method isBiggerThan in the class Square
  boolean testSquareIsBiggerThan(Tester t) {
    return
    t.checkExpect(this.s1.isBiggerThan(this.s2), false) &&
    t.checkExpect(this.s2.isBiggerThan(this.s1), true) &&
    t.checkExpect(this.s1.isBiggerThan(this.c1), true) &&
    t.checkExpect(this.s3.isBiggerThan(this.c1), false);
  }
 
   // test the method contains in the class Circle
  boolean testCircleContains(Tester t) {
    return
    t.checkExpect(this.c1.contains(new CartPt(100, 100)), false) &&
    t.checkExpect(this.c2.contains(new CartPt(40, 60)), true);
  }
 
  // test the method contains in the class Square
  boolean testSquareContains(Tester t) {
    return
    t.checkExpect(this.s1.contains(new CartPt(100, 100)), false) &&
    t.checkExpect(this.s2.contains(new CartPt(55, 60)), true);
  }
}