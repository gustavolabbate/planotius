function changeInputTextValue(value, id) {
    var element = document.getElementById(id);
    element.setAttribute("value", value);
    return value;
}

function fillByButton() {
    var element = document.getElementById("filledByButton");
    element.setAttribute("value", "button clicked!");
}