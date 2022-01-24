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
  }
   
  class ExamplesShapes {
    ExamplesShapes() {}
   
    IShape c1 = new Circle(50, 50, 10, "red");
    IShape s1 = new Square(50, 50, 30, "red");
   
    // test the method area in the classes that implement IShape
    boolean testIShapeArea(Tester t) {
      return
      t.checkInexact(this.c1.area(), 314.15, 0.01) &&
      t.checkInexact(this.s1.area(), 900.0, 0.01);
    }
  }