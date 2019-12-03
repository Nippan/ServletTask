$(function () {
    $('#createUser').submit(function(event) {
        // Предотвращаем обычную отправку формы
        event.preventDefault();
        addUser();
    });

    function addUser() {
        const user = {
            username:$('#login').val(),
            email:$('#email').val(),
            password:$('#password').val(),
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url:$('#createUser').attr('action'),
            data: JSON.stringify(user),
            dataType : 'json',
            success:function () {
                alert(user.username + ' - added');
                getTable();
            },
            error : function(e) {
                alert(user.username + ' - is Exist!');
                console.log("ERROR: ", e);
            }
        });
        resetData();
    }

    function resetData(){
        $("#login").val("");
        $("#email").val("");
        $("#password").val("");
    }

});