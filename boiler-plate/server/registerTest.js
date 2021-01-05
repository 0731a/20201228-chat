const express = require('express');
const router = express.Router();
const models = require("./models");

console.log("server start");


router.get('/sign_up', function(req, res, next) {
  console.log("sign in");
  res.render("user/signup");
});


router.post("/sign_up", function(req,res,next){
  let body = req.body;
  console.log("sign in");

  models.user.create({
    name: body.userName,
    email: body.userEmail,
    password: body.password
  })
  .then( result => {
    res.redirect("/users/sign_up");
  })
  .catch( err => {
    console.log(err)
  })
})

module.exports = router;