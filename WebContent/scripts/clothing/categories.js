/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This function is used to find the main category details.
 */
function getCategoryItem(catId){
	
	$('#row').show();
	 $.get("CategoriesServlet", {'parentCategory': catId}, function (data, status) {
		if('success'==status){
			$('#filter').empty();
			$('#filter').append('<h4>Color</h4>');
			$.each(data[0],function(key,value){
				$('#filter').append('<div  style="left=15px"><input type="checkbox"  name="color" value='+value.id+'>'+value.name+'</div>');
			});	
			$('#filter').append('<h4>Size</h4>');
			$.each(data[1],function(key,value){
				$('#filter').append('<div style="left=15px"><input type="checkbox"  name="size" value='+value.id+'>'+value.sizeCode+'</div>');
			});	
			$('#subcat').empty();
			$.each(data[2],function(key,value){
				var link='<a onclick="getSubCatItems('+value.id+')" class="btn btn-lg">'+value.categoryName+'</a>';
				$('#subcat').append('<tr><td>'+link+'</tr></td>');
			});	
			
			searchItems(data[3]);
			
		}else{
			
		}
	 });
}
function searchItem() {
    var keyword = $("#search").val();
//    alert(keyword);
    $.get('SellerItemsServlet', {'getItemByName': 'getItemByName', 'keyword': keyword}, function (data) {
    	displayItems(data);
    });
};
function displayItems(data){
	 $("#searchResult").empty();
     $("#searchResult").append('<div class="row"><div class="col-xs-4 col-md-3 col-lg-3">Item name</div><div class="col-xs-4 col-md-3 col-lg-3">Style</div><div class="col-xs-2 col-md-2 col-lg-2">Price</div><div class="col-xs-3 col-md-3 col-lg-3">Seller</div></div>');
     
     var i = 0;
     $.each(data, function (key, value) {
     	
         var id = "id_" + i;
        // var seller ='<a href="/seller?id=">'
         var styleName="";
         if(typeof value.style!='undefined'){
         	styleName = value.style.name;            	
         }
         var sellerName="";
         var sellerId="";
         if(typeof value.seller!='undefined'){
        	 sellerId = value.seller.userId; 
        	 sellerName = value.seller.userName; 
         }
        
         $("#searchResult").append('<input type="hidden" id="' + id + '" value="' + value.itemid + '"><div class="row"><div class="col-xs-4 col-md-3 col-lg-3"><a onclick="loadItemDetails('+value.itemid+')">' + value.itemName + '</a></div><div class="col-xs-4 col-md-3 col-lg-3">' + styleName + '</div><div class="col-xs-2 col-md-2 col-lg-2">$' + value.price + '</div><div class="col-xs-3 col-md-3 col-lg-3"><a onclick="getSellerDetails('+sellerId+')"> ' + sellerName + '</a></div><div class="col-xs-1 col-md-2 col-lg-2"></div></div>');
         i++;
     });
}
function getSellerDetails(sellerId){
	
}
function searchItems(items){
	console.log(items);
	$('#body').empty();
	
	$('#body').append('<div class="col-xs-12 col-md-12 col-lg-12"><div class="col-xs-10 col-md-8 col-lg-8 input-group"><span class="input-group-addon  glyphicon glyphicon-search"></span><input type="search" placeholder="Search Item" class="form-control" onkeyup="searchItem()" id="search"></div');
	$('#body').append('<div id="searchResult" style="overflow:scroll-y;position:absolute;left:10px;top:50px;width:90%"> <div class="col-xs-4 col-md-3 col-lg-3">Item name</div><div class="col-xs-4 col-md-3 col-lg-3">Style</div><div class="col-xs-2 col-md-2 col-lg-2">Price</div><div class="col-xs-3 col-md-3 col-lg-3">Seller</div></div>');
	
	$('#body').append('<div id="itemDetails" style="overflow:scroll-y;position:absolute;left:10px;top:200px;width:90%"></div');
	
	displayItems(items);
	/*
	$.each(items,function(key,value){	
		var record='<div class="row"><div class="col-xs-6 col-md-2 col-lg-2"><input type="button" class="btn btn-link" value="'+value.itemName+'" loadItemDetails('+value.itemid+')>';
		record+='</div><div class="col-xs-6 col-md-2 col-lg-2"><label class="control-label">$'+value.price+'</label></div></div>';
		$('#body').append(record);
	
	});*/
	
	$('#body').append('</div>');
}
/**
 * This function is used 
 * @param catId
 */
function getSubCatItems(catId){
	 $.get("SearchServlet", {'category': catId}, function (data, status) {
			if('success'==status){
				searchItems(data);
			}
	 });
}

function loadItemDetails(id){
	$('#body').load('itemDetails.jsp');
	
    $.get("SearchItemServlet", {'item': id}, function (data, status) {
        $.get('UserRatingServlet', {'item': id, 'getRatings': 'getRatings'}, function (data) {
            $("#ratings").empty();
            var i=0;
               
            $.each(data,function(key,val){
            var divSubmit = $(document.createElement('div'));
            divSubmit.attr("class", "col-md-12 col-xs-12 col-lg-12");

            $(divSubmit).append('<label  >'+val.user.firstName + " " + val.user.lastName+'</label><input readonly="true" id="input-id_'+i+'" value="'+val.rating+'" type="number" class="rating" min="1" max="5" step="1" data-size="sm" data-rtl="false"><label >'+val.comment+'</label>');
            $("#ratings").append(divSubmit);
            $("#input-id_"+i).rating();
             i++;
        });
        });
       
        item = data;
        //                alert(data + "  " + data.itemDetails.length);
        $("#itemName").html(data.itemName);
        $("#price").html("$" + data.price);
        if(null!=data.style)
        $("#style").html(data.style.name);
        $("#detailsDiv").empty();
        for (var i = 0; i < data.itemDetails.length; i++) {
            var colorName = "color_" + i;
            var sizeName = "size_" + i;
            var itemdetail = data.itemDetails[i];
            var divSubmit = $(document.createElement('div'));
            divSubmit.attr("class", "row form-group");
            //            console.log("created");
            $(divSubmit).append('<div class="col-xs-5 col-md-5 col-lg-5"> <div class="col-md-1 col-lg-1"><label class="control-label">Color</label></div>&nbsp; &nbsp; <div class="col-xs-4 col-md-4 col-lg-4"><label class="control-label" id="' + colorName + '">' + itemdetail.color.name + ' </label></div> </div> <div class="col-xs-6 col-md-6"> <div class="col-md-2 col-lg-2"><label class="control-label">Size</label></div> <div class="col-xs-4 col-md-4 col-lg-4"><label class="control-label" id="' + sizeName + '">' + itemdetail.size.sizeCode + ' </label></div> </div><div class="col-xs-1 col-md-1"> <button  onclick="onAddToCart(' + id + ','+itemdetail.color.id+','+itemdetail.size.id+')" type="button"  class="btn btn-toolbar"><span class=" glyphicon glyphicon-shopping-cart"></span> </button> </div>');
            //            console.log("appended");
            $("#detailsDiv").append(divSubmit);
        }

    });
}
