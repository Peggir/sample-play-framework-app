package data.resultSetMapper;

import com.peggir.SimpleDbUtil.DbCallResultSetMapper;
import com.peggir.SimpleDbUtil.exceptions.DbCallResultSetMapperException;
import data.models.ContactForm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactFormResultSetMapper implements DbCallResultSetMapper<ContactForm> {


    @Override
    public ContactForm map(final ResultSet rs) throws DbCallResultSetMapperException {
        try {
            return new ContactForm(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("emailAddress"),
                    rs.getString("message"),
                    rs.getTimestamp("creationDate")
            );
        } catch (final SQLException e) {
            throw new DbCallResultSetMapperException(DbCallResultSetMapperException.DEFAULT_ERROR_MSG, e);
        }
    }

}
