package data.models;

import java.util.Date;

public class ContactForm {

    private int id;
    private String name;
    private String emailAddress;
    private String message;
    private Date creationDate;

    public ContactForm() {
    }

    public ContactForm(
            final int id,
            final String name,
            final String emailAddress,
            final String message,
            final Date creationDate) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.message = message;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}
