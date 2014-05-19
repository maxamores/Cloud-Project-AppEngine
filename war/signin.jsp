<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Sign In</title>
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
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form class="form-signin">
			<h3 class="heading-desc">Login to Gallery</h3>
			<div class="social-box">
				<div class="row mg-btm">
	            	<div class="col-md-12">
						<div id="gConnect">
						  	<button class="g-signin"
						        data-scope="https://www.googleapis.com/auth/plus.login"
						        data-requestvisibleactions="http://schemas.google.com/AddActivity"
						        data-clientId="755063995734-2ic6ktogds23h3lss18mhabvko19764q.apps.googleusercontent.com"
						        data-callback="onSignInCallback"
						        data-theme="dark"
						        data-cookiepolicy="single_host_origin"
						        data-width="wide">
						    </button>
						</div>
					</div>
				</div>
			</div>
			<div class="login-footer"></div>
	      </form>
	</div> 
</body>
<script type="text/javascript">
var helper = (function() {
  var BASE_API_PATH = 'plus/v1/';

  return {
    /**
     * @param {Object} authResult An Object which contains the access token and
     *   other authentication information.
     */
    onSignInCallback: function(authResult) {
      gapi.client.load('plus','v1', function(){
        if (authResult['access_token']) {
        	window.location.replace('index.jsp');
        } else if (authResult['error']) {
          // There was an error, which means the user is not signed in.
          // As an example, you can handle by writing to the console:
          console.log('There was an error: ' + authResult['error']);
        }
        console.log('authResult', authResult);
      });
    }};
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
