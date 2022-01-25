// Lets make an upgraded person
// with some additional information
//name, year of birth, gender

interface IAT {
    //to compute the number of known ancestors of this ancestor tree (excluding this ancestor tree itself)
    int count();

    // To compute the number of known ancestors of this ancestor tree (*including* this ancestor tree itself)
    int countHelp();

    // To compute how many ancestors of this ancestor tree (excluding this ancestor tree itself)
    // are women older than 40 (in the current year).
    int femaleAncOver40();

    // To compute how many ancestors of this ancestor tree (*including* this ancestor tree itself)
    // are women older than 40 (in the current year).
    int femaleAncOver40Help();

    //to detemrine if this ancestry tree is well-formed
    boolean wellFormed();

    // To determine if this ancestry tree is born in or before the given year
    boolean bornInOrBefore(int yob);
}

class Unknown implements IAT {
   Unknown() { 
       //nothing here, default case
   }

   // To compute the number of known ancestors of this Unknown (excluding this Unknown itself)
    public int count() {
        return 0;
    }
    
    // To compute the number of known ancestors of this Unknown (*including* this Unknown itself)
    public int countHelp() {
        return 0;
    }

    // To compute how many ancestors of this Unknown (excluding this Unknown itself)
    // are women older than 40 (in the current year).
    public int femaleAncOver40() { 
        return 0; 
    }

    // To compute how many ancestors of this Unknown (*including* this Unknown itself)
    // are women older than 40 (in the current year).
    public int femaleAncOver40Help() {
        return 0; 
    }

    // To determine if this Unknown is well-formed
    // all Unknowns are definitely older, so it is always true
    public boolean wellFormed() { 
        return true; 
    }

    // To determine if this Unknown is born in or before the given year
    public boolean bornInOrBefore(int yob) {
        return true; 
    }

}

class Person implements IAT {
    String name;
    int yob;
    boolean isMale;
    IAT mom;
    IAT dad;

    Person(String name, int yob, boolean isMale, IAT mom, IAT dad) {
        this.name = name;
        this.yob = yob;
        this.isMale = isMale;
        this.mom = mom;
        this.dad = dad;
    }

    //we have to add the methods that we want to know how to do (look in examples)
    public int count() {
        /* Template:
        *Fields:
        * this.name -- String
        * this.yob -- int
        * this.isMale -- boolean
        * this.mom -- IAT
        * this.dad -- IAT
        *Methods:
        * this.count() -- int
        * Methods of fields:
        * this.mom.count() -- int
        * this.dad.count() -- int
        */

        // To compute the number of known ancestors of this Person (excluding this Person itself)
        return this.mom.countHelp() + this.dad.countHelp();   
    }

    // To compute the number of known ancestors of this Person (*including* this Person itself)
    public int countHelp() {
        return 1 + this.mom.countHelp() + this.dad.countHelp();
    }

    // To compute how many ancestors of this Person (excluding this Person itself)
    // are women older than 40 (in the current year).
    public int femaleAncOver40() {
        /* Template:

        * Fields:
        * this.name -- String
        * this.yob -- int
        * this.isMale -- boolean
        * this.mom -- IAT
        * this.dad -- IAT

        * Methods:
        * this.count() -- int
        * this.countHelp() -- int
        * this.femaleAncOver40() -- int
        * this.femaleAncOver40Help() -- int

        * Methods of fields:
        * this.mom.count() -- int
        * this.mom.countHelp() -- int
        * this.mom.femaleAncOver40() -- int
        * this.mom.femaleAncOver40Help() -- int
        * this.dad.count() -- int
        * this.dad.countHelp() -- int
        * this.dad.femaleAncOver40() -- int
        * this.dad.femaleAncOver40Help() -- int
        */
        return this.mom.femaleAncOver40Help() + this.dad.femaleAncOver40Help();
    }

    // To compute how many ancestors of this Person (*including* this Person itself)
    // are women older than 40 (in the current year).
    public int femaleAncOver40Help() {
        /* same template as above */
        if (2015 - this.yob > 40 && !this.isMale) {
            return 1 + this.mom.femaleAncOver40Help() + this.dad.femaleAncOver40Help();
        }
        else {
            return this.mom.femaleAncOver40Help() + this.dad.femaleAncOver40Help();
        }
    }

    //We describe someone to be well-formed if every Person in it is younger than its parents
    // To determine if this Person is well-formed
    public boolean wellFormed() {
        /* Template:
        * Fields:
        * this.yob -- int
        * ... others as before
        * Methods:
        * ... others as before
        * this.wellFormed() -- boolean
        * Methods of fields:
        * ... others as before
        * this.mom.wellFormed() -- boolean
        * this.dad.wellFormed() -- boolean
        */
        return this.mom.bornInOrBefore(this.yob) &&
            this.dad.bornInOrBefore(this.yob) &&
            this.mom.wellFormed() &&
            this.dad.wellFormed();
    }

    // To determine if this Person is born in or before the given year
public boolean bornInOrBefore(int yob) {
    /* Template:
     * Fields:
     * this.yob -- int
     * ... others as before
     * Methods:
     * ... others as before
     * Methods of fields:
     * ... others as before
     */
     // Hooray -- we have all the information we need!
     return this.yob <= yob;
}
}

// We also have a list of strings to work with

interface ILoString {
 
}

// to represent a non-empty list of strings
class ConsLoString implements ILoString {
    String first;
    ILoString rest; //the rest of a list always has to be an interface because it is a type of union data

    ConsLoString(String first, ILoString rest) {
        this.first = first;
        this.rest = rest;
    }

}

//to represent an empty list of strings
class MtLoString implements ILoString {
    MtLoString() { }

}

//to figure out how to make different methods, we have to make examples and tests
// so we know what the result will be

class ExamplesIAT {
    IAT enid = new Person("Enid", 1904, false, new Unknown(), new Unknown());
    IAT edward = new Person("Edward", 1902, true, new Unknown(), new Unknown());
    IAT emma = new Person("Emma", 1906, false, new Unknown(), new Unknown());
    IAT eustace = new Person("Eustace", 1907, true, new Unknown(), new Unknown());
 
    IAT david = new Person("David", 1925, true, new Unknown(), this.edward);
    IAT daisy = new Person("Daisy", 1927, false, new Unknown(), new Unknown());
    IAT dana = new Person("Dana", 1933, false, new Unknown(), new Unknown());
    IAT darcy = new Person("Darcy", 1930, false, this.emma, this.eustace);
    IAT darren = new Person("Darren", 1935, true, this.enid, new Unknown());
    IAT dixon = new Person("Dixon", 1936, true, new Unknown(), new Unknown());
 
    IAT clyde = new Person("Clyde", 1955, true, this.daisy, this.david);
    IAT candace = new Person("Candace", 1960, false, this.dana, this.darren);
    IAT cameron = new Person("Cameron", 1959, true, new Unknown(), this.dixon);
    IAT claire = new Person("Claire", 1956, false, this.darcy, new Unknown());
 
    IAT bill = new Person("Bill", 1980, true, this.candace, this.clyde);
    IAT bree = new Person("Bree", 1981, false, this.claire, this.cameron);
 
    IAT andrew = new Person("Andrew", 2001, true, this.bree, this.bill);
 
    boolean testCount(Tester t) {
        return
            t.checkExpect(this.andrew.count(), 16) &&
            t.checkExpect(this.david.count(), 1) &&
            t.checkExpect(this.enid.count(), 0) &&
            t.checkExpect(new Unknown().count(), 0);
    }
    boolean testFemaleAncOver40(Tester t) {
        return
            t.checkExpect(this.andrew.femaleAncOver40(), 7) &&
            t.checkExpect(this.bree.femaleAncOver40(), 3) &&
            t.checkExpect(this.darcy.femaleAncOver40(), 1) &&
            t.checkExpect(this.enid.femaleAncOver40(), 0) &&
            t.checkExpect(new Unknown().femaleAncOver40(), 0);
    }
    boolean testWellFormed(Tester t) {
        return
            t.checkExpect(this.andrew.wellFormed(), true) &&
            t.checkExpect(new Unknown().wellFormed(), true) &&
            t.checkExpect(
                new Person("Zane", 2000, true, this.andrew, this.bree).wellFormed(),
                false);
    }
    boolean testAncNames(Tester t) {
        return
            t.checkExpect(this.david.ancNames(),
                new ConsLoString("David",
                    new ConsLoString("Edward", new MtLoString()))) &&
            t.checkExpect(this.eustace.ancNames(),
                new ConsLoString("Eustace", new MtLoString())) &&
            t.checkExpect(new Unknown().ancNames(), new MtLoString());
    }
    boolean testYoungestGrandparent(Tester t) {
        return
            t.checkExpect(this.emma.youngestGrandparent(), new Unknown()) &&
            t.checkExpect(this.david.youngestGrandparent(), new Unknown()) &&
            t.checkExpect(this.claire.youngestGrandparent(), this.eustace) &&
            t.checkExpect(this.bree.youngestGrandparent(), this.dixon) &&
            t.checkExpect(this.andrew.youngestGrandparent(), this.candace) &&
            t.checkExpect(new Unknown().youngestGrandparent(), new Unknown());
    }
}

