function get_contacts() {
    let contacts_p_id = ['address', 'gmap', 'pn1', 'pn2', 'email', 'fb', 'insta', 'tw'];
    let iframe = document.getElementById('iframe');

    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/admin/settings/contact", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = function () {
        contacts_data = JSON.parse(this.responseText);
        set_contact_data(contacts_data);
    }
    xhr.send();
}

function set_contact_data(data)
{
    document.getElementById('reach_us_iframe').setAttribute("src",data.iframe);
    let pn1 = document.getElementById('reach_us_pn1');
    pn1.setAttribute("href", data.pn1);
    pn1.innerHTML += data.pn1;
    let pn2 = document.getElementById('reach_us_pn2');
    pn2.setAttribute("href", data.pn2);
    pn2.innerHTML += data.pn2;
    document.getElementById("reach_us_tw").setAttribute("href", data.tw);
    document.getElementById("reach_us_fb").setAttribute("href", data.fb);
    document.getElementById("reach_us_insta").setAttribute("href", data.insta);
}


function get_carousel()
{
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
            <div class="swiper-slide">
                    <img src="http://localhost:8080/getimage/${obj.image}" class="w-100 d-block"/>
            </div>
            `;
            document.getElementById("carousel-data").innerHTML += htmlString
        }
    }
    xhr.send();
}
window.onload = function() {
    get_contacts();
    get_carousel()
}