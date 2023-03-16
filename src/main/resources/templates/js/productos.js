
$(document).ready( function () {
	
	tableProducts = $('#tableProducts').DataTable({  
	"processing": true,
    "serverSide": true,
	"ordering": false,
    "ajax":{            
        "url": "/productos/listProducts", 
        "method": 'GET',
        "dataType": "json",
        "contentType": "application/json",
        "data": function (d) {
            return JSON.stringify(d);
        }
    },
    "columns":[
		{"data": "sku"},
    	{"data": "name"},
		{"data": "brand"},
		{"data": "size"},
		{"data": "price"},
		{"data": "principalImage"},
		{"data": "otherImages"}
	    ]
	}); 

	
});
