package src.shallow;


class Person implements Cloneable {
  //Lower-level object
  static private Car car;

  private String name;
  
  public static Car getCar() {
	  System.out.println("getCar()");
    return car;
  }
  public String getName() {
	  System.out.println("getName() of Person");
    return name;
  }
  public void setName(String s) {
    name = s;
  }
  public Person(String s, String t) {
    name = s;
    car = new Car(t);
  }
  public Object clone() {
    //shallow copy
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }
}
class Car {

  private String name;

  public String getName() {
	  System.out.println("getCar() + getName()");
    return name;
  }
  public void setName(String s) {
    name = s;
  }
  public Car(String s) {
    name = s;
  }
}
public class ShallowCopyTest {

  @SuppressWarnings("static-access")
public static void main(String[] args) {
    //Original Object
	  
	  System.out.println("Hola mundo");
    Person p = new Person("Person-A","Civic");
    System.out.println("Original (orginal values): " +
                       p.getName() + " - " + 
                       p.getCar().getName());

    //Clone as a shallow copy
    Person q = (Person) p.clone();

    System.out.println("Clone (before change): " +
                       q.getName() + " - " + 
                       q.getCar().getName());

    //change the primitive member
    q.setName("Person-B");

    //change the lower-level object
    q.getCar().setName("Accord");

    System.out.println("Clone (after change): " +
                       q.getName() + " - " + 
                       q.getCar().getName());

    System.out.println(
      "Original (after clone is modified): " +
      p.getName() + " - " + p.getCar().getName());
    
    Person persona = new Person("uno", "dos");
    
    getPersona(persona);
    
    System.out.println(
    	      "Original:"+persona.getName());
    	    
    
    

  }
  
  
  private static void getPersona(Person persona){
	  persona.setName("Hola mundo");
	  System.out.print(persona);
  }
}
