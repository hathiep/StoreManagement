function getAllSupplier() {
    fetch('http://localhost:8080/api/suppliers')
        .then(response => response.json())
        .then(data => {
            const searchInput = document.getElementById('searchInputSupplier');
            const dropdownContent = document.getElementById('supplierDropdown');

            searchInput.addEventListener('input', function() {
                const searchTerm = this.value.toLowerCase();
                const filteredSuppliers = data.filter(supplier => supplier.name.toLowerCase().includes(searchTerm));
                displaySupplierOptions(filteredSuppliers);
            });

            // Hide dropdown when click outside
            document.addEventListener('click', function(event) {
                if (!dropdownContent.contains(event.target) && event.target !== searchInput) {
                    dropdownContent.style.display = 'none';
                }
            });
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi khi gọi API: ', error);
        });
}

function displaySupplierOptions(suppliers) {
    const dropdownContent = document.getElementById('supplierDropdown');
    dropdownContent.innerHTML = '';

    suppliers.forEach(supplier => {
        const option = document.createElement('div'); // Tạo một div thay vì một option
        option.classList.add('dropdown-option'); // Thêm class cho div để tạo giao diện dropdown
        option.textContent = supplier.name;
        option.addEventListener('click', function() {
            // Hiển thị thông tin nhà cung cấp đã chọn
            displaySupplierInfo(supplier);
            // Tự động thêm tên nhà cung cấp vào ô input tìm kiếm
            document.getElementById('searchInputSupplier').value = supplier.name;
            // Ẩn dropdown sau khi chọn một tùy chọn
            dropdownContent.style.display = 'none';
        });
        dropdownContent.appendChild(option);
    });

    dropdownContent.style.display = 'block';
}

function displaySupplierInfo(supplier) {
    const infoDiv = document.getElementById("supplierInfo");
    infoDiv.textContent = supplier.name
}

// Gọi hàm getAllSupplier khi trang được tải
window.onload = getAllSupplier;

function saveChanges() {
    var supplierName = document.getElementById("supplierInfo").textContent;
    var bill_id = document.getElementById("bill_id").textContent;
    if (supplierName == "") {
        alert('Vui lòng chọn nhà cung cấp!');
        return;
    }
    var itemsToSave = [];
    var ok = 0;

    var itemElements = document.querySelectorAll("#itemTableBody tr"); // Lấy tất cả các hàng trong bảng

    itemElements.forEach(row => {
        var idElement = row.querySelector("td:nth-child(1)");
        var productNameElement = row.querySelector("td:nth-child(2)");
        var priceElement = row.querySelector("input[type='number'][id^='price_']");
        var quantityElement = row.querySelector("input[type='number'][id^='quantity_']");


        // Kiểm tra các phần tử có tồn tại không
        if (idElement && productNameElement && quantityElement && priceElement) {
            var id = idElement.textContent;
            var productName = productNameElement.textContent;
            var quantity = quantityElement.value;
            var price = priceElement.value;

            if(quantity == ""  || price == ""){
                alert('Vui lòng nhập đầy đủ thông tin sản phẩm!');
                ok = 1;
                return;
            }
            if(quantity <=0  || price <= 0){
                alert('Vui lòng nhập số dương!');
                ok = 1;
                return;
            }
            if(quantity%1!=0  || price%1!=0){
                alert('Vui lòng nhập số nguyên!');
                ok = 1;
                return;
            }

            itemsToSave.push({
                id: id,
                billId: bill_id,
                productName: productName,
                quantity: parseInt(quantity),
                inPrice: parseFloat(price),
                totalPrice: parseFloat(price) * parseInt(quantity)
            });
        } else {
            console.error("One or more elements are missing.");
        }
    });

    if(ok == 1) return;
    if(itemsToSave.length == 0){
        alert('Vui lòng chọn sản phẩm!');
        return;
    }


    var dataToSend = {
        itemsToSave: itemsToSave,
        supplierName: supplierName
    };

    var xhr = new XMLHttpRequest();
    xhr.open("PUT", "http://localhost:8080/api/bill/update", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                alert('Đơn nhập đã được sửa thành công!');
                window.location.href = 'detail_bill.html?billId=' + bill_id;
            } else {
                alert('Đã có lỗi xảy ra, vui lòng thử lại!');
            }
        }
    };
    xhr.send(JSON.stringify(dataToSend));
}


function getBillDetail(id){
    fetch("http://localhost:8080/api/bill?bill_id=" + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById('bill_id').textContent = id;
            document.getElementById('searchInputSupplier').value = data.supplierName;
            document.getElementById('supplierInfo').textContent = data.supplierName;
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
                row += "<td>" + item.id + "</td>";
                row += "<td>" + item.productName + "</td>";
                let quantity = item.quantity;
                let inprice = item.inPrice;

                row += "<td style='text-align: right'><input style='text-align: right' type='number' id='price_" + item.id + "' value='" + inprice + "' placeholder='InPrice'></td>";
                row += "<td style='text-align: right'><input style='text-align: right' type='number' id='quantity_" + item.id + "' value='" + quantity + "' placeholder='Quantity'></td>";

                row += "</tr>";
                itemTableBody.innerHTML += row;

            });
        })
        .catch(error => console.error('Error fetching items:', error));
}
