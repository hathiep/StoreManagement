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
