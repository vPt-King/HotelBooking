function get_members() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/admin/settings/management_team", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let show = document.getElementById("management_team_show").innerHTML = "";
    xhr.onload = function () {
        resultArray = JSON.parse(this.responseText);
        for (var i = 0; i < resultArray.length; i++) {
            var obj = resultArray[i];
            var htmlString = `
            <div class="swiper-slide bg-white text-center overflow-hidden rounded">
                <img src="http://localhost:8080/getimage/${obj.picture}" class="w-100">
                <h5 class="mt-2">${obj.name}</h5>
            </div>
            `;
            document.getElementById("management_team_show").innerHTML += htmlString
        }
    }
    xhr.send();
}


window.onload = function() {
    get_members();
}