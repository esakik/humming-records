function calculateTotalAmount() {
    var num = document.addItemForm.quantity.selectedIndex;
    var quantity = document.addItemForm.quantity.options[num].value;
    var value = document.getElementById("price").innerHTML.toString().replace(',', '');
    document.getElementById("totalAmount").innerHTML = [ quantity * value ].toString().replace(/(\d)(?=(\d{3})+$)/g, '$1,');
}