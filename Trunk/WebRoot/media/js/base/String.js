String.prototype.startWith=function(str){     
  var reg=new RegExp("^"+str);     
  return reg.test(this);        
};

String.prototype.length=function(str){     
       
  return str.length;        
};

String.prototype.equal=function(str){     
    if(this==str){
    	return true;
    }    
    else{
    	return false;
    }  
} ;  
 
String.prototype.endsWith=function(str){     
  var reg=new RegExp(str+"$");     
  return reg.test(this);        
} ;

String.prototype.contain=function(strArray){
   if(strArray==''){
   		return true;
   }
   else if(this.indexOf(strArray) > -1){
   		return true;
   }
   else{
   		return false;
   }
};

String.prototype.contains=function(strArray){
   if(strArray==''){
   		return true;
   }
   else if(this.indexOf(strArray) > -1){
   		return true;
   }
   else{
   		return false;
   }
};

String.prototype.isNull= function(){
	return this == '' || this == 'null';
};

//String去前后空格
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, ""); 
};

String.prototype.intValue=function(v){
   var intV=parseInt(v);
   
   if(intV=="NaN"){
	   intV=0;
   }
   
   return intV;
};

String.prototype.parseDate=function(v){
	
};

String.prototype.length=function(){        
  return this.length;        
};

String.prototype.displayPart=function(displayLength){
	var text = this;
	
	if(displayLength == undefined){
		displayLength = 100;
	}
    var result = ""; 
    var count = 0; 
    for(var i = 0; i < displayLength; i++) { 
      var _char = text.charAt(i); 
      if(count >= displayLength) break; 
      if (/[^x00-xff]/.test(_char)) count++;
 
      result += _char; 
      count++; 
    } 
    if(result.length < text.length){ 
      result += "..."; 
    } 
    return result;
};
