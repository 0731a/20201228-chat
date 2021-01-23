let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('public_chat');
});


router.post('/writeMessage', function(req,res,next){
    let body = req.body;
    let user_idx = body.idx;
    let text = body.text;

    console.log(text);

    let result = models.public_chat.create({
        writer: user_idx,
        text: text
      })    
      res.redirect("/getMessage");

});


module.exports = router;