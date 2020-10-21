package controllers;

import com.peggir.SimpleDbUtil.exceptions.DbCallException;
import data.dao.ContactFormDao;
import data.models.ContactForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import views.html.about;
import views.html.index;
import views.html.overview;

import javax.inject.Inject;
import java.util.List;

public class Application extends Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ContactFormDao contactFormDao;

    @Inject
    public Application(final Database database) {
        contactFormDao = new ContactFormDao(database);
    }

    public Result index(final Request request) {
        return ok(index.render(request));
    }

    public Result overview(final Request request) {
        try {
            final List<ContactForm> forms = contactFormDao.getAll();
            return ok(overview.render(forms, request));
        } catch (final DbCallException e) {
            logger.error("Database error when retrieving the overview", e);
            return internalServerError("Database error.");
        }
    }

    public Result about(final Request request) {
        return ok(about.render(request));
    }

}
