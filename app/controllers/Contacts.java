package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

import models.Contact;

public class Contacts extends Controller{
    public static Result newContact() {
        Form<Contact> contactForm = Form.form(Contact.class);
        return ok(views.html.contacts.newContact.render(contactForm));
    }

    public static Result create() {
        return ok();
    }
}
