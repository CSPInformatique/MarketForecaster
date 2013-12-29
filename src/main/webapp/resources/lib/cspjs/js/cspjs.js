var indexOf = function(array, needle) {
    if(typeof Array.prototype.indexOf === 'function') {
        return array.indexOf(needle);
    } else {
        var i = -1, index = -1;

        for(i = 0; i < this.length; i++) {
            if(this[i] === needle) {
                index = i;
                break;
            }
        }

        return index;
    }
};