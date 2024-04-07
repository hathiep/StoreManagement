//CreateProduct

function CreateProduct() {

    var name = document.getElementById("txtName").value;
    var image = document.getElementById("txtImage").value;
    var outPrice = document.getElementById("txtOutPrice").value;
    // var quantity = document.getElementById("txtQuantity").value;
    var des = document.getElementById("txtDes").value;

    if(name == "" || image == "" || outPrice == ""){
        alert('Vui lòng điền đầy đủ thông tin');
        return; // Dừng hàm nếu có lỗi
    }
    if (outPrice <= 0) {
        alert('Giá sản phẩm không hợp lệ! Vui lòng nhập số dương.');
        return; // Dừng hàm nếu có lỗi
    }

    var raw = {
        "name": name,
        "image": image,
        "outPrice": outPrice,
        "quantity": 0,
        "des": des
    };

    var requestOptions = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(raw)
    };

    fetch('http://localhost:8080/api/product/create', requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Có lỗi xảy ra khi tạo sản phẩm.');
            }
            return response.text();
        })
        .then(data => {
            console.log(data);
            alert('Thêm sản phẩm thành công!');
            window.location.href = 'product.html';
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            alert('Sản phẩm đã có trong hệ thống! Vui lòng chọn tên khác!');
        });
}

//GetProducts
function GetProducts() {
    fetch('http://localhost:8080/api/products')
        .then(response => response.json())
        .then(data => {
            const danhsachChitiet = document.querySelector('.danhsach-chitiet');
            danhsachChitiet.innerHTML = ''; // Xóa nội dung hiện tại của danh sách

            // Duyệt qua mỗi sản phẩm và thêm vào danh sách
            data.forEach(item => {
                const li = document.createElement('ul');
                li.classList.add('danhsach-item');
                li.innerHTML = `
                    <li style="width:60px;">${item.id}</li>
                    <li style="width:300px;">${item.name}</li>
                    <li style="width:200px;"><img src="${item.image}" style="height: 100px;"</li>
                    <li style="width:200px; text-align: right;">${item.outPrice} đ</li>
                    <li style="width:100px; text-align: right;">${item.quantity}</li>
                    <li style="width:200px; margin-left: 2%">${item.des}</li>
                    <li style="width:150px; float:right; text-align:right;">
                        <a class="lnkSua" name="btnSua${item.id}" data-id="${item.id}" title="Sửa" href="updateProduct.html?id=${item.id}">Sửa</a>
                        <a class="lnkXoa" name="btnXoa${item.id}" data-id="${item.id}" title="Xoá" onclick="DeleteProduct(${item.id})">Xoá</a>
                    </li>
                `;
                danhsachChitiet.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}

//GetProductById
function GetProductById(id) {
    fetch('http://localhost:8080/api/product?id=' + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById('pId').textContent = data.id;
            document.getElementById('txtName').value = data.name;
            document.getElementById('txtImage').value = data.image;
            document.getElementById("txtOutPrice").value = data.outPrice;
            document.getElementById('txtQuantity').textContent = data.quantity;
            document.getElementById('txtDes').value = data.des;
        })
        .catch(error => console.error('Error:', error));
}

//UpdateProduct
function UpdateProduct() {

    var id = document.getElementById('pId').textContent;
    var name = document.getElementById("txtName").value;
    var image = document.getElementById("txtImage").value;
    var outPrice = document.getElementById("txtOutPrice").value;
    var quantity = document.getElementById("txtQuantity").textContent;
    var des = document.getElementById("txtDes").value;

    if(name == "" || image == "" || outPrice == ""){
        alert('Vui lòng điền đầy đủ thông tin');
        return; // Dừng hàm nếu có lỗi
    }
    if (outPrice <= 0) {
        alert('Giá sản phẩm không hợp lệ! Vui lòng nhập số dương.');
        return; // Dừng hàm nếu có lỗi
    }

    var raw = {
        "id": id,
        "name": name,
        "image": image,
        "outPrice": outPrice,
        "quantity": quantity,
        "des": des
    };

    var requestOptions = {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(raw)
    };

    fetch('http://localhost:8080/api/product/update', requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Có lỗi xảy ra khi tạo sản phẩm.');
            }
            return response.text();
        })
        .then(result => {
            alert('Sửa sản phẩm thành công!');
            location.reload();
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error('There has been a problem with your fetch operation:', error);
            alert('Sản phẩm đã có trong hệ thống! Vui lòng chọn tên khác!');
        });
}

//DeleteProduct
function DeleteProduct(id) {
    // Hỏi người dùng xác nhận trước khi xóa
    const confirmation = confirm("Bạn có muốn xóa sản phẩm có ID " + id + " không?");

    if (!confirmation) {
        console.log("Hủy bỏ xóa sản phẩm.");
        return; // Không thực hiện xóa nếu người dùng hủy bỏ
    }

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

    const urlencoded = new URLSearchParams();
    urlencoded.append("id", id);

    const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
        body: urlencoded,
        redirect: "follow"
    };

    fetch('http://localhost:8080/api/product/delete?id=' + id, requestOptions)
        .then((response) => response.text())
        .then((result) => {
            alert("sản phẩm có ID " + id + " đã được xóa thành công.");
            location.reload();
        })
        .catch((error) => console.error(error));
}

//SubmitSearch
function submitSearch() {
    const keyword = document.getElementById('txtSearch').value; // Lấy từ khoá tìm kiếm từ input

    // Gọi hàm tìm kiếm với từ khoá đã nhập
    SearchProducts(keyword);
}

//SearchProducts
function SearchProducts(keyword) {
    fetch('http://localhost:8080/api/products/search?keyword=' + keyword)
        .then(response => response.json())
        .then(data => {
            const danhsachChitiet = document.querySelector('.danhsach-chitiet');
            danhsachChitiet.innerHTML = ''; // Xóa nội dung hiện tại của danh sách

            // Duyệt qua mỗi sản phẩm và thêm vào danh sách
            data.forEach(item => {
                const li = document.createElement('ul');
                li.classList.add('danhsach-item');
                li.innerHTML = `
                    <li style="width:60px;">${item.id}</li>
                    <li style="width:300px;">${item.name}</li>
                    <li style="width:200px;"><img src="${item.image}" style="height: 100px;"</li>
                    <li style="width:200px; text-align: right;">${item.outPrice} đ</li>
                    <li style="width:100px; text-align: right;">${item.quantity}</li>
                    <li style="width:200px; margin-left: 2%">${item.des}</li>
                    <li style="width:150px; float:right; text-align:right;">
                        <a class="lnkSua" name="btnSua${item.id}" data-id="${item.id}" title="Sửa" href="updateProduct.html?id=${item.id}">Sửa</a>
                        <a class="lnkXoa" name="btnXoa${item.id}" data-id="${item.id}" title="Xoá" onclick="DeleteProduct(${item.id})">Xoá</a>
                    </li>
                `;
                danhsachChitiet.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}