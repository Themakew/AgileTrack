<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to AgileTrack</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <sec:ifNotLoggedIn>
            <li class="dropdown">
                <a href="login/auth">Login</a>
            </li>
        </sec:ifNotLoggedIn>
        <sec:ifAllGranted roles="ROLE_ADMIN">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Register Requirements<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="investimentTheme/index">Add Investiment Theme</a>
                    </li>
                    <li>
                        <a href="epic/index">Add Epic</a>
                    </li>
                    <li>
                        <a href="features/index">Add Features</a>
                    </li>
                    <li>
                        <a href="sprint/index">Add Sprint</a>
                    </li>
                    <li>
                        <a href="userStory/index">Add User Story</a>
                    </li>
                    <li>
                        <a href="task/index">Add Task</a>
                    </li>
                    <li>
                        <a href="acceptanceCriteria/index">Add Acceptance Criteria</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="tracking/index">See Track</a>
            </li>
            <li class="dropdown">
                <form class="dropdown" href="#" name="logout" method="POST" action="${createLink(controller:'logout') }">
                <input type="submit" value="Logout"></form>
            </li>
        </sec:ifAllGranted>
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    </div>
</body>
</html>
