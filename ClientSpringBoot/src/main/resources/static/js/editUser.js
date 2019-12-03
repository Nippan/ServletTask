function editById(form) {
    let $form = $(form);
    const user = {
            username:$form.find( "input[name='username']" ).val(),
            email:$form.find( "input[name='email']" ).val(),
            password:$form.find( "input[name='password']" ).val(),
    };
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url:$form.attr('action'),
        data: JSON.stringify(user),
        dataType : 'json',
        success:function () {
            alert(user.username + ' - updated');
            location.reload();
        },
        error : function(e) {
            alert('Данные не отправлены')
        }
    });

    return false;
}