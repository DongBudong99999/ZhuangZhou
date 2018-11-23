function post_file() {
    alert("进入js")

    $.ajax({
        type: "post",
        url: "upload/getPath",    //向后端请求数据的url--需要修改
        data: {},
        success: function (data) {
            alert("回调")
            alert(data.value())
        }
    });
}
