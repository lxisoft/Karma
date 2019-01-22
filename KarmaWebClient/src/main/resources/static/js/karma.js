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
    console.log("view comments worked with needId: " + id);
    //$('#comments-' + needId).show();
    var element = "#comment-data-" + id;
    var commentType;

   console.log("element: " + element + ' ' + $(element).attr("checkedNeedId"));
     
    if ($(element).data("checkedneedid") != null) {
        commentType = "Need";
        $('#comments-section-' + id).load('/comments/getAllCommentsByNeedId/' + id);

    }
    else if ($(element).data("checkedhelpid") != null) {
        commentType = "Help";
      //  $('#comments-section-' + id).load('/comments/getAllCommentsBy' + commentType + 'Id/' + id);
        $('#comments-section-' + id).load('/comments/getAllCommentsByHelpId/' + id);

    }
   /* else {
        commentType = "Post";
       // $('#comments-section-' + id).load('/comments/getAllCommentsBy' + commentType + 'Id/' + id);
        $('#comments-section-' + id).load('/comments/getAllCommentsByPostId/' + id);

    }*/
 //   $('#comments-section-' + id).load('/comments/getAllCommentsBy' + commentType + 'Id/' + id);
    $('#comments-section-' + id).show();
    /* console.log(getCurrentTime()); */
}
function viewReplies(commentId) {
    console.log("viewReplies- commentId :" + commentId + ' element: ' + $('#replies-' + commentId).val());
    $('#replies-' + commentId).load('/replies/getAllRepliesByCommentId/' + commentId);
}
function viewNeed(referenceId) {
    console.log("view need with needId: " + referenceId);
    $('#feed-need-section-' + referenceId).load('/needs/' + referenceId);
    $('#feed-need-section-' + referenceId).show();
}
function viewHelp(referenceId) {
    console.log("view help with helpId: " + referenceId);
    $('#feed-help-section-' + referenceId).load('/helps/getHelpById/' + referenceId);
    $('#feed-help-section-' + referenceId).show();
}
function addComment(id) {
    var element = "#comment-data-" + id;
    console.log("addComment: " + element + ' ' + $(element).attr("data-checkedNeedId"));
    //console.log("addComment: " + element + ' ' + $(element).data("checkedNeedId"));
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

            $("#need-" + id).html(response);
            viewComments(id);
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
function logOut() {
    $.ajax({
        type: 'POST',
        url: '/logout',
        headers: {
            'X-CSRF-TOKEN': $("input[name='_csrf']").val()
        },
        data: {
            '_csrf': $("input[name='_csrf']").val()
        },
        success: function (response) {
            console.log("logged out successfully ");

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
    //console.log("getCurrentTime()" + year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds);
    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}
function divToggle() {
	  var content = "#feed-need-section-"+feed.referenceId;
	  if (content.style.display === "none") {
	    content.style.display = "block";
	  } else {
	    content.style.display = "none";
	  }
	}
function viewFeed(id, feedId) {
    console.log("view comments worked with needId: " + id);
    var element = "#feed-data-" + id;
    
    console.log("viewfeed inside: " + element + ' ' + $(element).attr("data-need"));
    
    console.log("feed id"+feedId);
    
    if ($(element).data("need") === true) {
       
    	$('#feed-section-' + feedId).load('/needs/' + id);
    	  console.log("inside need");
    	 

    }
    else if ($(element).data("help") === true) {
       
    	$('#feed-section-' + feedId).load('/helps/getHelpById/' + id);
    	  console.log("inside help");
          
    }
    else{
    	
    }
   $('#feed-section-' + feedId).show();
}
function divToggle() {
	$(".card-feed").hide();
}

/*function divToggle() {
	$(document).ready(function () {
	    $(".card-feed").hide();
	    $(".content").on("click", function () {
	        var txt = $(".card-feed").is(':visible') ? 'Read More' : 'Read Less';
	        $(".content").text(txt);
	        $(this).next('.card-feed').slideToggle(200);
	    });
	});
}*/
