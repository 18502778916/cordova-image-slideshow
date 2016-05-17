var exec=require('cordova/exec');
module.exports={
showImages:function(content,type,isOpen){
exec(isOpen,null,"showImages","showImages",[content,type]);
}
};