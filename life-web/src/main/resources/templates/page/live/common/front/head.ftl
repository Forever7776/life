<link rel="icon" href="${root}/images/favicon.ico" mce_href="${root}/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${root}/images/favicon.ico" mce_href="${root}/images/favicon.ico" type="image/x-icon">
<link href="${root}/css/live/front/style.css" rel='stylesheet' type='text/css' />
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${root}/css/live/front/flexslider.css" type="text/css" media="screen" />
<script type="application/x-javascript">
    addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
</script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="${root}js/live/front/move-top.js"></script>
<script src="${root}js/live/front/easing.js"></script>
<script>
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>

