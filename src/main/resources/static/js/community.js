function post() {
    var questionId = $(" #question_id ").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if(response.code == 200){
                $("#comment_section").hide();//请求成功关闭回复框
            }else {
                if(response.code == 2003){
                //登录异常
                    var isAccept = confirm(response.message);//windows对象自带的confirm框，没有登录时会弹出对话框，点击yes返回true
                    if(isAccept){
                        //点击yes 条状到登录页面
                        //打开新的窗口
                        window.open("https://github.com/login/oauth/authorize?client_id=553a246519d95c8aaad2&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable","true");
                    }
                }else {
                    //不是登录异常
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}