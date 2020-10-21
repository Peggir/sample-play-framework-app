'use strict';

var contactFormEl = $('#contact-form');
var formNameEl = $('#form-name');
var formEmailEl = $('#form-email');
var formMessageEl = $('#form-message');
var formSubmitBtnEl = $('#form-submit-btn');
var deleteFormBtnEls = $('.delete-form-btn');

var isSubmittingForm = false;
var isDeletingForm = false;

$(function () {
    console.log('Initialized script.');
    contactFormEl.submit(handleFormSubmit);
    deleteFormBtnEls.click(handleDeleteContactForm);
});

function handleFormSubmit(e) {
    e.preventDefault();

    if (isSubmittingForm) {
        return;
    }

    setLoading(true);
    clearErrors();
    if (!validateForm()) {
        setLoading(false);
        return;
    }

    $.ajax({
        url: jsRoutes.controllers.api.ContactFormApi.submit().url + '?csrfToken=' + csrfToken,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            name: formNameEl.val(),
            emailAddress: formEmailEl.val(),
            message: formMessageEl.val()
        })
    }).done(function () {
        console.log('Successfully submitted contact form');
        formNameEl.val('');
        formEmailEl.val('');
        formMessageEl.val('');
    }).fail(function (e) {
        console.log('Error submitting contact form', e);
    }).always(function () {
        setLoading(false);
    });

    function setLoading(load) {
        isSubmittingForm = load;
        formSubmitBtnEl.attr('disabled', load);
        formNameEl.attr('disabled', load);
        formEmailEl.attr('disabled', load);
        formMessageEl.attr('disabled', load);
    }

    function clearErrors() {
        formNameEl.removeClass('is-invalid');
        formEmailEl.removeClass('is-invalid');
        formMessageEl.removeClass('is-invalid');
    }

    function validateForm() {
        var isValid = true;

        if (formNameEl.val().trim().length === 0) {
            isValid = false;
            formNameEl.addClass('is-invalid');
        }

        if (formEmailEl.val().trim().length === 0) {
            isValid = false;
            formEmailEl.addClass('is-invalid');
        }

        if (formMessageEl.val().trim().length === 0) {
            isValid = false;
            formMessageEl.addClass('is-invalid');
        }

        return isValid;
    }
}

function handleDeleteContactForm(e) {
    e.preventDefault();
    var btnEl = $(this);
    var contactFormId = parseInt(btnEl.attr('data-id'));

    if (isDeletingForm) {
        return;
    }

    setLoading(true);
    if (!confirm('Are you sure you want to delete this contact form?')) {
        setLoading(false);
        return;
    }

    $.ajax({
        url: jsRoutes.controllers.api.ContactFormApi.delete(contactFormId).url + '?csrfToken=' + csrfToken,
        type: 'DELETE'
    }).done(function () {
        $('#form-row-' + contactFormId).remove();
    }).fail(function (e) {
        console.log('Error deleting contact form', e);
    }).always(function () {
        setLoading(false);
    });

    function setLoading(load) {
        isDeletingForm = load;
        btnEl.attr('disabled', load);
    }
}
