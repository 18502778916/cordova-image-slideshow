var exec=require('cordova/exec');
module.exports={
showImages:function(content,type){
exec(null,null,"showImages","showImages",[content,type]);
}
};