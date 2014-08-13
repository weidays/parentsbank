
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if IE 9]>    <html class="no-js ie9 oldie" lang="en"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js login desktop" lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login to your account - Uberflip</title>
<link rel="icon"
	href="https://ecdn.uberflip.com/img/uberflip_icon.ico?v=523"
	type="image/x-icon" />

<script type="text/javascript">
	if (top != self) {
		top.location.href = self.location.href
	}
</script>
<script type="text/javascript">
	var g_webroot = '/';
	var g_isLoggedIn = 0;
	var g_img_url = 'https://ecdn.uberflip.com/';
	var g_files_url = 'https://ecdn.uberflip.com/';
	var g_freetrial_url = 'https://app.uberflip.com/users/freetrial';
	var g_isManaging = '';
	var g_issueInfo = '';
	var g_env_issue = 'Issue';
	var g_env_title = 'Title';

	var g_heightModifier = 40;
	var g_managedUserId = 0;
	var g_isMobile = false;
	var g_userAccountDetails = [];
</script>
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/bootstrap.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/bootstrap_overrides.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/jquery.ui.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/thickbox.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/ui.datepicker.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/jquery.userguides.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/core_styles.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/uberflip_backend.css?v=641" />
<link rel="stylesheet" type="text/css"
	href="https://ecdn.uberflip.com/css/layout_override_login.css?v=641" />
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/jquery/jquery-1.7.1.min.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-transition.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-alert.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-dropdown.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-tooltip.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-tab.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-popover.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-modal.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-collapse.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/bootstrap-carousel.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/default.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/default_backend.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/jquery/jquery.selectbox.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/swfobject.2.1.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/json.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/flash_message_box.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/util.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/thickbox.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/application_account_limit_logic.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/jquery/jquery.form-latest.js?v=671"></script>
<script type="text/javascript"
	src="https://ecdn.uberflip.com/js/modernizr-2.0.6.min.js?v=671"></script>
<!--[if gte IE 9]><style type="text/css">.gradient {filter: none;}</style><![endif]-->

<!--[if lt IE 9]><script src="https://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<script>
	if ($j.browser.msie) {
		window.onerror = function() {
			return false
		}
	}
</script>
</head>

<body class="login">

	<div id="progress">
		<span>&nbsp;</span>
	</div>




	<div id="content-container" class="container">

		<div id="content" class="clearfix">
			<nocache>
			<div id="flash_message" class="alert " style="display: none;">
				<span class='icon'> </span> <a class="close" data-dismiss="alert"
					href="#">&times;</a>
				<p></p>
			</div>

			<div id="flash_mini" style="display: none; position: absolute">
				<div id="flash_mini_close"
					style="text-align: right; font-size: 11px">
					<a class="closeFlashMini" href="javascript:closeFlashMini();">x</a>
				</div>
				<div id="flash_mini_title"></div>
				<div id="flash_mini_content"></div>
			</div>

			</nocache>

			<div class="login">

				<div class="left rounded8">

					<form action="/login" method="POST" class="login">

						<div class="branding rounded4"></div>
						<div>
							<label>Username</label> <input name="data[User][username]"
								placeholder="username" class="input" tabindex="1" value=""
								type="text" id="UserUsername" />
							<div class="text-right">
								<a href="https://app.uberflip.com/users/forgot_username">forgot
									username?</a>
							</div>
						</div>

						<div>
							<label>Password</label> <input type="password"
								name="data[User][password]" value="" placeholder="password"
								class="input" tabindex="2" id="UserPassword" />
							<div class="text-right">
								<a href="https://app.uberflip.com/users/forgot_password">forgot
									password?</a>
							</div>
						</div>

						<div>
							<input type="submit" class="btn btn-large" tabindex="3"
								value="Login" />
						</div>

					</form>

				</div>

				<div class="right">
					<h1>Need an account?</h1>
					<p>Uberflip offers you the easiest way to create magical,
						trackable experiences from all your content - no coding required.</p>

					<p>
						<a class="btn btn-large btn-primary"
							href="https://app.uberflip.com/try/plus"
							onclick="return g_trackEvent('record', 'Clicked Signup Button', {'button-location':'login-page'});"
							rel="tooltip" title="no credit card required :)"
							data-placement="right"> Try It FREE For 14 Days </a>
					</p>

					<div class="clr"></div>

					<div class="small" style="margin: 16px 0;">
						Not convinced yet? <a href="https://app.uberflip.com/home">learn
							more</a>
					</div>
				</div>

				<div class="clr"></div>
			</div>
		</div>
		<!-- content -->

	</div>
	<!-- container -->




	<script type="text/javascript">
		var _kmq = _kmq || [], _kmk = _kmk
				|| 'a231dbb7492e6c40c632bcaef544e813f64a55b1';
		function _kms(u) {
			setTimeout(
					function() {
						var d = document, f = d.getElementsByTagName('script')[0], s = d
								.createElement('script');
						s.type = 'text/javascript';
						s.async = true;
						s.src = u;
						f.parentNode.insertBefore(s, f);
					}, 1);
		}
		_kms('//i.kissmetrics.com/i.js');
		_kms('//doug1izaerwt3.cloudfront.net/' + _kmk + '.1.js');
	</script>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-4803985-10' ], [ '_setDomainName',
				'uberflip.com' ], [ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>


