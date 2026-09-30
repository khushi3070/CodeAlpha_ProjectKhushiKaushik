//l-5
//import java.util.Scanner;
//public class lecjj {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);

//        System.out.println("ENTER NUMBER 1");
//        int a = sc.nextInt();

//        System.out.println("ENTER NUMBER 2");
//        int b = sc.nextInt();

//        int sum =a+b;
//        System.out.println("this is the sum of a and b");
//        System.out.println(sum);

//   }
//}


// L -6(asking marks from the user)
// import java.util.Scanner;
// public class HelloWorld {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         System.out.println("enter marks 1");
//         int a = sc.nextInt();
//         System.out.println("enter marks 2");
//         int b = sc.nextInt();
//         System.out.println("enter marks 3");
//         int c = sc.nextInt();
//         float percentage = ((a+b+c)/300.0f)*100;
//         System.out.println("this is the percentage");
//         System.out.println(percentage);
//     }
// }


// L-10
//    public class javaprograms{
//        public static void main(String[] args)
//        {
//            byte x = 5;
//            int y = 5;
//            short z = 8;
//            int a = y + z;
//            float b = a + x;
//            System.out.println(b);
// INCREMENT AND DECREMENT OPERATOR
//            int i = 56;
//            //   int b = i++;
//            System.out.println (i++);
//            System.out.println(i);
//            System.out.println(++i);
//            System.out.println(i);
//            int y = 7;
//            System.out.println(++y *8);
//            char ch ='a';
//            System.out.println(++ch);
//        }
//    }
//
//}


// L-15??????????????
//import java.util.Scanner;
//public class lecjj {
//  public static void main(String[] args) {
// error program
//        int a =10;
// if (a =11)
//     System.out.println("i am 11");
//else
//    System.out.println("i am not 11")
//ques -2
//     byte n1, n2,n3;
//     Scanner sc = new Scanner(System.in);
//         System.out.println("enter your marks in physics");
//         n1 = sc.nextByte();
//         System.out.println("enter your marks in chem");
//         n2 = sc.nextByte();
//         System.out.println("enter your marks in maths");
//         n3 = sc.nextByte();
//         float avg = (n1+n2+n3)/3.0f;
//         System.out.println(" your over all percentage is:" +avg);
//         if(avg>=48 && n1>=33 && n2>=33 && n3>=33){
//             System.out.println("congratulations, you have been promoted");
//         }
//         else {
//             System.out.println("sorry, you are not promoted");
//         }
//         }
//     }
// QUES 3
//         float tax = 0;
//         float income = 10.3f;
//         if (income <= 2.5) {
//             tax = tax + 0;
//         }
//         else if (income>2.5f && income<5.0f){
//             tax = tax + 0.05f * (income - 2.5f);
//         }
//         else if (income>5f && income<=10.0f){
//             tax = tax + 0.05f * (5.0f - 2.5f);
//                tax = tax + 0.02f * (income - 5f);
//             }
//         else if (income >10.0f){
//            tax = tax + 0.05f * (5.0f - 2.5f);
//                tax = tax + 0.02f * (10.0f - 5f);
//                tax = tax + 0.05f * (income - 10.5f);
//         }
//         System.out.println("The total tax paid by the employee is:" +tax);


    //l-15
    //Problem-1
    // String name = "Jack Parker";
    //name = name.toLowerCase();
    //System.out.println(name);
    //Problem-2
    //String text = "To my               friend";
    //text = text.replace(" ", "_");
    //System.out.println(text);
    //problem 3
//   String letter = "Dear <|name|>, Thanks a lot!";
//   letter =letter.replace("<|name|>", "Harry");
//         System.out.println(letter);
//problem -4
//String mystring = "This string contains      double and triple space";
//         System.out.println(mystring.indexOf("  "));
//         System.out.println(mystring.indexOf("   "));

    /// /problem -5
//String myLetter = "Dear Harry,\n\tThis Java Course is Nice. \n\tThanks!";
//         System.out.println(myLetter);

    //l-16//
//         Scanner sc = new Scanner(System.in);
//         System.out.println("enter Age:");
//         int age = sc.nextInt();
//      if(age>18){
//          System.out.println("you can drive!");
//      }
//     else{
//          System.out.println("you can't drive!");
//      }
//     }
// }

    //L-17//
//    public static void main(String[] args) {
//        boolean a = true;
//        boolean b = true;
//        if (a && b) {
//            System.out.println("yes");
//        } else {
//            System.out.println("no");
//        }
//        System.out.println("for logial or .....");
//        if(a || b){
//            System.out.println("yes");
//        }
//        else{
//            System.out.println("no");
//        }
// else  if //
//        int age = 18;
//   if(age>=56){
//       System.out.println("you are experienced!");
//   }
//   else if(age<=46){
//       System.out.println("you are semi experiennce");
//   }
//   else{
//       System.out.println("u r not experiemced");
//   }
    /// switch cases
//switch(age){
//    case 18:
//        System.out.println("u r going to become adult");
//        break;
//    case 23:
//        System.out.println("u r going to join a job ");
//        break;
//    case 68:
//        System.out.println("u r going to dead ");
//        break;
//    default:
//        System.out.println("enjoy uour life");
//}
//        System.out.println("thannk you");
//    }
//}
// l-21(while loop)//
//        int i=1;
//        while (i <= 9) {
//            System.out.println(i);
//            i++;
//        }
//        int b=1;
    // L-22
//        do {
//            System.out.println(b);
//             b++;
//        }while(b <5);
    //L-23
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(i);
//        }
//    first odd number
//int n =5;
//        for(int i=0; i<5;i++){
//            System.out.println(2*i+1);
//        }
// L-24//
    //(break and continue statement)
//        for (int i = 1; i < 5; i++) {
//            System.out.println(i);
//            System.out.println("java is great");
//            if (i == 2) ;
//            System.out.println(("ending the loop"));
//            break;
    //break end the lop but continue recheck the  condition
//        }
    // L-25
    //practice problem 1(fibnocii)
//        int n =4;
//        for(int i=n;i>0; i--){
//            for(int j=0;  j<i; j++){
//                System.out.println("*");
//            }
//            System.out.println("\n");
//        }
//    }

    ///////////////////////////////  SK  /////////////////////////////////
    //public static void main(String[] args) {
//        System.out.print("hello \n world");// print ln is used to print in next line//
//        int a = 25;
//        int b = 10;
//        int c = 2 * (a + b);
//        System.out.print("area of rectangle is :");
//        System.out.println(c);
    //input
//        Scanner sc = new Scanner(System.in);
//        int age = sc.nextInt();
//        System.out.println(name);
//        if (age > 18){
//            System.out.println("u r adult");
//        }
//        else{
//            System.out.println("u r not adult");
//        }
//        int a = sc.nextInt();
//        int b = sc.nextInt();

//        if (a%2==0){
//            System.out.println("no is even");
//        }
//        else{
//            System.out.println("no is odd");
//        }
//        if(a==b){
//            System.out.println("equal");
//        }
//        else if(a>b){
//            System.out.println("greater");
//        }
//        else{
//            System.out.println("lesser");
//        }
//        int button = sc.nextInt();
//        switch (button) {
//            case 1:
//                System.out.println("hello");
//                break;
//            case 2:
//                System.out.println("namaste");
//                break;
//            case 3:
//                System.out.println("bonjor");
//                break;
//            default:
//                System.out.println("invalid button");
//        }
    //loops
//    for(int counter = 0; counter <10; counter = counter +1)
//    {
//        System.out.println("hello world");
//    }
//        int i=0;
//    while(i<11){
//        System.out.println(i);
//        i =i+1;
//    }
//    int n= 4;
//    int m= 5;
//    //outer layer
//        for (int i =1; i<=n; i++){
//            //inner layer
//            for(int j= 1; j<=m; j++){
//                //cell->(i,j)
//                if(i==1|| j==1 || i==n || j==m){
//                    System.out.print("*");
//                }
//                else{
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//        int n = 5;
//        // first half upper half
//        for (int i = 1; i <= n; i++) {
//            //1 st part
//            for (int j = 1; j < i; j++) {
//                System.out.print("*");
//            }
//            //spaces
//            int spaces = 2 * (n - i);
//            for (int j = 1; j <= spaces; j++) {
//                System.out.print(" ");
//            }
//            //2nd part
//            for(int j=1;j<=i; j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//        int []marks = new int[3];
//        marks[0]=97;
//        marks[1]=98;
//        marks[2]=95;
//          System.out.println(marks[0]);
//        System.out.println(marks[1]);
//for(int i=0; i<3; i++){
//    System.out.println(marks[i]);
//}
//        Scanner sc = new Scanner(System.in);
//        int size = sc.nextInt();
//        int number[] =new int[size];
//        //input
//        for(int i =0; i<size; i++){
//            number[i]= sc.nextInt();
//        }
//        int x =sc.nextInt();
//        //output
//        for(int i =0; i<size; i++){
//            System.out.println(number[i]);
//        }
//        2-D array
//        Scanner sc = new Scanner(System.in);
//        int rows = sc.nextInt();
//        int column = sc.nextInt();
//        int[][]number = new int[rows][column];
//        //input and output
//        for(int i =0; i<rows; i++){
//            for(int j =0; j<column; j++) {
//                number[i][j] = sc.nextInt();
//            }
//        }
//        //output
//        for(int i =0; i<rows; i++){
//            for(int j =0; j<column; j++) {
//                System.out.print(number[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
/////////////////////////////// HC ///////////////////////////////////////
//l-26//
//public class lecjj {
    //    public static void main(String[] args) {
    //classroom of 500 student - you have to store marks of these 500 students
//        int[] marks = new int[5];
//        marks[0] = 100;
//        marks[1] = 200;
//        marks[2] = 300;
//        marks[3] = 400;
//        marks[4] = 500;
//        System.out.println(marks[0]);
// l-27
//   int[] marks = {100,200,300,400,500,600,700,800};
//        System.out.println(marks[1]);
//        for(int i=0; i<marks.length;i++){
//            System.out.println(marks[i]);
//        }
    //l-28
//        int[][] flats;// 2 D array
//        flats = new int[2][3];
//        flats[0][0] = 101;
//        flats[0][1] = 102;
//        for (int i = 0; i < flats.length; i++) {
//            for (int j = 0; j < flats.length; j++) {
//                System.out.println(flats[i][j]);
//            }
//        }
    //l-29(practice question)
//        static int logic(int a, int b ){
//            int z;
//            if(a>b){
//                z=a+b;
//            }
//            else {
//            }
//                z= (a+b) * 5;
//            return z;
//        }
//public static void main(String[] args) {
//    int a =5;
//        int b =7;
//        int c;
//        c = logic(a,b);
//        int a1 =2;
//        int b1 =3;
//        int c1;
//        c1 =logic(a1,b1);
//        System.out.println(c);
//        System.out.println(c1);
//    static void telljoke(){
//        System.out.println("i invent a word pilgarism");
//    }
//public static void main(String[] args) {
//telljoke();
//    int[] marks ={56,76,87,69};
//    int x =45;
//    change(x);
//    System.out.println("the value of x is "+x);
    //l-32 and 33
//    public class method_overloading {
//        static void change(int [] arr) {
//            arr[3] = 98;
//        }
//
//        static void change2(int[] arr) {
//            arr[5] = 98;
//        }
//        static int sum(int ...arr){
//            int result =8;
//            for(int a: arr){
//                result += a;
//            }
//                    return result;
//        }
//        public static void main(String[] args) {
//            System.out.println("welcome to my program");
//            System.out.println("the sum" + sum( 4,5));
//            System.out.println("the multiple" +sum(4,5,6));
//        }
    //l-34
//    static int factorial(int n) {
//        if (n == 0 || n == 1) {
//            return 1;
//        } else {
//            int product = 1;
//            for (int i = 1; i <= n; i++) {
//                product *= i;
//            }
//        return n * factorial(n-1);
//        }
//    }
//    }
//l-38
//package com.g;
//    class Employee{
//        int id;
//        String name;
//        public void printdetails(){
//            System.out.println("Employee id " + id);
//            System.out.println("Employee name " +name);
//        }
//}
//public class lecjj {
//
//        public static void main(String[] args) {
//            System.out.println("this is our custom class");
//            Employee harry = new Employee();
//            Employee john = new Employee();
//            harry.id = 12;
//            harry.name = "harry";
//            john.id =17;
//            john.name = "john";
//            System.out.println(harry.id);
//            System.out.println(harry.name);
//            john.printdetails();
//        }
//    }
//    l-42
//class MyEMPLOYEE{
//    private int id;
//    private String name;
//    public String getname(){
//        return name;
//    }
//    public void setname(String n){
//        name=n;
//    }
//    public void setid(int i){
//        id = i;
//    }
//    public int getId(){
//        return id;
//    }
//}
//public class lecjj {
//    public static void main(String[] args) {
//        MyEMPLOYEE khushi = new MyEMPLOYEE();
//        khushi.setname("code with me");
//        System.out.println(khushi.getname());
//    }
//}
//    l-43
//    import java.util.Scanner;
//    import java.util.Random;
//public class lecjj {
//    public static void main(String[] args) {
//        guess the number game //
//        step 1 initialization //
//        Random random = new Random();
//        Scanner scanner = new Scanner(System.in);
//        int numberToGuess = random.nextInt(100) + 1; // Range: 1 to 100
//        int numberOfTries = 0;
//        int guess = 0;
//        boolean win = false;
//
//        System.out.println("Welcome to the Guess the Number Game!");
//        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");
//
//        // Step 2: Game loop
//        while (win == false) {
//            System.out.print("Enter your guess: ");
//
//            // Check for valid integer input
//            if (scanner.hasNextInt()) {
//                guess = scanner.nextInt();
//                numberOfTries++;
//
//                if (guess == numberToGuess) {
//                    win = true;
//                } else if (guess < numberToGuess) {
//                    System.out.println("Too low! Try again.");
//                } else if (guess > numberToGuess) {
//                    System.out.println("Too high! Try again.");
//                }
//            } else {
//                System.out.println("Please enter a valid number.");
//                scanner.next(); // Clear invalid input
//            }
//        }
//
//        // Step 3: Game conclusion
//        System.out.println("Congratulations! You found the number in " + numberOfTries + " tries.");
//        scanner.close();
//    }
//}
//    l-45
//class base{
//    int x ;
//    public int getx(){
//        return x;
//    }
//    public void setx(int x){
//        System.out.println("i am setting x now");
//        this .x = x;
//    }
//    public void printMe(){
//        System.out.println("i am constructor");
//    }
//}
//class derived extends base {
//    int y;
//
//    public int getx() {
//        return y;
//
//    }
//
//    public class lecjj {
//        public static void main(String[] args) {
//            derived d = new derived();
//            d.setx(4);
//            System.out.println(d.getx());
//        }
//    }
//}
//l-46
//class base {
//    base() {
//        System.out.println("i am a constructor");
//    }
//}
//class derived extends base{
//        derived(){
//            System.out.println("i am a derived constructor");
//        }
//}
//public class lecjj{
//    public static void main(String[] args){
//        derived d = new derived();
//    }
//}
//l-48
// Demonstration of Me
// thod Overriding in Java
//import java.util.Scanner;
//
//// Parent class
//class Animal {
//    // Method to be overridden
//    public void sound() {
//        System.out.println("Animal makes a sound");
//    }
//}
//
//// Child class overriding the sound() method
//class Dog extends Animal {
//    @Override // Annotation ensures correct overriding
//    public void sound() {
//        System.out.println("Dog barks");
//    }
//}
//

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TimeZone;

/// / Another child class
//class Cat extends Animal {
//    @Override
//    public void sound() {
//        System.out.println("Cat meows");
//    }
//}
//
//public class lecjj {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Choose an animal (dog/cat): ");
//        String choice = sc.nextLine().trim().toLowerCase();
//
//        Animal myAnimal; // Reference of parent type
//
//        // Input validation and object creation
//        switch (choice) {
//            case "dog":
//                myAnimal = new Dog();
//                break;
//            case "cat":
//                myAnimal = new Cat();
//                break;
//            default:
//                System.out.println("Invalid choice. Defaulting to generic Animal.");
//                myAnimal = new Animal();
//        }
//
//        // Runtime polymorphism: method call resolved at runtime
//        myAnimal.sound();
//
//        sc.close();
//    }

//l-53
//    abstract class parent2{
//        public parent2(){
//            System.out.println("Base2");
//        }
//        public void sayhello(){
//            System.out.println("hello");
//        }
//         public abstract void greet();
//        }
//        class child2 extends parent2{
//        @Override
//        public  void greet(){
//            System.out.println("good morning");
//        }
//        public class lecjj{
//            public static void main(String[] args){
//                child2 obj = new child2();
//                obj.sayhello();
//                obj.greet();
//            }
//            }
//        }


//l-55
//interface Bicycle{
//    int a =45;
//    void applyBreak(int decrement);
//    void speedUp(int increment);
//    }
//    class Avoncycle implements Bicycle{
//        void Blowhorn(){
//        System.out.println("Blowhorn");
//    }
//    void applyBrakes(int decrement){
//        System.out.println("Apply brakes");
//    }
//    void speedup(int increment){
//        System.out.println("Speedup");
//    }
//    }
//    public class lecjj{
//    public static void main (String [] args){
//
//    }
//    }

//l-57
//interface camera{
//    void takesnap();
//    void recordvideo();
//}
//    interface wifi {
//        String[] getnetworks();
//
//        void connecttonetwork(String network);
//    }
//    class cellphone{
//    void snap()
//    {
//        System.out.println("takesnap");
//    }
//    }
//    public class lecjj{
//    }

//l-66
//package com.company;
//class c1{
//    public int x =5;
//    protected int y = 45;
//    int z = 7;
//    private int a = 88;
//    public void meth1(){
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(z);
//        System.out.println(a);
//    }
//}
//public class lecjj{
//    public static void main(String[] args) {
//    c1 c = new c1();
//    c.meth1();
//    }
//}

//l-70
//        class Mythread extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//                System.out.println("my thread is running");
//                System.out.println("I am happy");
//            }
//        }
//    }
//    class Mythread2 extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//                System.out.println("my thread 2 is running");
//                System.out.println("I am sad");
//            }
//        }
//    }
//public class lecjj {
//public static void main(String[] args) {
//    Mythread c1 = new Mythread();
//    Mythread2 c2 = new Mythread2();
//    c1.start();
//    c2.start();
//}
//}

//l-71
//class MyThread1Runnable implements Runnable {
//    public void run(){
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//        System.out.println("I am a thread 1");
//    }
//    }
//class MyThread2Runnable implements Runnable {
//    public void run() {
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//        System.out.println("I am a thread 2");
//
//    }
//}
//    public class lecjj {
//    public  static void main(String[] args) {
//       MyThread1Runnable t1 = new MyThread1Runnable();
//       MyThread2Runnable t2 = new MyThread2Runnable();
//       Thread gun1 = new Thread(t1);
//       Thread gun2 =  new Thread(t2);
//       gun1.start();
//       gun2.start();
//    }
//}

//l-73
//class Mythr extends Thread {
//    public Mythr(String name) {
//        super(name);
//    }
//    public void run() {
//        while(true){
//            System.out.println("i am a thread");
//        }
//    }
//}
//    public class lecjj {
//        public static void main(String[] args) {
//            Mythr t = new Mythr(name: "Harry");
//            t.start();
//            System.out.println("the id of the thread is " + t.getId());
//        }
//    }

//L-75
//class mythread1 extends Thread {
//        public mythread1(String name) {
//            super(name);
//        }
//
//        public void run() {
//            while (true) {
//                System.out.println("i m thread");
//                System.out.println("THANK U");
//            }
//        }
//    }
//    class mynewthread extends Thread{
//    public void run(){
//        while (true) {
//            System.out.println("i m thread 2");
//            System.out.println("THANK U ji");
//        }
//    }
//    }
//public class lecjj{
//        public static void main(String[] args){
//            mythread1 t1 = new mythread1("t1");
//            mythread1 t2 = new mythread1("t2");
//            t1.start();
//            try{
//                t1.join();
//            }
//            catch(Exception e){
//                System.out.println("error");
//            }
//            t2.start();
//        }
//}

//l-80
//public class lecjj {
//    public static void main(String[] args) {
//        int a=60000;
//        int b=0;
//        try {
//            int c = a / b;
//        }
//        catch(ArithmeticException e) {
//            System.out.println("Arithmetic Exception");
//            System.out.println(e);
//        }
//    }
//    }

//l-83
// Demonstrates throw vs throws in Java
//    public class lecjj{
//        // Method declares it may throw an exception (checked exception)
//        static void checkAge(int age) throws Exception {
//            if (age < 18) {
//                // throw is used to actually create and throw the exception
//                throw new Exception("Age must be 18 or above.");
//            }
//            System.out.println("Access granted.");
//        }
//
//        public static void main(String[] args) {
//            try {
//                checkAge(15); // This will cause an exception
//            } catch (Exception e) {
//                System.out.println("Exception caught: " + e.getMessage());
//            }
//
//            try {
//                checkAge(20); // This will pass
//            } catch (Exception e) {
//                System.out.println("Exception caught: " + e.getMessage());
//            }
//        }
//    }


//l-85
//    public class lecjj {
//
//        public static void main(String[] args) {
//            try {
//                int a = 5;
//                int b = 8;
//                int c = a / b; // integer division
//                System.out.println("Result: " + c);
//            }
//            catch (Exception e) {
//                System.out.println("Error: " + e);
//            }
//            finally {
//                System.out.println("This is the end of this program");
//            }
//
//            greet(); // call greet method
//        }
//
//        // Added greet method
//        public static void greet() {
//            System.out.println("Hello from greet method!");
//        }
//    }

//l-91
//import java.util.*;
//public class lecjj {
//    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        for(int i=0; i<4; i++){
//            list.add(list.get(i)+list.get(i+1));
//        }
//    }
//}

//l-93
//import java.util.ArrayDeque;
//public class lecjj {
//    public static void main(String[] args) {
//        ArrayDeque<Integer> a = new ArrayDeque<>();
//        a.add(6);
//        a.add(4);
//        System.out.println(a.size());
//        System.out.println(a.getFirst());
//        System.out.println(a.getLast());
//    }
//}

//l-95
//import java.util.HashSet;
//
//public class lecjj {
//    public static void main(String[] args) {
//        // Create a HashSet with initial capacity 6 and load factor 0.85
//        HashSet<Integer> myHashSet = new HashSet<>(6, 0.85f);
//        // Add elements
//        myHashSet.add(6);
//        myHashSet.add(7);
//        myHashSet.add(8);
//        // Print the HashSet
//        System.out.println("HashSet elements: " + myHashSet);
//    }
//}

//l-96
//public class lecjj {
//    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis()/1000/3600/24/365);
//    }
//}

//l-97
//    import java.util.Date;
//public class lecjj {
//    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(date);
//    }
//}

//l-98
//public class lecjj {
//    public static void main(String[] args) {
//        Calendar c= Calendar.getInstance();
//        System.out.println(c.getCalendarType());
//        System.out.println(c.getTimeZone());
//        System.out.println(c.getTime());
//    }
//}

//l-99
//public class lecjj {
//    public static void main(String[] args) {
//        Calendar c = Calendar.getInstance();
//        System.out.println(c.getTime());
//        System.out.println(c.get(Calendar.YEAR));
//        System.out.println(c.get(Calendar.MONTH));
//        System.out.println(c.get(Calendar.DAY_OF_MONTH));
//        System.out.println(c.get(Calendar.HOUR_OF_DAY));
//        System.out.println(c.get(Calendar.MINUTE));
//        System.out.println(c.get(Calendar.SECOND));
//        System.out.println(TimeZone.getAvailableIDs()[1]);
//    }
//}

//l-100
//package com.company;
//import java.time.localDate;
//public class lecjj {
//    public static void main(String[] args) {
//        localDate d = localDate.now();
//    }
//}






import java.util.*;
public class lecjj {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(int i=0; i<4; i++){
            list.add(list.get(i)+list.get(i+1));
        }
    }
}






