function refresh(){
    window.location.reload();
}

<!--转换进制的js函数-->
var byteConvert = function(bytes) {
    if (isNaN(bytes)) {
        return '';
    }
    var symbols = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    var exp = Math.floor(Math.log(bytes)/Math.log(2));
    if (exp < 1) {
        exp = 0;
    }
    var i = Math.floor(exp / 10);
    bytes = bytes / Math.pow(2, 10 * i);
    if (bytes.toString().length > bytes.toFixed(2).toString().length) {
        bytes = bytes.toFixed(2);
    }
    return bytes + ' ' + symbols[i];
};


function getAllIp() {
    $.ajax({
        url:"/allServerIP",
        type:"get",
        dataType:"json",
        success:function getAllip(data) {
            // alert(data[0].ip + "  " + data[1].ip );
            for(var k = 0;k<data.length;k++){
                allimageinfo(data,k);
            }
        },
        error:function (data) {
        }
    })
}

function allimageinfo(data,k) {
    $(function () {
        $.ajax({
            url: "/allImageInfo",
            type: "post",
            data: {"dockerServerIP":data[k].ip},
            dataType: "json",
            success: function (imagesInfoData) {
                var table = $("#imagelist");
                console.log(imagesInfoData.length);
                // imagesInfoData = $.parseJSON(imagesInfoData);
                var  imagesInfoLength = imagesInfoData.length;
                for(var i = 0;i<imagesInfoLength;i++) {
                    var ip = "'"+data[k].ip+"'";
                    var ID = "'"+imagesInfoData[i].Id.slice(7,19)+"'";
                    var RepoTags = imagesInfoData[i].RepoTags;
                    var Size = byteConvert(parseInt(imagesInfoData[i].Size));
                    if (i % 2 == 0) {
                        table.append('<tr class="gradeX" >' +
                            '<td id='+ID.toString()+'ip>'+ip+'</td>'+
                            '<td id=' + ID + '>' + ID + '</td>' +
                            '<td >' + RepoTags + '</td>' +
                            '<td>' + Size + '</td>' +
                            '<td>' + '<button style="color: #1b961b" class="tpl-table-black-operation-del" ' +
                            ' onclick="enter('+ip+','+ID+')" >删除</button>' + '</td>' +
                            '</tr>');
                    } else {
                        table.append('<tr class="gradeX" >' +
                            '<td id='+ID.toString()+'ip>'+ip+'</td>'+
                            '<td id=' + ID + '>' + ID + '</td>' +
                            '<td >' + RepoTags + '</td>' +
                            '<td>' + Size + '</td>' +
                            '<td>' + '<button style="color: #1b961b" class="tpl-table-black-operation-del" ' +
                            ' onclick="enter('+ip+','+ID+')" >删除</button>' + '</td>' +
                            '</tr>');
                    }
                }
            }
        });
    });
}

function enter(dockerServerIP,imageID){
    console.info(imageID);
    var x;
    var r=confirm("是否删除此镜像: "+imageID);
    if (r==true){
        $.ajax({
            url:"/deleteImage",
            type:"POST",
            data:{"dockerServerIP":dockerServerIP,"imageID":imageID},
            datatype:'TEXT',
            success:function (result) {
                if(result == 200) {
                    alert("删除成功");
                    refresh();
                }else {
                    alert("删除失败");
                    refresh();
                }
            },
            error:function (result) {
                alert("请求失败");
                refresh();
            }
        });
    }
    else{
    }
}

function BuildImage() {
    var formdata = new FormData();

    var dockerServerIP = document.getElementById("hostip").value;
    var name = document.getElementById("name").value;
    var tag =  document.getElementById("tag").value;
    var file = $("#filename")[0].files[0];
    formdata.append("dockerServerIP",dockerServerIP);
    formdata.append("name",name);
    formdata.append("tag",tag);
    formdata.append("file",file);

    alert(dockerServerIP+name+tag);
    $.ajax({
        url: '/buileByDockerfile',
        type: 'post',
        cache: false,
        data: formdata,
        processData: false,
        contentType: false,
        success:function (response) {
            alert(response+"response");
            if(response == 200){
                alert("定制成功");
            }else {
                alert("定制失败，请检查dockerfile语法！！！");
            }
        },
        error:function (response) {
            alert("请求失败"+response);
        }
    });
}

























