package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.peggir.SimpleDbUtil.exceptions.DbCallException;
import data.dao.ContactFormDao;
import data.models.ContactForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.Database;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;

import javax.inject.Inject;

public class ContactFormApi extends Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ContactFormDao contactFormDao;

    @Inject
    public ContactFormApi(final Database database) {
        contactFormDao = new ContactFormDao(database);
    }

    public Result submit(final Request request) {
        try {
            final JsonNode json = request.body().asJson();
            if (json == null) {
                return badRequest();
            }

            final ContactForm newForm = Json.fromJson(json, ContactForm.class);
            contactFormDao.insert(newForm);

            return created();
        } catch (final DbCallException e) {
            logger.error("Database error after submitting a new contact form", e);
            return internalServerError();
        }
    }

    public Result delete(final int contactFormId) {
        try {
            contactFormDao.delete(contactFormId);
            return ok();
        } catch (final DbCallException e) {
            logger.error("Database error during contact form deletion", e);
            return internalServerError();
        }
    }

}
