/**
 * This script file is used for validation of data
 */

/**
 * This script file is used for validation of seller name
 */
function validateUserName() {
	
	var username = $('#username').val();
	$
			.get(
					'SellerSignupServlet',
					{
						'validate' : 'validate',
						'username' : username
					},
					function(data, status) {
						if ('success' == status) {
							var msg = '';
							if (data) {
								msg = '<h5 style=\"color:green\">User name is available</h5>';
							} else {
								msg = '<h5 style=\"color:red\">User name is not available</h5>';
							}
							$('#usenamemsg').html(msg);
						}
					});

}

