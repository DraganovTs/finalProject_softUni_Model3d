const modelId = document.getElementById('modelId').getAttribute("value")
const commentForm = document.getElementById('commentForm')
commentForm.addEventListener("submit", handleFormSubmission)

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const commentContaier = document.getElementById('comments')

async function handleFormSubmission(event) {
    event.preventDefault()

    const messageVal = document.getElementById('message').value

    fetch(`http://localhost:8080/api/${modelId}/comments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            message: messageVal
        })
    }).then(res => res.json())
        .then(data => {
            document.getElementById('message').value = ""
            commentContaier.innerHTML += comementAsHtml(data)
        })
}

function comementAsHtml(comment) {
    let commentHtml = `<div class= "spr-review-header" >\n`
            commentHtml += `<span class = "spr-review-header-byline">`
            commentHtml += `<strong>` + comment.firstName + `</strong>`
            commentHtml += `</div>\n`
            commentHtml += `<div class="spr-review-content">`
            commentHtml += `<p>` + comment.message + `</p>`
            commentHtml += `</div>\n`

    return commentHtml
}

fetch(`http://localhost:8080/api/${modelId}/comments`, {
    headers: {
        "Accept": "application/json"
    }
}).then(res => res.json())
    .then(data => {
        for(let comment of data) {
            commentContaier.innerHTML += comementAsHtml(comment)
        }
    })



