//CreateSupplier

function CreateSupplier() {

    var name = document.getElementById("txtName").value;
    var address = document.getElementById("txtAddress").value;
    var phone = document.getElementById("txtPhone").value;
    var bank = document.getElementById("txtBank").value;
    var manager = document.getElementById("txtManager").value;

    var raw = {
        "name": name,
        "address": address,
        "phone": phone,
        "bank": bank,
        "manager": manager
    };

    var requestOptions = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(raw)
    };

    fetch('http://localhost:8080/api/supplier/create', requestOptions)
        .then((response) => response.text())
        .then(data => {
            // Xử lý dữ liệu trả về nếu cần
            console.log(data);
            alert('Thêm nhà cung cấp thành công!');
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error('There has been a problem with your fetch operation:', error);
            alert('Đã xảy ra lỗi, vui lòng thử lại sau!');
        });
}