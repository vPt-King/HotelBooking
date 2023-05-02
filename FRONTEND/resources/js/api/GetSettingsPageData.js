let general_data, contacts_data;
let general_s_form = document.getElementById('general_s_form');
let site_title_inp = document.getElementById('site_title_inp');
let site_about_inp = document.getElementById('site_about_inp');
let contacts_s_form = document.getElementById('contacts_s_form');
let team_s_form = document.getElementById('team_s_form');
let member_name_inp = document.getElementById('member_name_inp');
let member_picture_inp = document.getElementById('member_picture_inp');


function get_general() {
    let site_title = document.getElementById("site_title");
    let site_about = document.getElementById("site_about");
    let shutdown_toggle = document.getElementById("shutdown-toggle");
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/admin/settings/general", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        console.log("đây là json:" + this.responseText);
        general_data = JSON.parse(this.responseText);
        console.log("đây là xử lí json:");
        console.log(general_data);
        site_title.innerText = general_data.site_title;
        site_about.innerText = general_data.site_about;
        site_title_inp.value = general_data.site_title;
        site_about_inp.value = general_data.site_about;
        if (general_data.shutdown == 1) {
            shutdown_toggle.checked = true;
            shutdown_toggle.value = 1;
        }
        else {
            shutdown_toggle.checked = false;
            shutdown_toggle.value = 0;
        }
    }
    xhr.send();
}

general_s_form.addEventListener('submit', function (e) {
    e.preventDefault();
    data = {
        id: 1,
        site_title: site_title_inp.value,
        site_about: site_about_inp.value,
        shutdown: parseInt(document.getElementById('shutdown-toggle').value)
    }
    upd_general(data);
});

//Reload data
function upd_general(data) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/admin/settings/general", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        var myModal = document.getElementById('general-s');
        var modal = bootstrap.Modal.getInstance(myModal);
        modal.hide();
        get_general();
    }
    xhr.send(JSON.stringify(data));
}

// update shutdown
function upd_shutdown(val) {
    let valshut = 0;
    if (parseInt(val) === 0) {
        valshut = parseInt(val) + 1;
    }
    else {
        valshut = parseInt(val) - 1;
    }
    data = {
        id: 1,
        site_title: document.getElementById("site_title").innerText,
        site_about: document.getElementById("site_about").innerText,
        shutdown: valshut
    }
    console.log(JSON.stringify(data));
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/admin/settings/general", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function () {
        if (val == 0) {
            alert("Shutdown mode is on");
        }
        else {
            alert("Shutdown mode is off");
        }
        get_general();
    }
    xhr.send(JSON.stringify(data));
}

function get_contacts() {
    let contacts_p_id = ['address', 'gmap', 'pn1', 'pn2', 'email', 'fb', 'insta', 'tw'];
    let iframe = document.getElementById('iframe');

    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/admin/settings/contact", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        console.log("here");
        contacts_data = JSON.parse(this.responseText);
        console.log(contacts_data);
        contacts_data = Object.values(contacts_data);

        for (i = 0; i < contacts_p_id.length; i++) {
            document.getElementById(contacts_p_id[i]).innerText = contacts_data[i + 1];
        }
        iframe.src = contacts_data[9];
        contacts_inp(contacts_data);
    }
    xhr.send();
}
function contacts_inp(data) {
    let contacts_inp_id = ['address_inp', 'gmap_inp', 'pn1_inp', 'pn2_inp', 'email_inp', 'fb_inp', 'insta_inp', 'tw_inp', 'iframe_inp'];

    for (i = 0; i < contacts_inp_id.length; i++) {
        document.getElementById(contacts_inp_id[i]).value = data[i + 1];
    }
}

contacts_s_form.addEventListener('submit', function (e) {
    e.preventDefault();
    upd_contacts();
});

function upd_contacts() {
    let index = ['address', 'gmap', 'pn1', 'pn2', 'email', 'fb', 'insta', 'tw', 'iframe'];
    let contacts_inp_id = ['address_inp', 'gmap_inp', 'pn1_inp', 'pn2_inp', 'email_inp', 'fb_inp', 'insta_inp', 'tw_inp', 'iframe_inp'];

    data = {
        id: 1,
        address: document.getElementById('address_inp').value,
        gmap: document.getElementById('gmap_inp').value,
        pn1: document.getElementById('pn1_inp').value,
        pn2: document.getElementById('pn2_inp').value,
        email: document.getElementById('email_inp').value,
        fb: document.getElementById('fb_inp').value,
        insta: document.getElementById('insta_inp').value,
        tw: document.getElementById('tw_inp').value,
        iframe: document.getElementById('iframe_inp').value,
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/admin/settings/contact", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        var myModal = document.getElementById('contacts-s');
        var modal = bootstrap.Modal.getInstance(myModal);
        modal.hide();
        get_contacts();
    }
    xhr.send(JSON.stringify(data));
}

team_s_form.addEventListener('submit', function (e) {
    e.preventDefault();
    add_member();
});


const fileInput = document.querySelector('input[type="file"]');
fileInput.addEventListener('change', (event) => {
    let btn = document.getElementById("btn_add_member");
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


function add_member() {
    let data = new FormData();
    data.append('name', member_name_inp.value);
    data.append('picture', member_picture_inp.files[0]);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/admin/settings/management_team", true);
    xhr.onload = function () {
        var myModal = document.getElementById('team-s');
        var modal = bootstrap.Modal.getInstance(myModal);
        modal.hide();
        alert('success');
        member_name_inp.value = '';
        member_picture_inp.value = '';
        get_members();
    }
    xhr.send(data);
}


function get_members() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/admin/settings/management_team", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    document.getElementById("team-data").innerHTML = "";
    xhr.onload = function () {
        resultArray = JSON.parse(this.responseText);
        for (var i = 0; i < resultArray.length; i++) {
            var obj = resultArray[i];
            var htmlString = `
                                        <div class="col-md-2 mb-3">
                                            <div class="card bg-dark text-white">
                                                <img src='http://localhost:8080/getimage/${obj.picture}'  class="card-img">
                                                <div class="card-img-overlay text-end">
                                                    <button type="button" onclick="rem_member(${obj.id})" class="btn btn-danger btn-sm shadow-none">
                                                        <i class="bi bi-trash"></i> Delete
                                                    </button>
                                                </div>
                                                <p class="card-text text-center px-3 py-2">${obj.name}</p>
                                            </div>
                                        </div>
                                    `;
            document.getElementById("team-data").innerHTML += htmlString
        }
    }
    xhr.send("get_members");
}

function rem_member(val) {

    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/admin/settings/management_team", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function () {
        get_members();
    }
    xhr.send(JSON.stringify(val));
}

window.onload = function () {
    get_general();
    get_contacts();
    get_members();
}