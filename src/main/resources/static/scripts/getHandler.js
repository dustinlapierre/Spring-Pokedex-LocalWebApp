let id_box = document.querySelector("#id-box");
let endpoint_box = document.querySelector("#endpoint-box");
let JSONbody = document.querySelector(".JSON-box");
let btn = document.querySelector("#btn");

//On click event that sends GET request to this REST api
btn.addEventListener("click", () =>
{
    async function getRequest()
    {
        let response = await fetch("http://localhost:8080/api?id=" + id_box.value + "&endpoint=" + endpoint_box.value);
        //check the status code and return error on fail
        if(!response.ok)
        {
            throw Error("Invalid GET request </br>Status: " + response.status + response.statusText);
        }
        let data = await response.json();
        return data;
    }
    getRequest().then(response =>
    {
        JSONbody.innerHTML = `<pre> ${JSON.stringify(response, null, 4)} </pre>`;
    }).catch((err) =>
    {
        JSONbody.innerHTML = err;
    });
});