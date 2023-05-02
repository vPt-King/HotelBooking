let carousel_s_form = document.getElementById('carousel_s_form');
let carousel_picture_inp = document.getElementById('carousel_picture_inp');


carousel_s_form.addEventListener('submit', function (e) {
    e.preventDefault();
    add_image();
});


const fileInput = document.querySelector('input[type="file"]');
fileInput.addEventListener('change', (event) => {
    let btn = document.getElementById("btn_add_image");
    btn.setAttribute("disabled", true);
    const file = event.target.files[0];
    const fileSize = file.size; // lấy kích thước tệp
    const fileType = file.type; // lấy loại tệp
    // kiểm tra kích thước tệp
    if (fileSize > 1024 * 1024) {
        alert('Tệp quá lớn, vui lòng chọn một tệp nhỏ hơn 1MB');
        return;
    }
    else {
        btn.removeAttribute('disabled');
    }
});


function add_image() {
    let data = new FormData();
    data.append('picture', carousel_picture_inp.files[0]);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/admin/carousel", true);
    xhr.onload = function () {
        var myModal = document.getElementById('carousel-s');
        var modal = bootstrap.Modal.getInstance(myModal);
        modal.hide();
        alert('success');
        carousel_picture_inp.value = '';
        get_carousel();
    }
    xhr.send(data);
}


function get_carousel() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/admin/carousel", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    document.getElementById("carousel-data").innerHTML = "";
    xhr.onload = function () {
        resultArray = JSON.parse(this.responseText);
        console.log(resultArray);
        for (var i = 0; i < resultArray.length; i++) {
            var obj = resultArray[i];
            var htmlString = `
                                        <div class="col-md-4 mb-3">
                                            <div class="card bg-dark text-white">
                                                <img src='http://localhost:8080/getimage/${obj.image}'  class="card-img">
                                                <div class="card-img-overlay text-end">
                                                    <button type="button" onclick="rem_image(${obj.id})" class="btn btn-danger btn-sm shadow-none">
                                                        <i class="bi bi-trash"></i> Delete
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    `;
            document.getElementById("carousel-data").innerHTML += htmlString
        }
    }
    xhr.send();
}

function rem_image(val) {
    data = {
        id : parseInt(val),
        image: ""
    }
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/admin/carousel", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function () {
        get_carousel();
    }
    xhr.send(JSON.stringify(data));
}

window.onload = function () {
    get_carousel();
}