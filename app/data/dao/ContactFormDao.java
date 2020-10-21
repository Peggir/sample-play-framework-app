package data.dao;

import com.peggir.SimpleDbUtil.DbCall;
import com.peggir.SimpleDbUtil.exceptions.DbCallException;
import data.models.ContactForm;
import data.resultSetMapper.ContactFormResultSetMapper;
import play.db.Database;

import java.util.List;

public class ContactFormDao {

    private final Database database;

    public ContactFormDao(final Database database) {
        this.database = database;
    }

    public void insert(final ContactForm newForm) throws DbCallException {
        new DbCall<>(
                database,
                "INSERT INTO \"contact_form\" (\"name\", \"emailAddress\", \"message\") VALUES (?,?,?);",
                stmt -> {
                    stmt.setString(1, newForm.getName());
                    stmt.setString(2, newForm.getEmailAddress());
                    stmt.setString(3, newForm.getMessage());
                }
        ).execute();
    }

    public List<ContactForm> getAll() throws DbCallException {
        return new DbCall<>(
                database,
                new ContactFormResultSetMapper(),
                "SELECT \"id\", \"name\", \"emailAddress\", \"message\", \"creationDate\" " +
                        "FROM \"contact_form\" " +
                        "ORDER BY \"creationDate\" DESC;"
        ).getAll();
    }

    public void delete(final int contactFormId) throws DbCallException {
        new DbCall<>(
                database,
                "DELETE FROM \"contact_form\" WHERE \"id\"=?;",
                stmt -> stmt.setInt(1, contactFormId)
        ).execute();
    }

}
