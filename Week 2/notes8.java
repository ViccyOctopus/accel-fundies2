//In person notes

//Trees
//Its always better to choose data that is static and doesn't change
//For example, to represent someone's age, you can represent in two ways:
// - Number age
// - Birth date
// However, the number age depends on the day that you are asked.
// One day, you can ask someone if they're 19, the next day they can be 20
// The birth date is something that doesn't change
interface IAT {
    boolean isWellFormed(); //check if every person was born after their parents
    boolean isBornBefore(int year); // if unknown, return true always because
    //parents are always older
}

class Unknown implements IAT {
    public isWellFormed() {
        return ...
    }
    public boolean isBornBefore(int year) {
        return true;
    }
}

class Person implements IAT {
    int yob; //year of birth

    public boolean isWellFormed() {
        return ...
        //What if mom is unknown? this is a problem
        //Instead of asking "am I older than mom and dad?"
        //ask "Is mom and dad older than me?"
        //Delegating another object to finish the job for us (called delegating)

    }
}

