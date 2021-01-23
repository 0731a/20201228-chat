'use strict';
// 1. config/config.json 파일의 설정값을 읽어 sequelize 생성
// 2. models 폴더 아래에 존재하는 js파일을 모두 로딩
// 3. db객체에 model을 정의하여 반환 

const fs = require('fs');
const path = require('path');
const Sequelize = require('sequelize');
const basename = path.basename(__filename);
const env = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/../config/config.json')[env];
const db = {};

let sequelize;
if (config.use_env_variable) {
  sequelize = new Sequelize(process.env[config.use_env_variable], config);
} else {
  sequelize = new Sequelize(config.database, config.username, config.password, config);
}

fs
  .readdirSync(__dirname)
  .filter(file => {
    return (file.indexOf('.') !== 0) && (file !== basename) && (file.slice(-3) === '.js');
  })
  .forEach(file => {
    const model = require(path.join(__dirname, file))(sequelize, Sequelize.DataTypes);
    db[model.name] = model;
  });

Object.keys(db).forEach(modelName => {
  if (db[modelName].associate) {  //모델 관계 설정 
    db[modelName].associate(db);
  }
});

db.sequelize = sequelize;
db.Sequelize = Sequelize;

//db.mbti = require('./mbti')(sequelize,Sequelize);
db.mbti_type = require('./mbti_type')(sequelize,Sequelize);

db.user = require('./user')(sequelize,Sequelize);

db.user_has_board = require('./user_has_board')(sequelize,Sequelize);

db.board = require('./board')(sequelize,Sequelize);
db.board_post = require('./board_post')(sequelize,Sequelize);

db.public_chat = require('./public_chat')(sequelize,Sequelize);

db.random_chat_matching = require('./random_chat_matching')(sequelize,Sequelize);
db.random_chat_message = require('./random_chat_message')(sequelize,Sequelize);

db.question_chat_list = require('./question_chat_list')(sequelize,Sequelize);
db.question_chat_answer = require('./question_chat_answer')(sequelize,Sequelize);

// 포함 관계 

db.mbti_type.hasMany(db.user, {foreignKey: 'mbti_type_idx', sourceKey:"idx"});
db.user.belongsTo(db.mbti_type, {foreignKey:'mbti_type_idx', targetKey: "idx"});

db.question_chat_list.hasMany(db.question_chat_answer, {foreignKey: 'question_idx', sourceKey:"idx"});
db.question_chat_answer.belongsTo(db.question_chat_list, {foreignKey:'question_idx', targetKey: "idx"});

db.user.hasMany(db.question_chat_answer, {foreignKey: 'user_idx', sourceKey:"idx"});
db.question_chat_answer.belongsTo(db.question_chat_list, {foreignKey:'user_idx', targetKey: "idx"});

db.user.hasMany(db.public_chat, {foreignKey: 'writer', sourceKey:"idx"});
db.public_chat.belongsTo(db.user, {foreignKey:'writer', targetKey: "idx"});

db.user.hasMany(db.board, {foreignKey: 'maker', sourceKey:"idx"});
db.board.belongsTo(db.user, {foreignKey:'maker', targetKey: "idx"});

db.user.hasMany(db.board_post, {foreignKey: 'writer', sourceKey:"idx"});
db.board_post.belongsTo(db.user, {foreignKey:'writer', targetKey: "idx"});

db.user.hasMany(db.user_has_board, {foreignKey: 'user_idx', sourceKey:"idx"});
db.user_has_board.belongsTo(db.user, {foreignKey:'user_idx', targetKey: "idx"});
db.board.hasMany(db.user_has_board, {foreignKey: 'board_idx', sourceKey:"idx"});
db.user_has_board.belongsTo(db.board, {foreignKey:'board_idx', targetKey: "idx"});

db.board.hasMany(db.board_post, {foreignKey: 'board_idx', sourceKey:"idx"});
db.board_post.belongsTo(db.user, {foreignKey:'board_idx', targetKey: "idx"});

db.user.hasMany(db.random_chat_matching, {foreignKey: 'user_idx_first', sourceKey:"idx"});
db.random_chat_matching.belongsTo(db.user, {foreignKey:'user_idx_first', targetKey: "idx"});
db.user.hasMany(db.random_chat_matching, {foreignKey: 'user_idx_second', sourceKey:"idx"});
db.random_chat_matching.belongsTo(db.user, {foreignKey:'user_idx_second', targetKey: "idx"});
db.random_chat_matching.hasMany(db.random_chat_message, {foreignKey: 'matching_idx', sourceKey:"idx"});
db.random_chat_message.belongsTo(db.random_chat_matching, {foreignKey:'matching_idx', targetKey: "idx"});




module.exports = db
