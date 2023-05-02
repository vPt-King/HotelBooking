const express = require('express');
const app = express();
const port = 3000;


var views = __dirname + "/resources/views/"
app.use(express.static(__dirname + "/resources"));



// Admin routes


app.get('/admin/rooms', function(req, res) {
    res.sendFile(views + "Admin/Rooms.html");
});


app.get('/admin/features_facilities', function(req, res) {
    res.sendFile(views + "Admin/Features_facilities.html");
});

app.get('/admin/user-queries', function(req, res) {
    res.sendFile(views + "Admin/User_queries.html");
});

app.get('/admin/settings', function(req, res) {
    res.sendFile(views + "Admin/Settings.html");
});


app.get('/admin/dashboard', function(req, res) {
    res.sendFile(views + "Admin/DashBoard.html");
});


app.get('/admin/carousel', function(req, res) {
    res.sendFile(views + "Admin/Carousel.html");
});

app.get('/admin/login', function(req, res) {
    res.sendFile(views + "Admin/Admin.html");
});


app.get('/admin', function(req, res) {
    res.sendFile(views + "Admin/Admin.html");
});






// Client routes
app.get('/homepage', function(req, res) {
    res.sendFile(views + "Client/HomePage.html");
});

app.get('/rooms', function(req, res) {
    res.sendFile(views + "Client/Rooms.html");
});

app.get('/contact', function(req, res) {
    res.sendFile(views + "Client/Contact.html");
});
  
app.get('/facilities', function(req, res) {
    res.sendFile(views + "Client/Facilities.html");
});


app.get('/about', function(req, res) {
    res.sendFile(views + "Client/About.html");
});

app.get('/', function(req, res) {
    res.sendFile(views + "Client/HomePage.html");
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});