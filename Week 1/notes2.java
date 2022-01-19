//Suppose we want to represent train stations on the subway.
// Each station has a name and the name of the line that serves it.
// The subway station also has a price it costs to get on the train.
// In addition, a station on the commuter lne may be skipped by the express trains.

//Examples:
//Harvard station on the Red line costs $1.25 to enter

//Kenmore station on the Green line costs $1.25 to enter

//Riverside station on the Green line costs $2.50 to enter

//Back Bay station on the Framingham line is an express stop

//West Newton stop on the Framingham line is not an express stop

//Wellesley Hills on the Worcester line is not an express stop



//The common name for the data type that fits BOTH kinds of stations (CommStation and TStop)
// is the IStation, which is an interface. We can show this as:

// to represent a train station
interface IStation {
}
 
// to represent a subway station
class TStop implements IStation {
  String name;
  String line;
  double price;
 
  TStop(String name, String line, double price) {
    this.name = name;
    this.line = line;
    this.price = price;
  }
}
 
// to represent a stop on a commuter line
class CommStation implements IStation {
  String name;
  String line;
  boolean express;
 
  CommStation(String name, String line, boolean express) {
    this.name = name;
    this.line = line;
    this.express = express;
  }
}

// To designate an interface in this class, we put the letter I before the name of the interface.
//Lets define examples of data in a class called ExamplesIStation:

class ExamplesIStation{
    ExamplesIStation() {}
   
    /*
     Harvard station on the Red line costs $1.25 to enter
     Kenmore station on the Green line costs $1.25 to enter
     Riverside station on the Green line costs $2.50 to enter
   
     Back Bay station on the Framingham line is an express stop
     West Newton stop on the Framingham line is not an express stop
     Wellesely Hills on the Worcester line is not an express stop
    */
   
    IStation harvard = new TStop("Harvard", "red", 1.25);
    IStation kenmore = new TStop("Kenmore", "green", 1.25);
    IStation riverside = new TStop("Riverside", "green", 2.50);
   
    IStation backbay = new CommStation("Back Bay", "Framingham", true);
    IStation wnewton = new CommStation("West Newton", "Framingham", false);
    IStation wellhills = new CommStation("Wellesley Hills", "Worcester", false);
  }

//Lab1 introduces you to the Tester Library, and it allows you to test your code.



// Interfaces in trees
// Interface can be used to show an ancestry tree, where you have a Person, and each person
// has a mom, dad, and a name. 
// Normally, in Racket, the mom and dad would have the data type of 
// an Ancestor tree (AT), where an AT is one of:
// - unknown
// - Person
// But, it works differently in Java.

// We replaced AT with IAT since in Java the union type will be represented as an interface
// This is how it would be represented in Java:


// to represent an ancestor tree
interface IAT{ }
 
// to represent an unknown member of an ancestor tree
class Unknown implements IAT{
  Unknown() {}
}
 
// to represent a person with the person's ancestor tree
class Person implements IAT{
  String name;
  IAT mom;
  IAT dad;
 
  Person(String name, IAT mom, IAT dad) {
    this.name = name;
    this.mom = mom;
    this.dad = dad;
  }
}

// Let's define examples:

// examples and tests for the class hierarchy that represents
// ancestor trees
class ExamplesAncestors{
    ExamplesAncestors() {}
   
    IAT unknown = new Unknown();
    IAT mary = new Person("Mary", this.unknown, this.unknown);
    IAT robert = new Person("Robert", this.unknown, this.unknown);
    IAT john = new Person("John", this.unknown, this.unknown);
   
    IAT jane = new Person("Jane", this.mary, this.robert);
   
    IAT dan = new Person("Dan", this.jane, this.john);
  }




