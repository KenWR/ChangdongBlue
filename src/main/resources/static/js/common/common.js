async function sendRequest() {
  const response = await fetch("/student/reg", { method: "POST", headers });
  if (response.ok) {
    const responseJSON = await response.json();
    return responseJSON;
  } else {
    throw new Error("error occurred");
  }
}
async function sendRequestB(url) {
  return (await fetch(url)).json;
}
