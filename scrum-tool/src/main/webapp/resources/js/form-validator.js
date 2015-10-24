$(document).ready(
		function() {
			$("#registrationForm").validate(
					{
						rules : {
							firstname : {
								required : true,
								minlength : 3
							},
							lastname : {
								required : true,
								minlength : 3
							},
							email : {
								email : true,
								required : true,
								minlength : 1
							},
							password : {
								required : true,
								minlength : 6
							}
						},
						highlight : function(element) {
							$(element).closest(".form-group").removeClass(
									"has-success").addClass("has-error");
						},
						unhighlight : function(element) {
							$(element).closest(".form-group").removeClass(
									"has-error").addClass("has-success");
						}
					});
		});