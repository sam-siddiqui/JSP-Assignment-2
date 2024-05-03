/**
 * @param {int} user_id
 * */
function returnAll(user_id) {
    const form = document.createElement("form");
    form.setAttribute("action", baseUrl + "/returnAll");
    form.setAttribute("method", "post");
    form.setAttribute("style", "display: none");

    const userIDInput = document.createElement("input");
    userIDInput.setAttribute("type", "text");
    userIDInput.setAttribute("name", "user_id");
    userIDInput.setAttribute("value", user_id.toString());

    const s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("value", "Submit");

    form.appendChild(userIDInput);
    form.appendChild(s);

    document.querySelector(`#returnAll`).append(form);
    form.submit();
}

/**
 * @param {int} book_id
 * */
function returnBook(book_id) {
    const form = document.createElement("form");
    form.setAttribute("action", baseUrl + "/return");
    form.setAttribute("method", "post");
    form.setAttribute("style", "display: none");

    const userIDInput = document.createElement("input");
    userIDInput.setAttribute("type", "text");
    userIDInput.setAttribute("name", "book_id");
    userIDInput.setAttribute("value", book_id.toString());

    const s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("value", "Submit");

    form.appendChild(userIDInput);
    form.appendChild(s);

    document.querySelector(`#return-${book_id}`).append(form);
    form.submit();
}

/**
 * @param {int} book_id
 * */
function reserveBook(book_id) {
    const form = document.createElement("form");
    form.setAttribute("action", baseUrl + "/reserve");
    form.setAttribute("method", "post");
    form.setAttribute("style", "display: none");

    const userIDInput = document.createElement("input");
    userIDInput.setAttribute("type", "text");
    userIDInput.setAttribute("name", "book_id");
    userIDInput.setAttribute("value", book_id.toString());

    const s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("value", "Submit");

    form.appendChild(userIDInput);
    form.appendChild(s);

    document.querySelector(`#reserve-${book_id}`).append(form);
    form.submit();
}

function filterResults() {
    console.log("Here");
    let topicSelector = document.getElementById("topicSelector");
    const form = document.createElement("form");
    form.setAttribute("action", baseUrl + "/");
    form.setAttribute("method", "post");
    form.setAttribute("style", "display: none");

    const topicID = document.createElement("input");
    topicID.setAttribute("type", "text");
    topicID.setAttribute("name", "selectedTopicID");
    topicID.setAttribute("value", topicSelector.value);

    const s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("value", "Submit");

    form.appendChild(topicID);
    form.appendChild(s);

    document.querySelector(`#searchBox`).append(form);
    form.submit();
}