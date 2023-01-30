d.addEventListener("DOMContentLoaded", function () {
  $("#save-st-att").click(function () {
    var list = [];
    $('input[name="sno"]').each(function (i) {
      list.push($(this).val());
    });
    var obj = { list: list };
    $.ajax({
      type: "post",
      headers,
      dataType: "json",
      contentType: "application/x-www-form-urlencoded; charset=UTF-8",
      url: "/mimi",
      data: obj,
      success: function () {
        console.log(obj);
      },
      error: function () {
        console.log("aaaa");
      },
    });
  });
});
