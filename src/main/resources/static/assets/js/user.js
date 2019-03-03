function refresh(){
    window.location.reload();
}


function enterDeleteUser(numberPhone) {

    console.info(numberPhone);
    var x;
    var r=confirm("是否删除此用户: "+numberPhone);

    $.ajax({
            url:"deleteUser",
            type:"post",
            data:{"numberPhone":numberPhone},
            datatype:"TEXT",
            success:function (msg) {
                if(msg == "true"){
                    alert("删除成功");
                    refresh();
                }else {
                    alert("删除失败");
                    refresh();

                }
            },
            error:function (msg) {
                alert("请求失败"+msg);
                refresh();
            }
    });
}

function getAllUser(){

    $.ajax({
        url:"/getAllUser",
        type:"post",
        datatype:"json",
        success:function (userdata) {
            var table = $("#userlist");
            userdataLength = userdata.length;
            for(var i = 0;i<userdataLength;i++){
                var name = userdata[i].name;
                var passwd = userdata[i].passwd;
                var numberPhone = userdata[i].numberPhone;
                if(i % 2 == 0) {
                    table.append('<tr class="gradeX">' +
                        '<td >'+name+'</td>'+
                        '<td >' + numberPhone + '</td>' +
                        '<td>' + passwd + '</td>' +
                        '<td>'+'<button style="color: #1b961b" onclick="enterDeleteUser('+numberPhone+')" class="tpl-table-black-operation-del">删除</button>'+ '</td>'+
                        '</tr>');
                }else {
                    table.append('<tr class="gradeC">' +
                        '<td >'+name+'</td>'+
                        '<td >' + numberPhone + '</td>' +
                        '<td>' + passwd + '</td>' +
                        '<td>'+'<button style="color: #1b961b" onclick="enterDeleteUser('+numberPhone+')" class="tpl-table-black-operation-del">删除</button>'+ '</td>'+
                        '</tr>');

                }
            }
        }
    });
}

function regUser() {
    var username = document.getElementById("username").value;
    var userpasswd = document.getElementById("userpasswd").value;
    var numberPhone = document.getElementById("numberPhone").value;

    $.ajax({
        url:"/regUser",
        type:"post",
        data:{"username":username,"userpasswd":userpasswd,"numberPhone":numberPhone},
        datatype:"TEXT",
        sucess:function (msg) {
                alert("注册成功");
                refresh();
        },
        error:function (msg) {
            alert("请求失败"+msg);
        }

    });

}