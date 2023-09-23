toastr.options={
    closeButton:true,
    debug:false,
    newestOnTop:true,
    progressBar:true,
    positionClass:"toast-top-right",
    preventDuplicates:false,
    onclick:null,
    showDuration:"300",
    hideDuration:"1000",
    timeOut:"5000",
    extendedTimeOut:"1000",
    showEasing:"swing",
    hideEasing:"linear",
    showMethod:"fadeIn",
    hideMethod:"fadeOut"
};

function parseMsg(msg){
    const[pureMsg,ttl] = msg.split(";ttl=");
    const currentJsUnixTimeStamp = new Date().getTime();

    if(ttl&&parseInt(ttl)+5000<currentJsUnixTimeStamp){
        return [purseMsg,false];
    }

    return[purseMsg,true];
}

function toastNotice(msg){
    const [pureMsg,needToShow] = parseMsg(msg);
    if(needToShow){
        toast["success"](pureMsg,"알림");
    }
}

function toastWarning(msg){
    const [pureMsg,needToShow] = parseMsg(msg);

    if(needToShow){
        toastr["warning"](pureMsg,"경고");
    }
}