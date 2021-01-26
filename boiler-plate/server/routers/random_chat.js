let express = require('express');
let router = express.Router();
const models = require('../models');
const sequelize = require('sequelize');
const Op = sequelize.Op;


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
            
            idxList.push(userItem.dataValues.idx);
            
            let result = models.random_chat_matching.create({
                user_idx_first: user_idx,
                user_idx_second: userItem.dataValues.idx
            })
            
            let chatResult = models.random_chat_message.create({
                writer: user_idx,
                text: text,
                matching_idx: result.dataValues.idx
            })
        });
    }); 

});

router.post('/myChatList', function(req,res,next){
    console.log(req.body);
    models.random_chat_matching.findAndCountAll({[Op.or]: [{user_idx_first: req.body.id}, {user_idx_second: req.body.id}]})
    .then( function(data){
        console.log(data.rows);
        if( (data== null || data == undefined) === false)
          res.json({count:data.count, message:"이미 사용중인 이메일 입니다."});
        else
        res.json({result:false, message:"사용가능한 이메일 입니다."});
    }).catch(function(error){
        console.log(error);
    })
});


module.exports = router;