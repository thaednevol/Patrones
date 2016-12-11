package src.prototype;

public class AccountPrototypeFactory {

  private static UserAccount accountRep;
  private static UserAccount supervisor;

  public AccountPrototypeFactory(UserAccount supervisorAccount,
      UserAccount arep) {
    accountRep = arep;
    supervisor = supervisorAccount;
  }
  public static UserAccount getAccountRep() {
    return (UserAccount) accountRep.clone();
  }
  public static UserAccount getSupervisor() {
    return (UserAccount) supervisor.clone();
  }
}
