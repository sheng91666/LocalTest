$(function(){
    console.log("aaaaaaaaaa");
    $.ajax({
        url:'/queryAll',
        data:{
            pageSize:0,
            pageNum:10
        },
        type:'get',
        dateType:'JSON',
        success:function(data){
            console.log("succes");
            if(data){

            }
        }

    })
});