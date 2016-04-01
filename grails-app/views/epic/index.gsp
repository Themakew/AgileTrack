<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'epic.label', default: 'Epic')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-epic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Register Requirements<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="/investimentTheme/index">Add Investiment Theme</a>
                    </li>
                    <li>
                        <a href="/epic/index">Add Epic</a>
                    </li>
                    <li>
                        <a href="/features/index">Add Features</a>
                    </li>
                    <li>
                        <a href="/sprint/index">Add Sprint</a>
                    </li>
                    <li>
                        <a href="/userStory/index">Add User Story</a>
                    </li>
                    <li>
                        <a href="/task/index">Add Task</a>
                    </li>
                    <li>
                        <a href="/acceptanceCriteria/index">Add Acceptance Criteria</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="/tracking/index">See Track</a>
            </li>
        </div>
        <div id="list-epic" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${epicList}" />

            <div class="pagination">
                <g:paginate total="${epicCount ?: 0}" />
            </div>
        </div>
    </body>
</html>