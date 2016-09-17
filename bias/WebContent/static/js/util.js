var RequestUtil = {
    POST: function (url, param) {
        $.post(url, param, function(data) {
            return data;
        });
    },
    GET: function (url, param) {
        $.get(url, param,function(data) {
            return data;
        });
    }
};