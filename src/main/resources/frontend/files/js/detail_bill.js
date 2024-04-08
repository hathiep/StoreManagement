function getBillDetail(id){
    fetch("http://localhost:8080/api/bill?bill_id=" + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById('bill_id').textContent = id;
            document.getElementById('date').textContent = data.date;
            document.getElementById('supplierInfo').textContent = data.supplierName;
            document.getElementById('total_cost').innerHTML = data.totalCost;
        })
        .catch(error => console.error('Error:', error));
}
function getAllItem(id) {

    fetch("http://localhost:8080/api/bill/get_item?billId=" + id)
        .then(response => response.json())
        .then(items => {

            var itemTableBody = document.getElementById("itemTableBody");
            items.forEach(item => {
                var row = "<tr>";
                row += "<td >" + item.id + "</td>";
                row += "<td>" + item.productName + "</td>";
                row += "<td class='right'>" + item.inPrice + "</td>";
                row += "<td class='right'>" + item.quantity + "</td>";
                row += "<td class='right'>" + item.inPrice * item.quantity + "</td>";
                row += "</tr>";
                itemTableBody.innerHTML += row;
            });
        })
        .catch(error => console.error('Error fetching items:', error));
}
function UpdateBill(){
    window.location.href = "update_bill.html?billId="+ document.getElementById("bill_id").textContent;
}
