let express = require('express');
let router = express.Router();
const models = require('../models');
const sequelize = require('sequelize');



router.get('/', function(req,res,next){
    res.render('public_chat');
});


router.post('/randMessage', function(req,res,next){
    let body = req.body;
    let user_idx = body.idx;
    let text = body.text;

    console.log(text);



    models.user.findAll({ order: sequelize.literal('rand()'),  attributes: ['idx'], limit: 3 }).then((encounter) => {
        idxList = []
        console.log(encounter);
        encounter.forEach(function(userItem){
            if (userItem.dataValues.idx == user_idx ) continue;
            idxList.push(userItem.dataValues.idx);
            
            let result = models.random_chat_matching.create({
                user_idx_first: user_idx,
                user_idx_second: userItem.dataValues.idx
            })
            
            models.random_chat_matching.create({
                writer: user_idx,
                text: text,
                matching_idx: result.dataValues.idx
            })
        });
    }); 

});


module.exports = router;