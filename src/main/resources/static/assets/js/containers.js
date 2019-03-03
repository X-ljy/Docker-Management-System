function refresh(){
    window.location.reload();
}


function getAllIp() {
    $.ajax({
        url:"/allServerIP",
        type:"get",
        dataType:"json",
        success:function getAllip(data) {
            // alert(data[0].ip + "  " + data[1].ip );
            for(var k = 0;k<data.length;k++){
                getAllContainersInfo(data,k);
            }
        },
        error:function (data) {
        }
    })
}
function getAllContainersInfo(data,k){
            $.ajax({
               url:"/containersInfo",
               type:"post",
                data:{"dockerServerIP":data[k].ip},
               datatype:"json",
                success:function (containerInfodata) {

                   var table = $("#containlist");
                   containerInfodata = $.parseJSON(containerInfodata);
                   var  responseLength = containerInfodata.length;
                   for (var i = 0;i<responseLength;i++) {

                           var ip = "'"+data[k].ip+"'";
                           var ID = "'"+containerInfodata[i].Id.slice(0,12)+"'";
                           var name = containerInfodata[i].Names;
                           var state = containerInfodata[i].State;
                           var Status = containerInfodata[i].Status;
                           if(i%2 == 0) {
                               table.append('<tr class="gradeX">' +
                                   '<td id='+ID.toString()+'ip>'+ip+'</td>'+
                                   '<td id='+ID+'>' + ID + '</td>' +
                                   '<td>' + name + '</td>' +
                                   '<td>' + state + '</td>' +
                                   '<td>' + Status + '</td>' +
                                   '<td>'+'<button style="color: #1b961b" onclick="enterStart('+ip+','+ID+')" class="tpl-table-black-operation-del">启动</button>'+ '</td>'+
                                   '<td>'+'<button style="color: #1b961b" onclick="enterRestart('+ip+','+ID+')" class="tpl-table-black-operation-del">重启</button>'+ '</td>'+
                                   '<td>'+'<button style="color: #1b961b" onclick="enterStop('+ip+','+ID+')" class="tpl-table-black-operation-del">停止</button>'+ '</td>'+
                                   '<td>'+'<button style="color: #1b961b" onclick="enterDelete('+ip+','+ID+')" class="tpl-table-black-operation-del">删除</button>'+ '</td>'+
                                   '</tr>');
                           }else {
                               table.append('<tr class="gradeC">' +
                                   '<td id='+ID.toString()+'ip>'+ip+'</td>'+
                                   '</td id='+ID+'>'+'<td>' + ID + '</td>' +
                                   '<td>' + name + '</td>' +
                                   '<td>' + state + '</td>' +
                                   '<td>' + Status + '</td>' +
                                   '<td>'+'<button style="color: #1b961b" onclick="enterStart('+ip+','+ID+')" class="tpl-table-black-operation-del">启动</button>'+ '</td>'+
                                   '<td>'+'<button style="color: #1b961b" onclick="enterRestart('+ip+','+ID+')" class="tpl-table-black-operation-del">重启</button>'+ '</td>'+
                                   '<td>'+'<button style="color: #1b961b" onclick="enterStop('+ip+','+ID+')" class="tpl-table-black-operation-del">停止</button>'+ '</td>'+
                                   '<td>'+'<button style="color: #1b961b" onclick="enterDelete('+ip+','+ID+')" class="tpl-table-black-operation-del">删除</button>'+ '</td>'+
                                   '</tr>');
                           }

                   }
               }
            });

}

function enterStart(dockerServerIP,containerID){
    console.info(containerID);
    var x;
    var r=confirm("是否启动此容器: "+containerID);
    if (r==true){
        $.ajax({
            url:"/startContainer",
            type:"POST",
            data:{"dockerServerIP":dockerServerIP,"containerID":containerID},
            datatype:'TEXT',
            success:function (result) {
                if(result == 204) {
                    alert("启动成功");
                    refresh();
                }else {
                    alert("启动失败");
                    refresh();
                }
            },
            error:function (result) {
                console.log(result);
                alert("请求失败");
                refresh();
            }

        });
    }
    else{
    }
}


function enterRestart(dockerServerIP,containerID){
    console.info(containerID);
    var x;
    var r=confirm("是否重启此容器: "+containerID);
    if (r==true){
        $.ajax({
            url:"/restartContainer",
            type:"POST",
            data:{"dockerServerIP":dockerServerIP,"containerID":containerID},
            datatype:'TEXT',
            success:function (result) {
                if(result == 204) {
                    alert("重启成功");refresh();
                }else {
                    alert("重启失败");refresh();
                }
            }
        });
    }
    else{
    }
}

function enterStop(dockerServerIP,containerID){
    console.info(containerID);
    var x;
    var r=confirm("是否停止此容器: "+containerID);
    if (r==true){
        x="你按下了\"确定\"按钮!";
        $.ajax({
            url:"/killContainer",
            type:"POST",
            data:{"dockerServerIP":dockerServerIP,"containerID":containerID},
            datatype:'TEXT',
            success:function (result) {
                if(result == 204) {
                    alert("停止成功");refresh();
                }else {
                    alert("停止失败");refresh();
                }
            },
            error:function (result) {
                alert("请求失败"+result);refresh();
            }

        });
    }
    else{
    }
};

function enterDelete(dockerServerIP,containerID){
    console.info(dockerServerIP  ,  containerID);
    var x;
    var r=confirm("是否删除此容器: "+containerID);
    if (r==true){
        $.ajax({
            url:"/removeContainer",
            type:"POST",
            data:{"dockerServerIP":dockerServerIP,"containerID":containerID},
            datatype:'TEXT',
            success:function (result) {
                if(result == 204) {
                    alert("删除成功");refresh();
                }else {
                    alert("删除失败");refresh();
                }
            },
            error:function (result) {
                alert("请求失败");refresh();
            }
        });
    }
    else{
    }
};

function createContainerByImage() {
    var dockerServerIP = document.getElementById("hostip").value;
    var imageID = document.getElementById("imageID").value;
    var containerName = document.getElementById("name").value;

    console.log(dockerServerIP+imageID+containerName);

    $.ajax({
        url: '/createContainer',
        type: 'post',
        data:{"dockerServerIP":dockerServerIP,"imageID":imageID,"containerName":containerName},
        datatype:"TEXT",
        success:function (response) {
            alert(response+"response");
            if(response == 201){
                alert("创建成功");
            }else {
                alert("创建失败，请检查dockerfile语法！！！");
            }
        },
        error:function (response) {
            alert("请求失败"+response);
        }
    });

}



