-- !Ups
CREATE TABLE "contact_form"
(
    "id"           SERIAL        NOT NULL,
    "name"         VARCHAR(128)  NOT NULL,
    "emailAddress" VARCHAR(128)  NOT NULL,
    "message"      VARCHAR(2048) NOT NULL,
    "creationDate" TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_contactForm PRIMARY KEY ("id")
);

-- !Downs
DROP TABLE "contact_form";