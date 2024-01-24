function getCookie(name) {
    function escape(s) { return s.replace(/([.*+?\^$(){}|\[\]\/\\])/g, '\\$1'); }
    var match = document.cookie.match(RegExp('(?:^|;\\s*)' + escape(name) + '=([^;]*)'));
    return match ? match[1] : null;
}
function addToCart(i) {
    var name = 'product'+i;
    var value = getCookie(name);
    value = parseInt(value);
    if (isNaN(value)) {
        value = 0;
    }
    value = value + 1;
    document.cookie = name + '=' + value +  ";path=/";
    showAmount();
}

function substractFromCart(i) {
    var name = 'product'+i;
    var value = getCookie(name);
    value = parseInt(value);
    if (isNaN(value)) {
        value = 0;
    }
    value = value - 1;
    var cookieText = name + '=' + value;
    if (value == 0) {

        cookieText += ';expires=' + (new Date()).getTime();
    }
    document.cookie = cookieText +  ";path=/";
    showAmount();
}

function removeFromCart(i) {
    var name = 'product'+i;
    var cookieText = name + '=';
    cookieText += ';expires=' + (new Date()).getTime();
    document.cookie = cookieText +  ";path=/";
    showAmount();
}

function amountInCart(i) {
    var x = getCookie('product'+i);
    value = parseInt(x);
    if (isNaN(value)) value = 0;
    return value;
}

function showAmount() {
    var elements = document.getElementsByName("product");

    // Iterate through the elements and set their text content
    for (var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var elementId = element.id;

        // Set the text content to the result of f(x)
        element.innerHTML = amountInCart(elementId).toString();
    }

    var rows = document.getElementsByName("product_row");

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var cell = row.getElementsByTagName("td")[3];
        if (cell.innerText === '0') {
            row.style.display= 'none';
        }
        else {
            row.style.display= '';
        }
    }
}