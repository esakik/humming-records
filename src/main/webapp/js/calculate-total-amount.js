function calculateTotalAmount() {
    var num = document.shoppingCartForm.quantity.selectedIndex;
    var quantity = document.shoppingCartForm.quantity.options[num].value;
    var value = document.getElementById("price").innerHTML.toString().replace(',', '');
    document.getElementById("totalAmount").innerHTML = [ quantity * value ].toString().replace(/(\d)(?=(\d{3})+$)/g, '$1,');
}