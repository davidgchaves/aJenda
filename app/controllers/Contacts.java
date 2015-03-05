package controllers;

import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

import models.Contact;

public class Contacts extends Controller{

    public static Result index() {
        List<Contact> contacts = Contact.finder.all();
        return ok(views.html.contacts.index.render(contacts));
    }

    public static Result newContact() {
        Form<Contact> contactForm = Form.form(Contact.class);
        return ok(views.html.contacts.newContact.render(contactForm));
    }

    public static Result create() {
        Form<Contact> contactForm = Form.form(Contact.class).bindFromRequest();
        Contact contact = contactForm.get();
        contact.save();
        return redirect(routes.Contacts.index());
    }

    public static Result show(Long id) {
      return ok();
    }

}
