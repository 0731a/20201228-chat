let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('mbti');
});

router.post('/getMbtiIdxByType', function(req,res,next){

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type});
})
});

module.exports = router;