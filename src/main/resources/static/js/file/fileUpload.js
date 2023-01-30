function gImgChanged(el) {
  var fileData = $(el)[0].files[0];
  console.log(fileData);

  var formData = new FormData();
  formData.append("gImg", fileData);

  $.ajax({
    url: "/teacher/temp-upload",
    type: "post",
    contentType: false,
    processData: false,
    headers,
    data: formData,
    success: function (map) {
      //서버에 업로드이후 url을 리턴-> 파라미터에 전달
      console.log(map);

      $(el)
        .siblings(".img")
        .css("background-image", "url(" + map.url + ")");
      $(el).siblings(".newName").val(map.newName);
      $(el).siblings(".orgName").val(map.orgName);
      //대표이미지 와 추가이미지 구분
      if ($(el).prop("id") == "defImg") return; //대표이미지인경우 여기서 함수종료
    },
  });
}
