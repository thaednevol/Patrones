package txt;

import java.util.*;

public class Item {

  private HashMap<String, Category> categories;
  private String desc;

  public Item(String s) {
    desc = s;
    categories = new HashMap<String, Category>();
  }

  public String getDesc() {
    return desc;
  }
  public void add(Category cat) {
    categories.put(cat.getDesc(), cat);
  }
  public void delete(Category cat) {
    categories.remove(cat.getDesc());
  }

}
