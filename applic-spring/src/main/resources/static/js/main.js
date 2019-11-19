$(document).ready(function () {
    $('#myModal').on('show.bs.modal', function(e) {
        var userId = $(e.relatedTarget).data('object-id');
        var login = $(e.relatedTarget).data('object-login');
        var email = $(e.relatedTarget).data('object-email');
        var pass = $(e.relatedTarget).data('object-pass');


        $('#idInput').val(userId);
        $('#emailInput').val(email);
        $('#loginInput').val(login);
        $('#passwordInput').val(pass);
    });

    $('#myModal').on('hidden.bs.modal', function() {
        var form = $(this).find('form');
        form[0].reset();
    });
});