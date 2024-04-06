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
    var itemsToSave = [];

    var itemElements = document.querySelectorAll("#itemTableBody tr"); // Lấy tất cả các hàng trong bảng

    itemElements.forEach(row => {
        var idElement = row.querySelector("td:nth-child(1)");
        var billIdElement = row.querySelector("td:nth-child(2)");
        var productNameElement = row.querySelector("td:nth-child(3)");
        var quantityElement = row.querySelector("input[type='number'][id^='quantity_']");
        var priceElement = row.querySelector("input[type='number'][id^='price_']");

        // Kiểm tra các phần tử có tồn tại không
        if (idElement && billIdElement && productNameElement && quantityElement && priceElement) {
            var id = idElement.textContent;
            var billId = billIdElement.textContent;
            var productName = productNameElement.textContent;
            var quantity = quantityElement.value;
            var price = priceElement.value;

            itemsToSave.push({
                id: id,
                billId: billId,
                productName: productName,
                quantity: parseInt(quantity),
                inPrice: parseFloat(price),
                totalPrice: parseFloat(price) * parseInt(quantity)
            });
        } else {
            console.error("One or more elements are missing.");
        }
    });


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
                alert('Selected items have been saved successfully.');
            } else {
                alert('Failed to save selected items.');
            }
        }
    };
    xhr.send(JSON.stringify(dataToSend));
}





function getAllItem(id, supplierName) {
    document.getElementById('searchInputSupplier').value = supplierName;
    document.getElementById('supplierInfo').textContent = supplierName;

    fetch("http://localhost:8080/api/bill/get_item?billId=" + id)
        .then(response => response.json())
        .then(items => {

            var itemTableBody = document.getElementById("itemTableBody");
            items.forEach(item => {
                var row = "<tr>";
                row += "<td >" + item.id + "</td>";
                row += "<td>" + item.billId + "</td>";
                row += "<td>" + item.productName + "</td>";
                let quantity = item.quantity;
                let inprice = item.inPrice;

                row += "<td><input type='number' id='quantity_" + item.id + "' value='" + quantity + "' placeholder='Quantity'></td>";
                row += "<td><input type='number' id='price_" + item.id + "' value='" + inprice + "' placeholder='InPrice'></td>";

                row += "</tr>";
                itemTableBody.innerHTML += row;

            });
        })
        .catch(error => console.error('Error fetching items:', error));
}

function selectItem(id, productName, inPrice, quantity) {
    // Thực hiện các hành động khi chọn sản phẩm ở đây
    console.log("Selected item:", id, productName, inPrice, quantity);
    // Ví dụ: Thêm sản phẩm vào đơn hàng hoặc làm bất kỳ thao tác nào khác
}