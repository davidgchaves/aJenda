package models;

// JPA related
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import play.db.ebean.Model;

@Entity
public class Contact extends Model {

  @Id
  @GeneratedValue
  public long Id;

  public String name;
  public String phone;
  public String email;

  public static Finder<Long, Contact> finder = new Finder<Long, Contact>(Long.class, Contact.class);
}
