<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
    <title>Image Upload</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/jasny-bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript">
	  (function() {
	    var po = document.createElement('script');
	    po.type = 'text/javascript'; po.async = true;
	    po.src = 'https://plus.google.com/js/client:plusone.js';
	    var s = document.getElementsByTagName('script')[0];
	    s.parentNode.insertBefore(po, s);
	  })();
	</script>
 	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" ></script>
</head>
<body>
  	<div id="gConnect" class="hide">
   	 	<button class="g-signin"
        	data-scope="https://www.googleapis.com/auth/plus.login"
			data-requestvisibleactions="http://schemas.google.com/AddActivity"
			data-clientId="755063995734-2ic6ktogds23h3lss18mhabvko19764q.apps.googleusercontent.com"
			data-callback="onSignInCallback"
			data-theme="dark"
			data-cookiepolicy="single_host_origin">
		</button>
	</div>
	<div id="authOps" style="display:none">
	    <div id="header"></div>
		<!-- Main -->
		<div class="container">
		<div class="row">
			<div id="sidemenu"></div>			
			<div class="col-md-9">
				<div id="topmenu"></div>
				<div>
					<div class="panel panel-default">
						<div class="panel-heading"><h4>Upload</h4></div>
						<div class="panel-body">
							<form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data" role="form">
								<div id="gid"></div>
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 300px; height: 200px;"></div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="myFile">
										</span>
										<a href="#" class="btn btn-danger fileinput-exists" data-dismiss="fileinput">Remove</a>
										<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div><!--/col-span-9-->
		</div>
		</div>
		<!-- /Main -->
		<div id="footer"></div>
	</div>
    <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type='text/javascript' src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type='text/javascript' src="bootstrap/js/jasny-bootstrap.min.js"></script>

    <!-- JavaScript jQuery code -->
    <script type="text/javascript">
    	$(document).ready(function(e) {
		    $('#header').load('includes/header.html');
		});
		$(document).ready(function(e) {
		    $('#sidemenu').load('includes/side.html');
		});
		$(document).ready(function(e) {
		    $('#topmenu').load('includes/top.html');
		});
		$(document).ready(function(e) {
		    $('#footer').load('includes/footer.html');
		});
		
	</script>

</body>
<script type="text/javascript">
var helper = (function() {
  var BASE_API_PATH = 'plus/v1/';

  return {
    /**
     * Hides the sign in button and starts the post-authorization operations.
     *
     * @param {Object} authResult An Object which contains the access token and
     *   other authentication information.
     */
    onSignInCallback: function(authResult) {
      gapi.client.load('plus','v1', function(){
        if (authResult['access_token']) {
          $('#authOps').show();
          helper.profile();
        } else if (authResult['error']) {
          console.log('There was an error: ' + authResult['error']);
          $('#authOps').hide('slow');
          window.location.replace('signin.jsp');
        }
        console.log('authResult', authResult);
      });
    },
    
       /**
     * Gets and renders the currently signed in user's profile data.
     */
    profile: function(){
      var request = gapi.client.plus.people.get( {'userId' : 'me'} );
      request.execute( function(profile) {
        if (profile.error) {
          return;
        }
        $('#gname').append(profile.displayName);
        $('#gid').append($('<input type="hidden" name="uid" value="' + profile.id + '">'));
        $('#gal').append($("<a href='/gallery?uid=" + profile.id + "'><i class='glyphicon glyphicon-picture'></i> Gallery</a>"));
        $('#tag').append($("<a href='/tags?uid=" + profile.id + "'><i class='glyphicon glyphicon-search'></i> Search</a>"));
      });
    }
  };
})();

/**
 * jQuery initialization
 */
$(document).ready(function() {
  $('#disconnect').click(helper.disconnect);
  $('#loaderror').hide();
  if ($('[data-clientid="YOUR_CLIENT_ID"]').length > 0) {
    alert('This sample requires your OAuth credentials (client ID) ' +
        'from the Google APIs console:\n' +
        '    https://code.google.com/apis/console/#:access\n\n' +
        'Find and replace YOUR_CLIENT_ID with your client ID.'
    );
  }
});

/**
 * Calls the helper method that handles the authentication flow.
 *
 * @param {Object} authResult An Object which contains the access token and
 *   other authentication information.
 */
function onSignInCallback(authResult) {
  helper.onSignInCallback(authResult);
}
</script>

</html>