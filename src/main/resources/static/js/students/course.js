$(function () {
  $(".find-student").on("click", function () {
    //버튼은 class로
    $.ajax({
      url: "/students/course/on",
      type: "post",
      headers,
      data: { cno: $("#cno").val() }, //값은 id로
      success: function (cno) {
        alert("성공");
      },
      error: function (cno) {
        alert("실패");
      },
    });
  });
});

/*success: function (result) {
      $("#students-list").html(result);
      },*/
