public class Client {

  public static void main(String[] args) {
    CreditCard cc =
      new VisaCard("1234123412341234",11, 2021);
    if (cc.isValid())
      System.out.println("Valid Credit Card Information");
  }
}
