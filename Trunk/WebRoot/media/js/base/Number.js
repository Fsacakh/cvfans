
function intValue()
{
   var v= this;
   var intV=parseInt(v);
   if(intV=="NaN"){
   	intV=0;
   }
   return intV;
}

function doubleValue()
{
   var v= this;
   return parseFloat(v);
}

function floatValue()
{
   var v= this;
   return parseFloat(v);
}

function longValue()
{
   var v= this;
   return parseInt(v);
}

Number.prototype.intValue = intValue;
Number.prototype.doubleValue = doubleValue;
Number.prototype.floatValue = floatValue;
Number.prototype.longValue = longValue;

