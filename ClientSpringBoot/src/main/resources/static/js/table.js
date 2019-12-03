$(function () {
    getTable();
});
const html = $('.table');

function getTable() {
    reset();
    $.getJSON('/admin/users', function (users) {
        users.forEach(function (user) {
            let userID = user['id'];
            let userName = user['username'];
            let email = user['email'];
            let userPass = user['password'];
            let userRole = user['roles'];
            html.append(`
                    <tr class="table_tr">
                       <td scope='row'>` + userID + `</td>
                       <td>` + userName + `</td>
                       <td>` + email + `</td>
                       <td>` + userPass + `</td>
                       <td>` + userRole + `</td>
                       <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal-` + userID + `"> Edit</button>
                          
                            <!-- Edit Modal -->
                            <div class="modal text-center" id="exampleModal-` + userID + `" tabindex='-1' aria-labelledby="editModalLabel" role="dialog" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editModalLabel">Edit user</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                           <form action=/admin/${user['id']} onsubmit='return editById(this)'>
                                                <div class="form-group">
                                                    <label for="idInput" class="font-weight-bold">ID</label>
                                                    <input type="text"  name="userId" class="form-control" 
                                                            id="idInput" value="` + userID + `" readonly/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="emailInput" class="form-control-label font-weight-bold">Email</label>
                                                    <input type="email"  name="email" class="form-control" autofocus="autofocus"  
                                                        id="emailInput" value="` + email + `" />
                                                </div>
                                                <div class="form-group">
                                                    <label for="loginInput" class="form-control-label font-weight-bold">Login</label>
                                                    <input type="text"  name="username" class="form-control"  
                                                        id="loginInput" value="` + userName + `" />
                                                </div>
                                                <div class="form-group">
                                                    <label for="passwordInput" class="form-control-label font-weight-bold">Password</label>
                                                    <input type="password" name="password" class="form-control"  
                                                        id="passwordInput" value="` + userPass + `" />
                                                </div>
                                                <div class="form-group">
                                                    <input type="submit" class="form-control btn btn-primary" value="Edit user" />
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                       </td>
                       <td>
                          <button onclick='deleteUser(${user.id})' class='btn btn-primary' id='userDelete'> Delete </button>
                       </td>
                    </tr>
                   
            `);
            // console.log(users);
        });

        // $('.table').append(html);
    });
}

function reset() {
    $('.table_tr').html("");
}