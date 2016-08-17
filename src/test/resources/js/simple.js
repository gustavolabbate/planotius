function changeInputTextValue(value, id) {
    var element = document.getElementById(id);
    element.setAttribute("value", value);
    return value;
}