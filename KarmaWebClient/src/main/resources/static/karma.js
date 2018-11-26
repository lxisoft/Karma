function markGenuine(needId) {
    $.ajax({
        type: 'POST',
        url: 'user-checks/markingGenuineness',
        data: {
            isGenuine: "true",
            checkedNeedId: needId
        },
        success: function (response) {
            console.log("marked genuine successfully");
            if (response.success) {
                $("#needs").load(" #needs");
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
        url: 'user-checks/markingGenuineness',
        data: {
            isGenuine: "false",
            checkedNeedId: needId
        },
        success: function (response) {
            console.log("marked fake successfully");
            if (response.success) {
                $("#needs").load(" #needs");
                //document.getElementById("success-message").innerHTML="success";
            }
        },
        error: function () {
            console.log("marked fake failed...!");
        }
    });
}
function viewComments(needId) {
    $('#comments-' + needId).load(url);
}
function viewReplies(commentId) {
    $('#replies-' + commentId).load(url);
}
function commentOnNeed(needId) {

}
function likeComment(commentId) {
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

function commentOnHelp(helpId) {

}

function likeHelp(helpId) {
    $.ajax({
        type: 'POST',
        url: 'user-checks/markingGenuineness',
        data: {
            isGenuine: "false",
            id: helpId
        },
        success: function (response) {
            console.log("marked fake successfully");
            if (response.success) {
                $("#needs").load(" #needs");
                //document.getElementById("success-message").innerHTML="success";
            }
        },
        error: function () {
            console.log("marked fake failed...!");
        }
    });
}