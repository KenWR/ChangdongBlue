function CheckValue() {
  var checkArr = [];
  $("input[name='AttendanceStudent']:checked").each(function (i) {
    checkArr.push($(this).val());
  });
  $.ajax({
    url: "AttendanceStudent",
    type: "post",
    dataType: "text",
    headers,
    data: {
      valueArr: checkArr,
    },
  });
}
