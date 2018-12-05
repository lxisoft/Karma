function markGenuine(needId) {
    $.ajax({
        type: 'POST',
        url: '/user-checks/markingGenuineness',
        headers: {
            'X-CSRF-TOKEN': $("input[name='_csrf']").val()
        },
        data: {
            'isGenuine': true,
            'checkedNeedId': needId,
            '_csrf': $("input[name='_csrf']").val()
        },
        success: function (response) {
            console.log("marked genuine successfully");
            //$("#needs").html(response);
            //window.location.reload();
            $("#needs").html(response);

            if (response.success) {
                console.log("marked genuine successfully in if true");
            }
            //document.getElementById("success-message").innerHTML="success";
        },
        error: function () {
            console.log("marked genuine failed...!");
        }
    });
}
function markFake(needId) {
    $.ajax({
        type: 'POST',
        url: '/user-checks/markingGenuineness',
        headers: {
            'X-CSRF-TOKEN': $("input[name='_csrf']").val()
        },
        data: {
            'isGenuine': false,
            'checkedNeedId': needId,
            '_csrf': $("input[name='_csrf']").val()
        },
        success: function (response) {
            console.log("marked fake successfully");
            $("#needs").html(response);
            if (response.success) {

                //document.getElementById("success-message").innerHTML="success";
                console.log("marked fake successfully in if condition");
            }
        },
        error: function () {
            console.log("marked fake failed...!");
        }
    });
}
function viewComments(id) {
    var element = "#comment-box-" + id;
    console.log("view comments worked with id: " + element);
    //$('#comments-' + needId).show();
    $('#comments-section-' + id).load('/comments/getAllCommentsByHelpId/' + id);
    $('#comments-section-' + id).show();
}
function viewReplies(commentId) {
    $('#comments-section-' + needId).show();
    $('#replies-section-' + commentId).load('/comments/getAllCommentsByNeedId/' + commentId);
}
function addComment(id) {
    var element = "#comment-box-" + id;
    console.log("addComment: " + element + ' ' + $(element).data("checkedneedid"));
    console.log("message: " + $("#comment-box-text-" + id).val());
    /* 			if ($(element).data("checkedNeedId") != null)
                    id = needId;
                else if (helpId != null)
                    id = helpId;
                else
                    id = postId; */

    $.ajax({
        type: 'POST',
        url: '/comments/addComment',
        headers: {
            'X-CSRF-TOKEN': $("input[name='_csrf']").val()
        },
        data: {
            'needId': $(element).data("checkedneedid"),
            'helpId': $(element).data("checkedhelpid"),
            'postId': $(element).data("postid"),
            'message': $("#comment-box-text-" + id).val(),
            'dateInString': getCurrentTime(),
            'commentedUserId': 1,
            '_csrf': $("input[name='_csrf']").val()
        },
        success: function (response) {
            console.log("comment added successfully " + id);

            $("#comments-" + id).html(response);
            if (response.success) {

                //document.getElementById("success-message").innerHTML="success";
                console.log("comment added successfully in if condition");
            }
        },
        error: function () {
            console.log("comment added failed...!");
        }
    });
}
function addReply(commentId) {
    $.ajax({
        type: 'POST',
        url: '/replies/addReply',
        headers: {
            'X-CSRF-TOKEN': $("input[name='_csrf']").val()
        },
        data: {
            'commentId': commentId,
            'message': $("#reply-box-text-" + commentId).val(),
            'repliedUserId': 1,
            'dateInString': getCurrentTime(),
            '_csrf': $("input[name='_csrf']").val()
        },
        success: function (response) {
            console.log("reply added successfully " + commentId);

            $("#replies-" + commentId).html(response);

            if (response.success) {

                //document.getElementById("success-message").innerHTML="success";
                console.log("reply added successfully in if condition");
            }
        },
        error: function () {
            console.log("reply added failed...!");
        }
    });
}
function likeComment(commentId) {
    /* 			$.ajax({
                    type: 'POST',
                    url: 'user-checks/markingGenuineness',
                    data: {
                        'commentId': commentId,
                        'checkedUserId': 1
                    },
                    success: function (response) {
                        console.log("marked fake successfully");
                        if (response.success) {
                            $("#needs").load("#needs");
                            //document.getElementById("success-message").innerHTML="success";
                        }
                    },
                    error: function () {
                        console.log("marked fake failed...!");
                    }
                }); */
    console.log("like comment worked with commentId:" + commentId);
}
function likeReply(replyId) {
    $.ajax({
        type: 'POST',
        url: 'user-checks/markingGenuineness',
        data: {
            isGenuine: "false",
            checkedNeedId: needId
        },
        success: function (response) {
            console.log("marked fake successfully");
            if (response.success) {
                $("#needs").load("#needs");
                //document.getElementById("success-message").innerHTML="success";
            }
        },
        error: function () {
            console.log("marked fake failed...!");
        }
    });
}

function getCurrentTime() {
    var date = new Date();
    var year = date.getFullYear();
    var month = (date.getMonth() < 9) ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
    var day = (date.getDate() < 10) ? "0" + date.getDate() : date.getDate();
    var hours = (date.getHours() < 10) ? "0" + date.getHours() : date.getHours();

    seconds = (date.getSeconds() < 10) ? "0" + date.getSeconds() : date.getSeconds();
    var minutes = (date.getMinutes() < 10) ? "0" + date.getMinutes() : date.getMinutes();

    //var hours = date.getHours();
    //var minutes = date.getMinutes();
    //var seconds = date.getSeconds();
    console.log("getCurrentTime()" + year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds);
    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}