/**
 * 提交问题的回复
 */
function post() {
    var questionId = $(" #question_id ").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

/**
 *封装的提交回复功能 包括问题回复和评论回复
 * @param targetId
 * @param type
 * @param content
 */
function comment2target(targetId, type, content) {
    if (!content) {
        alert("内容不能为空");//显示对话框
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",/*是在这请求的commentController 局部刷新 不用另外的html*/
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {/*response是ResultDTO类型的*/
            if (response.code == 200) {
                window.location.reload();//重新加载页面
                ///$("#comment_section").hide();//请求成功关闭回复框
            } else {
                if (response.code == 2003) {
                    //登录异常
                    var isAccept = confirm(response.message);//windows对象自带的confirm框，没有登录时会弹出对话框，点击yes返回true
                    if (isAccept) {
                        //点击yes 条状到登录页面
                        //打开新的窗口
                        window.open("https://github.com/login/oauth/authorize?client_id=553a246519d95c8aaad2&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable", "true");
                    }
                } else {
                    //不是登录异常
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

/**
 * 提交评论的回复
 * @param commentId
 */
function comment(e) {
    //拿到被评论的comment的id
    var commentId = e.getAttribute("data-id");//this是当前点击的this元素
    //获取input输入的content 评论内容
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    //拿到页面的id 接下来把当前的id里面评论对应的属性改变
    var id = e.getAttribute("data-id");//this是当前点击的this元素
    var comments = $("#comment-" + id);//获取二级评论的id 拿到comments类  一级评论绑定二级评论
    //向class的collapse添加或删除in，然后就有了下拉显示功能
    //展开或折叠二级评论
    comments.toggleClass("in");//toggleClass 切换功能 如果有该类就删除 没有就添加
    var hasIn = comments.hasClass("in");//判断comments元素是否包含in类
    //展开了二级评论 获取二级评论内容并显示到页面
    if (hasIn) {
        var subCommentContainer = $("#comment-" + id);//根据id获取二级评论的类 不要忘记#号
        if(subCommentContainer.children().length ==1 )//为了不重复显示子评论
        {
            //链接/comment/"+id地址 然后获取commentController的第二个api返回的结果data是ResultDTO<List<CommentDTO>>
            $.getJSON("/comment/" + id, function (data) {
                //拼接标签
                console.log(data);
                $.each(data.data.reverse(), function (index,comment) {//相当于foreach  遍历所有的子元素
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
            });
        }
    }
}

/*点击input时候显示标签框*/
function showSelectTag() {
    $("#select-tag").show();
}

/*点击标签 写入input中去*/
function selectTag(e) {
    var value = e.getAttribute("data-tag");//value就是publishHtml中的selectTag
    /*点击值放进input中*/
    var previous = $("#tag").val();/*获取input的id对应的类 就是input类 previous是该类的值*/

    if(previous.indexOf(value) == -1)/*原来没有该值 添加input*/
    {
        if(previous){
            $("#tag").val(previous + ',' + value);/*如果原来有值就添加上去 如果没有就直接写入*/
        }else {
            $("#tag").val(value);
        }
    }
}