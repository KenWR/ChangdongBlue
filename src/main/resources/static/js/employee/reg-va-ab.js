function clickCheck(target) {
  document.querySelectorAll(`input[type=checkbox]`).forEach((el) => (el.checked = false));

  target.checked = true;
}
