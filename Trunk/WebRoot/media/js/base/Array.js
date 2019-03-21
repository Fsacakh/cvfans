Array.prototype.clear = function() {  
    this.length = 0;  
};  

/**
 * 判断数据项在数组中的位置
 * @param obj
 * @returns {Number}
 */
var oldArrayIndexOf = Array.indexOf;//判断是否原始浏览器是否存在indexOf方法
Array.prototype.indexOf = function(obj) {
    if(!oldArrayIndexOf) {
        for(var i = 0, imax = this.length; i < imax; i++) {
            if(this[i] === obj) {
                return i;
            }
        }
        return -1;
    } else{
        return oldArrayIndexOf(obj);
    }
};

/**
 * 判断数据项是否在该数组中
 * @param obj
 */
Array.prototype.contain = function(obj) {
	return this.indexOf(obj) != -1;
};

/**
 * 把数据项添加到指定的位置
 * @param index
 * @param obj
 */
Array.prototype.insertAt = function(index, obj) {
    if(index < 0) index = 0;     if(index > this.length) index = this.length;
    this.length++;
    for(var i = this.length - 1; i > index; i--) {
        this[i] = this[i - 1];
    }
    this[index] = obj;
};

/**
 * 返回最有一项数据
 */
Array.prototype.last = function() {
    return this[this.length - 1];
};