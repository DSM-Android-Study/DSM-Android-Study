var exam_script = {
    plus_num: function(num){
        try{
            var result = num * 2
            BlackJin.getDoubleNum(result)
        }catch(err){
            console.log(">> [exam_script.plus_num()] " + err)
        }
    }
}