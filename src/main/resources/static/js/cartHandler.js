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

function subtractFromCart(i) {
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
        element.innerText = amountInCart(elementId);
    }

    var rows = document.getElementsByName("product_row");
    var sum = 0;

    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var amount = row.getElementsByTagName("td")[3];
        var price = row.getElementsByTagName("td")[1];
        price = parseFloat(price.innerText);
        amount = parseInt(amount.innerText);
        if (isNaN(amount) || amount === 0) {
            row.style.display= 'none';
        }
        else {
            row.style.display= '';

            if (!isNaN(price)) {
                sum += price*amount;
            }
        }
    }

    document.getElementById("cartSum").innerText = sum.toFixed(2);
}