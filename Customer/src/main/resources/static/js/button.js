
const modelId = document.getElementById('modelId').getAttribute("value")
document.getElementById("myButton").onclick = function () {
    location.href = `http://localhost:8080/model-detail/${modelId}`;
};