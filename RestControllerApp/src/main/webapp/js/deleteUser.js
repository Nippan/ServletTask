function deleteUser(id){
    $.ajax({
        type:'DELETE',
        url:'http://localhost:8080/admin/' + id,
        success:function () {
            alert('User deleted');
            location.reload();
        },
        error : function(e) {
            $('.table').append("<strong>Error"+ e +"</strong>");
        }
    })
}