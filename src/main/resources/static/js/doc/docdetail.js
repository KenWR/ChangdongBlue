function changeDocStat(el) {
  var target = $(el); //등록버튼

  $.ajax({
    type: "put",
    headers,
    data: {
      docNo: target.siblings(".docNo").val(),
      docStatus: target.parents("tr").find(".docStatus").val(),
    },
    url: "/docUpdate",
    success: function () {
      //console.log(aaaa);
    },
    error: function () {
      //console.log(aaaa);
    },
  });
}
