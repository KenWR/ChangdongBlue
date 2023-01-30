$(function () {
  $("#go-to-home").on("click", function () {
    $.ajax({
      async: false,
      type: "patch",
      url: "/att/off",
      contentType: "application/x-www-form-urlencoded; charset=utf-8",
      dataType: "text",
      headers,
      success: function () {
        alert("퇴근완료");
      },
      error: function () {
        alert("퇴근실패");
      },
    });
  });
});

$(function () {
  $("#go-to-work").on("click", function () {
    $.ajax({
      async: false,
      type: "post",
      url: "/att/on",
      contentType: "application/x-www-form-urlencoded; charset=utf-8",
      dataType: "text",
      headers,
      success: function () {
        alert("출근완료");
      },
      error: function () {
        alert("출근실패");
      },
    });
  });
});

$(function () {
  $("#detail-page-doc-No-btn").on("click", function () {
    var no = { no: $("#detail-page-doc-No").val() };
    $.ajax({
      type: "patch",
      url: "/att/e-absence",
      data: JSON.stringify(no),
      dataType: "json",
      headers,
      contentType: "application/json; charset=utf-8",
      success: function () {},
      error: function () {},
    });
  });
});

/*
function changeDocStat(el) {
	target=$(el);//등록버튼
	
    $.ajax({
      type: "put",
      data: {
		docNo : target.siblings(".docNo").val(),
		docStatus : target.parents("tr").find(".docStatus").val()},
      url: "/docUpdate",
      success: function () {
     	//console.log(aaaa);
      },
      error: function () {
      	//console.log(aaaa);
      }
    });
}
*/
