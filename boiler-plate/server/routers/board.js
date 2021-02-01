let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('public_chat');
});

router.post('/makeBoard', function(req,res,next){
    let body = req.body;
    let user_idx = body.user_idx;
    let board_title = body.board_title;
    let board_description = body.board_description;

   models.board.findOne({while:{name:board_title}})
   .then( function(data){
        if( (data== null || data == undefined) === false)
        res.json({result:false, message:"이미 동일한 이름의 보드가 존재합니다."});
        return;
   })

   models.board.create({
    maker: user_idx,
    name: board_title,
    description: board_description
  })
   .then( function(data){
      res.json({result:true, message:"성공적으로 보드가 생성되었습니다."});

})
});

router.post('/deleteBoard', function(req,res,next){
    let body = req.body;
    let user_idx = body.idx;
    let text = body.text;

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});

})
});


router.post('/getBoardList', function(req,res,next){

    models.board.findAll()
    .then( function(data){
       //console.log(data);
       let boards = []
 
       data.forEach( function(board){
            console.log(board);
            boards.push({
                idx:board.dataValues.idx,
                board_title: board.dataValues.name,
                board_description: board.dataValues.description
            });
           
       });
 
 
       res.json({result:"sucess", data:boards});
    });
});

router.post('/postInBoard', function(req,res,next){

    models.mbti_type.findOne({while:{type:req.body.type}})
    .then( function(data){
       res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});
 
 })
 });

router.post('/deleteInBoard', function(req,res,next){

    models.mbti_type.findOne({while:{type:req.body.type}})
    .then( function(data){
       res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});
 
})
});

router.post('/getPosts', function(req,res,next){

    models.board_post.findAll({  include: [{
        model: models.user,
        attributes: ['nickName', 'mbti_type_idx'],
        include: models.mbti_type
    }]})
    .then( function(data){
       //console.log(data);
       let posts = [];
 
       data.forEach( function(post){
           posts.push({
               boardpost_writer: post.dataValues.User.nickName,
               boardpost_idx: post.dataValues.idx,
               boardpost_text: post.dataValues.text,
               boardpost_createdAt: post.dataValues.createdAt,
               user_mbti: post.dataValues.User.mbti
           })

           });

           res.json({result:"sucess", data:posts});
       });
  
 
});


router.post('/deletePost', function(req,res,next){
    models.board_post.delete({where:{type:req.body.idx}});
});
 


module.exports = router;