//CreateSupplier

function CreateSupplier() {

    var name = document.getElementById("txtName").value;
    var address = document.getElementById("txtAddress").value;
    var phone = document.getElementById("txtPhone").value;

    if(name == "" || address == "" || phone == ""){
        alert('Vui lòng điền đầy đủ thông tin!');
        return; // Dừng hàm nếu có lỗi
    }
    if (name.length > 255 || address.length > 255) {
        alert("Giá trị nhập vượt quá giới hạn cho phép (255 ký tự). Vui lòng nhập lại! ");
        return;
    }
    if (!validatePhoneNumber(phone)) {
        alert('Số điện thoại không hợp lệ! Vui lòng nhập 10 chữ số!');
        return; // Dừng hàm nếu có lỗi
    }

    var raw = {
        "name": name,
        "address": address,
        "phone": phone
    };

    var requestOptions = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(raw)
    };

    fetch('http://localhost:8080/api/supplier/create', requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Có lỗi xảy ra khi tạo sản phẩm.');
            }
            return response.text();
        })
        .then(data => {
            // Xử lý dữ liệu trả về nếu cần
            console.log(data);
            alert('Thêm nhà cung cấp thành công!');
            window.location.href = 'bill.html';
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error('There has been a problem with your fetch operation:', error);
            alert('Nhà cung cấp đã có trong hệ thống! Vui lòng chọn tên khác!');
        });
}

function validatePhoneNumber(phoneNumber) {
    // Biểu thức chính quy kiểm tra số điện thoại
    var phoneRegex = /^\d{10}$/;

    // Kiểm tra xem số điện thoại có khớp với biểu thức chính quy hay không
    return phoneRegex.test(phoneNumber);
}
